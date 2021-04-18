package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.voice.VoiceChannelMemberJoinedEvent;
import org.javacord.api.event.channel.server.voice.ServerVoiceChannelMemberJoinEvent;
import org.javacord.api.listener.channel.server.voice.ServerVoiceChannelMemberJoinListener;

public class AServerVoiceChannelMemberJoinListener implements ServerVoiceChannelMemberJoinListener {
    @Override
    public void onServerVoiceChannelMemberJoin(ServerVoiceChannelMemberJoinEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new VoiceChannelMemberJoinedEvent(e));
    }
}