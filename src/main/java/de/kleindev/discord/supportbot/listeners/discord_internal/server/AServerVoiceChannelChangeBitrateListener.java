package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.voice.VoiceChannelBitrateChangedEvent;
import org.javacord.api.event.channel.server.voice.ServerVoiceChannelChangeBitrateEvent;
import org.javacord.api.listener.channel.server.voice.ServerVoiceChannelChangeBitrateListener;

public class AServerVoiceChannelChangeBitrateListener implements ServerVoiceChannelChangeBitrateListener {
    @Override
    public void onServerVoiceChannelChangeBitrate(ServerVoiceChannelChangeBitrateEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new VoiceChannelBitrateChangedEvent(e));
    }
}