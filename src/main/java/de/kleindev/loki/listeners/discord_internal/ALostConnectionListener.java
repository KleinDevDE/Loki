package de.kleindev.loki.listeners.discord_internal;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.DiscordConnectionLostEvent;
import org.javacord.api.event.connection.LostConnectionEvent;
import org.javacord.api.listener.connection.LostConnectionListener;

public class ALostConnectionListener implements LostConnectionListener {
    @Override
    public void onLostConnection(LostConnectionEvent e) {
        Loki.getInstance().getEventManager().callEvent(new DiscordConnectionLostEvent());
    }
}