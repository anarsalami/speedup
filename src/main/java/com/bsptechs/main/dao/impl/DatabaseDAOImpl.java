package com.bsptechs.main.dao.impl;

import com.bsptechs.main.bean.Charset;
import com.bsptechs.main.bean.Collation;
import com.bsptechs.main.bean.ui.tree.database.bean.SUConnectionBean;
import com.bsptechs.main.bean.ui.tree.database.bean.SUDatabaseBean;
import com.bsptechs.main.bean.ui.tree.database.bean.SUTableBean;
import com.bsptechs.main.bean.ui.tree.database.SUTableTreeNode;
import com.bsptechs.main.bean.ui.table.TableCell;
import com.bsptechs.main.bean.ui.table.CustomTableModel;
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
import java.util.Vector;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Penthos
 */
public class DatabaseDAOImpl extends AbstractDatabase implements DatabaseDAOInter {

    @SneakyThrows
    @Override
    public List<SUDatabaseBean> getAllDatabases(SUConnectionBean connection) {
        List<SUDatabaseBean> databasesList = new ArrayList<>();

        Connection conn = connect(connection);
        Statement stmt = conn.createStatement();
        ResultSet resultset = stmt.executeQuery("SHOW DATABASES;");

        if (stmt.execute("SHOW DATABASES;")) {
            resultset = stmt.getResultSet();
        }

        while (resultset.next()) {
            String result = resultset.getString("Database");
            databasesList.add(new SUDatabaseBean(result, connection));
        }
        return databasesList;
    }

    @Override
    @SneakyThrows
    public List<SUTableBean> getAllTables(SUDatabaseBean database) {
        List<SUTableBean> list = new ArrayList<>();
        Connection conn = connect(database.getConnection());
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM information_schema.tables where table_schema = ?");
        stmt.setString(1, database.getName());
        ResultSet resultset = stmt.executeQuery();
        while (resultset.next()) {
            String result = resultset.getString("table_name");
            list.add(new SUTableBean(result, database));
        }
        return list;
    }

    @Override
    @SneakyThrows
    public boolean renameTable(SUTableBean table, String newTblName) {
        Connection conn = connect(table.getDatabase().getConnection());
        PreparedStatement stmt = conn.prepareStatement(
                "RENAME "
                + " TABLE `" + table.getDatabase().getName() + "`.`" + table.getName() + "` "
                + " TO `" + table.getDatabase().getName() + "`.`" + newTblName + "`");
        stmt.executeUpdate();
        table.setName(newTblName);
        return true;
    }

    @SneakyThrows
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
    public CustomTableModel runQuery(String query, SUConnectionBean connection, SUDatabaseBean database) throws Exception {
        Connection conn = connect(connection);

        Statement stmt = conn.createStatement();
        if (database != null && StringUtils.isNoneEmpty(database.getName())) {
            String setDatabase = "USE " + database.getName() + ";";
            stmt.executeQuery(setDatabase);
        }

        ResultSet rs = stmt.executeQuery(query);
        List<String> columns = getColumns(rs);
        List<TableRow> rows = new ArrayList<>();
        String databaseName = getDatabaseName(rs, 1);

        String tableName = getTableName(rs, 1);
        while (rs.next()) {
            TableRow row = new TableRow(databaseName, tableName);

            for (int i = 0; i < columns.size(); i++) {
                String column = columns.get(i);
                Object o = rs.getObject(column);
                String tableNameCell = getTableName(rs, i + 1);
                String databaseNameCell = getDatabaseName(rs, i + 1);
                System.out.println("databasenamecell=" + databaseNameCell);
                row.add(new TableCell(column, o, databaseNameCell, tableNameCell, isPrimaryKey()));
            }

            rows.add(row);
        }
        CustomTableModel table = new CustomTableModel(rows, columns, databaseName, tableName);
        return table;
    }

    @SneakyThrows
    @Override
    public boolean emptyTable(SUDatabaseBean DBName, String tblName) {
        Connection conn = connect(DBName.getConnection());
        PreparedStatement stmt = conn.prepareStatement("delete  from " + DBName + "." + tblName);

        stmt.executeUpdate();

        return true;
    }

    @SneakyThrows
    @Override
    public boolean truncateTable(SUDatabaseBean DBName, String tblName) {
        Connection conn = connect(DBName.getConnection());

        PreparedStatement stmt = conn.prepareStatement("TRUNCATE TABLE " + DBName + "." + tblName);

        stmt.executeUpdate();

        return true;
    }

    @SneakyThrows
    @Override
    public boolean dublicateTable(SUDatabaseBean DBName, String tbLName) {
        Connection conn = connect(DBName.getConnection());
        String newTbLName = tbLName.concat("_copy");
        PreparedStatement stmt = conn.prepareStatement("CREATE TABLE " + DBName + "." + newTbLName + " LIKE " + DBName + "." + tbLName);
        PreparedStatement stmt1 = conn.prepareStatement("INSERT " + DBName + "." + newTbLName + "SELECT * FROM " + DBName + "." + tbLName);

        stmt.executeUpdate();
        return true;
    }

