package com.bsptechs.main.dao.inter;

import com.bsptechs.main.bean.ConnectionBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author sarkhanrasullu
 */
public abstract class AbstractDatabase {

    public Connection connect(ConnectionBean connection) throws ClassNotFoundException, SQLException {
        if(connection.getParentConnection()!=null){
            System.out.println(connection.getName()+" is using its own connection which created before");
            return connection.getParentConnection();
        } 
        
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://" + connection.getIpAdr() + ":" + connection.getPort() + "/";
        String username = connection.getUserName();
        String password = connection.getPassword();
        Connection c = DriverManager.getConnection(url, username, password);
        connection.setParentConnection(c);
        
        return c;
    }
    
     public boolean isPrimaryKey() {
        return true;
    }

    public String getTableName(ResultSet rs, int columnIndex) throws Exception{
        ResultSetMetaData metadata = rs.getMetaData();
        String name = metadata.getTableName(columnIndex);
        return name;
    }
    
    public String getDatabaseName(ResultSet rs, int columnIndex) throws Exception{
        ResultSetMetaData metadata = rs.getMetaData();
        String name = metadata.getCatalogName(columnIndex);
        return name;
    }

}