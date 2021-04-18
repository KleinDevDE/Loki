package de.kleindev.discord.supportbot.listeners.discord_internal.roles;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.RoleChangedEvent;
import de.kleindev.discord.supportbot.events.discord.role.RoleDeletedEvent;
import org.javacord.api.event.server.role.RoleDeleteEvent;
import org.javacord.api.listener.server.role.RoleDeleteListener;

public class ARoleDeleteListener implements RoleDeleteListener {
    @Override
    public void onRoleDelete(RoleDeleteEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        SupportBot.getInstance().getEventManager().callEvent(new RoleDeletedEvent(e));
    }
}