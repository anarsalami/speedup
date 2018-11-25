package com.bsptechs.main.bean;

import com.bsptechs.main.bean.ui.uielement.UiElementDatabase;
import com.bsptechs.main.bean.ui.uielement.UiElementConnection;
import com.bsptechs.main.Main;
import com.bsptechs.main.bean.ui.panel.PanelDataTransferGeneral;
import com.bsptechs.main.util.FileUtility;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Penthos
 */
public final class Config implements Serializable {

    private static final String fileName = "mySql.txt";
    private List<UiElementConnection> connections = null;
    private static UiElementConnection currentConnection = null;
    private static UiElementDatabase currentDatabaseName = null;
    private static Config config = null;
    private static volatile Main main;
    private static volatile PanelDataTransferGeneral paneldatatransfer;

    public static void initialize() {
        config = readConfig();
    }

    public static Config instance() {
        return config;
    }

    public List<UiElementConnection> getConnections() {
        return connections;
    }

    public UiElementConnection getConnectionByName(String connectionName) {
        if (connections == null) {
            return null;
        }
        for (int i = 0; i < connections.size(); i++) {
            UiElementConnection connection = connections.get(i);
            if (connection.getName().equalsIgnoreCase(connectionName)) {
                return connection;
            }
        }
        return null;
    }

    public void appendConnection(UiElementConnection connection) {
        if (connections == null) {
            connections = new ArrayList<>();
        }
        connections.add(connection);
    }

    public static void setCurrentConnection(UiElementConnection connection) {
        currentConnection = connection;
    }
//

    public static UiElementConnection getCurrentConnection() {
        return currentConnection;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setMain(Main frame) {
        main = frame;
    }

    public static Main getMain() {
        return main;
    }

    public static UiElementDatabase getCurrentDatabaseName() {
        return currentDatabaseName;
    }

    public static void setCurrentDatabaseName(UiElementDatabase currentDatabaseName) {
        Config.currentDatabaseName = currentDatabaseName;
    }

    public static void saveConfig() {
        FileUtility.writeObjectToFile(Config.instance(), Config.getFileName());
    }

    public static Config readConfig() {
        Object configObj = FileUtility.readFileDeserialize(Config.getFileName());
        if (configObj == null) {
            return new Config();
        } else {
            return (Config) configObj;
        }
    }

    public static void setPanelDataTransfer(PanelDataTransferGeneral frame) {
        paneldatatransfer = frame;
    }

    public static PanelDataTransferGeneral getPanelDataTransfer() {
        return paneldatatransfer;
    }

}
