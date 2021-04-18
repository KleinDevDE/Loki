package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.server.customemoji.CustomEmojiDeletedEvent;
import org.javacord.api.event.server.emoji.KnownCustomEmojiDeleteEvent;
import org.javacord.api.listener.server.emoji.KnownCustomEmojiDeleteListener;

public class AKnownCustomEmojiDeleteListener implements KnownCustomEmojiDeleteListener {
    @Override
    public void onKnownCustomEmojiDelete(KnownCustomEmojiDeleteEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new CustomEmojiDeletedEvent(e));
    }
}