package user;

import java.sql.SQLException;

public interface UserRepository {
	public void createUser(String name, String password) throws SQLException;
	public User readUser( String name, String password ) throws SQLException, ClassNotFoundException;
}
