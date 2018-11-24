package com.bsptechs.main.dao.inter;

import com.bsptechs.main.bean.Charset;
import com.bsptechs.main.bean.Collation;
import com.bsptechs.main.bean.ui.tree.database.bean.ConnectionBean;
import com.bsptechs.main.bean.ui.tree.database.bean.DatabaseBean;
import com.bsptechs.main.bean.ui.tree.database.bean.TableBean;
import com.bsptechs.main.bean.ui.table.CustomTableModel;
import com.bsptechs.main.bean.ui.table.TableRow;
import java.util.List;

/**
 *
 * @author sarkhanrasullu
 */
public interface DatabaseDAOInter {

    List<DatabaseBean> getAllDatabases(ConnectionBean connection);

    List<TableBean> getAllTables(DatabaseBean database);

    boolean emptyTable(DatabaseBean db, String tblName);

    boolean truncateTable(DatabaseBean DBName, String tblName);

    boolean dublicateTable(DatabaseBean DBName, String tbLName);

    boolean pasteTable(String information, DatabaseBean DBName, String tbLName);

    boolean renameTable(TableBean table, String newTblName);

    public CustomTableModel runQuery(String query, ConnectionBean connection, DatabaseBean database) throws Exception;

    public boolean createDb(ConnectionBean ui, String name, String charset, String collate);

    public List<Charset> getAllCharsets(ConnectionBean connection);

    public List<Collation> getAllCollations(ConnectionBean connection, Charset charset);

    public boolean deleteRow(ConnectionBean connection, TableRow row);

    public boolean deleteRows(ConnectionBean connection, List<TableRow> rows);
    
    public boolean saveRow(ConnectionBean connection, TableRow row);

}
