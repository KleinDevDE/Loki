package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserAvatarChangedEvent;
import org.javacord.api.event.user.UserChangeAvatarEvent;
import org.javacord.api.listener.user.UserChangeAvatarListener;

public class AUserChangeAvatarListener implements UserChangeAvatarListener {
    @Override
    public void onUserChangeAvatar(UserChangeAvatarEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserAvatarChangedEvent(e));
    }
}