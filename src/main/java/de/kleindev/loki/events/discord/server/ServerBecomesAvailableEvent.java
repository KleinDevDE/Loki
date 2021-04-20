package de.kleindev.loki.events.discord.server;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;


public class ServerBecomesAvailableEvent extends Event {
    private DiscordApi api;
    private Server server;

    public ServerBecomesAvailableEvent(org.javacord.api.event.server.ServerBecomesAvailableEvent e){
        api = e.getApi();
        server = e.getServer();
    }

    public Server getServer() {
        return server;
    }

    public DiscordApi getApi() {
        return api;
    }
}
