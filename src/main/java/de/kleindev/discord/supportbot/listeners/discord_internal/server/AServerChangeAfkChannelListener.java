package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerAFKChannelChangedEvent;
import org.javacord.api.event.server.ServerChangeAfkChannelEvent;
import org.javacord.api.listener.server.ServerChangeAfkChannelListener;

public class AServerChangeAfkChannelListener implements ServerChangeAfkChannelListener {
    @Override
    public void onServerChangeAfkChannel(ServerChangeAfkChannelEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerAFKChannelChangedEvent(e));
    }
}