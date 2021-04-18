package de.kleindev.discord.supportbot.listeners.discord_internal.message;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.reaction.ReactionAddedEvent;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;

public class AReactionAddListener implements ReactionAddListener {
    @Override
    public void onReactionAdd(ReactionAddEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ReactionAddedEvent(e));
    }
}