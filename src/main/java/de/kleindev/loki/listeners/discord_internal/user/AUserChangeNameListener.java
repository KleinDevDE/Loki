package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserNameChangedEvent;
import org.javacord.api.event.user.UserChangeNameEvent;
import org.javacord.api.listener.user.UserChangeNameListener;

public class AUserChangeNameListener implements UserChangeNameListener {
    @Override
    public void onUserChangeName(UserChangeNameEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserNameChangedEvent(e));
    }
}