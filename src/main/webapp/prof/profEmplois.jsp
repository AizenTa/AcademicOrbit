<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.ProfessorsDAO, business.Professeur" %>
<%@page import="DAO.MaConnexion"%>

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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emploi du Temps du Professeur</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
        padding: 20px;
    }
    table {
        width: 100%;
        border-collapse: separate;
        border-spacing: 0;
        margin-top: 100px;
        background-color: #ffffff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    th, td {
        width: 100px;
        height: 80px;
        text-align: center;
        border: 1px solid #dddddd;
        box-sizing: border-box;
    }
    th {
        background-color: #007bff; /* Changed from green to blue */
        color: white;
        font-weight: bold;
        padding: 10px;
    }
    td {
        padding: 5px;
    }
    .timetable-cell {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100%;
    }
    .timetable-cell div {
        margin: 2px 0;
    }
    .empty-cell {
        background-color: #f9f9f9;
    }
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    tr:nth-child(odd) {
        background-color: #ffffff;
    }
</style>

</head>
<body>

<% 
int id = Integer.parseInt(request.getParameter("id"));
    MaConnexion connexion = new MaConnexion();
    ProfessorsDAO profDAO = new ProfessorsDAO(connexion);
    String[][] timetable = profDAO.showProfessorTimetable(id);
    request.setAttribute("timetable", timetable);
    if (timetable == null) {
        timetable = new String[8][5]; // Initialize an empty timetable
    }
%>
<table>
    <thead>
        <tr>
            <th>Jours</th>
            <th>08:00-09:00</th>
            <th>09:00-10:00</th>
            <th>10:00-11:00</th>
            <th>11:00-12:00</th>
            <th>14:00-15:00</th>
            <th>15:00-16:00</th>
            <th>16:00-17:00</th>
            <th>17:00-18:00</th>
        </tr>
    </thead>
    <tbody>
        <% 
            String[] days = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
            for (int j = 0; j < days.length; j++) {
        %>
            <tr>
                <td><%= days[j] %></td>
                <% for (int i = 0; i < 8; i++) { %>
                    <td class="<%= (timetable[i][j] == null || timetable[i][j].isEmpty()) ? "empty-cell" : "" %>">
                        <div class="timetable-cell">
                            <% if (timetable[i][j] != null && !timetable[i][j].isEmpty()) { 
                                String[] details = timetable[i][j].split(",");
                                for (String detail : details) {
                            %>
                                    <div><%= detail.trim() %></div>
                            <% 
                                }
                            } 
                            %>
                        </div>
                    </td>
                <% } %>
            </tr>
        <% } %>
    </tbody>
</table>
<jsp:include page="profnavbar.jsp" />
</body>
</html>