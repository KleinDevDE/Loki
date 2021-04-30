package de.kleindev.loki.managers;

import de.kleindev.loki.Loki;
import de.kleindev.loki.utils.MySQLConnection;

import java.util.HashMap;

public class DatabaseManager {
    private HashMap<String, MySQLConnection> mySQLConnectionHashMap = new HashMap<>();

    public DatabaseManager(){
        this.registerMySQLConnection("loki", new MySQLConnection(
                Loki.getInstance().getLokiConfiguration().mysqlHostname,
                Loki.getInstance().getLokiConfiguration().mysqlPort,
                Loki.getInstance().getLokiConfiguration().mysqlDatabase,
                Loki.getInstance().getLokiConfiguration().mysqlUsername,
                Loki.getInstance().getLokiConfiguration().mysqlPassword,
                false,
                false,
                true
        ));
    }

    public void registerMySQLConnection(String alias, MySQLConnection mySQLConnection) {
        if (mySQLConnection == null){
            return;
        }

        if (mySQLConnectionHashMap.containsKey(alias)) {
            mySQLConnectionHashMap.get(alias).disconnect();
            mySQLConnectionHashMap.replace(alias, mySQLConnection);
            return;
        }

        mySQLConnectionHashMap.put(alias, mySQLConnection);
    }

    public MySQLConnection getMySQLConnection(String alias) {
        return mySQLConnectionHashMap.getOrDefault(alias, null);
    }
    public MySQLConnection getDefaultMySQLConnection() {
        return getMySQLConnection("loki");
    }

    public void reconnectAllConnections() {

    }
}
