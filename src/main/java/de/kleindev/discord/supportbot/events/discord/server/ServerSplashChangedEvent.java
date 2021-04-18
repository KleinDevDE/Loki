package de.kleindev.discord.supportbot.events.discord.server;

import de.kleindev.discord.supportbot.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.server.ServerChangeSplashEvent;

import java.util.Optional;


public class ServerSplashChangedEvent extends Event {
    private DiscordApi api;
    private Server server;
    private Optional<Icon> oldSPlash;
    private Optional<Icon> newSPlash;

    public ServerSplashChangedEvent(ServerChangeSplashEvent e){
        api = e.getApi();
        server = e.getServer();
        oldSPlash = e.getOldSplash();
        newSPlash = e.getNewSplash();
    }

    public Server getServer() {
        return server;
    }

    public DiscordApi getApi() {
        return api;
    }

    public Optional<Icon> getNewSPlash() {
        return newSPlash;
    }

    public Optional<Icon> getOldSPlash() {
        return oldSPlash;
    }
}
