package de.kleindev.discord.supportbot.listeners.discord_internal.channel;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.privatechannel.PrivateChannelDeletedEvent;
import org.javacord.api.event.channel.user.PrivateChannelDeleteEvent;
import org.javacord.api.listener.channel.user.PrivateChannelDeleteListener;

public class APrivateChannelDeleteListener implements PrivateChannelDeleteListener {
    @Override
    public void onPrivateChannelDelete(PrivateChannelDeleteEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new PrivateChannelDeletedEvent(e));
    }
}