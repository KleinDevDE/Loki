package de.kleindev.loki.events.discord.channels.serverchannel;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.channel.server.ServerChannelCreateEvent;


public class ServerChannelCreatedEvent extends Event {
    private DiscordApi api;
    private ServerChannel serverChannel;
    private Server server;

    public ServerChannelCreatedEvent(ServerChannelCreateEvent e){
        api = e.getApi();
        serverChannel = e.getChannel();
        server = e.getServer();
    }

    public DiscordApi getApi() {
        return api;
    }

    public Server getServer() {
        return server;
    }

    public ServerChannel getServerChannel() {
        return serverChannel;
    }
}
