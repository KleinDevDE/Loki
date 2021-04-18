package de.kleindev.discord.supportbot.listeners.discord_internal.roles;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.RoleChangedEvent;
import de.kleindev.discord.supportbot.events.discord.role.RolePermissionsChangedEvent;
import org.javacord.api.event.server.role.RoleChangePermissionsEvent;
import org.javacord.api.listener.server.role.RoleChangePermissionsListener;

public class ARoleChangePermissionsListener implements RoleChangePermissionsListener {
    @Override
    public void onRoleChangePermissions(RoleChangePermissionsEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        SupportBot.getInstance().getEventManager().callEvent(new RolePermissionsChangedEvent(e));
    }
}