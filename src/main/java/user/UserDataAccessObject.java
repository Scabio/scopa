package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.db.Db;
import utils.db.PooledConnectionPool;

public class UserDataAccessObject implements UserDataAccessObjectInterface {
	private String create = "INSERT INTO users VALUES (?, crypt(?, gen_salt('bf') ) );";
	private String read = "SELECT password=crypt(?,(SELECT password FROM users WHERE name=? )) AS pswmatch FROM users WHERE name = ?;";
	
	@Override
	public void createUser(String name, String password) {

		try {
			Connection conn = PooledConnectionPool.getConnection().getConn();
			PreparedStatement ps = conn.prepareStatement(create);
			ps.setString(1, name);
			ps.setString(2, password);
			if (ps.execute()) {
				// TODO inserire log a tomcat
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User readUser(String name, String password) throws SQLException {
		ResultSet result = null;
		try {
			Connection conn = PooledConnectionPool.getConnection().getConn();
			PreparedStatement ps = conn.prepareStatement(read);
			result = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		result.next();
		if(result.getBoolean("pswmatch")) {
			return new User(name);
		} else {
			return null;
		}
	}

}
