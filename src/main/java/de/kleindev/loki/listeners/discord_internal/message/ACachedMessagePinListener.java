package de.kleindev.loki.listeners.discord_internal.message;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.message.CachedMessagePinnedEvent;
import org.javacord.api.event.message.CachedMessagePinEvent;
import org.javacord.api.listener.message.CachedMessagePinListener;

public class ACachedMessagePinListener implements CachedMessagePinListener {
    @Override
    public void onCachedMessagePin(CachedMessagePinEvent e) {
        Loki.getInstance().getEventManager().callEvent(new CachedMessagePinnedEvent(e));
    }
}