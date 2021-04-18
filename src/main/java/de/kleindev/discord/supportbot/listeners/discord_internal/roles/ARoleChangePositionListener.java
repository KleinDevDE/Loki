package de.kleindev.discord.supportbot.listeners.discord_internal.roles;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.RoleChangedEvent;
import de.kleindev.discord.supportbot.events.discord.role.RolePositionChangedEvent;
import org.javacord.api.event.server.role.RoleChangePositionEvent;
import org.javacord.api.listener.server.role.RoleChangePositionListener;

public class ARoleChangePositionListener implements RoleChangePositionListener {
    @Override
    public void onRoleChangePosition(RoleChangePositionEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        SupportBot.getInstance().getEventManager().callEvent(new RolePositionChangedEvent(e));
    }
}