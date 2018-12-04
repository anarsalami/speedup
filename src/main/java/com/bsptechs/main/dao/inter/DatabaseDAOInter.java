package com.bsptechs.main.dao.inter;

import com.bsptechs.main.bean.Charset;
import com.bsptechs.main.bean.Collation;
import com.bsptechs.main.bean.ui.tree.database.bean.SUConnectionBean;
import com.bsptechs.main.bean.ui.tree.database.bean.SUDatabaseBean;
import com.bsptechs.main.bean.ui.tree.database.bean.SUTableBean;
import com.bsptechs.main.bean.ui.table.CustomTableModel;
import com.bsptechs.main.bean.ui.table.TableRow;
import java.util.List;

/**
 *
 * @author sarkhanrasullu
 */
public interface DatabaseDAOInter {

    List<SUDatabaseBean> getAllDatabases(SUConnectionBean connection);

    List<SUTableBean> getAllTables(SUDatabaseBean database);

    boolean emptyTable(SUDatabaseBean db, String tblName);

    boolean truncateTable(SUDatabaseBean DBName, String tblName);

    boolean dublicateTable(SUDatabaseBean DBName, String tbLName);

    boolean pasteTable(String information, SUDatabaseBean DBName, String tbLName);

    boolean renameTable(SUTableBean table, String newTblName);

    public CustomTableModel runQuery(String query, SUConnectionBean connection, SUDatabaseBean database) throws Exception;

    public boolean createDbGeneral(SUConnectionBean ui, String query);

    public boolean createDbOptions(SUConnectionBean ui, String name, String charset, String collate);

    public List<Charset> getAllCharsets(SUConnectionBean connection);

    public List<Collation> getAllCollations(SUConnectionBean connection, Charset charset);

    public boolean deleteRow(SUConnectionBean connection, TableRow row);

    public boolean deleteRows(SUConnectionBean connection, List<TableRow> rows);

    public boolean saveRow(SUConnectionBean connection, TableRow row);

}
