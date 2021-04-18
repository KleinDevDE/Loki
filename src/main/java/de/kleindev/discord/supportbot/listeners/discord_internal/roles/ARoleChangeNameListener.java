package de.kleindev.discord.supportbot.listeners.discord_internal.roles;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.RoleChangedEvent;
import de.kleindev.discord.supportbot.events.discord.role.RoleNameChangedEvent;
import org.javacord.api.event.server.role.RoleChangeNameEvent;
import org.javacord.api.listener.server.role.RoleChangeNameListener;

public class ARoleChangeNameListener implements RoleChangeNameListener {
    @Override
    public void onRoleChangeName(RoleChangeNameEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        SupportBot.getInstance().getEventManager().callEvent(new RoleNameChangedEvent(e));
    }
}