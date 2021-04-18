package de.kleindev.discord.supportbot.listeners.discord_internal;


import de.kleindev.discord.supportbot.SupportBot;
import org.javacord.api.event.channel.server.text.WebhooksUpdateEvent;
import org.javacord.api.listener.channel.server.text.WebhooksUpdateListener;

public class AWebhooksUpdateListener implements WebhooksUpdateListener {
    @Override
    public void onWebhooksUpdate(WebhooksUpdateEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new de.kleindev.discord.supportbot.events.discord.channels.WebhooksUpdateEvent(e));
    }
}