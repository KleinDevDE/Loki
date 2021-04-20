package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.voice.VoiceChannelUserLimitChangedEvent;
import org.javacord.api.event.channel.server.voice.ServerVoiceChannelChangeUserLimitEvent;
import org.javacord.api.listener.channel.server.voice.ServerVoiceChannelChangeUserLimitListener;

public class AServerVoiceChannelChangeUserLimitListener implements ServerVoiceChannelChangeUserLimitListener {
    @Override
    public void onServerVoiceChannelChangeUserLimit(ServerVoiceChannelChangeUserLimitEvent e) {
        Loki.getInstance().getEventManager().callEvent(new VoiceChannelUserLimitChangedEvent(e));
    }
}