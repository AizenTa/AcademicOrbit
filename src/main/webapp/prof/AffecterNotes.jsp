<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DAO.ProfessorsDAO" %>
<%@ page import="business.Module" %>
<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.ProfessorsDAO, business.Classe, business.Etudiant" %>
<%@page import="DAO.MaConnexion"%>

<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String username = ""; 
	 if(session.getAttribute("username")!=null){
		 username = session.getAttribute("username").toString();
	 }else{
		 response.sendRedirect("../Login.jsp");
	 }
	MaConnexion conn = new MaConnexion();

    ProfessorsDAO dao = new ProfessorsDAO(conn);
    int moduleId = Integer.parseInt(request.getParameter("moduleId"));
    List<Etudiant> etudiants = dao.getStudentsByModuleId(moduleId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Affecter Notes</title>
</head>
<body>
    <h2>Affecter Notes pour le Module <%= moduleId %></h2>
    <form action="${pageContext.request.contextPath}/EnregistrerNotesServlet" method="post">
        <input type="hidden" name="moduleId" value="<%= moduleId %>">
        <table border="1">
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Note</th>
            </tr>
            <%
				    for (Etudiant etudiant : etudiants) {
				        // Get the existing note for the student if it exists
				        float existingNote = dao.getNoteByStudentIdAndModuleId(etudiant.getId(), moduleId);
				%>
				<tr>
				    <td><%= etudiant.getNom() %></td>
				    <td><%= etudiant.getPrenom() %></td>
				    <td><input type="text" name="note_<%= etudiant.getId() %>" value="<%= existingNote %>"></td>
				</tr>
				<% } %>

        </table>
        <button type="submit">Enregistrer</button>
    </form>
</body>
</html>