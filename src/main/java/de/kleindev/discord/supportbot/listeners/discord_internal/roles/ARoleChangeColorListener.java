package de.kleindev.discord.supportbot.listeners.discord_internal.roles;


import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.events.discord.RoleChangedEvent;
import de.kleindev.discord.supportbot.events.discord.role.RoleColorChangedEvent;
import org.javacord.api.event.server.role.RoleChangeColorEvent;
import org.javacord.api.listener.server.role.RoleChangeColorListener;

public class ARoleChangeColorListener implements RoleChangeColorListener {
    @Override
    public void onRoleChangeColor(RoleChangeColorEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new RoleChangedEvent(e.getApi(), e.getServer(), e.getRole()));
        SupportBot.getInstance().getEventManager().callEvent(new RoleColorChangedEvent(e));
    }
}