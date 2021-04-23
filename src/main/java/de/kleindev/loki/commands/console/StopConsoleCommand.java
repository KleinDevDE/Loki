package de.kleindev.loki.commands.console;

import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.commands.ConsoleCommand;
import de.kleindev.loki.logging.Logger;

@CommandInfo(cmd = "stop", description = "Stops the bot", permission = "application.stop", aliase = {"exit", "shutdown", "end"})
public class StopConsoleCommand extends ConsoleCommand {
    @Override
    public void executeConsole(String[] args) {
        Logger.info("Shutting down...");
        System.exit(0);
    }
}
