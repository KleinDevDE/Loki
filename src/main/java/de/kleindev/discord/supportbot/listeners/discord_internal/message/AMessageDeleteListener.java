package de.kleindev.discord.supportbot.listeners.discord_internal.message;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.message.MessageDeletedEvent;
import org.javacord.api.event.message.MessageDeleteEvent;
import org.javacord.api.listener.message.MessageDeleteListener;

public class AMessageDeleteListener implements MessageDeleteListener {
    @Override
    public void onMessageDelete(MessageDeleteEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new MessageDeletedEvent(e));
    }
}