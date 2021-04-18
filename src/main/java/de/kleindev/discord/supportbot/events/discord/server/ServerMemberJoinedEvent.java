package de.kleindev.discord.supportbot.events.discord.server;

import de.kleindev.discord.supportbot.events.Event;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.server.member.ServerMemberJoinEvent;


public class ServerMemberJoinedEvent extends Event {
    private DiscordApi api;
    private Server server;
    private User user;

    public ServerMemberJoinedEvent(ServerMemberJoinEvent e){
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
