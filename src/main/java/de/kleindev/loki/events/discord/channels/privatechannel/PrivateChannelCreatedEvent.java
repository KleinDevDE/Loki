package de.kleindev.loki.events.discord.channels.privatechannel;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.channel.user.PrivateChannelCreateEvent;


public class PrivateChannelCreatedEvent extends Event {
    private DiscordApi api;
    private TextChannel textChannel;
    private User user;

    public PrivateChannelCreatedEvent(PrivateChannelCreateEvent e) {
        api = e.getApi();
        textChannel = e.getChannel();
        user = e.getRecipient().get();
    }

    public DiscordApi getApi() {
        return api;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }

    public User getUser() {
        return user;
    }
}
