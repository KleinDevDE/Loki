package de.kleindev.loki.commands;

import de.kleindev.loki.objects.CommandSender;

public abstract class Command {
    public abstract void executeDiscord(CommandSender commandSender, String[] args);

    public String getCommand() {
        return this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getAnnotation(CommandInfo.class).cmd() : "";
    }

    public String getDescription() {
        return this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getAnnotation(CommandInfo.class).description() : "";
    }

    public String[] getAliase() {
        return this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getAnnotation(CommandInfo.class).aliase() : new String[]{};
    }

    public String getPermission() {
        return this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getAnnotation(CommandInfo.class).permission() : "";
    }
}
