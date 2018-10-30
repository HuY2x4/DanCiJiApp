package com.util;
import java.sql.Connection;

public class DBUtil {
	private static final String jdbcUrl="jdbc:mysql://47.106.110.34:3306/dcapp?useServerPrepStmts=false&rewriteBatchedStatements=true";
	private static final String dbUser="root";
	private static final String dbPwd="chenkan";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
	}
}
