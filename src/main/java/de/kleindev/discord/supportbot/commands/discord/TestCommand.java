package de.kleindev.discord.supportbot.commands.discord;

import de.kleindev.discord.supportbot.commands.Command;
import de.kleindev.discord.supportbot.commands.CommandInfo;
import de.kleindev.discord.supportbot.objects.CommandSender;

@CommandInfo(cmd = "test", description = "A test command for every scenario", permission = "")
public class TestCommand extends Command {

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        commandSender.getTextChannel().sendMessage("Test erfolgreich!");
    }
}
