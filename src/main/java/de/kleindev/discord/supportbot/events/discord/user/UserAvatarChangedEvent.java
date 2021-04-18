package de.kleindev.discord.supportbot.events.discord.user;

import de.kleindev.discord.supportbot.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.user.UserChangeAvatarEvent;


public class UserAvatarChangedEvent extends Event {
	private Icon oldAvatar;
	private Icon newAvatar;
	private DiscordApi api;
	private User user;

	public UserAvatarChangedEvent(UserChangeAvatarEvent javaCordEvent) {
		this.oldAvatar = javaCordEvent.getOldAvatar();
		this.newAvatar = javaCordEvent.getNewAvatar();
		this.api = javaCordEvent.getApi();
		this.user = javaCordEvent.getUser();
	}

	public Icon getOldAvatar() {
		return oldAvatar;
	}

	public Icon getNewAvatar() {
		return newAvatar;
	}

	public DiscordApi getApi() {
		return api;
	}

	public User getUser() {
		return user;
	}

}
