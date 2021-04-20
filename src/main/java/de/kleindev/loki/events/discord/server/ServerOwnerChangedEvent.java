package de.kleindev.loki.events.discord.server;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.server.ServerChangeOwnerEvent;

//TODO Alert!!
public class ServerOwnerChangedEvent extends Event {
    private DiscordApi api;
    private Server server;
    private User oldOwner;
    private User newOwner;

    public ServerOwnerChangedEvent(ServerChangeOwnerEvent e){
        api = e.getApi();
        server = e.getServer();
        oldOwner = e.getOldOwner().get();
        newOwner = e.getNewOwner().get();
    }

    public Server getServer() {
        return server;
    }

    public DiscordApi getApi() {
        return api;
    }

    public User getNewOwner() {
        return newOwner;
    }

    public User getOldOwner() {
        return oldOwner;
    }
}
