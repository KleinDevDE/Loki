package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.channels.serverchannel.ServerChannelPositionChangedEvent;
import org.javacord.api.event.channel.server.ServerChannelChangePositionEvent;
import org.javacord.api.listener.channel.server.ServerChannelChangePositionListener;

public class AServerChannelChangePositionListener implements ServerChannelChangePositionListener {
    @Override
    public void onServerChannelChangePosition(ServerChannelChangePositionEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerChannelPositionChangedEvent(e));
    }
}