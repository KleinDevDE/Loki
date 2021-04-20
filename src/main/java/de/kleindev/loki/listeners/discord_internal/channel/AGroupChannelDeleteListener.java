package de.kleindev.loki.listeners.discord_internal.channel;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.group.GroupChannelDeletedEvent;
import org.javacord.api.event.channel.group.GroupChannelDeleteEvent;
import org.javacord.api.listener.channel.group.GroupChannelDeleteListener;

public class AGroupChannelDeleteListener implements GroupChannelDeleteListener {
    @Override
    public void onGroupChannelDelete(GroupChannelDeleteEvent e) {
        Loki.getInstance().getEventManager().callEvent(new GroupChannelDeletedEvent(e));
    }
}