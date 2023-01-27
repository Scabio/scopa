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
		final UserService userService = new UserService();
		boolean isLogIn;
		User user = null;
		/*
		 * Controlli coerenza parametri
		 */
		if (name.length() > 32) {
			log.debug("Qualcuno ha provato ad accedere con un nome troppo lungo");
			request.getRequestDispatcher("./jsp/welcome.jsp").forward(request, response);
		}

		user = userDAO.readUser(name, password);
		isLogIn = userService.isLogIn(name, password);
		if (isLogIn) {
			response.addCookie(new Cookie("name", user.getName()));
			request.getRequestDispatcher("./jsp/home.jsp").forward(request, response);
		} else {
			
			request.getRequestDispatcher("./jsp/index.jsp").forward(request, response);
		}

	}

}
