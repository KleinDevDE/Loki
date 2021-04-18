package de.kleindev.discord.supportbot.events.discord.user;

import de.kleindev.discord.supportbot.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.user.UserChangeNicknameEvent;

import java.util.Optional;


public class UserNicknameChangedEvent extends Event {
	private Optional<String> newNickname;
	private Server server;
	private Optional<String> oldNickname;
	private DiscordApi api;
	private User user;

	public UserNicknameChangedEvent(UserChangeNicknameEvent javaCordEvent) {
		this.newNickname = javaCordEvent.getNewNickname();
		this.server = javaCordEvent.getServer();
		this.oldNickname = javaCordEvent.getOldNickname();
		this.api = javaCordEvent.getApi();
		this.user = javaCordEvent.getUser();
	}

	public Optional<String> getNewNickname() {
		return newNickname;
	}

	public Server getServer() {
		return server;
	}

	public Optional<String> getOldNickname() {
		return oldNickname;
	}

	public DiscordApi getApi() {
		return api;
	}

	public User getUser() {
		return user;
	}

}
