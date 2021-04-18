package de.kleindev.discord.supportbot.listeners.discord_internal.server;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.server.customemoji.CustomEmojiWhitelistedRolesChangedEvent;
import org.javacord.api.event.server.emoji.KnownCustomEmojiChangeWhitelistedRolesEvent;
import org.javacord.api.listener.server.emoji.KnownCustomEmojiChangeWhitelistedRolesListener;

public class AKnownCustomEmojiChangeWhitelistedRolesListener implements KnownCustomEmojiChangeWhitelistedRolesListener {
    @Override
    public void onKnownCustomEmojiChangeWhitelistedRoles(KnownCustomEmojiChangeWhitelistedRolesEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new CustomEmojiWhitelistedRolesChangedEvent(e));
    }
}