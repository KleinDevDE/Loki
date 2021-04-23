package de.kleindev.loki.events.discord.server;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.server.ServerChangeNameEvent;


public class ServerNameChangedEvent extends Event {
    private String oldName;
    private String newName;
    private DiscordApi api;
    private Server server;

    public ServerNameChangedEvent(ServerChangeNameEvent e) {
        api = e.getApi();
        server = e.getServer();
        oldName = e.getOldName();
        newName = e.getNewName();
    }

    public DiscordApi getApi() {
        return api;
    }

    public Server getServer() {
        return server;
    }

    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }
}
