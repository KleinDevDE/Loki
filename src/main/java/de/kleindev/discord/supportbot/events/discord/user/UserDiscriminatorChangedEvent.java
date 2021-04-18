package de.kleindev.discord.supportbot.events.discord.user;

import de.kleindev.discord.supportbot.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.user.UserChangeDiscriminatorEvent;


public class UserDiscriminatorChangedEvent extends Event {
	private String oldDiscriminator;
	private String newDiscriminator;
	private DiscordApi api;
	private User user;

	public UserDiscriminatorChangedEvent(UserChangeDiscriminatorEvent javaCordEvent) {
		this.oldDiscriminator = javaCordEvent.getOldDiscriminator();
		this.newDiscriminator = javaCordEvent.getNewDiscriminator();
		this.api = javaCordEvent.getApi();
		this.user = javaCordEvent.getUser();
	}

	public String getOldDiscriminator() {
		return oldDiscriminator;
	}

	public String getNewDiscriminator() {
		return newDiscriminator;
	}

	public DiscordApi getApi() {
		return api;
	}

	public User getUser() {
		return user;
	}

	
}
