package de.kleindev.loki.managers;

import de.kleindev.loki.utils.MySQLConnection;

import java.util.HashMap;

public class DatabaseManager {
    private HashMap<String, MySQLConnection> mySQLConnectionHashMap = new HashMap<>();

    public void registerMySQLConnection(String alias, MySQLConnection mySQLConnection) {
        if (mySQLConnectionHashMap.containsKey(alias)) {
            mySQLConnectionHashMap.get(alias).disconnect();
            mySQLConnectionHashMap.replace(alias, mySQLConnection);
            return;
        }

        mySQLConnectionHashMap.put(alias, mySQLConnection);
    }

    public MySQLConnection getMySQLConnection(String alias) {
        return mySQLConnectionHashMap.get(alias);
    }

    public void reconnectAllConnections() {

    }
}
