package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import DAO.AdminDAO;
import DAO.MaConnexion;
import business.Etudiant;

@WebServlet("/controller/ModifierEtudiant")
public class ModifierEtudiant extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	   

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	        HttpSession session = request.getSession();
	        if(session.getAttribute("username")!=null){
	        	int id = Integer.parseInt(request.getParameter("id"));
	        	String username = request.getParameter("username");
	            String password = request.getParameter("password");
	            String name = request.getParameter("name");
	            String lastName = request.getParameter("lastName");
	            String address = request.getParameter("address");
	            String sex = request.getParameter("sex");
	            int age = Integer.parseInt(request.getParameter("age"));
	            String cneEtudiant = request.getParameter("cneEtudiant");
	            int abscence_hours = Integer.parseInt(request.getParameter("abscenceHours"));

	            Etudiant etudiant = new Etudiant(username, password, name, lastName, address, sex, age, cneEtudiant,abscence_hours);
	            try {
	            	 MaConnexion connexion= new MaConnexion();
	                 AdminDAO dao = new AdminDAO(connexion);
	                 dao.modifierEtudiant(etudiant,id);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	            response.sendRedirect("./admin/etudiant-liste.jsp");
	 
	        }else response.sendRedirect("../Login.jsp");
	        }
}
