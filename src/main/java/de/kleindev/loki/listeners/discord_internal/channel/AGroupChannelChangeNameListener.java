package de.kleindev.loki.listeners.discord_internal.channel;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.group.GroupChannelNameChangedEvent;
import org.javacord.api.event.channel.group.GroupChannelChangeNameEvent;
import org.javacord.api.listener.channel.group.GroupChannelChangeNameListener;

public class AGroupChannelChangeNameListener implements GroupChannelChangeNameListener {
    @Override
    public void onGroupChannelChangeName(GroupChannelChangeNameEvent e) {
        Loki.getInstance().getEventManager().callEvent(new GroupChannelNameChangedEvent(e));
    }
}