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
      matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
    }
    matcher.appendTail(sb);
    return sb.toString();
  }

}