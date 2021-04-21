package de.kleindev.loki.listeners.discord_internal.message;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.message.MessageEditedEvent;
import org.javacord.api.event.message.MessageEditEvent;
import org.javacord.api.listener.message.MessageEditListener;

public class AMessageEditListener implements MessageEditListener {
    @Override
    public void onMessageEdit(MessageEditEvent e) {
        Loki.getInstance().getEventManager().callEvent(new MessageEditedEvent(e));
    }
}