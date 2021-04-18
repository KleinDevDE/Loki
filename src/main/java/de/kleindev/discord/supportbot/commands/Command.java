package de.kleindev.discord.supportbot.commands;

import de.kleindev.discord.supportbot.objects.CommandSender;

public abstract class Command {
    public abstract void execute(CommandSender commandSender, String[] args);

    public String getCommand(){
        return this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getAnnotation(CommandInfo.class).cmd() : "";
    }

    public String getDescription(){
        return this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getAnnotation(CommandInfo.class).description() : "";
    }

    public String[] getAliase(){
        return this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getAnnotation(CommandInfo.class).aliase() : new String[]{};
    }

    public String getPermission(){
        return this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getAnnotation(CommandInfo.class).permission() : "";
    }
}
