package de.kleindev.loki.events.discord.channels.voice;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.channel.server.voice.ServerVoiceChannelChangeBitrateEvent;


public class VoiceChannelBitrateChangedEvent extends Event {
	private Server server;
	private int newBitrate;
	private ServerVoiceChannel channel;
	private int oldBitrate;
	private DiscordApi api;

	public VoiceChannelBitrateChangedEvent(ServerVoiceChannelChangeBitrateEvent javaCordEvent) {
		this.server = javaCordEvent.getServer();
		this.newBitrate = javaCordEvent.getNewBitrate();
		this.channel = javaCordEvent.getChannel();
		this.oldBitrate = javaCordEvent.getOldBitrate();
		this.api = javaCordEvent.getApi();
	}

	public Server getServer() {
		return server;
	}

	public int getNewBitrate() {
		return newBitrate;
	}

	public ServerVoiceChannel getChannel() {
		return channel;
	}

	public int getOldBitrate() {
		return oldBitrate;
	}

	public DiscordApi getApi() {
		return api;
	}

}
