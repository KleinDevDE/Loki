package de.kleindev.loki.listeners.discord_internal.channel;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.privatechannel.PrivateChannelCreatedEvent;
import org.javacord.api.event.channel.user.PrivateChannelCreateEvent;
import org.javacord.api.listener.channel.user.PrivateChannelCreateListener;

public class APrivateChannelCreateListener implements PrivateChannelCreateListener {
    @Override
    public void onPrivateChannelCreate(PrivateChannelCreateEvent e) {
        Loki.getInstance().getEventManager().callEvent(new PrivateChannelCreatedEvent(e));
    }
}