package com.bsptechs.main;

import com.bsptechs.main.bean.ui.tree.database.bean.SUConnectionBean;
import com.bsptechs.main.bean.SUArrayList;
import com.bsptechs.main.util.FileUtility;
import java.io.Serializable;
import com.bsptechs.main.util.LogUtil;

public final class Config implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FILE_NAME = "mySql.txt";
    private static Config config = null;

    private SUArrayList<SUConnectionBean> connectionBeans = new SUArrayList<>();

    public static void initialize() {
        config = readConfig();
    }

    public static Config instance() {
        return config;
    }

    public static SUArrayList<SUConnectionBean> getConnectionBeans() {
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
            LogUtil.log("null");
        } else {
            cnf = (Config) configObj;
            LogUtil.log("else");
        }
        return cnf;
    }

}
