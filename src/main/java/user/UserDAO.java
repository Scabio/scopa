package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.db.Db;

public class UserDAO implements UserRepository {
	private String create = "INSERT INTO users VALUES (?, crypt(?, gen_salt('bf') ) );";
	private String read = "SELECT password = crypt( ?,(SELECT password FROM users WHERE name = ? )) AS pswmatch FROM users WHERE name = ?";
	private final Logger log = LogManager.getLogger(UserDAO.class);

	@Override
	public synchronized void createUser(String name, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Db.getConnection();
			ps = conn.prepareStatement(create);
			ps.setString(1, name);
			ps.setString(2, password);
			if (ps.execute()) {
				log.debug("Utente creato");
			} else {
				log.debug("Utente non creato");
			}
		} catch (SQLException | ClassNotFoundException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(ps != null)
					ps.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}

	}

	@Override
	public User readUser(String name, String password) {
		ResultSet result = null;
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = Db.getConnection();
			if (conn == null) {
				log.debug("Connessione nulla");
			} else {
				log.debug("Connessione non nulla");
			}
			ps = conn.prepareStatement(read);
			ps.setString(1, password);
			ps.setString(2, name);
			ps.setString(3, name);
			result = ps.executeQuery();
			result.next();
			if (result.getBoolean("pswmatch")) {
				user = new User(name);
			} else {
				user = null;
			}
		} catch (SQLException | ClassNotFoundException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(ps != null)
					ps.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
		return user;
	}

}
