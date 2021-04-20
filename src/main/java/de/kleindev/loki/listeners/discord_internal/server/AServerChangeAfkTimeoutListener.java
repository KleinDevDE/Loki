package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerAFKTimeoutChangedEvent;
import org.javacord.api.event.server.ServerChangeAfkTimeoutEvent;
import org.javacord.api.listener.server.ServerChangeAfkTimeoutListener;

public class AServerChangeAfkTimeoutListener implements ServerChangeAfkTimeoutListener {
    @Override
    public void onServerChangeAfkTimeout(ServerChangeAfkTimeoutEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerAFKTimeoutChangedEvent(e));
    }
}