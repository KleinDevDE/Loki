package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerRegionChangedEvent;
import org.javacord.api.event.server.ServerChangeRegionEvent;
import org.javacord.api.listener.server.ServerChangeRegionListener;

public class AServerChangeRegionListener implements ServerChangeRegionListener {
    @Override
    public void onServerChangeRegion(ServerChangeRegionEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerRegionChangedEvent(e));
    }
}