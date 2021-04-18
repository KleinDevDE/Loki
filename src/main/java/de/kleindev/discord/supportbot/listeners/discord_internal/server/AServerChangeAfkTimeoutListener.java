package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerAFKTimeoutChangedEvent;
import org.javacord.api.event.server.ServerChangeAfkTimeoutEvent;
import org.javacord.api.listener.server.ServerChangeAfkTimeoutListener;

public class AServerChangeAfkTimeoutListener implements ServerChangeAfkTimeoutListener {
    @Override
    public void onServerChangeAfkTimeout(ServerChangeAfkTimeoutEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerAFKTimeoutChangedEvent(e));
    }
}