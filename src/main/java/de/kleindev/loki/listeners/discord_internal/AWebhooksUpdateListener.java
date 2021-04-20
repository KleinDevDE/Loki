package de.kleindev.loki.listeners.discord_internal;


import de.kleindev.loki.Loki;
import org.javacord.api.event.channel.server.text.WebhooksUpdateEvent;
import org.javacord.api.listener.channel.server.text.WebhooksUpdateListener;

public class AWebhooksUpdateListener implements WebhooksUpdateListener {
    @Override
    public void onWebhooksUpdate(WebhooksUpdateEvent e) {
        Loki.getInstance().getEventManager().callEvent(new de.kleindev.loki.events.discord.channels.WebhooksUpdateEvent(e));
    }
}