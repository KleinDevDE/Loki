package de.kleindev.discord.supportbot.listeners.discord_internal.channel;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.ChannelPinsUpdatedEvent;
import org.javacord.api.event.message.ChannelPinsUpdateEvent;
import org.javacord.api.listener.message.ChannelPinsUpdateListener;

public class AChannelPinsUpdateListener implements ChannelPinsUpdateListener {
    @Override
    public void onChannelPinsUpdate(ChannelPinsUpdateEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ChannelPinsUpdatedEvent(e));
    }
}