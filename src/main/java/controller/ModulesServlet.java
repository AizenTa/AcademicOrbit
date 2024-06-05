package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import DAO.MaConnexion;
import DAO.ProfessorsDAO;
import business.Module;

@WebServlet("/controller/ModulesServlet")
public class ModulesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        if(session.getAttribute("username")!=null){
	        	MaConnexion connexion;
	        	try {
					connexion = new MaConnexion();
			        ProfessorsDAO dao = new ProfessorsDAO(connexion);
			        int profId = (Integer) session.getAttribute("profId");
			        List<Module> modules;
					modules = dao.getModulesByProfId(profId);
			        request.setAttribute("modules", modules);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


		        RequestDispatcher dispatcher = request.getRequestDispatcher("./prof/listeModules.jsp");
		        dispatcher.forward(request, response);
	        }
			
	    }

}
