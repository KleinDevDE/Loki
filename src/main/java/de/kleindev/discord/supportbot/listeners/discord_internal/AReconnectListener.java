package de.kleindev.discord.supportbot.listeners.discord_internal;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.DiscordReconnectEvent;
import org.javacord.api.event.connection.ReconnectEvent;
import org.javacord.api.listener.connection.ReconnectListener;

public class AReconnectListener implements ReconnectListener {
    @Override
    public void onReconnect(ReconnectEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new DiscordReconnectEvent());
    }
}