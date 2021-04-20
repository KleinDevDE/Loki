package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerNameChangedEvent;
import org.javacord.api.event.server.ServerChangeNameEvent;
import org.javacord.api.listener.server.ServerChangeNameListener;

public class AServerChangeNameListener implements ServerChangeNameListener {
    @Override
    public void onServerChangeName(ServerChangeNameEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerNameChangedEvent(e));
    }
}