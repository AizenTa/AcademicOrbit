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
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
            width: 100%;
            padding: 20px;
        }
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        .form-container form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .form-container label {
            margin-bottom: 10px;
        }
        .form-container input[type="text"],
        .form-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-container button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .form-container button:hover {
            background-color: #0056b3;
        }
        .chart-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        canvas {
            max-width: 100%;
            max-height: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container" style="margin-bottom:200px">
            <!-- Self-modification form -->
            <h2 style="color:#007BFF;">Modify Your Information</h2>
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
    </div>
</body>
</html>
