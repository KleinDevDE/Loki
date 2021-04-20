package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserRoleAddedEvent;
import org.javacord.api.event.server.role.UserRoleAddEvent;
import org.javacord.api.listener.server.role.UserRoleAddListener;

public class AUserRoleAddListener implements UserRoleAddListener {
    @Override
    public void onUserRoleAdd(UserRoleAddEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserRoleAddedEvent(e));
    }
}