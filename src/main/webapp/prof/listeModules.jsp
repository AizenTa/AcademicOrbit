<!-- modules.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DAO.ProfessorsDAO" %>
<%@ page import="business.Module" %>
<%@ page import="business.Classe" %>
<%@ page import="DAO.MaConnexion" %>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

String username = ""; 
if (session.getAttribute("username") != null) {
    username = session.getAttribute("username").toString();
} else {
    response.sendRedirect("../Login.jsp");
}

MaConnexion conn = new MaConnexion();
ProfessorsDAO dao = new ProfessorsDAO(conn);
int profId = Integer.parseInt(request.getParameter("id"));
List<Module> modules = dao.getModulesByProfId(profId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modules Enseignés</title>
    <style>
        /* Add your CSS styling here */
    </style>
</head>
<body>
    <h2>Liste des Modules Enseignés</h2>
    <table border="1">
        <tr>
            <th>Nom du Module</th>
            <th>Classes</th>
        </tr>
        <% for (Module module : modules) {
            List<Classe> classes = dao.getClassesByModuleId(module.getId());
        %>
        <tr>
            <td><%= module.getName() %></td>
            <td>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Filliere</th>
                        <th>Annee</th>
                        <th>Action</th>
                    </tr>
                    <% for (Classe classe : classes) { %>
                    <tr>
                        <td><%= classe.getId() %></td>
                        <td><%= classe.getName() %></td>
                        <td><%= classe.getFilliere() %></td>
                        <td><%= classe.getGrade() %></td>
                        <td><a href="${pageContext.request.contextPath}/AffecterNotesServlet?moduleId=<%= module.getId() %>&classId=<%= classe.getId() %>">Affecter Note</a></td>
                    </tr>
                    <% } %>
                </table>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>