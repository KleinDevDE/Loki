package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.ServerChangedEvent;
import de.kleindev.loki.events.discord.server.ServerDefaultMessageNotificationLevelChangedEvent;
import org.javacord.api.event.server.ServerChangeDefaultMessageNotificationLevelEvent;
import org.javacord.api.listener.server.ServerChangeDefaultMessageNotificationLevelListener;

public class AServerChangeDefaultMessageNotificationLevelListener implements ServerChangeDefaultMessageNotificationLevelListener {
    @Override
    public void onServerChangeDefaultMessageNotificationLevel(ServerChangeDefaultMessageNotificationLevelEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerChangedEvent());
        Loki.getInstance().getEventManager().callEvent(new ServerDefaultMessageNotificationLevelChangedEvent(e));
    }
}