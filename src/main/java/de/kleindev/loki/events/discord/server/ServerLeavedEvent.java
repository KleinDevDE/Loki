package de.kleindev.loki.events.discord.server;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.server.ServerLeaveEvent;

// TODO send message if left eona server
public class ServerLeavedEvent extends Event {
    private DiscordApi api;
    private Server server;

    public ServerLeavedEvent(ServerLeaveEvent e) {
        api = e.getApi();
        server = e.getServer();
    }

    public DiscordApi getApi() {
        return api;
    }

    public Server getServer() {
        return server;
    }
}
