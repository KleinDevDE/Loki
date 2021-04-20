package de.kleindev.loki.listeners.discord_internal.channel;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.ChannelPinsUpdatedEvent;
import org.javacord.api.event.message.ChannelPinsUpdateEvent;
import org.javacord.api.listener.message.ChannelPinsUpdateListener;

public class AChannelPinsUpdateListener implements ChannelPinsUpdateListener {
    @Override
    public void onChannelPinsUpdate(ChannelPinsUpdateEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ChannelPinsUpdatedEvent(e));
    }
}