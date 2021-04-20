package de.kleindev.loki.listeners.discord_internal.message;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.reaction.ReactionRemovedEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

public class AReactionRemoveListener implements ReactionRemoveListener {
    @Override
    public void onReactionRemove(ReactionRemoveEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ReactionRemovedEvent(e));
    }
}