package tk.gbl.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 设置数据库的信息
 * @author Don'T PH 7.0
 * 2013-12-3
 */
public class Message {
	private String dburl = "jdbc:mysql://127.0.0.1:3306/wah";
	private String dbUserName = "root";
	private String dbUserPsw = "admin";

	Connection conn;
	Statement stmt;

	DatabaseMetaData metaData;

	public void init() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(dburl, dbUserName, dbUserPsw);
		metaData = conn.getMetaData();
	}

	public List<String> getTableList() throws SQLException {
		ResultSet rs = metaData.getTables(null, "%", "%",
				new String[] { "TABLE" });

		List<String> tableLists = null;
		tableLists = new ArrayList<String>();
		while (rs.next()) {
			tableLists.add(rs.getString("TABLE_NAME"));
		}
		return tableLists;
	}

	public Map<String, String> getField(String tableName) throws SQLException {
		ResultSet rs = metaData.getColumns(null, "%", tableName, "%");

		Map<String, String> fieldMaps = new HashMap<String, String>();
		String columnName;
		String columnType;

		while (rs.next()) {
			columnName = rs.getString("COLUMN_NAME");
			columnType = rs.getString("TYPE_NAME");

			fieldMaps.put(columnName, columnType);
		}

		return fieldMaps;
	}


}

