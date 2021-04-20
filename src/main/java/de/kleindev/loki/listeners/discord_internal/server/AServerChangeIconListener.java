package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerIconChangedEvent;
import org.javacord.api.event.server.ServerChangeIconEvent;
import org.javacord.api.listener.server.ServerChangeIconListener;

public class AServerChangeIconListener implements ServerChangeIconListener {
    @Override
    public void onServerChangeIcon(ServerChangeIconEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerIconChangedEvent(e));
    }
}