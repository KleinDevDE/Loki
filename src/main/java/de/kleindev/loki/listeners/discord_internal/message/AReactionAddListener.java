package de.kleindev.loki.listeners.discord_internal.message;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.reaction.ReactionAddedEvent;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;

public class AReactionAddListener implements ReactionAddListener {
    @Override
    public void onReactionAdd(ReactionAddEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ReactionAddedEvent(e));
    }
}