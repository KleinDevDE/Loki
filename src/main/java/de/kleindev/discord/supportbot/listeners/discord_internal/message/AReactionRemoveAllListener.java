package de.kleindev.discord.supportbot.listeners.discord_internal.message;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.channels.reaction.ReactionAllRemovedEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveAllEvent;
import org.javacord.api.listener.message.reaction.ReactionRemoveAllListener;

public class AReactionRemoveAllListener implements ReactionRemoveAllListener {
    @Override
    public void onReactionRemoveAll(ReactionRemoveAllEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new ReactionAllRemovedEvent(e));
    }
}