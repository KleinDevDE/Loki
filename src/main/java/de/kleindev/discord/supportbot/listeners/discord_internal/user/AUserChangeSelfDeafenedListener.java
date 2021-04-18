package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserSelfDeafenChangedEvent;
import org.javacord.api.event.user.UserChangeSelfDeafenedEvent;
import org.javacord.api.listener.user.UserChangeSelfDeafenedListener;

public class AUserChangeSelfDeafenedListener implements UserChangeSelfDeafenedListener {
    @Override
    public void onUserChangeSelfDeafened(UserChangeSelfDeafenedEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserSelfDeafenChangedEvent(e));
    }
}