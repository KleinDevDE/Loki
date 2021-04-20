package de.kleindev.loki.listeners.discord_internal.roles;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.RoleChangedEvent;
import de.kleindev.loki.events.discord.role.RoleMentionableChangedEvent;
import org.javacord.api.event.server.role.RoleChangeMentionableEvent;
import org.javacord.api.listener.server.role.RoleChangeMentionableListener;

public class ARoleChangeMentionableListener implements RoleChangeMentionableListener {
    @Override
    public void onRoleChangeMentionable(RoleChangeMentionableEvent e) {
        Loki.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        Loki.getInstance().getEventManager().callEvent(new RoleMentionableChangedEvent(e));
    }
}