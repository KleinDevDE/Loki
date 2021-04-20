package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserStartedTypingEvent;
import org.javacord.api.event.user.UserStartTypingEvent;
import org.javacord.api.listener.user.UserStartTypingListener;

public class AUserStartTypingListener implements UserStartTypingListener {
    @Override
    public void onUserStartTyping(UserStartTypingEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserStartedTypingEvent(e));
    }
}