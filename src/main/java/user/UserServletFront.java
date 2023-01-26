package user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class UserServletFront extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServletFront() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("./jsp/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String name = request.getParameter("name");
		final String password = request.getParameter("password");
		final Logger log = LogManager.getLogger(UserServletFront.class);
		final UserDAO userDAO = new UserDAO();
		User user = null;
		/*
		 * Controlli coerenza parametri
		 */
		if (name.length() > 32) {
			log.debug("Qualcuno ha provato ad accedere con un nome troppo lungo");
			request.getRequestDispatcher("./jsp/welcome.jsp").forward(request, response);
		}

		try {
			user = userDAO.readUser(name, password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.debug("Cannot read user");
			e.printStackTrace();
		}

		if (user != null) {
			log.debug("Utente loggato");
			response.addCookie(new Cookie("name", user.getName()));
			request.getRequestDispatcher("./jsp/home.jsp");
		} else {
			log.debug("Utente non loggato");
			request.getRequestDispatcher("./jsp/welcome.jsp");
		}

	}

}
