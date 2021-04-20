package de.kleindev.loki.listeners.discord_internal.roles;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.RoleChangedEvent;
import de.kleindev.loki.events.discord.role.RoleHoistChangedEvent;
import org.javacord.api.event.server.role.RoleChangeHoistEvent;
import org.javacord.api.listener.server.role.RoleChangeHoistListener;

public class ARoleChangeHoistListener implements RoleChangeHoistListener {
    @Override
    public void onRoleChangeHoist(RoleChangeHoistEvent e) {
        Loki.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        Loki.getInstance().getEventManager().callEvent(new RoleHoistChangedEvent(e));
    }
}