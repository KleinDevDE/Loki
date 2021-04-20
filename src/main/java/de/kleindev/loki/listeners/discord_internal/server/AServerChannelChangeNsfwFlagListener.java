package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.channels.serverchannel.ServerChannelNSFWFlagChangedEvent;
import org.javacord.api.event.channel.server.ServerChannelChangeNsfwFlagEvent;
import org.javacord.api.listener.channel.server.ServerChannelChangeNsfwFlagListener;

public class AServerChannelChangeNsfwFlagListener implements ServerChannelChangeNsfwFlagListener {
    @Override
    public void onServerChannelChangeNsfwFlag(ServerChannelChangeNsfwFlagEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerChannelNSFWFlagChangedEvent(e));
    }
}