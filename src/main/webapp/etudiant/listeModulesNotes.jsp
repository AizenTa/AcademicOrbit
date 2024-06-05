<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="business.Module" %>
<%@ page import="DAO.StudentDAO" %>
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
    StudentDAO dao = new StudentDAO(conn);
    int studentId = Integer.parseInt(request.getParameter("id"));
    List<Module> modules = dao.getModulesByStudId(studentId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modules et Notes</title>
    <style>
        /* Add your CSS styling here */
    </style>
</head>
<body>
    <h2>Liste des Modules et Notes</h2>
    <table border="1">
        <tr>
            <th>Nom du Module</th>
            <th>Note</th>
        </tr>
        <% for (Module module : modules) {
            float note = dao.getNoteByStudentIdAndModuleId(studentId, module.getId());
        %>
        <tr>
            <td><%= module.getName() %></td>
            <td><%= note != -1 ? note : "Aucune note disponible" %></td>
        </tr>
        <% } %>
    </table>
    <jsp:include page="etudiantnavbar.jsp" />
</body>
</html>
