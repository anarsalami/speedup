package com.bsptechs.main.bean;

import com.bsptechs.main.bean.ui.tree.database.bean.ConnectionBean;
import com.bsptechs.main.Main;
import com.bsptechs.main.util.FileUtility;
import java.io.Serializable;

public final class Config implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FILE_NAME = "mySql.txt";
    private static Config config = null;

    private CustomList<ConnectionBean> connectionBeans = new CustomList<>();

    public static void initialize() {
        config = readConfig();
    }

    public static Config instance() {
        return config;
    }

    public static CustomList<ConnectionBean> getConnectionBeans() {
        return instance().connectionBeans;
    }

    public void saveConfig() {
        connectionBeans = Main.instance().getConnectionTree().getConnectionBeans();
        FileUtility.writeObjectToFile(Config.instance(), FILE_NAME);
    }

    public static Config readConfig() {
        Object configObj = FileUtility.readFileDeserialize(FILE_NAME);
        Config cnf;
        if (configObj == null) {
            cnf = new Config();
            System.out.println("null");
        } else {
            cnf = (Config) configObj;
            System.out.println("else");
        }
        return cnf;
    }

}
