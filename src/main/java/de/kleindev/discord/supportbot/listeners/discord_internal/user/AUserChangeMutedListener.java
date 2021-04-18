package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserMuteChangedEvent;
import org.javacord.api.event.user.UserChangeMutedEvent;
import org.javacord.api.listener.user.UserChangeMutedListener;

public class AUserChangeMutedListener implements UserChangeMutedListener {
    @Override
    public void onUserChangeMuted(UserChangeMutedEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserMuteChangedEvent(e));
    }
}