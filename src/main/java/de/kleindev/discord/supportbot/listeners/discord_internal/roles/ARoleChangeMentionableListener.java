package de.kleindev.discord.supportbot.listeners.discord_internal.roles;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.RoleChangedEvent;
import de.kleindev.discord.supportbot.events.discord.role.RoleMentionableChangedEvent;
import org.javacord.api.event.server.role.RoleChangeMentionableEvent;
import org.javacord.api.listener.server.role.RoleChangeMentionableListener;

public class ARoleChangeMentionableListener implements RoleChangeMentionableListener {
    @Override
    public void onRoleChangeMentionable(RoleChangeMentionableEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        SupportBot.getInstance().getEventManager().callEvent(new RoleMentionableChangedEvent(e));
    }
}