package de.kleindev.loki.listeners.discord_internal.roles;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.RoleChangedEvent;
import de.kleindev.loki.events.discord.role.RoleDeletedEvent;
import org.javacord.api.event.server.role.RoleDeleteEvent;
import org.javacord.api.listener.server.role.RoleDeleteListener;

public class ARoleDeleteListener implements RoleDeleteListener {
    @Override
    public void onRoleDelete(RoleDeleteEvent e) {
        Loki.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        Loki.getInstance().getEventManager().callEvent(new RoleDeletedEvent(e));
    }
}