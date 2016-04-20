package tk.gbl.core;

import java.util.List;
import java.util.Map;

public class MainTank {

  Message message = new Message();
  ToBean toBean;

  public void dododo() throws Exception {
    message.init();
    List<String> tables = message.getTableList();

    for (int i = 0; i < tables.size(); i++) {
      String tableName = tables.get(i);
      //System.out.println(tableName);
      Map<String, String> tableField = message.getField(tableName);

      toBean = new ToBean(tableName, tableField);
      toBean.execute();
    }
  }

  public void dododo(String pkg) throws Exception {
    message.init();
    List<String> tables = message.getTableList();

    for (int i = 0; i < tables.size(); i++) {
      String tableName = tables.get(i);
      //System.out.println(tableName);
      Map<String, String> tableField = message.getField(tableName);

      toBean = new ToBean(tableName, tableField, pkg);
      toBean.execute();
    }
  }


  public void genXml() throws Exception {
    message.init();
    List<String> tables = message.getTableList();

    for (int i = 0; i < tables.size(); i++) {
      String x = "<table tableName=\"%s\" domainObjectName=\"%s\" enableCountByExample=\"false\" enableUpdateByExample=\"false\" enableDeleteByExample=\"false\" enableSelectByExample=\"false\" selectByExampleQueryId=\"false\"></table>";
      String tableName = tables.get(i);
      String domainName = dealFieldName(tableName);
      System.out.println(String.format(x, tableName, domainName));
    }
  }


  private String dealFieldName(String name) {
    name = TempUtil.temp(name);
    if (name.startsWith("xd")) {
      name = name.substring(2);
    }

    StringBuilder sb = new StringBuilder();
    sb.append(name.substring(0, 1).toUpperCase());
    sb.append(name.substring(1));
    return sb.toString();
  }

  private String dealFieldNameLow(String name) {
    name = TempUtil.temp(name);
    if (name.startsWith("xd")) {
      name = name.substring(2);
    }

    StringBuilder sb = new StringBuilder();
    sb.append(name.substring(0, 1).toLowerCase());
    sb.append(name.substring(1));
    return sb.toString();
  }

  public void genMybatisXml() throws Exception {
    message.init();
    List<String> tables = message.getTableList();

//    for (int i = 0; i < tables.size(); i++) {
//      String x = "\t\t<typeAlias type=\"com.xiaodou.domain.%s\" alias=\"%s\" />\n";
//      String tableName = tables.get(i);
//      String domainName = dealFieldName(tableName);
//      System.out.println(String.format(x, domainName, domainName));
//    }
//
//
//    for (int i = 0; i < tables.size(); i++) {
//      String x = "\t    <mapper resource=\"conf/mybatis/%sMapper.xml\"/>\n";
//      String tableName = tables.get(i);
//      String domainName = dealFieldName(tableName);
//      System.out.println(String.format(x, domainName, domainName));
//    }
    for (int i = 0; i < tables.size(); i++) {
      String x = "<bean id=\"%sMapper\" class=\"org.mybatis.spring.mapper.MapperFactoryBean\">\n" +
          "    <property name=\"mapperInterface\" value=\"tk.gbl.dao.%sMapper\" />\n" +
          "    <property name=\"sqlSessionFactory\" ref=\"sqlSessionFactory\" />\n" +
          "  </bean>";
      String tableName = tables.get(i);

      String domainName = dealFieldName(tableName);
      String beanName = dealFieldNameLow(tableName);
      System.out.println(String.format(x, beanName, domainName));
    }

  }
}
