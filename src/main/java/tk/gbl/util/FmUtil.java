package tk.gbl.util;


import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;

/**
 * FreemarkerUtil
 * <p/>
 * Date: 2014/11/26
 * Time: 17:53
 *
 * @author Tian.Dong
 */
public class FmUtil {
    public static Template getTemplate(String name) {
        return getTemplate("/core/agent/", name);
    }

    public static Template getTemplate(String path, String name) {
        try {
            // 获得配置对象
            Configuration config = new Configuration();
            config.setEncoding(config.getLocale(), "UTF-8");
            config.setDefaultEncoding("UTF-8");
            // 设置模版的文件夹路径
            config.setClassForTemplateLoading(FmUtil.class, path);
            // 根据名字获得指定的一个模版
            Template template = config.getTemplate(name);
            return template;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getTemplateStr(String path, String name, Object obj) {
        try {
            // 获得配置对象
            Configuration config = new Configuration();
            //设置utf-8
            config.setEncoding(config.getLocale(), "UTF-8");
            config.setDefaultEncoding("UTF-8");
            // 设置模版的文件夹路径
            config.setClassForTemplateLoading(FmUtil.class, path);
            // 根据名字获得指定的一个模版
            Template template = config.getTemplate(name);
            StringWriter writer = new StringWriter();
            template.process(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}