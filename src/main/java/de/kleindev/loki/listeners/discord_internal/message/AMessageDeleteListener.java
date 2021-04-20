package de.kleindev.loki.listeners.discord_internal.message;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.message.MessageDeletedEvent;
import org.javacord.api.event.message.MessageDeleteEvent;
import org.javacord.api.listener.message.MessageDeleteListener;

public class AMessageDeleteListener implements MessageDeleteListener {
    @Override
    public void onMessageDelete(MessageDeleteEvent e) {
        Loki.getInstance().getEventManager().callEvent(new MessageDeletedEvent(e));
    }
}