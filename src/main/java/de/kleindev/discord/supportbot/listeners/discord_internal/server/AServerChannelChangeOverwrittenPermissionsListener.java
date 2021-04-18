package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.channels.serverchannel.ServerChannelOverwrittenPermissionsChangedEvent;
import org.javacord.api.event.channel.server.ServerChannelChangeOverwrittenPermissionsEvent;
import org.javacord.api.listener.channel.server.ServerChannelChangeOverwrittenPermissionsListener;

public class AServerChannelChangeOverwrittenPermissionsListener implements ServerChannelChangeOverwrittenPermissionsListener {
    @Override
    public void onServerChannelChangeOverwrittenPermissions(ServerChannelChangeOverwrittenPermissionsEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerChannelOverwrittenPermissionsChangedEvent(e));
    }
}