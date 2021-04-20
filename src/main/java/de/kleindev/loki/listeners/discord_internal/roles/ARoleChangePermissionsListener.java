package de.kleindev.loki.listeners.discord_internal.roles;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.RoleChangedEvent;
import de.kleindev.loki.events.discord.role.RolePermissionsChangedEvent;
import org.javacord.api.event.server.role.RoleChangePermissionsEvent;
import org.javacord.api.listener.server.role.RoleChangePermissionsListener;

public class ARoleChangePermissionsListener implements RoleChangePermissionsListener {
    @Override
    public void onRoleChangePermissions(RoleChangePermissionsEvent e) {
        Loki.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        Loki.getInstance().getEventManager().callEvent(new RolePermissionsChangedEvent(e));
    }
}