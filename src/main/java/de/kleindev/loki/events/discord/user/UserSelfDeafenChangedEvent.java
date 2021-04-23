package de.kleindev.loki.events.discord.user;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.user.UserChangeSelfDeafenedEvent;


public class UserSelfDeafenChangedEvent extends Event {
    private Server server;
    private DiscordApi api;
    private User user;

    public UserSelfDeafenChangedEvent(UserChangeSelfDeafenedEvent javaCordEvent) {
        this.server = javaCordEvent.getServer();
        this.api = javaCordEvent.getApi();
        this.user = javaCordEvent.getUser();
    }

    public Server getServer() {
        return server;
    }

    public DiscordApi getApi() {
        return api;
    }

    public User getUser() {
        return user;
    }

}
