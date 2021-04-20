package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.server.ServerLeavedEvent;
import org.javacord.api.event.server.ServerLeaveEvent;
import org.javacord.api.listener.server.ServerLeaveListener;

public class AServerLeaveListener implements ServerLeaveListener {
    @Override
    public void onServerLeave(ServerLeaveEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerLeavedEvent(e));
    }
}