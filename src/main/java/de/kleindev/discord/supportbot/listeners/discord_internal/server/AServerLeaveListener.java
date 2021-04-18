package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.server.ServerLeavedEvent;
import org.javacord.api.event.server.ServerLeaveEvent;
import org.javacord.api.listener.server.ServerLeaveListener;

public class AServerLeaveListener implements ServerLeaveListener {
    @Override
    public void onServerLeave(ServerLeaveEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerLeavedEvent(e));
    }
}