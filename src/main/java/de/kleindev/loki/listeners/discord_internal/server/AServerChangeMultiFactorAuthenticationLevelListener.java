package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerMultiFactorAuthenticationLevelChangedEvent;
import org.javacord.api.event.server.ServerChangeMultiFactorAuthenticationLevelEvent;
import org.javacord.api.listener.server.ServerChangeMultiFactorAuthenticationLevelListener;

public class AServerChangeMultiFactorAuthenticationLevelListener implements ServerChangeMultiFactorAuthenticationLevelListener {
    @Override
    public void onServerChangeMultiFactorAuthenticationLevel(ServerChangeMultiFactorAuthenticationLevelEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerMultiFactorAuthenticationLevelChangedEvent(e));
    }
}