package controller;

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
import business.Etudiant;

@WebServlet("/controller/EnregistrerNotesServlet")

public class EnregistrerNotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		  HttpSession session = request.getSession();
	        if(session.getAttribute("username")!=null){
	        	MaConnexion connexion;
	        	try {
					connexion = new MaConnexion();
			        ProfessorsDAO dao = new ProfessorsDAO(connexion);

			        int moduleId = Integer.parseInt(request.getParameter("moduleId"));
			        
			        List<Etudiant> students = dao.getStudentsByModuleId(moduleId);
			        for (Etudiant student : students) {
			            String noteParam = "note_" + student.getId();
			            float note = Float.parseFloat(request.getParameter(noteParam));
			            dao.affecterNoteEtudiant(moduleId, student.getId(), note);
			        }
			        				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  

	        }
	        response.sendRedirect("./prof/dashbordProf.jsp");

			
	    }
}