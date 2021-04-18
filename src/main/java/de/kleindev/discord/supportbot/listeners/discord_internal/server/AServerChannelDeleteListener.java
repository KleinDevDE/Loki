package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.channels.serverchannel.ServerChannelDeletedEvent;
import org.javacord.api.event.channel.server.ServerChannelDeleteEvent;
import org.javacord.api.listener.channel.server.ServerChannelDeleteListener;

public class AServerChannelDeleteListener implements ServerChannelDeleteListener {
    @Override
    public void onServerChannelDelete(ServerChannelDeleteEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerChannelDeletedEvent(e));
    }
}