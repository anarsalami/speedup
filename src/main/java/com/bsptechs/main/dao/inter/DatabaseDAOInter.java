package com.bsptechs.main.dao.inter;

import com.bsptechs.main.bean.Charset;
import com.bsptechs.main.bean.Collation;
import com.bsptechs.main.bean.ui.tree.database.node.DatabaseTreeNode;
import com.bsptechs.main.bean.ui.tree.database.node.ConnectionTreeNode;
import com.bsptechs.main.bean.ui.tree.database.node.TableTreeNode;
import com.bsptechs.main.bean.ui.table.CustomTableModel;
import com.bsptechs.main.bean.ui.table.TableRow;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sarkhanrasullu
 */
public interface DatabaseDAOInter {

    List<DatabaseTreeNode> getAllDatabases(ConnectionTreeNode connection);

    List<TableTreeNode> getAllTables(DatabaseTreeNode database);

    boolean emptyTable(DatabaseTreeNode db, String tblName);

    boolean truncateTable(DatabaseTreeNode DBName, String tblName);

    boolean dublicateTable(DatabaseTreeNode DBName, String tbLName);

    boolean pasteTable(String information, DatabaseTreeNode DBName, String tbLName);

    boolean renameTable(TableTreeNode table, String newTblName);

    public CustomTableModel runQuery(String query, ConnectionTreeNode connection, DatabaseTreeNode database) throws Exception;

    public boolean createDb(ConnectionTreeNode ui, String name, String charset, String collate);

    public List<Charset> getAllCharsets(ConnectionTreeNode connection);

    public List<Collation> getAllCollations(ConnectionTreeNode connection, Charset charset);

    public boolean deleteRow(ConnectionTreeNode connection, TableRow row);

    public boolean deleteRows(ConnectionTreeNode connection, List<TableRow> rows);
    
    public boolean saveRow(ConnectionTreeNode connection, TableRow row);

}
