package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.channels.serverchannel.ServerChannelNameChangedEvent;
import org.javacord.api.event.channel.server.ServerChannelChangeNameEvent;
import org.javacord.api.listener.channel.server.ServerChannelChangeNameListener;

public class AServerChannelChangeNameListener implements ServerChannelChangeNameListener {
    @Override
    public void onServerChannelChangeName(ServerChannelChangeNameEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerChannelNameChangedEvent(e));
    }
}