package de.kleindev.discord.supportbot;

import de.kleindev.discord.supportbot.config.SupportBotConfiguration;
import de.kleindev.discord.supportbot.managers.CommandManager;
import de.kleindev.discord.supportbot.managers.EventManager;
import de.kleindev.discord.supportbot.managers.PermissionManager;
import de.kleindev.discord.supportbot.utils.MySQLConnection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.javacord.api.DiscordApi;

import java.util.HashMap;

public class SupportBot {
    private static SupportBot INSTANCE;
    @Getter @Setter(AccessLevel.PROTECTED) private DiscordApi discordApi;

    // Managers
    @Getter private final EventManager eventManager;
    @Getter private final CommandManager commandManager;
//    @Getter private final DatabaseManager databaseManager;
    @Getter private final PermissionManager permissionManager;
    @Getter private final SupportBotConfiguration supportBotConfiguration;
    private final HashMap<String, MySQLConnection> mySQLConnectionHashMap = new HashMap<>();


    public SupportBot(SupportBotConfiguration supportBotConfiguration){
        this.discordApi = discordApi;
        this.supportBotConfiguration = supportBotConfiguration;
        SupportBot.INSTANCE = this;
        this.eventManager = new EventManager();
        this.commandManager = new CommandManager();
//        this.databaseManager = new DatabaseManager(supportBotConfiguration.mysqlHostname, supportBotConfiguration.mysqlPort, supportBotConfiguration.mysqlDatabase, supportBotConfiguration.mysqlUsername, supportBotConfiguration.mysqlPassword);
        this.permissionManager = new PermissionManager();
    }

    public static SupportBot getInstance() {
        return SupportBot.INSTANCE;
    }

    public void registerMySQLConnection(String name, MySQLConnection mySQLConnection) {
        this.mySQLConnectionHashMap.put(name, mySQLConnection);
    }

    public MySQLConnection getMySQLConnection(String name) {
        return mySQLConnectionHashMap.get(name);
    }
}
