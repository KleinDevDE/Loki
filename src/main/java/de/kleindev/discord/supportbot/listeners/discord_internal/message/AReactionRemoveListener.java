package de.kleindev.discord.supportbot.listeners.discord_internal.message;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.reaction.ReactionRemovedEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

public class AReactionRemoveListener implements ReactionRemoveListener {
    @Override
    public void onReactionRemove(ReactionRemoveEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ReactionRemovedEvent(e));
    }
}