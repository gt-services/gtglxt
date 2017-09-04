package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
//	private static final String URL = "jdbc:mysql://localhost:3306/gt?useUnicode=true&amp;characterEncoding=utf8";
//	private static final String USERNAME = "root";
//	private static final String PASSWORD = "123456";
	private static final String URL = "jdbc:mysql://rm-uf6533mo112otpf4so.mysql.rds.aliyuncs.com:3306/gt?useUnicode=true&amp;characterEncoding=utf8";
	private static final String USERNAME = "rootadmin";
	private static final String PASSWORD = "rootAdmin@123";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static Connection conn =null;
	private static Statement stat=null;
	private static ResultSet rs =null;
	// 1.注册驱动
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 数据库对象连接使用的
	public  Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭资源使用
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//查询使用
	public ResultSet select(String sql) {
		// 2.获取数据库连接对象
		try {
			conn = getConnection();
			// 3.获取执行sql语句使用的对象--statement
			stat = conn.createStatement();
			// 4.执行sql语句
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;
	}
	//增删改的通用方法
	public int execution(String sql) {
		// 2.获取数据库连接对象
		int i = 0;
		try {
			conn =getConnection();
			// 3.获取执行sql语句使用的对象--statement
			stat = conn.createStatement();
			// 4.执行sql语句
			i = stat.executeUpdate(sql);// 返回值表示数据库中受影响的行数
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return i;// 将执行sql后的返回值返回给调用者。
	}
}
