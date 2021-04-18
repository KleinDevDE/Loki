package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.server.customemoji.CustomEmojiCreatedEvent;
import org.javacord.api.event.server.emoji.KnownCustomEmojiCreateEvent;
import org.javacord.api.listener.server.emoji.KnownCustomEmojiCreateListener;

public class AKnownCustomEmojiCreateListener implements KnownCustomEmojiCreateListener {
    @Override
    public void onKnownCustomEmojiCreate(KnownCustomEmojiCreateEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new CustomEmojiCreatedEvent(e));
    }
}