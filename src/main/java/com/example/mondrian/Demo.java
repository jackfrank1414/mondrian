package com.example.mondrian;

import mondrian.olap.*;
import mondrian.olap4j.MondrianOlap4jConnection;
import mondrian.test.loader.MondrianFoodMartLoader;

import java.io.PrintWriter;

/**
 * @author : jackfrank
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2024/1/21 0:44
 */
public class Demo {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/foodmart?characterEncoding=utf8";
    private static String userName = "root";
    private static String passWord = "Wlj1414213562195";
    private static String xmlFile = "FoodMart.xml";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(driver);

        Util.PropertyList propertyList = new Util.PropertyList();
        propertyList.put("Provider", "mondrian");
        propertyList.put("JdbcDrivers", driver);
        propertyList.put("Jdbc", url);
        propertyList.put("JdbcUser", userName);
        propertyList.put("JdbcPassword", passWord);
        propertyList.put("Catalog", xmlFile);

        String mdxQueryString = "SELECT {[Time].[1997].[Q1]} on columns," +" {[Product].children} on rows "
                +"FROM[Sales] ";
        Connection connection = DriverManager.getConnection(propertyList, null);
        Query query = connection.parseQuery(mdxQueryString);
        Result result = connection.execute(query);

        PrintWriter printWriter = new PrintWriter(System.out);
        result.print(printWriter);
    }
}
