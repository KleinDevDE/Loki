package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerSplashChangedEvent;
import org.javacord.api.event.server.ServerChangeSplashEvent;
import org.javacord.api.listener.server.ServerChangeSplashListener;

public class AServerChangeSplashListener implements ServerChangeSplashListener {
    @Override
    public void onServerChangeSplash(ServerChangeSplashEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerSplashChangedEvent(e));
    }
}