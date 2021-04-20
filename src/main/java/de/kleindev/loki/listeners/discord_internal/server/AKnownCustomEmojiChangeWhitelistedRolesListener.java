package de.kleindev.loki.listeners.discord_internal.server;


import de.kleindev.loki.Loki;
import de.kleindev.loki.events.discord.server.customemoji.CustomEmojiWhitelistedRolesChangedEvent;
import org.javacord.api.event.server.emoji.KnownCustomEmojiChangeWhitelistedRolesEvent;
import org.javacord.api.listener.server.emoji.KnownCustomEmojiChangeWhitelistedRolesListener;

public class AKnownCustomEmojiChangeWhitelistedRolesListener implements KnownCustomEmojiChangeWhitelistedRolesListener {
    @Override
    public void onKnownCustomEmojiChangeWhitelistedRoles(KnownCustomEmojiChangeWhitelistedRolesEvent e) {
        Loki.getInstance().getEventManager().callEvent(new CustomEmojiWhitelistedRolesChangedEvent(e));
    }
}