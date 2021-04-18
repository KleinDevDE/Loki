package de.kleindev.discord.supportbot.managers;

import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

public class PermissionManager {
    public boolean hasPermission(Server server, User user, String permission){
        for(Role role : user.getRoles(server)){
            if (role.getPermissions().getAllowedPermission().contains(PermissionType.ADMINISTRATOR)){
                return true;
            }
        }

        return false;
    }
}
