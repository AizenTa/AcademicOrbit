<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>
<body>

<% 
    String[][] timetable = (String[][]) request.getAttribute("timetable");
    if (timetable == null) {
        timetable = new String[8][5]; // Initialize an empty timetable
    }
%>
<table border="1">
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
                    <td><%= timetable[i][j] != null ? timetable[i][j] : "" %></td>
                <% } %>
            </tr>
        <% } %>
    </tbody>
</table>
<jsp:include page="profnavbar.jsp" />
</body>
</html>
