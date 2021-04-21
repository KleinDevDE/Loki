package de.kleindev.loki.commands.discord;

import de.kleindev.loki.commands.Command;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.objects.CommandSender;

@CommandInfo(cmd = "say", description = "Simon's says")
public class SayCommand extends Command {
    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {
        commandSender.sendMessageToChannel(String.join(" ", args));
        commandSender.deleteCommandMessage();
    }
}
