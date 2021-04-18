package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.server.ServerJoinedEvent;
import org.javacord.api.event.server.ServerJoinEvent;
import org.javacord.api.listener.server.ServerJoinListener;

public class AServerJoinListener implements ServerJoinListener {
    @Override
    public void onServerJoin(ServerJoinEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerJoinedEvent(e));
    }
}