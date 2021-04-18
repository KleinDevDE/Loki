package de.kleindev.discord.supportbot.listeners.discord_internal.channel;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.privatechannel.PrivateChannelCreatedEvent;
import org.javacord.api.event.channel.user.PrivateChannelCreateEvent;
import org.javacord.api.listener.channel.user.PrivateChannelCreateListener;

public class APrivateChannelCreateListener implements PrivateChannelCreateListener {
    @Override
    public void onPrivateChannelCreate(PrivateChannelCreateEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new PrivateChannelCreatedEvent(e));
    }
}