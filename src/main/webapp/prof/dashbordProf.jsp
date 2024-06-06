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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: 'Montserrat', sans-serif;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            color: #2c3e50;
        }
        .container {
            display: flex;
            flex-direction: row;
            justify-content: space-around;
            align-items: center;
            width: 80%;
            max-width: 1200px;
        }
        .form-container, .chart-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            width: 45%;
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
        }
        h2 {
            text-align: center;
            color: #34495e;
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
            color: #34495e;
            margin: 10px 0 5px;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #bdc3c7;
            border-radius: 5px;
            background-color: #ecf0f1;
            color: #2c3e50;
            font-size: 1rem;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }
        input[type="text"]:focus, input[type="password"]:focus {
            border-color: #2980b9;
            box-shadow: 0 0 8px rgba(41, 128, 185, 0.3);
            outline: none;
        }
        button {
            padding: 14px;
            background-color: #2c3e50;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1.2rem;
            cursor: pointer;
            transition: background 0.3s ease-in-out, transform 0.2s;
        }
        button:hover {
            background-color: #273746;
            transform: translateY(-2px);
        }
        canvas {
            max-width: 100%;
            max-height: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <!-- Self-modification form -->
            <h2 style="color:#007BFF;">Modify Your Information</h2>
            <form action="update_professor_info.jsp" method="POST">
                <input type="hidden" name="profId" value="<%= professor.getId() %>">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="<%= professor.getUsername() %>">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="<%= professor.getPassword() %>">
                <!-- Add other fields as needed -->
                <button type="submit">Save Changes</button>
            </form>

            <!-- Statistics Section -->
            <h2 style="color:#007BFF;">Statistics</h2>
            <p>Total Students: <%= totalStudents %></p>
            <p>Total Grades Entered: <%= totalGradesEntered %></p>
            <p>Percentage of Grades Entered: <%= String.format("%.2f", percentage) %>%</p>
        </div>

        <div class="chart-container">
            <!-- Graph Section -->
            <h2 style="color:#007BFF;">Grades Entered</h2>
            <canvas id="gradesChart" width="100" height="100"></canvas>
        </div>
    </div>

    <script>
        var ctx = document.getElementById('gradesChart').getContext('2d');
        var chart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ['Grades Entered', 'Grades Not Entered'],
                datasets: [{
                    label: 'Grades Entry',
                    data: [<%= totalGradesEntered %>, <%= totalStudents - totalGradesEntered %>],
                    backgroundColor: ['#36a2eb', '#ff6384'],
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                var label = context.label || '';
                                if (label) {
                                    label += ': ';
                                }
                                label += Math.round(context.raw * 100 / <%= totalStudents %>) + '%';
                                return label;
                            }
                        }
                    }
                }
            }
        });
    </script>

    <jsp:include page="profnavbar.jsp" />
</body>
</html>
