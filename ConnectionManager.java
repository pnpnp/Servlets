package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection connection = null;

	private ConnectionManager() {

	}

	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uname = "hr";
		String pwd = "hr";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2nd step of getting connection object
			connection = DriverManager.getConnection(url, uname, pwd);

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] args) {
		ConnectionManager cm = new ConnectionManager();
		System.out.println(cm.getConnection());
	}
}
