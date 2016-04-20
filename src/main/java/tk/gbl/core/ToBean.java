package tk.gbl.core;

import java.util.*;
import java.io.*;

public class ToBean {
  String tableName;
  String pkg;

  Map<String, String> tableField;

  PrintWriter pw;

  public ToBean() {

  }

  public ToBean(String tableName, Map<String, String> tableField) {
    this.tableName = this.dealTableName(tableName);
    this.tableField = tableField;

    System.out.println(this.tableName);

    try {
      File dir = new File("./cls/");
      if (!dir.exists()) {
        dir.mkdir();
      }
      pw = new PrintWriter(new File(dir, this.tableName
          + ".java"));
    } catch (FileNotFoundException e) {
    }
  }

  public ToBean(String tableName, Map<String, String> tableField, String pkg) {
    this.tableName = this.dealTableName(tableName);
    this.tableField = tableField;
    this.pkg = pkg;

    try {
      File dir = new File("./cls/");
      if (!dir.exists()) {
        dir.mkdir();
      }
      pw = new PrintWriter(new File(dir, this.tableName
          + ".java"));
    } catch (FileNotFoundException e) {
    }
  }

  public void setPrintWriter(String path) {
    File dir = new File(path + "/cls/");
    if (!dir.exists()) {
      dir.mkdir();
    }
    File file = new File(dir, tableName + ".java");
    try {
      pw = new PrintWriter(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public String createStart(String tableName) {
    return "package " + pkg + ";\r\n\r\npublic class " + tableName + " {\r\n";
  }

  public String createField(String type, String name) {
    StringBuilder idtf = new StringBuilder();
    idtf.append("private ");
    idtf.append(type);
    idtf.append(' ');
    idtf.append(this.dealFieldName(name));
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
    getMethod.append(this.dealFieldMethodName(name));

    getMethod.append("()");
    getMethod.append('{');
    getMethod.append("\r\n");
    getMethod.append('\t');
    getMethod.append("return " + this.dealFieldName(name));
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
    setMethod.append(this.dealFieldMethodName(name));

    setMethod.append("(" + type + " " + this.dealFieldName(name) + ")");
    setMethod.append('{');
    setMethod.append("\r\n");
    setMethod.append('\t');

    setMethod.append("this." + this.dealFieldName(name));
    setMethod.append(" = " + this.dealFieldName(name));

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
    type = type.toLowerCase();
    String jtype = type;

    if (type.equalsIgnoreCase("text") || type.equalsIgnoreCase("char")
        || type.equalsIgnoreCase("varchar") || type.equalsIgnoreCase("tinytext")) {
      jtype = "String";
    } else if (type.equalsIgnoreCase("tinyint") || type.equalsIgnoreCase("int")
        || type.equalsIgnoreCase("smallint") || type.equalsIgnoreCase("mediumint")) {
      jtype = "Integer";
    } else if (type.equalsIgnoreCase("tinyint") || type.equalsIgnoreCase("long")
        || type.equalsIgnoreCase("bigint") || type.equalsIgnoreCase("mediumint")) {
      jtype = "Long";
    }
    //else if(type.equals.....
    else if (type.equalsIgnoreCase("datetime") || type.equalsIgnoreCase("time")
        || type.equalsIgnoreCase("year") || type.equalsIgnoreCase("timestamp")) {
      jtype = "Date";
    }

    return jtype;
  }

  public String dealTableName(String tableName) {
    tableName = TempUtil.temp(tableName);

    StringBuilder sb = new StringBuilder();
    sb.append(tableName.substring(0, 1).toUpperCase());
    sb.append(tableName.substring(1));
    System.out.println(sb);
    return sb.toString();

  }

  private String dealFieldName(String name) {
    name = TempUtil.temp(name);

    StringBuilder sb = new StringBuilder();
    sb.append(name.substring(0, 1).toLowerCase());
    sb.append(name.substring(1));
    return sb.toString();
  }

  private String dealFieldMethodName(String name) {
    name = TempUtil.temp(name);

    StringBuilder sb = new StringBuilder();
    sb.append(name.substring(0, 1).toUpperCase());
    sb.append(name.substring(1));
    return sb.toString();
  }


  public void execute() {

    pw.println(this.createStart(tableName));

    for (String name : tableField.keySet()) {
      String type = tableField.get(name);
      type = this.dealType(type);

      pw.println(this.createField(type, name));
      //pw.println(this.createGetMethod(type, name));
      //pw.println(this.createSetMethod(type, name));
    }

    for (String name : tableField.keySet()) {
      String type = tableField.get(name);
      type = this.dealType(type);

      //pw.println(this.createField(type, name));
      pw.println(this.createGetMethod(type, name));
      pw.println(this.createSetMethod(type, name));
    }
    pw.println(this.createEnd());

    pw.flush();
  }


}
