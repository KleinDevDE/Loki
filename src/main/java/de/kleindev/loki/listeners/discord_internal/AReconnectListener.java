package de.kleindev.loki.listeners.discord_internal;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.DiscordReconnectEvent;
import org.javacord.api.event.connection.ReconnectEvent;
import org.javacord.api.listener.connection.ReconnectListener;

public class AReconnectListener implements ReconnectListener {
    @Override
    public void onReconnect(ReconnectEvent e) {
        Loki.getInstance().getEventManager().callEvent(new DiscordReconnectEvent());
    }
}