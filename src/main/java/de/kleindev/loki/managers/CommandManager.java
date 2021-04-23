package de.kleindev.loki.managers;

import de.kleindev.loki.commands.Command;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.commands.ConsoleCommand;
import de.kleindev.loki.commands.SkipCommandRegistration;
import de.kleindev.loki.commands.exceptions.CommandAlreadyRegisteredException;
import de.kleindev.loki.commands.exceptions.NoCommandInfoAnnotationFoundException;
import de.kleindev.loki.logging.Logger;
import de.kleindev.loki.objects.CommandSender;
import de.kleindev.loki.plugin.BotPlugin;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CommandManager {
    //TODO Link command with plugin
    private static final HashMap<String, CommandEntry> commandHashMap = new HashMap<>();
    private static final HashMap<String, String> aliasHashMap = new HashMap<>();

    private static final HashMap<String, ConsoleCommandEntry> consoleCommandHashMap = new HashMap<>();
    private static final HashMap<String, String> consoleAliasHashMap = new HashMap<>();


    /**
     * Register all commands in package XY automatically. <br>
     * Classes with the annotation {@link SkipCommandRegistration} are skipped <br>
     * All not skipped Commands needs the {@link CommandInfo} class annotation!
     *
     * @param packagePath
     */
    @SneakyThrows
    public void registerCommandsInPackage(BotPlugin botPlugin, String packagePath) {
        Reflections reflections = new Reflections(packagePath, new SubTypesScanner(false));
        for (Class<?> clazz : reflections.getSubTypesOf(Command.class)) {

            /* Skip commands which has the @SkipCommandRegistration annotation */
            if (clazz.isAnnotationPresent(SkipCommandRegistration.class)) {
                Logger.debug("Skipping command \"" + clazz.getName() + "\" cause @SkipCommandRegistration annotation is present");
                continue;
            }

            registerCommand(botPlugin, (Command) clazz.getConstructor().newInstance());
        }
    }

    /**
     * Register all console commands in package XY automatically. <br>
     * Classes with the annotation {@link SkipCommandRegistration} are skipped <br>
     * All not skipped Commands needs the {@link CommandInfo} class annotation!
     *
     * @param packagePath
     */
    @SneakyThrows
    public void registerConsoleCommandsInPackage(BotPlugin botPlugin, String packagePath) {
        Reflections reflections = new Reflections(packagePath, new SubTypesScanner(false));
        for (Class<?> clazz : reflections.getSubTypesOf(ConsoleCommand.class)) {
            /* Skip commands which has the @SkipCommandRegistration annotation */
            if (clazz.isAnnotationPresent(SkipCommandRegistration.class)) {
                Logger.debug("Skipping command \"" + clazz.getName() + "\" cause @SkipCommandRegistration annotation is present");
                continue;
            }

            registerCommand(botPlugin, (ConsoleCommand) clazz.getConstructor().newInstance());
        }
    }

    /**
     * Register the provided command class. <br>
     * This command class needs the {@link CommandInfo} class!
     *
     * @param command
     */
    @SneakyThrows
    public void registerCommand(BotPlugin botPlugin, Command command) {
        /* Skip commands which has not the @CommandInfo annotation */
        if (!command.getClass().isAnnotationPresent(CommandInfo.class)) {
            Logger.error("Could not register command \"" + command.getClass().getName() + "\"!\n" +
                    "Missing @CommandInfo annotation!");
            return;
        }
        CommandInfo commandInfo = command.getClass().getAnnotation(CommandInfo.class);
        Logger.info("Registering command " + commandInfo.cmd() + " ...");
        if (commandHashMap.containsKey(commandInfo.cmd())) {
            Logger.error("Command \"" + commandInfo.cmd() + "\" is already registered!");
            return;
        }

        CommandEntry commandEntry = new CommandEntry();
        commandEntry.command = command;
        commandEntry.key = commandInfo.cmd();
        commandEntry.pluginID = botPlugin.getPluginID();
        commandHashMap.put(commandInfo.cmd(), commandEntry);

        for (String s : commandInfo.aliase()) {
            Logger.info("Registering alias \"" + s + "\" for \"" + commandInfo.cmd() + "\"..");
            if (aliasHashMap.containsKey(s)) {
                Logger.warning("Skipping alias registration cause this alias elaready exists!");
                continue;
            }
            aliasHashMap.put(s, commandInfo.cmd());
        }
    }

    /**
     * Register the provided command class. <br>
     * This command class needs the {@link CommandInfo} class!
     *
     * @param command
     */
    @SneakyThrows
    public void registerCommand(BotPlugin botPlugin, ConsoleCommand command) {
        /* Skip commands which has not the @CommandInfo annotation */
        if (!command.getClass().isAnnotationPresent(CommandInfo.class)) {
            throw new NoCommandInfoAnnotationFoundException(command.getClass());
        }
        CommandInfo commandInfo = command.getClass().getAnnotation(CommandInfo.class);
        Logger.info("Registering console command " + commandInfo.cmd() + " ...");
        if (consoleCommandHashMap.containsKey(commandInfo.cmd())) {
            throw new CommandAlreadyRegisteredException();
        }

        ConsoleCommandEntry consoleCommandEntry = new ConsoleCommandEntry();
        consoleCommandEntry.command = command;
        consoleCommandEntry.key = commandInfo.cmd();
        consoleCommandEntry.pluginID = botPlugin.getPluginID();
        consoleCommandHashMap.put(commandInfo.cmd(), consoleCommandEntry);

        for (String s : commandInfo.aliase()) {
            Logger.info("Registering alias \"" + s + "\" for \"" + commandInfo.cmd() + "\"..");
            if (consoleAliasHashMap.containsKey(s)) {
                Logger.warning("Skipping alias registration cause this alias elaready exists!");
                continue;
            }
            consoleAliasHashMap.put(s, commandInfo.cmd());
        }
    }


    /**
     * @see #registerCommand(BotPlugin, String, String, String, String[], BiConsumer)
     */
    public void registerCommand(BotPlugin botPlugin, String cmd, BiConsumer<CommandSender, String[]> biConsumer) {
        registerCommand(botPlugin, cmd, null, null, null, biConsumer);
    }

    /**
     * @see #registerCommand(BotPlugin, String, String, String, String[], BiConsumer)
     */
    public void registerCommand(BotPlugin botPlugin, String cmd, String permission, BiConsumer<CommandSender, String[]> biConsumer) {
        registerCommand(botPlugin, cmd, permission, null, null, biConsumer);
    }

    /**
     * @see #registerCommand(BotPlugin, String, String, String, String[], BiConsumer)
     */
    public void registerCommand(BotPlugin botPlugin, String cmd, String permission, String description, BiConsumer<CommandSender, String[]> biConsumer) {
        registerCommand(botPlugin, cmd, permission, description, null, biConsumer);
    }

    /**
     * @param cmd
     * @param permission
     * @param description
     * @param aliase
     * @param biConsumer
     */
    public void registerCommand(BotPlugin botPlugin, String cmd, String permission, String description, String[] aliase, BiConsumer<CommandSender, String[]> biConsumer) {
        CustomCommand customCommand = new CustomCommand(cmd, permission, description, aliase, biConsumer);
        registerCommand(botPlugin, customCommand);
    }

    /**
     * @see #registerConsoleCommand(BotPlugin, String, String, String, String[], Consumer)
     */
    public void registerConsoleCommand(BotPlugin botPlugin, String cmd, Consumer<String[]> consumer) {
        registerConsoleCommand(botPlugin, cmd, null, null, null, consumer);
    }

    /**
     * @see #registerConsoleCommand(BotPlugin, String, String, String, String[], Consumer)
     */
    public void registerConsoleCommand(BotPlugin botPlugin, String cmd, String permission, Consumer<String[]> consumer) {
        registerConsoleCommand(botPlugin, cmd, permission, null, null, consumer);
    }

    /**
     * @see #registerConsoleCommand(BotPlugin, String, String, String, String[], Consumer)
     */
    public void registerConsoleCommand(BotPlugin botPlugin, String cmd, String permission, String description, Consumer<String[]> consumer) {
        registerConsoleCommand(botPlugin, cmd, permission, description, null, consumer);
    }

    /**
     * @param cmd
     * @param permission
     * @param description
     * @param aliase
     * @param consumer
     */
    public void registerConsoleCommand(BotPlugin botPlugin, String cmd, String permission, String description, String[] aliase, Consumer<String[]> consumer) {
        CustomConsoleCommand customConsoleCommand = new CustomConsoleCommand(cmd, permission, description, aliase, consumer);
        registerCommand(botPlugin, customConsoleCommand);
    }

    public Command getCommand(String cmd) {
        if (commandHashMap.containsKey(cmd)) {
            return commandHashMap.get(cmd).command;
        }

        String alias = aliasHashMap.get(cmd);
        if (alias != null && commandHashMap.containsKey(alias)) {
            return commandHashMap.get(alias).command;
        }

        return null;
    }

    public ConsoleCommand getConsoleCommand(String cmd) {
        if (consoleCommandHashMap.containsKey(cmd)) {
            return consoleCommandHashMap.get(cmd).command;
        }

        String alias = consoleAliasHashMap.get(cmd);
        if (alias != null && consoleCommandHashMap.containsKey(alias)) {
            return consoleCommandHashMap.get(alias).command;
        }

        return null;
    }

    @SneakyThrows
    public void unregisterCommand(Command command) {
        /* Skip commands which has not the @CommandInfo annotation */
        if (!command.getClass().isAnnotationPresent(CommandInfo.class)) {
            throw new NoCommandInfoAnnotationFoundException(command.getClass());
        }
        CommandInfo commandInfo = command.getClass().getAnnotation(CommandInfo.class);

        if (!commandHashMap.containsKey(commandInfo.cmd())) {
            return;
        }

        for (String alias : commandInfo.aliase()) {
            aliasHashMap.remove(alias);
        }

        commandHashMap.remove(commandInfo.cmd());
    }

    public Stream<Command> getCommands() {
        return commandHashMap.values().parallelStream().map((a) -> {
            return a.command;
        });
    }

    public Stream<ConsoleCommand> getConsoleCommands() {
        return consoleCommandHashMap.values().parallelStream().map((a) -> {
            return a.command;
        });
    }

    @SneakyThrows
    public void unregisterCommand(ConsoleCommand command) {
        /* Skip commands which has not the @CommandInfo annotation */
        if (!command.getClass().isAnnotationPresent(CommandInfo.class)) {
            throw new NoCommandInfoAnnotationFoundException(command.getClass());
        }
        CommandInfo commandInfo = command.getClass().getAnnotation(CommandInfo.class);
        if (!consoleCommandHashMap.containsKey(commandInfo.cmd())) {
            return;
        }

        for (String alias : commandInfo.aliase()) {
            consoleAliasHashMap.remove(alias);
        }

        consoleCommandHashMap.remove(commandInfo.cmd());
    }

    public void unregisterCommands(BotPlugin botPlugin) {
        for (Map.Entry<String, CommandEntry> entry : commandHashMap.entrySet()) {
            if (entry.getValue().pluginID == botPlugin.getPluginID()) {
                unregisterCommand(entry.getValue().command);
            }
        }

        for (Map.Entry<String, ConsoleCommandEntry> entry : consoleCommandHashMap.entrySet()) {
            if (entry.getValue().pluginID == botPlugin.getPluginID()) {
                unregisterCommand(entry.getValue().command);
            }
        }
    }

    private static class CustomCommand extends Command implements CommandInfo {
        String cmd, permission, description;
        String[] aliase;
        BiConsumer<CommandSender, String[]> biConsumer;

        public CustomCommand(String cmd, String permission, String description, String[] aliase, BiConsumer<CommandSender, String[]> biConsumer) {
            this.cmd = cmd;
            this.permission = permission;
            this.description = description;
            this.aliase = aliase;
            this.biConsumer = biConsumer;
        }

        @Override
        public void executeDiscord(CommandSender commandSender, String[] args) {
            biConsumer.accept(commandSender, args);
        }

        @Override
        public String cmd() {
            return cmd;
        }

        @Override
        public String permission() {
            return permission;
        }

        @Override
        public String[] aliase() {
            return aliase;
        }

        @Override
        public String description() {
            return description;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return CommandInfo.class;
        }
    }

    private static class CustomConsoleCommand extends ConsoleCommand implements CommandInfo {
        String cmd, permission, description;
        String[] aliase;
        Consumer<String[]> consumer;

        public CustomConsoleCommand(String cmd, String permission, String description, String[] aliase, Consumer<String[]> consumer) {
            this.cmd = cmd;
            this.permission = permission;
            this.description = description;
            this.aliase = aliase;
            this.consumer = consumer;
        }

        @Override
        public String cmd() {
            return cmd;
        }

        @Override
        public String permission() {
            return permission;
        }

        @Override
        public String[] aliase() {
            return aliase;
        }

        @Override
        public String description() {
            return description;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return CommandInfo.class;
        }

        @Override
        public void executeConsole(String[] args) {
            consumer.accept(args);
        }
    }

    private static class CommandEntry {
        public String key;
        public UUID pluginID;
        public Command command;
    }

    private static class ConsoleCommandEntry {
        public String key;
        public UUID pluginID;
        public ConsoleCommand command;
    }
}
