package com.wondersgroup.qyws.tjfx.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wondersgroup.qyws.tjfx.module.DbConfig;

/**
 * 人员统计的数据库
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class DBUtil {
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnectionByDb(DbConfig config) {
		 
        try {
 
            Class.forName(config.getQdm());
            
            String url = config.getUrl();
           
            //String url = "jdbc:oracle:thin:@10.17.157.200:1521:orcl";
             
            //String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
            //String username = "zb";
            //String password = "zb";
 
            String username =config.getYhm();
 
            String password = config.getMm();
 
            Connection conn = DriverManager.getConnection(url, username,
 
                    password);
 
            return conn;
 
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
 
    }
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		 
        try {
 
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            String url = "jdbc:oracle:thin:@192.168.9.100:1521:orcl";
           
            //String url = "jdbc:oracle:thin:@10.17.157.200:1521:orcl";
             
            //String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
            //String username = "zb";
            //String password = "zb";
 
            String username = "hr";
 
            String password = "hrdata123";
 
            Connection conn = DriverManager.getConnection(url, username,
 
                    password);
 
            return conn;
 
        } catch (Exception e) {
 
            throw new IllegalArgumentException(e);
 
        }
 
    }
	
	/**
	 * 把resultset转变为list
	 * @param rs
	 * @return
	 * @throws java.sql.SQLException
	 */
	
	public static List resultSetToList(ResultSet rs) throws java.sql.SQLException {   
        if (rs == null)   
            return Collections.EMPTY_LIST;   
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数   
        List list = new ArrayList();   
        Map rowData = new HashMap();   
        while (rs.next()) {   
         rowData = new HashMap(columnCount);   
         for (int i = 1; i <= columnCount; i++) {   
                 rowData.put(md.getColumnName(i), rs.getObject(i));   
         }   
         list.add(rowData);   
         System.out.println("list:" + list.toString());   
        }   
        return list;   
   } 
	
}
