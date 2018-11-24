/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main;

import com.bsptechs.main.util.FileUtility;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sarkhanrasullu
 */
class DB implements Serializable {

    public String name;
}

class Connection implements Serializable {

    public transient List<DB> databases;
}

class Config implements Serializable {

    public List<Connection> connections = new ArrayList<>();

    {
        Connection conn = new Connection();
        conn.databases = new ArrayList<>();
        
        DB db = new DB();
        db.name = "db";
        conn.databases.add(db);
       
        connections.add(conn);
    }

}

public class Test {

    public static void main(String[] args) {
       
         List l = new ArrayList();
         System.out.println(l.get(0));
    }
}
