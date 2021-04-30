package de.kleindev.loki.commands.discord.moderation;

import de.kleindev.loki.commands.Command;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.objects.CommandSender;

@CommandInfo(cmd = "clear", description = "deletes x messages", permission = "moderation.clear")
public class ClearCommand extends Command {
    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {
        // clear X
        // clear <User-Mention> X
        // clear <Channel-Mention> X
        // clear <Channel-Mention> <User-Mention> X
    }
}
