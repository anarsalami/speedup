/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.popup;

import com.bsptechs.main.bean.ui.frame.ConnectionFrame;
import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.ui.frame.CreatDB;
import com.bsptechs.main.bean.ui.uielement.UiElementConnection;

/**
 *
 * @author sarkhanrasullu
 */
public class UiPopupConnection extends UiPopupAbstract {

   

    public UiPopupConnection() {
       
        
        addMenuItem("Delete Connection", () -> {
            delete();
        });
        addMenuItem("Connection Properties", () -> {
            properties();
        });

        addMenuItem("Connect", () -> {
            connect();
        });

        addMenuItem("Disconnect", () -> {
            disconnect();
        });
        
        addMenuItem("Create Database", () -> {
            createDb();
        });
        
    }
    
    public UiElementConnection getSelectedConnection(){
        return (UiElementConnection) getSelectedElement();
    }

    public void delete() {
        System.out.println("delete connection");
        UiElementConnection c = getSelectedConnection();
        Config.instance().getConnections().remove(c);
        Config.saveConfig();
        c.reset();
        Config.getMain().getListTable().removeUiElement(c);
        
    }

    public void properties() {
        System.out.println("properites connection");
        ConnectionFrame.showAsUpdate(getSelectedConnection());
    }

    public void connect() {
        System.out.println("connection connection");
        getSelectedConnection().connect();
    }

    public void disconnect() {
        System.out.println("disconnection connection");
        UiElementConnection cn = getSelectedConnection();
        cn.reset();
        cn.removeAllChildren();
    }

    
    public void createDb() {
        System.out.println("create database");
        CreatDB create = new CreatDB();
        create.setVisible(true);
    }
}
