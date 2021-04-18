package de.kleindev.discord.supportbot.events;

import de.kleindev.discord.supportbot.events.Event;

public class CriticalProcessTimeEvent extends Event {

	long millis;
	Event e;
	public boolean shouldBroadcast = true;
	
	public CriticalProcessTimeEvent(Event e, long millis) {
		this.e = e;
		this.millis = millis;
	}
	
	public boolean shouldBroadcast() {
		return shouldBroadcast;
	}
	
	public void setShouldBroadcast(boolean shouldBroadcast) {
		this.shouldBroadcast = shouldBroadcast;
	}
	
	public Event getEvent() {
		return e;
	}
	
	public long getTime() {
		return millis;
	}
	
}
