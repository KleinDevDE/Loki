package de.kleindev.discord.supportbot.events.discord.user;

import de.kleindev.discord.supportbot.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.Activity;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.user.UserChangeActivityEvent;

import java.util.Optional;
import java.util.Set;


public class UserActivityChangedEvent extends Event {
	private Set<Activity> oldActivities;
	private Set<Activity> newActivities;
	private DiscordApi api;
	private User user;

	public UserActivityChangedEvent(UserChangeActivityEvent javaCordEvent) {
		this.oldActivities = javaCordEvent.getOldActivities();
		this.newActivities = javaCordEvent.getNewActivities();
		this.api = javaCordEvent.getApi();
		this.user = javaCordEvent.getUser().get();
	}

	public Set<Activity> getNewActivities() {
		return newActivities;
	}

	public Set<Activity> getOldActivities() {
		return oldActivities;
	}

	public DiscordApi getApi() {
		return api;
	}

	public User getUser() {
		return user;
	}

}
