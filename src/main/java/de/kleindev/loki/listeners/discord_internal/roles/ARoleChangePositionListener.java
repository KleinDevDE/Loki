package de.kleindev.loki.listeners.discord_internal.roles;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.RoleChangedEvent;
import de.kleindev.loki.events.discord.role.RolePositionChangedEvent;
import org.javacord.api.event.server.role.RoleChangePositionEvent;
import org.javacord.api.listener.server.role.RoleChangePositionListener;

public class ARoleChangePositionListener implements RoleChangePositionListener {
    @Override
    public void onRoleChangePosition(RoleChangePositionEvent e) {
        Loki.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        Loki.getInstance().getEventManager().callEvent(new RolePositionChangedEvent(e));
    }
}