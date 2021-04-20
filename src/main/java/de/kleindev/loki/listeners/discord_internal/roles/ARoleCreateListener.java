package de.kleindev.loki.listeners.discord_internal.roles;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.RoleChangedEvent;
import de.kleindev.loki.events.discord.role.RoleCreatedEvent;
import org.javacord.api.event.server.role.RoleCreateEvent;
import org.javacord.api.listener.server.role.RoleCreateListener;

public class ARoleCreateListener implements RoleCreateListener {
    @Override
    public void onRoleCreate(RoleCreateEvent e) {
        Loki.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        Loki.getInstance().getEventManager().callEvent(new RoleCreatedEvent(e));
    }
}