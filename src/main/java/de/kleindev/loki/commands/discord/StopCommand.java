package de.kleindev.loki.commands.discord;

import de.kleindev.loki.commands.Command;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.commands.ConsoleCommand;
import de.kleindev.loki.logging.Logger;
import de.kleindev.loki.objects.CommandSender;

@CommandInfo(cmd = "stop", description = "Stops the bot", permission = "application.stop", aliase = {"exit", "shutdown", "end"})
public class StopCommand extends Command {
    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {
        Logger.info("Shutdown initialized by " + commandSender.getUser().getName());
        commandSender.sendMessageToChannel("Shutting down...");
        System.exit(0);
    }
}
