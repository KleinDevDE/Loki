package de.kleindev.loki.listeners.discord_internal.user;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.user.UserNicknameChangedEvent;
import org.javacord.api.event.user.UserChangeNicknameEvent;
import org.javacord.api.listener.user.UserChangeNicknameListener;

public class AUserChangeNicknameListener implements UserChangeNicknameListener {
    @Override
    public void onUserChangeNickname(UserChangeNicknameEvent e) {
        Loki.getInstance().getEventManager().callEvent(new UserNicknameChangedEvent(e));
    }
}