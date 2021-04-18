package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.server.customemoji.CustomEmojiNameChangedEvent;
import org.javacord.api.event.server.emoji.KnownCustomEmojiChangeNameEvent;
import org.javacord.api.listener.server.emoji.KnownCustomEmojiChangeNameListener;

public class AKnownCustomEmojiChangeNameListener implements KnownCustomEmojiChangeNameListener {
    @Override
    public void onKnownCustomEmojiChangeName(KnownCustomEmojiChangeNameEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new CustomEmojiNameChangedEvent(e));
    }
}