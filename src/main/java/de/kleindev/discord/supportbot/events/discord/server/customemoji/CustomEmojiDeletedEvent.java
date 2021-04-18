package de.kleindev.discord.supportbot.events.discord.server.customemoji;

import de.kleindev.discord.supportbot.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.emoji.KnownCustomEmoji;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.server.emoji.KnownCustomEmojiDeleteEvent;


public class CustomEmojiDeletedEvent extends Event {
    private KnownCustomEmoji knownCustomEmoji;
    private DiscordApi api;
    private Server server;

    public CustomEmojiDeletedEvent(KnownCustomEmojiDeleteEvent e){
        knownCustomEmoji = e.getEmoji();
        api = e.getApi();
        server = e.getServer();
    }

    public DiscordApi getApi() {
        return api;
    }

    public Server getServer() {
        return server;
    }

    public KnownCustomEmoji getKnownCustomEmoji() {
        return knownCustomEmoji;
    }
}
