package de.kleindev.discord.supportbot.listeners.discord_internal.roles;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.RoleChangedEvent;
import de.kleindev.discord.supportbot.events.discord.role.RoleHoistChangedEvent;
import org.javacord.api.event.server.role.RoleChangeHoistEvent;
import org.javacord.api.listener.server.role.RoleChangeHoistListener;

public class ARoleChangeHoistListener implements RoleChangeHoistListener {
    @Override
    public void onRoleChangeHoist(RoleChangeHoistEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        SupportBot.getInstance().getEventManager().callEvent(new RoleHoistChangedEvent(e));
    }
}