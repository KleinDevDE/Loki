package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerAFKChannelChangedEvent;
import org.javacord.api.event.server.ServerChangeAfkChannelEvent;
import org.javacord.api.listener.server.ServerChangeAfkChannelListener;

public class AServerChangeAfkChannelListener implements ServerChangeAfkChannelListener {
    @Override
    public void onServerChangeAfkChannel(ServerChangeAfkChannelEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerAFKChannelChangedEvent(e));
    }
}