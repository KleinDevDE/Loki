package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserRoleRemovedEvent;
import org.javacord.api.event.server.role.UserRoleRemoveEvent;
import org.javacord.api.listener.server.role.UserRoleRemoveListener;

public class AUserRoleRemoveListener implements UserRoleRemoveListener {
    @Override
    public void onUserRoleRemove(UserRoleRemoveEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserRoleRemovedEvent(e));
    }
}