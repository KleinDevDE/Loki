package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserMuteChangedEvent;
import org.javacord.api.event.user.UserChangeMutedEvent;
import org.javacord.api.listener.user.UserChangeMutedListener;

public class AUserChangeMutedListener implements UserChangeMutedListener {
    @Override
    public void onUserChangeMuted(UserChangeMutedEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserMuteChangedEvent(e));
    }
}