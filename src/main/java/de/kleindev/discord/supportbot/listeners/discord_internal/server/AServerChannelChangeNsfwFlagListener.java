package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.channels.serverchannel.ServerChannelNSFWFlagChangedEvent;
import org.javacord.api.event.channel.server.ServerChannelChangeNsfwFlagEvent;
import org.javacord.api.listener.channel.server.ServerChannelChangeNsfwFlagListener;

public class AServerChannelChangeNsfwFlagListener implements ServerChannelChangeNsfwFlagListener {
    @Override
    public void onServerChannelChangeNsfwFlag(ServerChannelChangeNsfwFlagEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerChannelNSFWFlagChangedEvent(e));
    }
}