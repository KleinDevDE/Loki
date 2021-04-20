package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.server.ServerTextChannelSlowmodeChangedEvent;
import org.javacord.api.event.channel.server.text.ServerTextChannelChangeSlowmodeEvent;
import org.javacord.api.listener.channel.server.text.ServerTextChannelChangeSlowmodeListener;

public class AServerTextChannelChangeSlowmodeListener implements ServerTextChannelChangeSlowmodeListener {
    @Override
    public void onServerTextChannelChangeSlowmodeDelay(ServerTextChannelChangeSlowmodeEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerTextChannelSlowmodeChangedEvent(e));
    }
}