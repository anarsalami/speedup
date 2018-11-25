package com.bsptechs.main.dao.impl;

import com.bsptechs.main.bean.Charset;
import com.bsptechs.main.bean.Collation;
import com.bsptechs.main.bean.ui.uielement.UiElementDatabase;
import com.bsptechs.main.bean.ui.uielement.UiElementConnection;
import com.bsptechs.main.bean.ui.uielement.UiElementTable;
import com.bsptechs.main.bean.ui.table.TableCell;
import com.bsptechs.main.bean.ui.table.TableData;
import com.bsptechs.main.bean.ui.table.TableRow;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Penthos
 */
public class DatabaseDAOImpl extends AbstractDatabase implements DatabaseDAOInter {

    @Override
    public List<UiElementDatabase> getAllDatabases(UiElementConnection connection) {
        List<UiElementDatabase> databasesList = new ArrayList<>();

        try {
            Connection conn = connect(connection);
            Statement stmt = conn.createStatement();
            ResultSet resultset = stmt.executeQuery("SHOW DATABASES;");

            if (stmt.execute("SHOW DATABASES;")) {
                resultset = stmt.getResultSet();
            }

            while (resultset.next()) {
                String result = resultset.getString("Database");
                databasesList.add(new UiElementDatabase(result, connection));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return databasesList;
        }
    }

    @Override
    public List<UiElementTable> getAllTables(UiElementDatabase database) {
        List<UiElementTable> list = new ArrayList<>();
        try {
            Connection conn = connect(database.getConnection());
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM information_schema.tables where table_schema = ?");
            stmt.setString(1, database.getName());
            ResultSet resultset = stmt.executeQuery();
            while (resultset.next()) {
                String result = resultset.getString("table_name");
                list.add(new UiElementTable(result, database));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            return list;
        }
    }

    @Override
    public boolean renameTable(UiElementTable table, String newTblName) {
        try {
            Connection conn = connect(table.getDatabaseName().getConnection());
            PreparedStatement stmt = conn.prepareStatement(
                    "RENAME "
                    + " TABLE `" + table.getDatabaseName().getName() + "`.`" + table.getTableName() + "` "
                    + " TO `" + table.getDatabaseName().getName() + "`.`" + newTblName + "`");
            stmt.executeUpdate();
            table.setTableName(newTblName);
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
    public TableData runQuery(String query, UiElementConnection connection, UiElementDatabase database) throws ClassNotFoundException, SQLException {
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
    public boolean emptyTable(UiElementDatabase DBName, String tblName) {
        try {
            Connection conn = connect(DBName.getConnection());
            PreparedStatement stmt = conn.prepareStatement("delete  from " + DBName + "." + tblName);

            stmt.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean truncateTable(UiElementDatabase DBName, String tblName) {
        try {
            Connection conn = connect(DBName.getConnection());

            PreparedStatement stmt = conn.prepareStatement("TRUNCATE TABLE " + DBName + "." + tblName);

            stmt.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean dublicateTable(UiElementDatabase DBName, String tbLName) {
        try {
            Connection conn = connect(DBName.getConnection());
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
    public boolean pasteTable(String information, UiElementDatabase DBName, String TblName) {

        try {
            Connection conn = connect(DBName.getConnection());
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE " + DBName + "." + TblName + " LIKE " + information);
            PreparedStatement stmt1 = conn.prepareStatement("INSERT " + DBName + "." + TblName + "SELECT * FROM " + information);
            stmt.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean dataTransfer(UiElementDatabase DBNameWeHave, String tbLNameWeHave, UiElementDatabase DBNameWeWant, String tbLNameWeWant) {
        try {
            Connection connWeHave = connect(DBNameWeHave.getConnection());
            Connection connWeWant = connect(DBNameWeWant.getConnection());
            String newTbLName1 = tbLNameWeHave;
            String newTbLName = tbLNameWeWant;
            PreparedStatement stmtWeHave = connWeHave.prepareStatement("SELECT FROM " + DBNameWeHave);
            PreparedStatement stmtWeWant = connWeWant.prepareStatement("CREATE DATABASE " + DBNameWeHave);
//	    ResultSet rs = stmtWeWant.executeQuery();
//	    ResultSetMetaData rsmd = rs.getMetaData();
//	    int columnCount = rsmd.getColumnCount();
//	    String tableName = null;
//	    StringBuilder sb = new StringBuilder(1024);
//	    if (columnCount > 0) {
//		sb.append("Create table ").append(rsmd.getTableName(1)).append(" ( ");
//	    }
//	    for (int i = 1; i <= columnCount; i++) {
//		if (i > 1) {
//		    sb.append(", ");
//		}
//		String columnName = rsmd.getColumnLabel(i);
//		String columnType = rsmd.getColumnTypeName(i);
//
//		sb.append(columnName).append(" ").append(columnType);
//
//		int precision = rsmd.getPrecision(i);
//		if (precision != 0) {
//		    sb.append("( ").append(precision).append(" )");
//		}
//	    } // for columns
//	    sb.append(" ) ");
//	    System.out.println(sb.toString());
//	    stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean createDb(UiElementConnection ui, String name, String charset, String collate) {
        Connection conn = null;
        try {
            conn = connect(ui);
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE SCHEMA `" + name + "` DEFAULT CHARACTER SET " + charset + " COLLATE " + collate + ";");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public List<Charset> getAllCharsets(UiElementConnection connection) {
        List<Charset> charset = new ArrayList<>();
        try {
            Connection conn = connect(connection);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(" SHOW CHARACTER SET;");
            while (rs.next()) {
                String name = rs.getString("Charset");
                charset.add(new Charset(name));

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseDAOImpl.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAOImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            return charset;
        }
    }

    @Override
    public List<Collation> getAllCollations(UiElementConnection connection, Charset charset) {
        if (charset != null && charset.getCollations() != null) {
            return charset.getCollations();
        }
        List<Collation> collations = new ArrayList<>();
        try {
            Connection conn = connect(connection);
            Statement stmt = conn.createStatement();
            stmt.execute(" SHOW COLLATION where CHARSET='" + charset + "'");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                String name = rs.getString("Collation");

                collations.add(new Collation(name));

            }

            charset.setCollations(collations);
            return collations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        UiElementConnection conn = new UiElementConnection("localhost", "localhost", "3306", "root", "");

        TableData data = new DatabaseDAOImpl().runQuery(
                "SELECT * FROM user;",
                conn,
                new UiElementDatabase("filemanagementsystem", conn));

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
