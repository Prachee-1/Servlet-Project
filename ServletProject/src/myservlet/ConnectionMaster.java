package myservlet;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMaster {

	public static Connection getConnection() {
		Connection cn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdemodb?autoReconnect=true&useSSL=false","root","Ashu@123");
			return cn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
