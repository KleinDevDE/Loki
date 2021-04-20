package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerRegionChangedEvent;
import org.javacord.api.event.server.ServerChangeRegionEvent;
import org.javacord.api.listener.server.ServerChangeRegionListener;

public class AServerChangeRegionListener implements ServerChangeRegionListener {
    @Override
    public void onServerChangeRegion(ServerChangeRegionEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerRegionChangedEvent(e));
    }
}