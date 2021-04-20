package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerVerificationLevelChangedEvent;
import org.javacord.api.event.server.ServerChangeVerificationLevelEvent;
import org.javacord.api.listener.server.ServerChangeVerificationLevelListener;

public class AServerChangeVerificationLevelListener implements ServerChangeVerificationLevelListener {
    @Override
    public void onServerChangeVerificationLevel(ServerChangeVerificationLevelEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerVerificationLevelChangedEvent(e));
    }
}