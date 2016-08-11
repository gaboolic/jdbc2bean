package tk.gbl.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TempUtil {

  public static String temp(String source) {
    String patternString = "_([a-zA-Z])";

    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(source);

    //两个方法：appendReplacement, appendTail
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
    }
    matcher.appendTail(sb);
    return sb.toString();
  }

  public static String getClassName(String name) {
    if (name.startsWith(CommonConfig.ignorePrefix)) {
      name = name.substring(CommonConfig.ignorePrefix.length());
    }
    name = TempUtil.temp(name);

    StringBuilder sb = new StringBuilder();
    sb.append(name.substring(0, 1).toUpperCase());
    sb.append(name.substring(1));
    return sb.toString();
  }



  public static String upFirst(String source) {
    return source.substring(0, 1).toUpperCase() + source.substring(1);
  }


}