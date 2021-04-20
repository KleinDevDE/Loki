package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserSelfMuteChangedEvent;
import org.javacord.api.event.user.UserChangeSelfMutedEvent;
import org.javacord.api.listener.user.UserChangeSelfMutedListener;

public class AUserChangeSelfMutedListener implements UserChangeSelfMutedListener {
    @Override
    public void onUserChangeSelfMuted(UserChangeSelfMutedEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserSelfMuteChangedEvent(e));
    }
}