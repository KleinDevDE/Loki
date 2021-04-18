package de.kleindev.discord.supportbot.listeners.discord_internal.user;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.user.UserNameChangedEvent;
import org.javacord.api.event.user.UserChangeNameEvent;
import org.javacord.api.listener.user.UserChangeNameListener;

public class AUserChangeNameListener implements UserChangeNameListener {
    @Override
    public void onUserChangeName(UserChangeNameEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new UserNameChangedEvent(e));
    }
}