package user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
	private final Logger log = LogManager.getLogger(UserDAO.class);
	public boolean isLogIn(String name, String password ) {
		boolean flag = false;
		UserDAO userDAO = new UserDAO();
		User user = userDAO.readUser(name, password);
		if(user != null) {
			log.debug("Utente loggato");
			flag = true;
		} else {
			log.debug("Utente non loggato");
		}
		return flag;
	}
}
