package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import org.javacord.api.event.server.ServerBecomesAvailableEvent;
import org.javacord.api.listener.server.ServerBecomesAvailableListener;

public class AServerBecomesAvailableListener implements ServerBecomesAvailableListener {
    @Override
    public void onServerBecomesAvailable(ServerBecomesAvailableEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new de.kleindev.discord.supportbot.events.discord.server.ServerBecomesAvailableEvent(e));
    }
}