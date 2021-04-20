package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.channels.serverchannel.ServerChannelCreatedEvent;
import org.javacord.api.event.channel.server.ServerChannelCreateEvent;
import org.javacord.api.listener.channel.server.ServerChannelCreateListener;

public class AServerChannelCreateListener implements ServerChannelCreateListener {
    @Override
    public void onServerChannelCreate(ServerChannelCreateEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerChannelCreatedEvent(e));
    }
}