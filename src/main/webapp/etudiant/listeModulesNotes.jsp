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
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            border: 1px solid #dddddd;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .no-note {
            font-style: italic;
            color: #888;
        }
    </style>
</head>
<body>
    <h2 style="margin-top:100px; color:#007BFF;">Liste des Modules et Notes</h2>
    <table>
        <tr>
            <th>Nom du Module</th>
            <th>Note</th>
        </tr>
        <% for (Module module : modules) {
            float note = dao.getNoteByStudentIdAndModuleId(studentId, module.getId());
        %>
        <tr>
            <td><%= module.getName() %></td>
            <td><%= note != -1 ? note : "<span class='no-note'>Aucune note disponible</span>" %></td>
        </tr>
        <% } %>
    </table>
    <jsp:include page="etudiantnavbar.jsp" />
</body>
</html>
