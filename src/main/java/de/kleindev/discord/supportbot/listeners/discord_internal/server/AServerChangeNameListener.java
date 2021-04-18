package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerNameChangedEvent;
import org.javacord.api.event.server.ServerChangeNameEvent;
import org.javacord.api.listener.server.ServerChangeNameListener;

public class AServerChangeNameListener implements ServerChangeNameListener {
    @Override
    public void onServerChangeName(ServerChangeNameEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerNameChangedEvent(e));
    }
}