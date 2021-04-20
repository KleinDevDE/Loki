package de.kleindev.loki.listeners.discord_internal.roles;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.RoleChangedEvent;
import de.kleindev.loki.events.discord.role.RoleColorChangedEvent;
import org.javacord.api.event.server.role.RoleChangeColorEvent;
import org.javacord.api.listener.server.role.RoleChangeColorListener;

public class ARoleChangeColorListener implements RoleChangeColorListener {
    @Override
    public void onRoleChangeColor(RoleChangeColorEvent e) {
        Loki.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        Loki.getInstance().getEventManager().callEvent(new RoleColorChangedEvent(e));
    }
}