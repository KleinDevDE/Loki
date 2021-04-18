package de.kleindev.discord.supportbot.listeners.discord_internal.message;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.message.CachedMessagePinnedEvent;
import org.javacord.api.event.message.CachedMessagePinEvent;
import org.javacord.api.listener.message.CachedMessagePinListener;

public class ACachedMessagePinListener implements CachedMessagePinListener {
    @Override
    public void onCachedMessagePin(CachedMessagePinEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new CachedMessagePinnedEvent(e));
    }
}