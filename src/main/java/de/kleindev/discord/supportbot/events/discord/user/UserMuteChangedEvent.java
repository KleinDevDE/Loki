package de.kleindev.discord.supportbot.events.discord.user;

import de.kleindev.discord.supportbot.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.user.UserChangeMutedEvent;


public class UserMuteChangedEvent extends Event {
	private Server server;
	private DiscordApi api;
	private User user;

	public UserMuteChangedEvent(UserChangeMutedEvent javaCordEvent) {
		this.server = javaCordEvent.getServer();
		this.api = javaCordEvent.getApi();
		this.user = javaCordEvent.getUser();
	}

	public Server getServer() {
		return server;
	}

	public DiscordApi getApi() {
		return api;
	}

	public User getUser() {
		return user;
	}

}
