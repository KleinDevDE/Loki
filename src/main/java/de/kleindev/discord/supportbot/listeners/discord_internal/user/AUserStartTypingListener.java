package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserStartedTypingEvent;
import org.javacord.api.event.user.UserStartTypingEvent;
import org.javacord.api.listener.user.UserStartTypingListener;

public class AUserStartTypingListener implements UserStartTypingListener {
    @Override
    public void onUserStartTyping(UserStartTypingEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserStartedTypingEvent(e));
    }
}