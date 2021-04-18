package de.kleindev.discord.supportbot.listeners.discord_internal;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.DiscordConnectionLostEvent;
import org.javacord.api.event.connection.LostConnectionEvent;
import org.javacord.api.listener.connection.LostConnectionListener;

public class ALostConnectionListener implements LostConnectionListener {
    @Override
    public void onLostConnection(LostConnectionEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new DiscordConnectionLostEvent());
    }
}