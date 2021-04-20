package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.channels.serverchannel.ServerChannelDeletedEvent;
import org.javacord.api.event.channel.server.ServerChannelDeleteEvent;
import org.javacord.api.listener.channel.server.ServerChannelDeleteListener;

public class AServerChannelDeleteListener implements ServerChannelDeleteListener {
    @Override
    public void onServerChannelDelete(ServerChannelDeleteEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerChannelDeletedEvent(e));
    }
}