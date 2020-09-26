package utils;

/**
 * 在C3P0连接池中 遵循了javax.sql.DataSource接口的实现类:
 * 		ComboPooledDataSource
 */
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {

	// 自动加载C3P0-config.xml文件
	private static ComboPooledDataSource ds = new ComboPooledDataSource();

	public static DataSource getDataSource() {
		return ds;
	}

	public static Connection getConnection() throws SQLException {
		// 获取连接,从C3P0连接池获取
		return ds.getConnection();
	}

	// 关闭所有资源的统一代码
	public static void closeAll(Connection conn, Statement st, ResultSet rs) {
		// 负责关闭
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close(); // 使用代理,放回到连接池中
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
