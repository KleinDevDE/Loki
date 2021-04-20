package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerSystemChannelChangedEvent;
import org.javacord.api.event.server.ServerChangeSystemChannelEvent;
import org.javacord.api.listener.server.ServerChangeSystemChannelListener;

public class AServerChangeSystemChannelListener implements ServerChangeSystemChannelListener {
    @Override
    public void onServerChangeSystemChannel(ServerChangeSystemChannelEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerSystemChannelChangedEvent(e));
    }
}