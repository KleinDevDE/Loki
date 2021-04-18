package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserNicknameChangedEvent;
import org.javacord.api.event.user.UserChangeNicknameEvent;
import org.javacord.api.listener.user.UserChangeNicknameListener;

public class AUserChangeNicknameListener implements UserChangeNicknameListener {
    @Override
    public void onUserChangeNickname(UserChangeNicknameEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserNicknameChangedEvent(e));
    }
}