package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.server.ServerMemberUnbannedEvent;
import org.javacord.api.event.server.member.ServerMemberUnbanEvent;
import org.javacord.api.listener.server.member.ServerMemberUnbanListener;

public class AServerMemberUnbanListener implements ServerMemberUnbanListener {
    @Override
    public void onServerMemberUnban(ServerMemberUnbanEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerMemberUnbannedEvent(e));
    }
}