 	package controller;


import DAO.MaConnexion;
import business.Classe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.AdminDAO;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller/AjouterClasse")
public class AjouterClasse extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	   

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	        HttpSession session = request.getSession();
	        if(session.getAttribute("username")!=null){
	            String name = request.getParameter("name");
	            String filliere = request.getParameter("filliere");
	            String annee = request.getParameter("grade");
	            Classe classe = new Classe(name, filliere,annee);
	            try {
	            	 MaConnexion connexion= new MaConnexion();
	                 AdminDAO dao = new AdminDAO(connexion);
	                 dao.ajouterClasse(classe);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	    			e.printStackTrace();
	    		}
	            response.sendRedirect("./admin/classe-liste.jsp");
	 
	        }else response.sendRedirect("../Login.jsp");
	        }
}
