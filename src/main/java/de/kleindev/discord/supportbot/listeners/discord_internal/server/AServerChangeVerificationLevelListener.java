package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerVerificationLevelChangedEvent;
import org.javacord.api.event.server.ServerChangeVerificationLevelEvent;
import org.javacord.api.listener.server.ServerChangeVerificationLevelListener;

public class AServerChangeVerificationLevelListener implements ServerChangeVerificationLevelListener {
    @Override
    public void onServerChangeVerificationLevel(ServerChangeVerificationLevelEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerVerificationLevelChangedEvent(e));
    }
}