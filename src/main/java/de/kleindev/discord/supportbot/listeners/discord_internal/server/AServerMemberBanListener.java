package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.server.ServerMemberBannedEvent;
import org.javacord.api.event.server.member.ServerMemberBanEvent;
import org.javacord.api.listener.server.member.ServerMemberBanListener;

public class AServerMemberBanListener implements ServerMemberBanListener {
    @Override
    public void onServerMemberBan(ServerMemberBanEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ServerMemberBannedEvent(e));
    }
}