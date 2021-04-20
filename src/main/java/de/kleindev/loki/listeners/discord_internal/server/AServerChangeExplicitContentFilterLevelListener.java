package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerExplicitContentFilterLevelChangedEvent;
import org.javacord.api.event.server.ServerChangeExplicitContentFilterLevelEvent;
import org.javacord.api.listener.server.ServerChangeExplicitContentFilterLevelListener;

public class AServerChangeExplicitContentFilterLevelListener implements ServerChangeExplicitContentFilterLevelListener {
    @Override
    public void onServerChangeExplicitContentFilterLevel(ServerChangeExplicitContentFilterLevelEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerExplicitContentFilterLevelChangedEvent(e));
    }
}