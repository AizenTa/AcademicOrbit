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
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f5f5f5;
        margin: 0;
        padding: 0;
    }
    h2 {
        color: #333;
        text-align: center;
        margin-top: 20px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        background-color: #fff;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }
    th, td {
        border: 1px solid #ddd;
        padding: 16px; /* Increased padding for better spacing */
        text-align: left;
        font-size: 16px; /* Increased font size */
    }
    th {
        background-color: #007bff;
        color: #fff;
        font-weight: bold;
    }
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    tr:hover {
        background-color: #ddd;
    }
    .inner-table {
        width: 100%;
        border-collapse: collapse;
    }
    .inner-table th, .inner-table td {
        border: 1px solid #ddd;
        padding: 14px; /* Increased padding */
        text-align: left;
        font-size: 14px; /* Increased font size */
    }
    .inner-table th {
        background-color: #f2f2f2;
        font-weight: bold;
    }
    .inner-table tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    .inner-table tr:hover {
        background-color: #ddd;
    }
    .action-link {
        text-decoration: none;
        color: #007bff;
        font-weight: bold;
    }
    .action-btn {
        display: inline-block;
        padding: 10px 16px; /* Increased padding */
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
    }
    .action-btn:hover {
        background-color: #0056b3;
    }
    .icon {
        margin-right: 5px;
    }
</style>


</head>
<body>
    <h2 style="margin-top:100px; color:#007BFF;">Liste des Modules Enseignés</h2>
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
<td>
    <a href="${pageContext.request.contextPath}/AffecterNotesServlet?moduleId=<%= module.getId() %>&classId=<%= classe.getId() %>" class="action-btn">
        <span class="icon">&#128196;</span> <!-- Icon for "Affecter Note" -->
        Affecter Note
    </a>
</td>
                    </tr>
                    <% } %>
                </table>
            </td>
        </tr>
        <% } %>
    </table>
        <jsp:include page="profnavbar.jsp" />
    
</body>
</html>