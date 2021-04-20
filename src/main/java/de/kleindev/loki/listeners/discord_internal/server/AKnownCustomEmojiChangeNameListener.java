package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.server.customemoji.CustomEmojiNameChangedEvent;
import org.javacord.api.event.server.emoji.KnownCustomEmojiChangeNameEvent;
import org.javacord.api.listener.server.emoji.KnownCustomEmojiChangeNameListener;

public class AKnownCustomEmojiChangeNameListener implements KnownCustomEmojiChangeNameListener {
    @Override
    public void onKnownCustomEmojiChangeName(KnownCustomEmojiChangeNameEvent e) {
        Loki.getInstance().getEventManager().callEvent(new CustomEmojiNameChangedEvent(e));
    }
}