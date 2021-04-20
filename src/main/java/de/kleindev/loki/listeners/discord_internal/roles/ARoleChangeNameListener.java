package de.kleindev.loki.listeners.discord_internal.roles;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.RoleChangedEvent;
import de.kleindev.loki.events.discord.role.RoleNameChangedEvent;
import org.javacord.api.event.server.role.RoleChangeNameEvent;
import org.javacord.api.listener.server.role.RoleChangeNameListener;

public class ARoleChangeNameListener implements RoleChangeNameListener {
    @Override
    public void onRoleChangeName(RoleChangeNameEvent e) {
        Loki.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        Loki.getInstance().getEventManager().callEvent(new RoleNameChangedEvent(e));
    }
}