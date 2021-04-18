package de.kleindev.discord.supportbot.listeners.discord_internal.roles;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.RoleChangedEvent;
import de.kleindev.discord.supportbot.events.discord.role.RoleCreatedEvent;
import org.javacord.api.event.server.role.RoleCreateEvent;
import org.javacord.api.listener.server.role.RoleCreateListener;

public class ARoleCreateListener implements RoleCreateListener {
    @Override
    public void onRoleCreate(RoleCreateEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        SupportBot.getInstance().getEventManager().callEvent(new RoleCreatedEvent(e));
    }
}