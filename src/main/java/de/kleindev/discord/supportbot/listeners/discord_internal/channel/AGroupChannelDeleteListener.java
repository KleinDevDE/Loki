package de.kleindev.discord.supportbot.listeners.discord_internal.channel;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.group.GroupChannelDeletedEvent;
import org.javacord.api.event.channel.group.GroupChannelDeleteEvent;
import org.javacord.api.listener.channel.group.GroupChannelDeleteListener;

public class AGroupChannelDeleteListener implements GroupChannelDeleteListener {
    @Override
    public void onGroupChannelDelete(GroupChannelDeleteEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new GroupChannelDeletedEvent(e));
    }
}