package de.kleindev.loki.commands.console;

import de.kleindev.loki.Loki;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.commands.ConsoleCommand;

@CommandInfo(cmd = "reload", description = "Reloads the whole bot", permission = "application.reload")
public class ReloadConsoleCommand extends ConsoleCommand {
    @Override
    public void executeConsole(String[] args) {
        Loki.getInstance().reload();
        //TODO message
    }
}
