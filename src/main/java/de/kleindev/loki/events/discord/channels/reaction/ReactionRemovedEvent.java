package de.kleindev.loki.events.discord.channels.reaction;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.GroupChannel;
import org.javacord.api.entity.channel.PrivateChannel;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAttachment;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.Reaction;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


public class ReactionRemovedEvent extends Event {
    private DiscordApi api;
    private TextChannel textChannel;
    private User user;
    private Emoji emoji;
    private CompletableFuture<List<User>> users;
    private Optional<Reaction> reaction;
    private Optional<Message> message;
    private long messageID;
    private Optional<List<MessageAttachment>> messageAttachments;
    private Optional<String> messageContent;
    private Optional<String> readableMessageContent;
    private Optional<MessageAuthor> messageAuthor;
    private Optional<Integer> count;
    private Optional<Server> server;
    private Optional<GroupChannel> groupChannel;
    private Optional<PrivateChannel> privateChannel;
    private Optional<ServerTextChannel> serverTextChannel;

    public ReactionRemovedEvent(ReactionRemoveEvent e) {
        api = e.getApi();
        textChannel = e.getChannel();
        users = e.getUsers();
        reaction = e.getReaction();
        user = e.getUser().get();
        emoji = e.getEmoji();
        message = e.getMessage();
        messageID = e.getMessageId();
        messageAttachments = e.getMessageAttachments();
        messageContent = e.getMessageContent();
        readableMessageContent = e.getReadableMessageContent();
        messageAuthor = e.getMessageAuthor();
        count = e.getCount();
        server = e.getServer();
        groupChannel = e.getGroupChannel();
        privateChannel = e.getPrivateChannel();
        serverTextChannel = e.getServerTextChannel();
    }

    public DiscordApi getApi() {
        return api;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }

    public CompletableFuture<List<User>> getUsers() {
        return users;
    }

    public User getUser() {
        return user;
    }

    public Emoji getEmoji() {
        return emoji;
    }

    public long getMessageID() {
        return messageID;
    }

    public Optional<Integer> getCount() {
        return count;
    }

    public Optional<ServerTextChannel> getServerTextChannel() {
        return serverTextChannel;
    }

    public Optional<PrivateChannel> getPrivateChannel() {
        return privateChannel;
    }

    public Optional<GroupChannel> getGroupChannel() {
        return groupChannel;
    }

    public Optional<List<MessageAttachment>> getMessageAttachments() {
        return messageAttachments;
    }

    public Optional<Message> getMessage() {
        return message;
    }

    public Optional<MessageAuthor> getMessageAuthor() {
        return messageAuthor;
    }

    public Optional<Reaction> getReaction() {
        return reaction;
    }

    public Optional<Server> getServer() {
        return server;
    }

    public Optional<String> getMessageContent() {
        return messageContent;
    }

    public Optional<String> getReadableMessageContent() {
        return readableMessageContent;
    }
}
