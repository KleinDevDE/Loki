package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.server.customemoji.CustomEmojiDeletedEvent;
import org.javacord.api.event.server.emoji.KnownCustomEmojiDeleteEvent;
import org.javacord.api.listener.server.emoji.KnownCustomEmojiDeleteListener;

public class AKnownCustomEmojiDeleteListener implements KnownCustomEmojiDeleteListener {
    @Override
    public void onKnownCustomEmojiDelete(KnownCustomEmojiDeleteEvent e) {
        Loki.getInstance().getEventManager().callEvent(new CustomEmojiDeletedEvent(e));
    }
}