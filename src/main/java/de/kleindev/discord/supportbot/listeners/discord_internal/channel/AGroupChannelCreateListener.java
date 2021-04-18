package de.kleindev.discord.supportbot.listeners.discord_internal.channel;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.group.GroupChannelCreatedEvent;
import org.javacord.api.event.channel.group.GroupChannelCreateEvent;
import org.javacord.api.listener.channel.group.GroupChannelCreateListener;

public class AGroupChannelCreateListener implements GroupChannelCreateListener {
    @Override
    public void onGroupChannelCreate(GroupChannelCreateEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new GroupChannelCreatedEvent(e));
    }
}