package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserDiscriminatorChangedEvent;
import org.javacord.api.event.user.UserChangeDiscriminatorEvent;
import org.javacord.api.listener.user.UserChangeDiscriminatorListener;

public class AUserChangeDiscriminatorListener implements UserChangeDiscriminatorListener {
    @Override
    public void onUserChangeDiscriminator(UserChangeDiscriminatorEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserDiscriminatorChangedEvent(e));
    }
}