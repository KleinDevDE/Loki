package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.voice.VoiceChannelUserLimitChangedEvent;
import org.javacord.api.event.channel.server.voice.ServerVoiceChannelChangeUserLimitEvent;
import org.javacord.api.listener.channel.server.voice.ServerVoiceChannelChangeUserLimitListener;

public class AServerVoiceChannelChangeUserLimitListener implements ServerVoiceChannelChangeUserLimitListener {
    @Override
    public void onServerVoiceChannelChangeUserLimit(ServerVoiceChannelChangeUserLimitEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new VoiceChannelUserLimitChangedEvent(e));
    }
}