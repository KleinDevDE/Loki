package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.channels.serverchannel.ServerChannelNameChangedEvent;
import org.javacord.api.event.channel.server.ServerChannelChangeNameEvent;
import org.javacord.api.listener.channel.server.ServerChannelChangeNameListener;

public class AServerChannelChangeNameListener implements ServerChannelChangeNameListener {
    @Override
    public void onServerChannelChangeName(ServerChannelChangeNameEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerChannelNameChangedEvent(e));
    }
}