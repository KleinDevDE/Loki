package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerExplicitContentFilterLevelChangedEvent;
import org.javacord.api.event.server.ServerChangeExplicitContentFilterLevelEvent;
import org.javacord.api.listener.server.ServerChangeExplicitContentFilterLevelListener;

public class AServerChangeExplicitContentFilterLevelListener implements ServerChangeExplicitContentFilterLevelListener {
    @Override
    public void onServerChangeExplicitContentFilterLevel(ServerChangeExplicitContentFilterLevelEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerExplicitContentFilterLevelChangedEvent(e));
    }
}