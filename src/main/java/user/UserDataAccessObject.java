package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import utils.db.Db;
import utils.db.PooledConnectionPool;

public class UserDataAccessObject implements UserDataAccessObjectInterface {
	private String create = "INSERT INTO users VALUES (?, crypt(?, gen_salt('bf') ) );";
	private String read = "SELECT password=crypt(?,(SELECT password FROM users WHERE name=? )) AS pswmatch FROM users WHERE name = ?;";
	static Logger log = Logger.getLogger(UserDataAccessObject.class.getName());
	
	@Override
	public void createUser(String name, String password) {

		try {
			Connection conn = PooledConnectionPool.getConnection().getConn();
			PreparedStatement ps = conn.prepareStatement(create);
			ps.setString(1, name);
			ps.setString(2, password);
			if (ps.execute()) {
				log.debug("Utente creato");
			} else {
				log.debug("Utente non creato");
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
			ps.setString(1, password);
			ps.setString(2, name);
			ps.setString(0, name);
			result = ps.executeQuery();
		} catch (SQLException e) {
			
		}
		result.next();
		if(result.getBoolean("pswmatch")) {
			return new User(name);
		} else {
			return null;
		}
	}

}
