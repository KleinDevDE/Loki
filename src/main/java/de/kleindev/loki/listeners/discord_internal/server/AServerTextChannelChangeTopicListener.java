package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.server.ServerTextChannelTopicChangedEvent;
import org.javacord.api.event.channel.server.text.ServerTextChannelChangeTopicEvent;
import org.javacord.api.listener.channel.server.text.ServerTextChannelChangeTopicListener;

public class AServerTextChannelChangeTopicListener implements ServerTextChannelChangeTopicListener {
    @Override
    public void onServerTextChannelChangeTopic(ServerTextChannelChangeTopicEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerTextChannelTopicChangedEvent(e));
    }
}