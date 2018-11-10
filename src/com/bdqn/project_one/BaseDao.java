package com.bdqn.project_one;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BaseDao {
	
	private static String Driver ;
	private static String url ;
	private static String username ;
	private static String pwd ;
	private static Connection conn = null;
	static {
		init();
	}
	
	public static void init() {
		Properties properties = new Properties();
		String config = "database.properties";
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(config);
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		username = properties.getProperty("user");
		pwd = properties.getProperty("password");
	}
	
	public static Connection getConnection() {
		if(null == conn) {
			try {
				Class.forName(Driver);
				try {
					conn = DriverManager.getConnection(url, username, pwd);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
