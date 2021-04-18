package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerSystemChannelChangedEvent;
import org.javacord.api.event.server.ServerChangeSystemChannelEvent;
import org.javacord.api.listener.server.ServerChangeSystemChannelListener;

public class AServerChangeSystemChannelListener implements ServerChangeSystemChannelListener {
    @Override
    public void onServerChangeSystemChannel(ServerChangeSystemChannelEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerSystemChannelChangedEvent(e));
    }
}