package de.kleindev.loki.listeners.discord_internal.message;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.message.CachedMessageUnpinnedEvent;
import org.javacord.api.event.message.CachedMessageUnpinEvent;
import org.javacord.api.listener.message.CachedMessageUnpinListener;

public class ACachedMessageUnpinListener implements CachedMessageUnpinListener {
    @Override
    public void onCachedMessageUnpin(CachedMessageUnpinEvent e) {
        Loki.getInstance().getEventManager().callEvent(new CachedMessageUnpinnedEvent(e));
    }
}