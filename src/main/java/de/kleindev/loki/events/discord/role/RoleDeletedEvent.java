package de.kleindev.loki.events.discord.role;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.server.role.RoleDeleteEvent;


public class RoleDeletedEvent extends Event {
    private DiscordApi api;
    private Server server;
    private Role role;

    public RoleDeletedEvent(RoleDeleteEvent e) {
        api = e.getApi();
        server = e.getServer();
        role = e.getRole();
    }

    public DiscordApi getApi() {
        return api;
    }

    public Server getServer() {
        return server;
    }

    public Role getRole() {
        return role;
    }
}
