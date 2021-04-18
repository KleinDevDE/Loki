package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserActivityChangedEvent;
import org.javacord.api.event.user.UserChangeActivityEvent;
import org.javacord.api.listener.user.UserChangeActivityListener;

public class AUserChangeActivityListener implements UserChangeActivityListener {
    @Override
    public void onUserChangeActivity(UserChangeActivityEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserActivityChangedEvent(e));
    }
}