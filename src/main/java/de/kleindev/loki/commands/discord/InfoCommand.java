package de.kleindev.loki.commands.discord;

import de.kleindev.loki.Main;
import de.kleindev.loki.commands.Command;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.commands.Response;
import de.kleindev.loki.objects.CommandSender;

import java.awt.*;

@CommandInfo(cmd = "info", description = "Shows information about this bot", aliase = {"about"})
public class InfoCommand extends Command {
    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {
        Response response = new Response(commandSender.getMessage());
        response.setDeletionTime(10);
        response.setColor(Color.cyan);
        response.setTitle("Informations about myself");
        response.setThumbnail("https://cdn.discordapp.com/avatars/221313993321480192/77ad2d2989d364fa401d5b4d5f019ab3.png");
        response.addField("Author", "KleinDev", false);
        response.addField("Version", Main.VERSION, false);
        response.send(true);
    }
}
