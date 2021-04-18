package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserStatusChangedEvent;
import org.javacord.api.event.user.UserChangeStatusEvent;
import org.javacord.api.listener.user.UserChangeStatusListener;

public class AUserChangeStatusListener implements UserChangeStatusListener {
    @Override
    public void onUserChangeStatus(UserChangeStatusEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserStatusChangedEvent(e));
    }
}