package de.kleindev.loki.config;

import java.io.File;

public class LokiConfiguration extends Config {
    private File file;

    public LokiConfiguration(File file) {
        this.file = file;
        load();
    }

    @ConfigPath(path = "discord.bot-token")
    public String discordBotToken = "XXX_TOKEN_XXX";
    @ConfigPath(path = "discord.nickname")
    public String discordNickname = "Loki";
    @ConfigPath(path = "discord.commandPrefix")
    public String commandPrefix = "!";
    @ConfigPath(path = "discord.status.type", description = "LISTENING | PLAYING | STREAMING | WATCHING")
    public String discordStatusType = "PLAYING";
    @ConfigPath(path = "discord.status.message", description = "Placeholders: %version%")
    public String discordStatusMessage = "v%version%";

    @ConfigPath(path = "mysql.hostname")
    public String mysqlHostname = "127.0.0.1";
    @ConfigPath(path = "mysql.port")
    public int mysqlPort = 3306;
    @ConfigPath(path = "mysql.database")
    public String mysqlDatabase = "";
    @ConfigPath(path = "mysql.username")
    public String mysqlUsername = "";
    @ConfigPath(path = "mysql.password")
    public String mysqlPassword = "";

    @ConfigPath(path = "application.logType", description = "Normal | Debug | Trace\nNormal = The normal things (info, error, warn, fatal, update)\nDebug = Normal + Debug output\nTrace = Debug + Trace output")
    public String logType = "normal";
    @ConfigPath(path = "application.pluginsFolder")
    public String pluginsFolder = "plugins";
    @ConfigPath(path = "application.logsFolder")
    public String logsFolder = "logs";

    @Override
    public File getFile() {
        return file;
    }
}
