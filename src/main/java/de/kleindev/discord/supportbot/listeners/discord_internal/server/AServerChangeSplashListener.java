package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerSplashChangedEvent;
import org.javacord.api.event.server.ServerChangeSplashEvent;
import org.javacord.api.listener.server.ServerChangeSplashListener;

public class AServerChangeSplashListener implements ServerChangeSplashListener {
    @Override
    public void onServerChangeSplash(ServerChangeSplashEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerSplashChangedEvent(e));
    }
}