<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="business.Module" %>
<%@ page import="DAO.StudentDAO" %>
<%@ page import="DAO.MaConnexion" %>
<%@ page import="business.Etudiant" %>
<%@ page import="java.util.*, java.sql.*" %>

<%
    // Establish database connection
    MaConnexion conn = new MaConnexion();
	StudentDAO dao = new StudentDAO(conn);

    // Get professor's details
    Etudiant etudiant = dao.getEtudiantByUsername((String) session.getAttribute("username"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Professor Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            margin: 20px;
        }
        .form-container {
            flex: 1;
            max-width: 45%;
        }
        .chart-container {
            flex: 1;
            max-width: 45%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        canvas {
            max-width: 90%;
            max-height: 90%;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <!-- Self-modification form -->
        <h2>Modify Your Information</h2>
        <form action="update_professor_info.jsp" method="POST">
            <input type="hidden" name="profId" value="<%= etudiant.getId() %>">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%= etudiant.getUsername() %>"><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="<%= etudiant.getPassword() %>"><br>
            <!-- Add other fields as needed -->
            <button type="submit">Save Changes</button>
        </form>
    </div>

    <jsp:include page="etudiantnavbar.jsp" />
</body>
</html>
