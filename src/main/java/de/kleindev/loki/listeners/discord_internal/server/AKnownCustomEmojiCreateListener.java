package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.server.customemoji.CustomEmojiCreatedEvent;
import org.javacord.api.event.server.emoji.KnownCustomEmojiCreateEvent;
import org.javacord.api.listener.server.emoji.KnownCustomEmojiCreateListener;

public class AKnownCustomEmojiCreateListener implements KnownCustomEmojiCreateListener {
    @Override
    public void onKnownCustomEmojiCreate(KnownCustomEmojiCreateEvent e) {
        Loki.getInstance().getEventManager().callEvent(new CustomEmojiCreatedEvent(e));
    }
}