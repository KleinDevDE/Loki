package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.channels.serverchannel.ServerChannelPositionChangedEvent;
import org.javacord.api.event.channel.server.ServerChannelChangePositionEvent;
import org.javacord.api.listener.channel.server.ServerChannelChangePositionListener;

public class AServerChannelChangePositionListener implements ServerChannelChangePositionListener {
    @Override
    public void onServerChannelChangePosition(ServerChannelChangePositionEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerChannelPositionChangedEvent(e));
    }
}