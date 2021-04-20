package de.kleindev.loki.commands.console;

import de.kleindev.loki.Loki;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.commands.ConsoleCommand;
import de.kleindev.loki.logging.Logger;

import java.util.stream.Stream;

@CommandInfo(cmd = "commands", description = "List all commands", permission = "*")
public class CommandsCommand extends ConsoleCommand {
    @Override
    public void executeConsole(String[] args) {
        Stream<ConsoleCommand> consoleCommandStream = Loki.getInstance().getCommandManager().getConsoleCommands();
        consoleCommandStream.forEach(consoleCommand -> {
            Logger.info(String.format("%s %s", consoleCommand.getCommand(), consoleCommand.getDescription()));
        });
    }
}
