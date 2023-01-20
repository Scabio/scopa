package utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PooledConnection {
	private Connection conn = null;
	private String url = Db.getUrl();

	public PooledConnection() throws SQLException{	
		this.setConn(DriverManager.getConnection(url));
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
