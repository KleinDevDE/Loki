package de.kleindev.loki.commands.discord;

import de.kleindev.loki.Loki;
import de.kleindev.loki.commands.Command;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.objects.CommandSender;

@CommandInfo(cmd = "reload", description = "Reloads the whole bot", permission = "application.reload")
public class ReloadCommand extends Command {
    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {
        commandSender.sendMessageToChannel("Reloading...", 5);
        Loki.getInstance().reload();
        commandSender.sendMessageToChannel("Reload complete!", 5);
    }
}
