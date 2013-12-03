package tk.gbl.core;

import java.util.*;
import java.io.*;

public class ToBean {
	String tableName;
	Map<String, String> tableField;

	PrintWriter pw;

	public ToBean() {

	}

	public ToBean(String tableName, Map<String, String> tableField) {
		this.tableName = this.dealTableName(tableName);
		this.tableField = tableField;

		System.out.println(this.tableName);
		
		try {

			pw = new PrintWriter(new File("./" + this.tableName
					+ ".java"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPrintWriter(String path) {
		File file = new File(path + "/" + tableName + ".java");
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String createStart(String tableName) {
		return "public class " + tableName + " {\r\n";
	}

	public String createField(String type, String name) {
		StringBuilder idtf = new StringBuilder();
		idtf.append("private ");
		idtf.append(type);
		idtf.append(' ');
		idtf.append(name);
		idtf.append(';');
		idtf.append("\r\n");
		return idtf.toString();
	}

	public String createGetMethod(String type, String name) {
		StringBuilder getMethod = new StringBuilder();
		getMethod.append("public ");
		getMethod.append(type);
		getMethod.append(' ');
		getMethod.append("get");
		getMethod.append((char) (name.charAt(0) + 'A' - 'a'));
		getMethod.append(name.substring(1));

		getMethod.append("()");
		getMethod.append('{');
		getMethod.append("\r\n");
		getMethod.append('\t');
		getMethod.append("return " + name);
		getMethod.append(';');
		getMethod.append("\r\n");

		getMethod.append('}');
		getMethod.append("\r\n");
		return getMethod.toString();
	}

	public String createSetMethod(String type, String name) {
		StringBuilder setMethod = new StringBuilder();
		setMethod.append("public ");
		setMethod.append("void");
		setMethod.append(' ');
		setMethod.append("set");
		setMethod.append((char) (name.charAt(0) + 'A' - 'a'));
		setMethod.append(name.substring(1));

		setMethod.append("(" + type + " " + name + ")");
		setMethod.append('{');
		setMethod.append("\r\n");
		setMethod.append('\t');

		setMethod.append("this." + name);
		setMethod.append(" = " + name);

		setMethod.append(';');
		setMethod.append("\r\n");

		setMethod.append('}');
		setMethod.append("\r\n");

		return setMethod.toString();
	}

	public String createEnd() {
		return "\r\n}";
	}

	public String dealType(String type) {
		String jtype = type;

		if (type.equals("text") || type.equals("char")
				|| type.equals("varchar") || type.equals("tinytext")) {
			jtype = "String";
		}
		else if(type.equals("tinyint") || type.equals("int")
				|| type.equals("smallint")|| type.equals("mediumint")){
			jtype = "int";
		}
		//else if(type.equals.....
		else if(type.equals("datetime") || type.equals("time")
				|| type.equals("year")|| type.equals("timestamp")){
			jtype = "Date";
		}

		return jtype;
	}
	public String dealTableName(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append((char)(tableName.charAt(0)+'A'-'a'));
		sb.append(tableName.substring(1));
		
		System.out.println("!!!"+sb.toString());
		return sb.toString();
		
	}
	public void execute() {

		pw.println(this.createStart(tableName));

		for (String name : tableField.keySet()) {
			String type = tableField.get(name);
			type = this.dealType(type);

			pw.println(this.createField(type, name));
			pw.println(this.createGetMethod(type, name));
			pw.println(this.createSetMethod(type, name));
		}
		pw.println(this.createEnd());

		pw.flush();
	}


}
