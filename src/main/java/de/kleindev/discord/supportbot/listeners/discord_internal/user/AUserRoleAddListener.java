package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserRoleAddedEvent;
import org.javacord.api.event.server.role.UserRoleAddEvent;
import org.javacord.api.listener.server.role.UserRoleAddListener;

public class AUserRoleAddListener implements UserRoleAddListener {
    @Override
    public void onUserRoleAdd(UserRoleAddEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserRoleAddedEvent(e));
    }
}