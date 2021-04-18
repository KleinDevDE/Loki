package de.kleindev.discord.supportbot.events.discord.user;

import de.kleindev.discord.supportbot.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.user.UserChangeNameEvent;


public class UserNameChangedEvent extends Event {
	private String newName;
	private String oldName;
	private DiscordApi api;
	private User user;

	public UserNameChangedEvent(UserChangeNameEvent javaCordEvent) {
		this.newName = javaCordEvent.getNewName();
		this.oldName = javaCordEvent.getOldName();
		this.api = javaCordEvent.getApi();
		this.user = javaCordEvent.getUser();
	}

	public String getNewName() {
		return newName;
	}

	public String getOldName() {
		return oldName;
	}

	public DiscordApi getApi() {
		return api;
	}

	public User getUser() {
		return user;
	}

}
