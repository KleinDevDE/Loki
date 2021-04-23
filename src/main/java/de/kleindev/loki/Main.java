package de.kleindev.loki;

import de.kleindev.loki.commands.ConsoleCommand;
import de.kleindev.loki.config.LokiConfiguration;
import de.kleindev.loki.events.application.SupportBotLoginEvent;
import de.kleindev.loki.events.application.SupportBotShutdownEvent;
import de.kleindev.loki.listeners.discord_internal.ALostConnectionListener;
import de.kleindev.loki.listeners.discord_internal.AReconnectListener;
import de.kleindev.loki.listeners.discord_internal.AResumeListener;
import de.kleindev.loki.listeners.discord_internal.AWebhooksUpdateListener;
import de.kleindev.loki.listeners.discord_internal.channel.*;
import de.kleindev.loki.listeners.discord_internal.message.*;
import de.kleindev.loki.listeners.discord_internal.roles.*;
import de.kleindev.loki.listeners.discord_internal.server.*;
import de.kleindev.loki.listeners.discord_internal.user.*;
import de.kleindev.loki.logging.Logger;
import de.kleindev.loki.plugin.InternalPlugin;
import de.kleindev.loki.utils.ApplicationManager;
import org.apache.commons.cli.*;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static String[] savedArgs;
    private static CommandLine commandLine;
    public final static String VERSION = "1.0";
    public static String[] getArgs() {
        return savedArgs;
    }

    public static void main(String[] args) {
        FallbackLoggerConfiguration.setDebug(false);
        FallbackLoggerConfiguration.setTrace(false);
        savedArgs = args;
        parseArgs(args);
        new Loki(new LokiConfiguration(new File(commandLine.getOptionValue("config", "config.yml"))));
        Logger.info("LogType: " + Loki.getInstance().getLokiConfiguration().logType);

        registerShutdownHook();
        initTables();
        InternalPlugin internalPlugin = new InternalPlugin();
        Loki.getInstance().getCommandManager().registerCommandsInPackage(internalPlugin, "de.kleindev.loki.commands.discord");
        Loki.getInstance().getCommandManager().registerConsoleCommandsInPackage(internalPlugin, "de.kleindev.loki.commands.console");
        Loki.getInstance().getEventManager().registerListenerInPackage(internalPlugin, "de.kleindev.loki.listeners.discord");
        initBot();
        initPlugins();
        listenCommands();
    }

    private static void registerShutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            Loki.getInstance().getDiscordApi().updateStatus(UserStatus.OFFLINE);
            if(ApplicationManager.shouldReboot)
                return;
            Loki.getInstance().getEventManager().callEvent(new SupportBotShutdownEvent());
            Loki.getInstance().getDiscordApi().disconnect();
        }));
    }

    private static void initBot(){
        Loki.getInstance().getEventManager().callEvent(new SupportBotLoginEvent());
        DiscordApiBuilder builder = new DiscordApiBuilder();
        builder.setToken(Loki.getInstance().getLokiConfiguration().discordBotToken);

        builder.addServerChannelChangeOverwrittenPermissionsListener(new AServerChannelChangeOverwrittenPermissionsListener());
        builder.addServerVoiceChannelChangeUserLimitListener(new AServerVoiceChannelChangeUserLimitListener());
        builder.addKnownCustomEmojiChangeNameListener(new AKnownCustomEmojiChangeNameListener());
        builder.addServerVoiceChannelMemberJoinListener(new AServerVoiceChannelMemberJoinListener());
        builder.addKnownCustomEmojiCreateListener(new AKnownCustomEmojiCreateListener());
        builder.addKnownCustomEmojiDeleteListener(new AKnownCustomEmojiDeleteListener());
        builder.addUserChangeDiscriminatorListener(new AUserChangeDiscriminatorListener());
        builder.addServerChangeAfkTimeoutListener(new AServerChangeAfkTimeoutListener());
        builder.addUserChangeSelfDeafenedListener(new AUserChangeSelfDeafenedListener());
        builder.addGroupChannelChangeNameListener(new AGroupChannelChangeNameListener());
        builder.addServerTextChannelChangeTopicListener(new AServerTextChannelChangeTopicListener());
        builder.addServerChannelChangeNsfwFlagListener(new AServerChannelChangeNsfwFlagListener());
        builder.addServerChangeVerificationLevelListener(new AServerChangeVerificationLevelListener());
        builder.addServerChannelChangePositionListener(new AServerChannelChangePositionListener());
        builder.addServerVoiceChannelMemberLeaveListener(new AServerVoiceChannelMemberLeaveListener());
        builder.addServerVoiceChannelChangeBitrateListener(new AServerVoiceChannelChangeBitrateListener());
        builder.addServerTextChannelChangeSlowmodeListener(new AServerTextChannelChangeSlowmodeListener());
        builder.addServerChannelChangeNameListener(new AServerChannelChangeNameListener());
        builder.addServerChangeSystemChannelListener(new AServerChangeSystemChannelListener());
        builder.addUserChangeNicknameListener(new AUserChangeNicknameListener());
        builder.addUserChangeStatusListener(new AUserChangeStatusListener());
        builder.addLostConnectionListener(new ALostConnectionListener());
        builder.addUserChangeNameListener(new AUserChangeNameListener());
        builder.addResumeListener(new AResumeListener());
        builder.addUserStartTypingListener(new AUserStartTypingListener());
        builder.addUserChangeMutedListener(new AUserChangeMutedListener());
        builder.addGroupChannelCreateListener(new AGroupChannelCreateListener());
        builder.addWebhooksUpdateListener(new AWebhooksUpdateListener());
        builder.addPrivateChannelDeleteListener(new APrivateChannelDeleteListener());
        builder.addReactionAddListener(new AReactionAddListener());
        builder.addUserChangeDeafenedListener(new AUserChangeDeafenedListener());
        builder.addReconnectListener(new AReconnectListener());
        builder.addReactionRemoveListener(new AReactionRemoveListener());
        builder.addMessageEditListener(new AMessageEditListener());
        builder.addChannelPinsUpdateListener(new AChannelPinsUpdateListener());
        builder.addMessageDeleteListener(new AMessageDeleteListener());
        builder.addMessageCreateListener(new AMessageCreateListener());
        builder.addCachedMessagePinListener(new ACachedMessagePinListener());
        builder.addRoleChangePositionListener(new ARoleChangePositionListener());
        builder.addUserRoleAddListener(new AUserRoleAddListener());
        builder.addRoleChangeMentionableListener(new ARoleChangeMentionableListener());
        builder.addRoleChangeNameListener(new ARoleChangeNameListener());
        builder.addRoleDeleteListener(new ARoleDeleteListener());
        builder.addUserRoleRemoveListener(new AUserRoleRemoveListener());
        builder.addRoleChangeHoistListener(new ARoleChangeHoistListener());
        builder.addServerJoinListener(new AServerJoinListener());
        builder.addServerChangeRegionListener(new AServerChangeRegionListener());
        builder.addRoleChangeColorListener(new ARoleChangeColorListener());
        builder.addUserChangeActivityListener(new AUserChangeActivityListener());
        builder.addServerChannelCreateListener(new AServerChannelCreateListener());
        builder.addCachedMessageUnpinListener(new ACachedMessageUnpinListener());
        builder.addUserChangeSelfMutedListener(new AUserChangeSelfMutedListener());
        builder.addServerChannelDeleteListener(new AServerChannelDeleteListener());
        builder.addRoleCreateListener(new ARoleCreateListener());
        builder.addServerChangeIconListener(new AServerChangeIconListener());
        builder.addServerChangeNameListener(new AServerChangeNameListener());
        builder.addUserChangeAvatarListener(new AUserChangeAvatarListener());
        builder.addRoleChangePermissionsListener(new ARoleChangePermissionsListener());
        builder.addServerMemberLeaveListener(new AServerMemberLeaveListener());
        builder.addGroupChannelDeleteListener(new AGroupChannelDeleteListener());
        builder.addReactionRemoveAllListener(new AReactionRemoveAllListener());
        builder.addServerMemberBanListener(new AServerMemberBanListener());
        builder.addServerMemberUnbanListener(new AServerMemberUnbanListener());
        builder.addPrivateChannelCreateListener(new APrivateChannelCreateListener());
        builder.addServerChangeMultiFactorAuthenticationLevelListener(new AServerChangeMultiFactorAuthenticationLevelListener());
        builder.addServerChangeDefaultMessageNotificationLevelListener(new AServerChangeDefaultMessageNotificationLevelListener());
        builder.addServerChangeExplicitContentFilterLevelListener(new AServerChangeExplicitContentFilterLevelListener());
        builder.addKnownCustomEmojiChangeWhitelistedRolesListener(new AKnownCustomEmojiChangeWhitelistedRolesListener());
        builder.addServerMemberJoinListener(new AServerMemberJoinListener());
        builder.addServerLeaveListener(new AServerLeaveListener());
        builder.addServerChangeSplashListener(new AServerChangeSplashListener());
        builder.addServerChangeOwnerListener(new AServerChangeOwnerListener());
        builder.addServerBecomesUnavailableListener(new AServerBecomesUnavailableListener());
        builder.addServerBecomesAvailableListener(new AServerBecomesAvailableListener());
        builder.addServerChangeAfkChannelListener(new AServerChangeAfkChannelListener());

        Loki.getInstance().setDiscordApi(builder.login().join());
        Loki.getInstance().getDiscordApi().updateStatus(UserStatus.ONLINE);
        Loki.getInstance().getDiscordApi().updateActivity(ActivityType.PLAYING, VERSION);
    }

    private static void initPlugins(){
        File pluginsFolder = new File(Loki.getInstance().getLokiConfiguration().pluginsFolder);
        if (!pluginsFolder.exists()){
            pluginsFolder.mkdirs();
        }
        for(File f : pluginsFolder.listFiles()){
            Loki.getInstance().getPluginManager().loadPlugin(f);
        }
    }

    private static void listenCommands(){
        new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            String cmd = "";
            while (true) {
                cmd = scanner.nextLine();
                String[] arr = cmd.split(" ");
                String[] args = new String[arr.length - 1];
                System.arraycopy(arr, 1, args, 0, args.length);
                ConsoleCommand consoleCommand = Loki.getInstance().getCommandManager().getConsoleCommand(arr[0]);

                if (consoleCommand != null) {
                    Logger.log("> " + cmd);
                    consoleCommand.executeConsole(args);
                } else {
                    Logger.log("Unknown command!");
                }
            }
        }).start();
    }

    private static void initTables(){
//        MySQLConnection eobot = SupportBot.getInstance().getMySQLConnection("eobot");
//        try {
//            eobot.preparedStatement("").execute();
//        } catch (SQLException e) {
//            ExceptionHandler.handle(e);
//        }
    }

    private static void parseArgs(String[] args){
        Options options = new Options();
        Option config = Option.builder()
                .longOpt("config")
                .hasArg(true)
                .required(false)
                .valueSeparator(' ')
                .build();
        options.addOption(config);

        CommandLineParser parser = new DefaultParser();
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Commandline parsing failed. Reason: " + e.getMessage());
        }

    }
}
