package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserRoleRemovedEvent;
import org.javacord.api.event.server.role.UserRoleRemoveEvent;
import org.javacord.api.listener.server.role.UserRoleRemoveListener;

public class AUserRoleRemoveListener implements UserRoleRemoveListener {
    @Override
    public void onUserRoleRemove(UserRoleRemoveEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserRoleRemovedEvent(e));
    }
}