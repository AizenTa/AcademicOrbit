package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import DAO.AdminDAO;
import DAO.MaConnexion;
import business.Admin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Ramesh Fadatare
 */

@WebServlet("/")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDAO adminDao;
	MaConnexion connexion;

	public void init() {
		adminDao = new AdminDAO(connexion);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertAdmin(request, response);
				break;
			case "/delete":
				deleteAdmin(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			default:
				Listadmin(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void Listadmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Admin> Listadmin = adminDao.selectAllAdmins();
		request.setAttribute("listAdmin", Listadmin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Listadmin.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Admin existingAdmin = adminDao.selectAdmin(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-form.jsp");
		request.setAttribute("admin", existingAdmin);
		dispatcher.forward(request, response);

	}

	private void insertAdmin(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String last_name = request.getParameter("last_name");
		Admin newAdmin = new Admin(username,password,name,last_name);
		adminDao.ajouterAdmin(newAdmin);
		response.sendRedirect("list");
	}


	private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		adminDao.deleteAdmin(id);
		response.sendRedirect("list");

	}

}