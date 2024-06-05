<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="business.Module" %>
<%@ page import="DAO.ProfessorsDAO" %>
<%@ page import="DAO.MaConnexion" %>
<%@ page import="business.Professeur" %>
<%@ page import="java.util.*, java.sql.*" %>

<%
    // Establish database connection
    MaConnexion conn = new MaConnexion();
    ProfessorsDAO dao = new ProfessorsDAO(conn);

    // Get professor's details
    Professeur professor = dao.getProfByUsername((String) session.getAttribute("username"));

    // Calculate the percentage of entered grades
    int totalStudents = dao.getTotalStudents();
    int totalGradesEntered = dao.getTotalGradesEntered(professor.getId());
    double percentage = totalStudents > 0 ? ((double) totalGradesEntered / totalStudents) * 100 : 0;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Professor Dashboard</title>
    <!-- Add your CSS styling here -->
</head>
<body>
    <!-- Self-modification form -->
    <h2>Modify Your Information</h2>
    <form action="update_professor_info.jsp" method="POST">
        <input type="hidden" name="profId" value="<%= professor.getId() %>">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= professor.getUsername() %>"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="<%= professor.getPassword() %>"><br>
        <!-- Add other fields as needed -->
        <button type="submit">Save Changes</button>
    </form>

    <!-- Statistics Section -->
    <h2>Statistics</h2>
    <p>Total Students: <%= totalStudents %></p>
    <p>Total Grades Entered: <%= totalGradesEntered %></p>
    <p>Percentage of Grades Entered: <%= String.format("%.2f", percentage) %>%</p>
<jsp:include page="profnavbar.jsp" />
</body>
</html>