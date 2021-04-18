package de.kleindev.discord.supportbot.listeners.discord_internal.channel;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.group.GroupChannelNameChangedEvent;
import org.javacord.api.event.channel.group.GroupChannelChangeNameEvent;
import org.javacord.api.listener.channel.group.GroupChannelChangeNameListener;

public class AGroupChannelChangeNameListener implements GroupChannelChangeNameListener {
    @Override
    public void onGroupChannelChangeName(GroupChannelChangeNameEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new GroupChannelNameChangedEvent(e));
    }
}