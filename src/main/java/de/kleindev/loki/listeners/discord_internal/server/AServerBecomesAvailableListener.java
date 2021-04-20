package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import org.javacord.api.event.server.ServerBecomesAvailableEvent;
import org.javacord.api.listener.server.ServerBecomesAvailableListener;

public class AServerBecomesAvailableListener implements ServerBecomesAvailableListener {
    @Override
    public void onServerBecomesAvailable(ServerBecomesAvailableEvent e) {
        Loki.getInstance().getEventManager().callEvent(new de.kleindev.loki.events.discord.server.ServerBecomesAvailableEvent(e));
    }
}