package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.ServerChangedEvent;
import de.kleindev.discord.supportbot.events.discord.server.ServerDefaultMessageNotificationLevelChangedEvent;
import org.javacord.api.event.server.ServerChangeDefaultMessageNotificationLevelEvent;
import org.javacord.api.listener.server.ServerChangeDefaultMessageNotificationLevelListener;

public class AServerChangeDefaultMessageNotificationLevelListener implements ServerChangeDefaultMessageNotificationLevelListener {
    @Override
    public void onServerChangeDefaultMessageNotificationLevel(ServerChangeDefaultMessageNotificationLevelEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        SupportBot.getInstance().getEventManager().callEvent(new ServerDefaultMessageNotificationLevelChangedEvent(e));
    }
}