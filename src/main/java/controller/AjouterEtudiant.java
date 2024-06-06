 	package controller;


import DAO.MaConnexion;
import business.Etudiant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.AdminDAO;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller/AjouterEtudiant")
public class AjouterEtudiant extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	   

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	        HttpSession session = request.getSession();
	        if(session.getAttribute("username")!=null){
	        	String username = request.getParameter("username");
	            String password = request.getParameter("password");
	            String name = request.getParameter("nom");
	            String lastName = request.getParameter("prenom");
	            String address = request.getParameter("address");
	            String sex = request.getParameter("sex");
	            int age = Integer.parseInt(request.getParameter("age"));
	            String cne_etudiant = request.getParameter("username");
	            String[] classIds = request.getParameterValues("classes");

	            Etudiant etudiant = new Etudiant(username, password, name, lastName,address,sex,age,cne_etudiant);
	            try {
	            	 MaConnexion connexion= new MaConnexion();
	                 AdminDAO dao = new AdminDAO(connexion);
	                 dao.ajouterEtudiant(etudiant,classIds);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	    			e.printStackTrace();
	    		}
	            response.sendRedirect("./admin/etudiant-liste.jsp");
	 
	        }else response.sendRedirect("../Login.jsp");
	        }
}
