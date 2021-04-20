package de.kleindev.loki.listeners.discord_internal.message;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.channels.reaction.ReactionAllRemovedEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveAllEvent;
import org.javacord.api.listener.message.reaction.ReactionRemoveAllListener;

public class AReactionRemoveAllListener implements ReactionRemoveAllListener {
    @Override
    public void onReactionRemoveAll(ReactionRemoveAllEvent e) {
        Loki.getInstance().getEventManager().callEvent(new ReactionAllRemovedEvent(e));
    }
}