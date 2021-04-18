package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import org.javacord.api.event.server.ServerBecomesUnavailableEvent;
import org.javacord.api.listener.server.ServerBecomesUnavailableListener;

public class AServerBecomesUnavailableListener implements ServerBecomesUnavailableListener {
    @Override
    public void onServerBecomesUnavailable(ServerBecomesUnavailableEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new de.kleindev.discord.supportbot.events.discord.server.ServerBecomesUnavailableEvent(e));
    }
}