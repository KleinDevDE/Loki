package de.kleindev.loki.events.discord;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;

public class RoleChangedEvent extends Event {
    public RoleChangedEvent(DiscordApi api, Server server, Role role) {

    }
}
