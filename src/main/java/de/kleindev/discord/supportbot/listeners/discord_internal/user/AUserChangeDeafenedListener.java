package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserDeafenChangedEvent;
import org.javacord.api.event.user.UserChangeDeafenedEvent;
import org.javacord.api.listener.user.UserChangeDeafenedListener;

public class AUserChangeDeafenedListener implements UserChangeDeafenedListener {
    @Override
    public void onUserChangeDeafened(UserChangeDeafenedEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserDeafenChangedEvent(e));
    }
}