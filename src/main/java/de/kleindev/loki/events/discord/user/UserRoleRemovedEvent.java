package de.kleindev.loki.events.discord.user;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.server.role.UserRoleRemoveEvent;


public class UserRoleRemovedEvent extends Event {
    private Server server;
    private Role role;
    private DiscordApi api;
    private User user;

    public UserRoleRemovedEvent(UserRoleRemoveEvent javaCordEvent) {
        this.server = javaCordEvent.getServer();
        this.role = javaCordEvent.getRole();
        this.api = javaCordEvent.getApi();
        this.user = javaCordEvent.getUser();
    }

    public Server getServer() {
        return server;
    }

    public Role getRole() {
        return role;
    }

    public DiscordApi getApi() {
        return api;
    }

    public User getUser() {
        return user;
    }

}
