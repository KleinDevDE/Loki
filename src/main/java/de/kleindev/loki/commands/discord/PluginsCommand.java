package de.kleindev.loki.commands.discord;

import de.kleindev.loki.Loki;
import de.kleindev.loki.commands.Command;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.commands.Response;
import de.kleindev.loki.objects.CommandSender;
import de.kleindev.loki.plugin.BotPlugin;

import java.util.Arrays;
import java.util.Collection;

@CommandInfo(cmd = "plugins", description = "List all plugins", permission = "*")
public class PluginsCommand extends Command {
    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {
        Response response = new Response(commandSender.getMessage());
        response.setDeletionTime(-1);
        Collection<BotPlugin> plugins = Loki.getInstance().getPluginManager().getActivePlugins();
        response.setTitle("Plugins ("+plugins.size()+"):");
        Response.EmbedListBuilder embedListBuilder = response.getEmbedListBuilder("Plugin (version)", "Autor");

        for(BotPlugin plugin : plugins){
            embedListBuilder.addLine(plugin.getPluginDescription().getPluginName() + "("+plugin.getPluginDescription().getVersion()+")", Arrays.toString(plugin.getPluginDescription().getAuthors()));
        }

        response.send(false);
    }
}
