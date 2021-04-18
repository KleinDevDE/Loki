package de.kleindev.discord.supportbot.events.discord.user;

import de.kleindev.discord.supportbot.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.server.role.UserRoleAddEvent;


public class UserRoleAddedEvent extends Event {
	private Server server;
	private Role role;
	private DiscordApi api;
	private User user;

	public UserRoleAddedEvent(UserRoleAddEvent javaCordEvent) {
		this.server = javaCordEvent.getServer();
		this.role = javaCordEvent.getRole();
		this.api = javaCordEvent.getApi();
		this.user = javaCordEvent.getUser();
	}

	public Server getServer() {
		return server;
	}

	public Role getRole() {
		return role;
	}

	public DiscordApi getApi() {
		return api;
	}

	public User getUser() {
		return user;
	}

}
