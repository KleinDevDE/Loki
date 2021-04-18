package de.kleindev.discord.supportbot;

import de.kleindev.discord.supportbot.commands.ConsoleCommand;
import de.kleindev.discord.supportbot.config.SupportBotConfiguration;
import de.kleindev.discord.supportbot.events.application.SupportBotLoginEvent;
import de.kleindev.discord.supportbot.events.application.SupportBotShutdownEvent;
import de.kleindev.discord.supportbot.listeners.discord_internal.ALostConnectionListener;
import de.kleindev.discord.supportbot.listeners.discord_internal.AReconnectListener;
import de.kleindev.discord.supportbot.listeners.discord_internal.AResumeListener;
import de.kleindev.discord.supportbot.listeners.discord_internal.AWebhooksUpdateListener;
import de.kleindev.discord.supportbot.listeners.discord_internal.channel.*;
import de.kleindev.discord.supportbot.listeners.discord_internal.message.*;
import de.kleindev.discord.supportbot.listeners.discord_internal.roles.*;
import de.kleindev.discord.supportbot.listeners.discord_internal.server.*;
import de.kleindev.discord.supportbot.listeners.discord_internal.user.*;
import de.kleindev.discord.supportbot.logging.Logger;
import de.kleindev.discord.supportbot.utils.ApplicationManager;
import de.kleindev.discord.supportbot.utils.ExceptionHandler;
import de.kleindev.discord.supportbot.utils.MySQLConnection;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public final static String VERSION = "1.0";
    private static String[] savedArgs;
    public static String[] getArgs() {
        return savedArgs;
    }

    public static void main(String[] args) {
        FallbackLoggerConfiguration.setDebug(false);
        FallbackLoggerConfiguration.setTrace(false);
        savedArgs = args;
        new SupportBot(new SupportBotConfiguration(new File("config.yml")));

        registerShutdownHook();
        initTables();
        SupportBot.getInstance().getCommandManager().registerCommandsInPackage("de.kleindev.discord.supportbot.commands.discord");
        SupportBot.getInstance().getCommandManager().registerConsoleCommandsInPackage("de.kleindev.discord.supportbot.commands.console");
        SupportBot.getInstance().getEventManager().registerListenerInPackage("de.kleindev.discord.supportbot.listeners.discord");
        initBot();
        listenCommands();
    }

    private static void registerShutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            if(ApplicationManager.shouldReboot)
                return;
            SupportBot.getInstance().getEventManager().callEvent(new SupportBotShutdownEvent());
        }));
    }

    private static void initBot(){
        SupportBot.getInstance().getEventManager().callEvent(new SupportBotLoginEvent());
        DiscordApiBuilder builder = new DiscordApiBuilder();
        builder.setToken(SupportBot.getInstance().getSupportBotConfiguration().discordBotToken);

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

        SupportBot.getInstance().setDiscordApi(builder.login().join());
        SupportBot.getInstance().getDiscordApi().updateStatus(UserStatus.ONLINE);
        SupportBot.getInstance().getDiscordApi().updateActivity(ActivityType.PLAYING, VERSION);
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
                ConsoleCommand consoleCommand = SupportBot.getInstance().getCommandManager().getConsoleCommand(arr[0]);

                if (consoleCommand != null) {
                    Logger.log("> " + cmd);
                    consoleCommand.execute(args);
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
}
