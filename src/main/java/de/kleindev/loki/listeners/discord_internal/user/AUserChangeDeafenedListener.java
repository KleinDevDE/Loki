package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserDeafenChangedEvent;
import org.javacord.api.event.user.UserChangeDeafenedEvent;
import org.javacord.api.listener.user.UserChangeDeafenedListener;

public class AUserChangeDeafenedListener implements UserChangeDeafenedListener {
    @Override
    public void onUserChangeDeafened(UserChangeDeafenedEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserDeafenChangedEvent(e));
    }
}