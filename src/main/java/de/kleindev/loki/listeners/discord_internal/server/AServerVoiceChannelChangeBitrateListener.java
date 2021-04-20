package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.voice.VoiceChannelBitrateChangedEvent;
import org.javacord.api.event.channel.server.voice.ServerVoiceChannelChangeBitrateEvent;
import org.javacord.api.listener.channel.server.voice.ServerVoiceChannelChangeBitrateListener;

public class AServerVoiceChannelChangeBitrateListener implements ServerVoiceChannelChangeBitrateListener {
    @Override
    public void onServerVoiceChannelChangeBitrate(ServerVoiceChannelChangeBitrateEvent e) {
        Loki.getInstance().getEventManager().callEvent(new VoiceChannelBitrateChangedEvent(e));
    }
}