package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserDiscriminatorChangedEvent;
import org.javacord.api.event.user.UserChangeDiscriminatorEvent;
import org.javacord.api.listener.user.UserChangeDiscriminatorListener;

public class AUserChangeDiscriminatorListener implements UserChangeDiscriminatorListener {
    @Override
    public void onUserChangeDiscriminator(UserChangeDiscriminatorEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserDiscriminatorChangedEvent(e));
    }
}