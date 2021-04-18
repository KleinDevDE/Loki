package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.server.ServerTextChannelTopicChangedEvent;
import org.javacord.api.event.channel.server.text.ServerTextChannelChangeTopicEvent;
import org.javacord.api.listener.channel.server.text.ServerTextChannelChangeTopicListener;

public class AServerTextChannelChangeTopicListener implements ServerTextChannelChangeTopicListener {
    @Override
    public void onServerTextChannelChangeTopic(ServerTextChannelChangeTopicEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerTextChannelTopicChangedEvent(e));
    }
}