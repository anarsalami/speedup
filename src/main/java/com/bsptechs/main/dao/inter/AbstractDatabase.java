package com.bsptechs.main.dao.inter;

import com.bsptechs.main.bean.SUArrayList;
import com.bsptechs.main.bean.ui.table.SUTableColumn;
import com.bsptechs.main.bean.ui.table.SUTableColumnType;
import com.bsptechs.main.bean.ui.tree.database.bean.SUConnectionBean;
import com.bsptechs.main.bean.ui.tree.database.bean.SUDatabaseBean;
import com.bsptechs.main.bean.ui.tree.database.bean.SUTableBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import lombok.SneakyThrows;

/**
 *
 * @author sarkhanrasullu
 */
public abstract class AbstractDatabase {

    public Connection connect(SUConnectionBean connection) throws ClassNotFoundException, SQLException {
        if (connection.getParentConnection() != null) {
            System.out.println(connection.getName() + " is using its own connection which created before");
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

    public SUTableBean getTable(SUConnectionBean connection, ResultSet rs, int columnIndex) throws Exception {
        SUDatabaseBean databaseBean = getDatabase(connection, rs, columnIndex);
        ResultSetMetaData metadata = rs.getMetaData();
        String name = metadata.getTableName(columnIndex);
        return new SUTableBean(name, databaseBean);
    }

    public SUDatabaseBean getDatabase(SUConnectionBean connection, ResultSet rs, int columnIndex) throws Exception {
        ResultSetMetaData metadata = rs.getMetaData();
        String name = metadata.getCatalogName(columnIndex);
        return new SUDatabaseBean(name, connection);
    }

    @SneakyThrows
    public SUTableColumnType getColumnType(ResultSet rs, SUConnectionBean connection, int columnIndex) {
        ResultSetMetaData metadata = rs.getMetaData();
        int columnTypeId = metadata.getColumnType(columnIndex);
        String columnTypeName = metadata.getColumnTypeName(columnIndex);
        return new SUTableColumnType(columnTypeId, columnTypeName);
    }

    @SneakyThrows
    public SUArrayList<SUTableColumn> getColumns(ResultSet rs, SUConnectionBean connection) throws SQLException {
        ResultSetMetaData metadata = rs.getMetaData();
        int cnt = metadata.getColumnCount();
        SUArrayList<SUTableColumn> columns = new SUArrayList<>();
        for (int i = 0; i < cnt; i++) {
            int columnIndex = i + 1;
            String name = metadata.getColumnName(columnIndex);
            SUTableBean tableBean = getTable(connection, rs, columnIndex);
            SUTableColumn column = new SUTableColumn(tableBean, name, false, getColumnType(rs, connection, columnIndex));

            columns.add(column);
        }
        fillReferencedColumns(columns, connection);
        return columns;
    }

    private SUTableBean getTable(SUArrayList<SUTableColumn> columns) {
        if (columns == null || columns.size() == 0) {
            return null;
        }
        SUTableBean res = columns.get(0).getTable();
        for (SUTableColumn c : columns) {
            if (!res.getName().equals(c.getTable().getName())) {
                return null;
            }
        }
        return res;
    }

    @SneakyThrows
    private void fillReferencedColumns(SUArrayList<SUTableColumn> columns, SUConnectionBean connection) throws SQLException {
        SUTableBean table = getTable(columns);
        if (table == null) {
            System.out.println("table is not unique");
            return;
        }
        Connection conn = connect(connection);

        String sqlQuery = "select "
                + "     table_name, column_name,referenced_table_name, referenced_column_name "
                + " from "
                + "     information_schema.KEY_COLUMN_USAGE "
                + " where table_name = ? and referenced_table_name is not null";

        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        System.out.println("table.getName()="+table.getName());
        stmt.setString(1, table.getName());

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String tableName = rs.getString("table_name");
            String columnName = rs.getString("column_name");
            SUTableColumn ownerColumn = columns.getByName(columnName);

            String refTableName = rs.getString("referenced_table_name");
            String refColumnName = rs.getString("referenced_column_name");

            SUTableColumn refColumnBean = new SUTableColumn(new SUTableBean(refTableName, null), refColumnName, true, getColumnType(rs, connection, 4));

            ownerColumn.setReferencedColumn(refColumnBean);
            System.out.println("owner column=" + ownerColumn);
            System.out.println("referenced column=" + refColumnBean);
            System.out.println("-------");
        }

    }

}
