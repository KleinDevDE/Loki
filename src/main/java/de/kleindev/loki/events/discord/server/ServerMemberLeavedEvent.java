package de.kleindev.loki.events.discord.server;

import de.kleindev.loki.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.server.member.ServerMemberLeaveEvent;


public class ServerMemberLeavedEvent extends Event {
    private DiscordApi api;
    private Server server;
    private User user;

    public ServerMemberLeavedEvent(ServerMemberLeaveEvent e) {
        api = e.getApi();
        server = e.getServer();
        user = e.getUser();
    }

    public DiscordApi getApi() {
        return api;
    }

    public Server getServer() {
        return server;
    }

    public User getUser() {
        return user;
    }
}
