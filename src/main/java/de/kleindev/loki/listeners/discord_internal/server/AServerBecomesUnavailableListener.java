package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import org.javacord.api.event.server.ServerBecomesUnavailableEvent;
import org.javacord.api.listener.server.ServerBecomesUnavailableListener;

public class AServerBecomesUnavailableListener implements ServerBecomesUnavailableListener {
    @Override
    public void onServerBecomesUnavailable(ServerBecomesUnavailableEvent e) {
        Loki.getInstance().getEventManager().callEvent(new de.kleindev.loki.events.discord.server.ServerBecomesUnavailableEvent(e));
    }
}