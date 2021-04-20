package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserStatusChangedEvent;
import org.javacord.api.event.user.UserChangeStatusEvent;
import org.javacord.api.listener.user.UserChangeStatusListener;

public class AUserChangeStatusListener implements UserChangeStatusListener {
    @Override
    public void onUserChangeStatus(UserChangeStatusEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserStatusChangedEvent(e));
    }
}