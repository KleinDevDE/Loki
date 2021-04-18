package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.server.ServerTextChannelSlowmodeChangedEvent;
import org.javacord.api.event.channel.server.text.ServerTextChannelChangeSlowmodeEvent;
import org.javacord.api.listener.channel.server.text.ServerTextChannelChangeSlowmodeListener;

public class AServerTextChannelChangeSlowmodeListener implements ServerTextChannelChangeSlowmodeListener {
    @Override
    public void onServerTextChannelChangeSlowmodeDelay(ServerTextChannelChangeSlowmodeEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerTextChannelSlowmodeChangedEvent(e));
    }
}