package de.kleindev.loki.events.discord.user;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.GroupChannel;
import org.javacord.api.entity.channel.PrivateChannel;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.user.UserStartTypingEvent;

import java.util.Optional;


public class UserStartedTypingEvent extends Event {
	private Optional<GroupChannel> groupChannel;
	private TextChannel channel;
	private Optional<PrivateChannel> privateChannel;
	private DiscordApi api;
	private User user;
	private Optional<ServerTextChannel> serverTextChannel;

	public UserStartedTypingEvent(UserStartTypingEvent javaCordEvent) {
		this.groupChannel = javaCordEvent.getGroupChannel();
		this.channel = javaCordEvent.getChannel();
		this.privateChannel = javaCordEvent.getPrivateChannel();
		this.api = javaCordEvent.getApi();
		this.user = javaCordEvent.getUser().get();
		this.serverTextChannel = javaCordEvent.getServerTextChannel();
	}

	public Optional<GroupChannel> getGroupChannel() {
		return groupChannel;
	}

	public TextChannel getChannel() {
		return channel;
	}

	public Optional<PrivateChannel> getPrivateChannel() {
		return privateChannel;
	}

	public DiscordApi getApi() {
		return api;
	}

	public User getUser() {
		return user;
	}

	public Optional<ServerTextChannel> getServerTextChannel() {
		return serverTextChannel;
	}

}
