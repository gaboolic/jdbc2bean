package tk.gbl.core;

import java.sql.*;
import java.util.*;

/**
 * 设置数据库的信息
 *
 * @author dongtian
 * 2013-12-3
 */
public class DBInfo {
    private String dburl = "jdbc:mysql://127.0.0.1:3306/myflight";
    private String dbUserName = "root";
    private String dbUserPsw = "123456";


    Connection conn;

    DatabaseMetaData metaData;

    public void init() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(dburl, dbUserName, dbUserPsw);
        metaData = conn.getMetaData();
    }

    public List<String> getTableList() throws SQLException {
        ResultSet rs = metaData.getTables(null, "%", "%",
                new String[]{"TABLE"});

        List<String> tableLists = null;
        tableLists = new ArrayList<String>();
        while (rs.next()) {
            tableLists.add(rs.getString("TABLE_NAME"));
        }
        return tableLists;
    }

    public Map<String, FieldInfo> getField(String tableName) throws SQLException {
        ResultSet rs = metaData.getColumns(null, "%", tableName, "%");

        Map<String, FieldInfo> fieldMaps = new LinkedHashMap<>();

        while (rs.next()) {
            FieldInfo fieldInfo = new FieldInfo();
            String columnName = rs.getString("COLUMN_NAME");
            String columnType = rs.getString("TYPE_NAME");
            String remarks = rs.getString("REMARKS");
            fieldInfo.setColumnName(columnName);
            fieldInfo.setColumnType(columnType);
            fieldInfo.setComment(remarks);
            fieldMaps.put(columnName, fieldInfo);
        }
        ResultSet pkRs = metaData.getPrimaryKeys(null, "%", tableName);
        while (pkRs.next()) {
            String pkColumnName =  pkRs.getString("COLUMN_NAME");
            FieldInfo fieldInfo = fieldMaps.get(pkColumnName);
            if(fieldInfo != null) {
                fieldInfo.setPk(true);
            }
        }
        return fieldMaps;
    }


}

