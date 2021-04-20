package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.channels.serverchannel.ServerChannelOverwrittenPermissionsChangedEvent;
import org.javacord.api.event.channel.server.ServerChannelChangeOverwrittenPermissionsEvent;
import org.javacord.api.listener.channel.server.ServerChannelChangeOverwrittenPermissionsListener;

public class AServerChannelChangeOverwrittenPermissionsListener implements ServerChannelChangeOverwrittenPermissionsListener {
    @Override
    public void onServerChannelChangeOverwrittenPermissions(ServerChannelChangeOverwrittenPermissionsEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerChannelOverwrittenPermissionsChangedEvent(e));
    }
}