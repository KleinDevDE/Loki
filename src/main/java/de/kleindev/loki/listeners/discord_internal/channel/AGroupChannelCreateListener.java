package de.kleindev.loki.listeners.discord_internal.channel;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.group.GroupChannelCreatedEvent;
import org.javacord.api.event.channel.group.GroupChannelCreateEvent;
import org.javacord.api.listener.channel.group.GroupChannelCreateListener;

public class AGroupChannelCreateListener implements GroupChannelCreateListener {
    @Override
    public void onGroupChannelCreate(GroupChannelCreateEvent e) {
        Loki.getInstance().getEventManager().callEvent(new GroupChannelCreatedEvent(e));
    }
}