    @SneakyThrows
    @Override
    public boolean pasteTable(String information, SUDatabaseBean DBName, String TblName) {

        Connection conn = connect(DBName.getConnection());
        PreparedStatement stmt = conn.prepareStatement("CREATE TABLE " + DBName + "." + TblName + " LIKE " + information);
        PreparedStatement stmt1 = conn.prepareStatement("INSERT " + DBName + "." + TblName + "SELECT * FROM " + information);
        stmt.executeUpdate();

        return true;

    }

    @SneakyThrows
    public boolean dataTransfer(SUDatabaseBean DBNameWeHave, String tbLNameWeHave, SUDatabaseBean DBNameWeWant, String tbLNameWeWant) {
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

    }

    @SneakyThrows
    @Override
    public boolean createDb(SUConnectionBean connection, String query, String name, String charset, String collate) {
        if ("".equals(query) && query == null) {
            Connection conn = connect(connection);
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE SCHEMA `" + name + "` DEFAULT CHARACTER SET " + charset + " COLLATE " + collate + ";");
            return true;

        } else {
            Connection conn = connect(connection);
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            return true;
        }
    }

    @SneakyThrows
    @Override
    public List<Charset> getAllCharsets(SUConnectionBean connection) {
        List<Charset> charset = new ArrayList<>();
        Connection conn = connect(connection);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(" SHOW CHARACTER SET;");
        while (rs.next()) {
            String name = rs.getString("Charset");
            charset.add(new Charset(name));
        }
        return charset;
    }

    @Override
    @SneakyThrows
    public List<Collation> getAllCollations(SUConnectionBean connection, Charset charset) {
        if (charset != null && charset.getCollations() != null) {
            return charset.getCollations();
        }
        List<Collation> collations = new ArrayList<>();
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
    }

    @SneakyThrows
    @Override
    public boolean deleteRows(SUConnectionBean connection, List<TableRow> rows) {
        for (TableRow row : rows) {
            deleteRow(connection, row);
        }
        return true;
    }

    @SneakyThrows
    @Override
    public boolean deleteRow(SUConnectionBean connection, TableRow row) {
        List<TableCell> primaryCells = row.getAllPrimaryCell();

        if (primaryCells == null || primaryCells.isEmpty()) {
            deleteRowByRow(connection, row);
        } else {
            TableCell pk = primaryCells.get(0);
            return deleteRowByCell(connection, pk);
        }

        return true;
    }

    @SneakyThrows
    public boolean deleteRowByRow(SUConnectionBean connection, TableRow row) {
        Connection conn = connect(connection);

        Vector<TableCell> cells = row;
        String query = "delete "
                + " from " + row.getDatabaseName() + "." + row.getTableName() + " where ";

        for (int i = 0; i < cells.size(); i++) {
            TableCell cell = cells.get(i);
            query += cell.getColumnName() + "=?";
        }
        System.out.println("query deleteRowByRow=" + query);
        PreparedStatement stmt = conn.prepareStatement(query);

        for (int i = 0; i < cells.size(); i++) {
            TableCell cell = cells.get(i);
            stmt.setObject(i + 1, cell.getColumnValue());
        }

        stmt.executeUpdate();
        return true;
    }

    @SneakyThrows
    private boolean deleteRowByCell(SUConnectionBean connection, TableCell cell) {
        Connection conn = connect(connection);
        String query = "delete "
                + " from " + cell.getDatabaseName() + "." + cell.getTable()
                + " where " + cell.getColumnName() + "=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        System.out.println("query deleteRowByCell=" + query);
        stmt.setObject(1, cell.getColumnValue());
        stmt.executeUpdate();

        return true;
    }

    @SneakyThrows
    @Override
    public boolean saveRow(SUConnectionBean connection, TableRow row) {
        Connection conn = connect(connection);

        Vector<TableCell> cells = row;
        String query = "update "
                + " " + row.getDatabaseName() + "." + row.getTableName() + " set ";

        for (int i = 0; i < cells.size(); i++) {
            TableCell cell = cells.get(i);
            if (cell.isUpdateMode()) {
                query += cell.getColumnName() + "=?,";
            }
        }
        query = query.substring(0, query.length() - 1);
        System.out.println("query deleteRowByRow=" + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        int index = 1;
        for (int i = 0; i < cells.size(); i++) {
            TableCell cell = cells.get(i);
            if (cell.isUpdateMode()) {
                stmt.setObject(index++, cell.getColumnValue());
            }
        }

        stmt.executeUpdate();
        return true;
    }

}
