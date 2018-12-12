package tk.gbl.test;

import tk.gbl.MainTank;

public class MyTest {

    public static void main(String[] args) throws Exception {
        new MainTank().genMybatisTemplateXml();
        new MainTank().dododo("com.xxx.po");
        new MainTank().genMybatisTemplateXml();
//        new MainTank().genXml();
    }

}
