package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.server.ServerMemberBannedEvent;
import org.javacord.api.event.server.member.ServerMemberBanEvent;
import org.javacord.api.listener.server.member.ServerMemberBanListener;

public class AServerMemberBanListener implements ServerMemberBanListener {
    @Override
    public void onServerMemberBan(ServerMemberBanEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ServerMemberBannedEvent(e));
    }
}