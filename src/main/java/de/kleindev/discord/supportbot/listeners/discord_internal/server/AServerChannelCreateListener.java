package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.channels.serverchannel.ServerChannelCreatedEvent;
import org.javacord.api.event.channel.server.ServerChannelCreateEvent;
import org.javacord.api.listener.channel.server.ServerChannelCreateListener;

public class AServerChannelCreateListener implements ServerChannelCreateListener {
    @Override
    public void onServerChannelCreate(ServerChannelCreateEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerChannelCreatedEvent(e));
    }
}