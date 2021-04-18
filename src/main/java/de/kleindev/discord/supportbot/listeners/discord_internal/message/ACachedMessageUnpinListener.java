package de.kleindev.discord.supportbot.listeners.discord_internal.message;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.message.CachedMessageUnpinnedEvent;
import org.javacord.api.event.message.CachedMessageUnpinEvent;
import org.javacord.api.listener.message.CachedMessageUnpinListener;

public class ACachedMessageUnpinListener implements CachedMessageUnpinListener {
    @Override
    public void onCachedMessageUnpin(CachedMessageUnpinEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new CachedMessageUnpinnedEvent(e));
    }
}