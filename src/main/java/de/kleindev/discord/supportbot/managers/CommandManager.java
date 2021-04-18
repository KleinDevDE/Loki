package de.kleindev.discord.supportbot.managers;

import de.kleindev.discord.supportbot.commands.Command;
import de.kleindev.discord.supportbot.commands.CommandInfo;
import de.kleindev.discord.supportbot.commands.ConsoleCommand;
import de.kleindev.discord.supportbot.commands.SkipCommandRegistration;
import de.kleindev.discord.supportbot.commands.exceptions.CommandAlreadyRegisteredException;
import de.kleindev.discord.supportbot.commands.exceptions.NoCommandInfoAnnotationFoundException;
import de.kleindev.discord.supportbot.logging.Logger;
import de.kleindev.discord.supportbot.objects.CommandSender;
import lombok.SneakyThrows;
import org.javacord.api.entity.user.User;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CommandManager {
    private static final HashMap<String, Command> commandHashMap = new HashMap<>();
    private static final HashMap<String, String> aliasHashMap = new HashMap<>();

    private static final HashMap<String, ConsoleCommand> consoleCommandHashMap = new HashMap<>();
    private static final HashMap<String, String> consoleAliasHashMap = new HashMap<>();


    /**
     * Register all commands in package XY automatically. <br>
     * Classes with the annotation {@link SkipCommandRegistration} are skipped <br>
     * All not skipped Commands needs the {@link CommandInfo} class annotation!
     * @param packagePath
     */
    @SneakyThrows
    public void registerCommandsInPackage(String packagePath){
        Reflections reflections = new Reflections(packagePath);
        for(Class<? extends Command> clazz : reflections.getSubTypesOf(Command.class)){
            /* Skip commands which has the @SkipCommandRegistration annotation */
            if (clazz.isAnnotationPresent(SkipCommandRegistration.class)){
                Logger.debug("Skipping command \""+clazz.getName()+"\" cause @SkipCommandRegistration annotation is present");
                continue;
            }

            registerCommand(clazz.getConstructor().newInstance());
        }
    }

    /**
     * Register all console commands in package XY automatically. <br>
     * Classes with the annotation {@link SkipCommandRegistration} are skipped <br>
     * All not skipped Commands needs the {@link CommandInfo} class annotation!
     * @param packagePath
     */
    @SneakyThrows
    public void registerConsoleCommandsInPackage(String packagePath){
        Reflections reflections = new Reflections(packagePath);
        for(Class<? extends ConsoleCommand> clazz : reflections.getSubTypesOf(ConsoleCommand.class)){
            /* Skip commands which has the @SkipCommandRegistration annotation */
            if (clazz.isAnnotationPresent(SkipCommandRegistration.class)){
                Logger.debug("Skipping command \""+clazz.getName()+"\" cause @SkipCommandRegistration annotation is present");
                continue;
            }

            registerCommand(clazz.getConstructor().newInstance());
        }
    }

    /**
     * Register the provided command class. <br>
     * This command class needs the {@link CommandInfo} class!
     * @param command
     */
    @SneakyThrows
    public void registerCommand(Command command){
        /* Skip commands which has not the @CommandInfo annotation */
        if (!command.getClass().isAnnotationPresent(CommandInfo.class)){
            Logger.error("Could not register command \""+command.getClass().getName()+"\"!\n" +
                    "Missing @CommandInfo annotation!");
            return;
        }
        CommandInfo commandInfo = command.getClass().getAnnotation(CommandInfo.class);
        Logger.info("Registering command "+commandInfo.cmd()+" ...");
        if (commandHashMap.containsKey(commandInfo.cmd())){
            Logger.error("Command \""+commandInfo.cmd()+"\" is already registered!");
            return;
        }

        commandHashMap.put(commandInfo.cmd(), command);

        for(String s : commandInfo.aliase()){
            Logger.info("Registering alias \""+s+"\" for \""+commandInfo.cmd()+"\"..");
            if (aliasHashMap.containsKey(s)){
                Logger.error("Skipping alias registration cause this alias elaready exists!");
                continue;
            }
            aliasHashMap.put(s, commandInfo.cmd());
        }
    }

    /**
     * Register the provided command class. <br>
     * This command class needs the {@link CommandInfo} class!
     * @param command
     */
    @SneakyThrows
    public void registerCommand(ConsoleCommand command){
        /* Skip commands which has not the @CommandInfo annotation */
        if (!command.getClass().isAnnotationPresent(CommandInfo.class)){
            throw new NoCommandInfoAnnotationFoundException(command.getClass());
        }
        CommandInfo commandInfo = command.getClass().getAnnotation(CommandInfo.class);
        Logger.info("Registering console command "+commandInfo.cmd()+" ...");
        if (consoleCommandHashMap.containsKey(commandInfo.cmd())){
            throw new CommandAlreadyRegisteredException();
        }

        consoleCommandHashMap.put(commandInfo.cmd(), command);

        for(String s : commandInfo.aliase()){
            Logger.info("Registering alias \""+s+"\" for \""+commandInfo.cmd()+"\"..");
            if (consoleAliasHashMap.containsKey(s)){
                Logger.error("Skipping alias registration cause this alias elaready exists!");
                continue;
            }
            consoleAliasHashMap.put(s, commandInfo.cmd());
        }
    }


    /** @see #registerCommand(String, String, String, String[], BiConsumer)  */
    public void registerCommand(String cmd, BiConsumer<CommandSender, String[]> biConsumer){registerCommand(cmd, null, null, null, biConsumer);}
    /** @see #registerCommand(String, String, String, String[], BiConsumer)  */
    public void registerCommand(String cmd, String permission, BiConsumer<CommandSender, String[]> biConsumer){registerCommand(cmd, permission, null, null, biConsumer);}
    /** @see #registerCommand(String, String, String, String[], BiConsumer)  */
    public void registerCommand(String cmd, String permission, String description, BiConsumer<CommandSender, String[]> biConsumer){registerCommand(cmd, permission, description, null, biConsumer);}
    /**
     *
     * @param cmd
     * @param permission
     * @param description
     * @param aliase
     * @param biConsumer
     */
    public void registerCommand(String cmd, String permission, String description, String[] aliase, BiConsumer<CommandSender, String[]> biConsumer){
        CustomCommand customCommand = new CustomCommand(cmd, permission, description, aliase, biConsumer);
        registerCommand(customCommand);
    }

    /** @see #registerConsoleCommand(String, String, String, String[], Consumer)  */
    public void registerConsoleCommand(String cmd, Consumer<String[]> consumer){registerConsoleCommand(cmd, null, null, null, consumer);}
    /** @see #registerConsoleCommand(String, String, String, String[], Consumer)  */
    public void registerConsoleCommand(String cmd, String permission, Consumer<String[]> consumer){registerConsoleCommand(cmd, permission, null, null, consumer);}
    /** @see #registerConsoleCommand(String, String, String, String[], Consumer)  */
    public void registerConsoleCommand(String cmd, String permission, String description, Consumer<String[]> consumer){registerConsoleCommand(cmd, permission, description, null, consumer);}
    /**
     *
     * @param cmd
     * @param permission
     * @param description
     * @param aliase
     * @param consumer
     */
    public void registerConsoleCommand(String cmd, String permission, String description, String[] aliase, Consumer<String[]> consumer){
        CustomConsoleCommand customConsoleCommand = new CustomConsoleCommand(cmd, permission, description, aliase, consumer);
        registerCommand(customConsoleCommand);
    }

    public Command getCommand(String cmd){
        if (commandHashMap.containsKey(cmd)){
            return commandHashMap.get(cmd);
        }

        return commandHashMap.getOrDefault(aliasHashMap.getOrDefault(cmd, ""), null);
    }

    public ConsoleCommand getConsoleCommand(String cmd){
        if (consoleCommandHashMap.containsKey(cmd)){
            return consoleCommandHashMap.get(cmd);
        }

        return consoleCommandHashMap.getOrDefault(consoleAliasHashMap.getOrDefault(cmd, ""), null);
    }

    private static class CustomCommand extends Command implements CommandInfo{
        String cmd, permission, description;
        String[] aliase;
        BiConsumer<CommandSender, String[]> biConsumer;

        public CustomCommand(String cmd, String permission, String description, String[] aliase, BiConsumer<CommandSender, String[]> biConsumer){
            this.cmd = cmd;
            this.permission = permission;
            this.description = description;
            this.aliase = aliase;
            this.biConsumer = biConsumer;
        }

        @Override
        public void execute(CommandSender commandSender, String[] args) {
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

    private static class CustomConsoleCommand extends ConsoleCommand implements CommandInfo{
        String cmd, permission, description;
        String[] aliase;
        Consumer<String[]> consumer;

        public CustomConsoleCommand(String cmd, String permission, String description, String[] aliase, Consumer<String[]> consumer){
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
        public void execute(String[] args) {
            consumer.accept(args);
        }
    }
}
