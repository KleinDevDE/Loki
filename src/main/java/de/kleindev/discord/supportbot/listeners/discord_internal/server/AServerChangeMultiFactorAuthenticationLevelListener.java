package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerMultiFactorAuthenticationLevelChangedEvent;
import org.javacord.api.event.server.ServerChangeMultiFactorAuthenticationLevelEvent;
import org.javacord.api.listener.server.ServerChangeMultiFactorAuthenticationLevelListener;

public class AServerChangeMultiFactorAuthenticationLevelListener implements ServerChangeMultiFactorAuthenticationLevelListener {
    @Override
    public void onServerChangeMultiFactorAuthenticationLevel(ServerChangeMultiFactorAuthenticationLevelEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerMultiFactorAuthenticationLevelChangedEvent(e));
    }
}