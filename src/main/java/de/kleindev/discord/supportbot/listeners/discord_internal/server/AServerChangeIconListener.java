package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerIconChangedEvent;
import org.javacord.api.event.server.ServerChangeIconEvent;
import org.javacord.api.listener.server.ServerChangeIconListener;

public class AServerChangeIconListener implements ServerChangeIconListener {
    @Override
    public void onServerChangeIcon(ServerChangeIconEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerIconChangedEvent(e));
    }
}