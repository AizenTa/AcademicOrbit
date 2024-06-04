package controller;

import DAO.AdminDAO;
import DAO.MaConnexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller/SupprimerModule")
public class SupprimerModule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if(session.getAttribute("username")!=null){
        	String id = request.getParameter("id");

            try {
            	 MaConnexion connexion= new MaConnexion();
                 AdminDAO dao = new AdminDAO(connexion);
                 dao.supprimerModule(id);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            response.sendRedirect("./admin/module-liste.jsp");

        }else response.sendRedirect("../Login.jsp");
        }
	}