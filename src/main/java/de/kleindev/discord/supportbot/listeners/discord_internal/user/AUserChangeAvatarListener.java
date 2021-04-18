package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserAvatarChangedEvent;
import org.javacord.api.event.user.UserChangeAvatarEvent;
import org.javacord.api.listener.user.UserChangeAvatarListener;

public class AUserChangeAvatarListener implements UserChangeAvatarListener {
    @Override
    public void onUserChangeAvatar(UserChangeAvatarEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserAvatarChangedEvent(e));
    }
}