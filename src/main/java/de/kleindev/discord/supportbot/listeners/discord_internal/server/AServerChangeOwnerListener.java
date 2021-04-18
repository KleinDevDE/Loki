package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerOwnerChangedEvent;
import org.javacord.api.event.server.ServerChangeOwnerEvent;
import org.javacord.api.listener.server.ServerChangeOwnerListener;

public class AServerChangeOwnerListener implements ServerChangeOwnerListener {
    @Override
    public void onServerChangeOwner(ServerChangeOwnerEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerOwnerChangedEvent(e));
    }
}