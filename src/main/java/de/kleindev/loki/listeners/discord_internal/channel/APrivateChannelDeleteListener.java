package de.kleindev.loki.listeners.discord_internal.channel;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.privatechannel.PrivateChannelDeletedEvent;
import org.javacord.api.event.channel.user.PrivateChannelDeleteEvent;
import org.javacord.api.listener.channel.user.PrivateChannelDeleteListener;

public class APrivateChannelDeleteListener implements PrivateChannelDeleteListener {
    @Override
    public void onPrivateChannelDelete(PrivateChannelDeleteEvent e) {
        Loki.getInstance().getEventManager().callEvent(new PrivateChannelDeletedEvent(e));
    }
}