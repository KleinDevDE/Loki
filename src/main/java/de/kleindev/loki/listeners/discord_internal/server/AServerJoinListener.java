package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.server.ServerJoinedEvent;
import org.javacord.api.event.server.ServerJoinEvent;
import org.javacord.api.listener.server.ServerJoinListener;

public class AServerJoinListener implements ServerJoinListener {
    @Override
    public void onServerJoin(ServerJoinEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerJoinedEvent(e));
    }
}