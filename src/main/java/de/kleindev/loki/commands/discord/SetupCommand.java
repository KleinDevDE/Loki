package de.kleindev.loki.commands.discord;

import de.kleindev.loki.commands.Command;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.commands.SkipCommandRegistration;
import de.kleindev.loki.objects.CommandSender;

@SkipCommandRegistration
@CommandInfo(cmd = "setup", description = "Setup the bot", aliase = {"settings"})
public class SetupCommand extends Command {
    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {

    }
}
