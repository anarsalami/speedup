package com.bsptechs.main.dao.impl;

import com.bsptechs.main.bean.DatabaseName;
import com.bsptechs.main.bean.NConnection;
import com.bsptechs.main.bean.TableName;
import com.bsptechs.main.bean.table.TableCell;
import com.bsptechs.main.bean.table.TableData;
import com.bsptechs.main.bean.table.TableRow;
import com.bsptechs.main.dao.inter.AbstractDatabase;
import com.bsptechs.main.dao.inter.DatabaseDAOInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Penthos
 */
public class DatabaseDAOImpl extends AbstractDatabase implements DatabaseDAOInter {

    @Override
    public List<DatabaseName> getAllDatabases(NConnection connection) {
        List<DatabaseName> databasesList = new ArrayList<>();

        try {
            Connection conn = connect(connection);
            Statement stmt = conn.createStatement();
            ResultSet resultset = stmt.executeQuery("SHOW DATABASES;");

            if (stmt.execute("SHOW DATABASES;")) {
                resultset = stmt.getResultSet();
            }

            while (resultset.next()) {
                String result = resultset.getString("Database");
                databasesList.add(new DatabaseName(result, connection));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return databasesList;
        }
    }

    @Override
    public List<TableName> getAllTables(DatabaseName database) {
        List<TableName> list = new ArrayList<>();
        try {
            Connection conn = connect(database.getConnection());
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM information_schema.tables where table_schema = ?");
            stmt.setString(1, database.getName());
            ResultSet resultset = stmt.executeQuery();
            while (resultset.next()) {
                String result = resultset.getString("table_name");
                list.add(new TableName(result, database));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            return list;
        }
    }

    @Override
    public boolean renameTable(TableName table, String newTblName) {
        try {
            Connection conn = connect(table.getDatabaseName().getConnection());
            PreparedStatement stmt = conn.prepareStatement("RENAME TABLE `" + table.getDatabaseName().getName() + "`.`" + table.getTableName() + "` TO `" + table.getDatabaseName().getName() + "`.`" + newTblName + "`");//PrepapredStatement ile edende dirnaqlara gore ishlemirdi ona gore bele etdim
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<String> getColumns(ResultSet rs) throws SQLException {
        ResultSetMetaData metdata = rs.getMetaData();
        int cnt = metdata.getColumnCount();
        List<String> columns = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            String columnName = metdata.getColumnName(i + 1);
            columns.add(columnName);
        }
        return columns;
    }

    @Override
    public TableData runQuery(String query, NConnection connection, DatabaseName database) throws ClassNotFoundException, SQLException {
        TableData table = null;
        Connection conn = connect(connection);
        Statement stmt = conn.createStatement();
        if (database != null && StringUtils.isNoneEmpty(database.getName())) {
            String setDatabase = "USE " + database.getName() + ";";
            stmt.executeQuery(setDatabase);
        }

        ResultSet rs = stmt.executeQuery(query);

        List<String> columns = getColumns(rs);
        List<TableRow> rows = new ArrayList<>();
        while (rs.next()) {
            List<TableCell> rowCells = new ArrayList<>();

            for (String column : columns) {
                Object o = rs.getObject(column);
                rowCells.add(new TableCell(column, o));
            }

            rows.add(new TableRow(rowCells));
        }
        table = new TableData(rows, columns);
        return table;
    }

    @Override
    public boolean emptyTable(String DBName, String tblName) {
        DatabaseName databaseName = new DatabaseName();
        try (Connection conn = connect(databaseName.getConnection())) {

            PreparedStatement stmt = conn.prepareStatement("delete  from " + DBName + "." + tblName);

            stmt.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean truncateTable(String DBName, String tblName) {
        DatabaseName databaseName = new DatabaseName();
        try (Connection conn = connect(databaseName.getConnection())) {

            PreparedStatement stmt = conn.prepareStatement("TRUNCATE TABLE " + DBName + "." + tblName);

            stmt.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean dublicateTable(String DBName, String tbLName) {
        DatabaseName databaseName = new DatabaseName();
        try (Connection conn = connect(databaseName.getConnection())) {
            String newTbLName = tbLName.concat("_copy");
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE " + DBName + "." + newTbLName + " LIKE " + DBName + "." + tbLName);
            PreparedStatement stmt1 = conn.prepareStatement("INSERT " + DBName + "." + newTbLName + "SELECT * FROM " + DBName + "." + tbLName);

            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean pasteTable(String information, String DBName, String TblName) {
        DatabaseName databaseName = new DatabaseName();
        try (Connection conn = connect(databaseName.getConnection())) {
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE " + DBName + "." + TblName + " LIKE " + information);
            PreparedStatement stmt1 = conn.prepareStatement("INSERT " + DBName + "." + TblName + "SELECT * FROM " + information);
            stmt.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public static void main(String[] args) throws Exception {
        NConnection conn = new NConnection("localhost", "localhost", "3306", "root", "");

        TableData data = new DatabaseDAOImpl().runQuery("SELECT * FROM user;", conn, new DatabaseName("filemanagementsystem", conn));

        List<TableRow> rows = data.getRows();
        for (TableRow r : rows) {
            System.out.println(r);
            System.out.println("-------------------");
            List<TableCell> cells = r.getCells();

            for (TableCell c : cells) {
                System.out.println(c);
            }
            System.out.println("");
        }
    }

}
