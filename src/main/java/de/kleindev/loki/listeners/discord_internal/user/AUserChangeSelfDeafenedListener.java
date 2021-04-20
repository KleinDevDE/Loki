package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserSelfDeafenChangedEvent;
import org.javacord.api.event.user.UserChangeSelfDeafenedEvent;
import org.javacord.api.listener.user.UserChangeSelfDeafenedListener;

public class AUserChangeSelfDeafenedListener implements UserChangeSelfDeafenedListener {
    @Override
    public void onUserChangeSelfDeafened(UserChangeSelfDeafenedEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserSelfDeafenChangedEvent(e));
    }
}