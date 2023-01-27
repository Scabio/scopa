package utils.db;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
	private Connection conn = null;
	
	public ConnectionPool() throws ClassNotFoundException, SQLException {
		conn = Db.getConnection();
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public void releaseConnection() {
		conn = null;
	}
	
	
}
