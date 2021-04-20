package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerOwnerChangedEvent;
import org.javacord.api.event.server.ServerChangeOwnerEvent;
import org.javacord.api.listener.server.ServerChangeOwnerListener;

public class AServerChangeOwnerListener implements ServerChangeOwnerListener {
    @Override
    public void onServerChangeOwner(ServerChangeOwnerEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerOwnerChangedEvent(e));
    }
}