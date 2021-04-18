package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserSelfMuteChangedEvent;
import org.javacord.api.event.user.UserChangeSelfMutedEvent;
import org.javacord.api.listener.user.UserChangeSelfMutedListener;

public class AUserChangeSelfMutedListener implements UserChangeSelfMutedListener {
    @Override
    public void onUserChangeSelfMuted(UserChangeSelfMutedEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserSelfMuteChangedEvent(e));
    }
}