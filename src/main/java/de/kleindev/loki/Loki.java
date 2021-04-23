package de.kleindev.loki;

import de.kleindev.loki.config.LokiConfiguration;
import de.kleindev.loki.managers.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;

public class Loki {
    private static Loki INSTANCE;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private DiscordApi discordApi;

    // Managers
    @Getter
    private final EventManager eventManager;
    @Getter
    private final PluginManager pluginManager;
    @Getter
    private final CommandManager commandManager;
    @Getter
    private final DatabaseManager databaseManager;
    @Getter
    private final PermissionManager permissionManager;
    @Getter
    private final LokiConfiguration lokiConfiguration;


    public Loki(LokiConfiguration lokiConfiguration) {
        Loki.INSTANCE = this;
        this.lokiConfiguration = lokiConfiguration;
        this.eventManager = new EventManager();
        this.commandManager = new CommandManager();
        this.databaseManager = new DatabaseManager();
        this.permissionManager = new PermissionManager();
        this.pluginManager = new PluginManager();
    }

    public static Loki getInstance() {
        return Loki.INSTANCE;
    }

    public void reload() {
        Loki.getInstance().getDiscordApi().updateStatus(UserStatus.IDLE);
        Loki.getInstance().getDiscordApi().updateActivity(ActivityType.PLAYING, "Reloading...");

        Loki.getInstance().getLokiConfiguration().reload();
        Loki.getInstance().getPluginManager().getActivePlugins().forEach(plugin -> Loki.getInstance().getPluginManager().reloadPlugin(plugin));
        //TODO Reconnect every MySQLConnection

        Loki.getInstance().getDiscordApi().updateStatus(UserStatus.ONLINE);
        Loki.getInstance().getDiscordApi().updateActivity(ActivityType.PLAYING, Main.VERSION);
    }
}
