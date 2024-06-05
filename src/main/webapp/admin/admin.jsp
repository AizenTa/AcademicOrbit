<%@ page language="java"%>
<%@ page import="java.sql.*"%>
<%@ page import="DAO.MaConnexion"%>
<%@ page import="DAO.AdminDAO" %>
<%@ page import="business.Statistics" %>
<%@ page import="business.Admin" %>

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
    AdminDAO dao = new AdminDAO(conn);
    Admin admin = dao.getAdminByUsername(username);
    Statistics stats = dao.getStatistics();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
<style>
@import url("https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap");
:root {
    --header-height: 3rem;
    --nav-width: 68px;
    --first-color: #A9D6B9;
    --first-color-light: #0000000;
    --white-color: #F7F6FB;
    --body-font: 'Nunito', sans-serif;
    --normal-font-size: 1rem;
    --z-fixed: 100;
}

*, ::before, ::after {
    box-sizing: border-box;
}

body {
    position: relative;
    margin: var(--header-height) 0 0 0;
    padding: 0 1rem;
    font-family: var(--body-font);
    font-size: var(--normal-font-size);
    transition: .5s;
}

h2.welcome {
    font-size: 2rem;
    color: var(--first-color);
    margin-bottom: 1rem;
}

.container {
    display: flex;
}

.left, .right {
    width: 50%;
    padding: 10px;
}

form label {
    display: inline-block;
    width: 140px;
    font-weight: bold;
}

form input {
    display: inline-block;
    margin-bottom: 10px;
}

form button {
    display: inline-block;
    padding: 10px 15px;
    background-color: var(--first-color);
    color: var(--white-color);
    border: none;
    cursor: pointer;
    font-weight: bold;
}

.chart-container {
    width: 100%;
    margin: 10px 0;
}

.chart-group {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 20px;
}

.small-chart {
    width: 45% !important;
    height: 300px !important;
}

.chart-title {
    text-align: center;
    font-size: 1.2rem;
    margin-bottom: 10px;
    color: var(--first-color);
}

.help-box {
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
    background-color: #f9f9f9;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div>
    <h2 class="welcome">Bienvenue, <%= admin.getNom() %> <%= admin.getPrenom() %>!</h2>

    <div class="container">
        <div class="left">
            <h2>Modifier vos informations</h2>
            <div class="help-box">
                Si vous ne souhaitez pas changer votre mot de passe, vous pouvez répéter votre ancien mot de passe.
            </div>
            <form action="${pageContext.request.contextPath}/UpdateAdminController" method="post">
                <label for="nom">Nom:</label>
                <input type="text" id="nom" name="nom" value="<%= admin.getNom() %>" required><br>

                <label for="prenom">Prénom:</label>
                <input type="text" id="prenom" name="prenom" value="<%= admin.getPrenom() %>" required><br>

                <label for="username">Nom d'utilisateur:</label>
                <input type="text" id="username" name="username" value="<%= admin.getUsername() %>" required><br>

                <label for="password">Nouveau mot de passe:</label>
                <input type="password" id="password" name="password" ><br>

                <label for="oldPassword">Ancien mot de passe:</label>
                <input type="password" id="oldPassword" name="oldPassword" required><br>

                <button type="submit">Mettre à jour</button>
            </form>
        </div>
        <div class="right">
            <h2>Statistiques</h2>
            <div class="chart-container">
                <canvas id="totalChart"></canvas>
            </div>
        </div>
    </div>
    <div class="chart-group">
        <div class="chart-container small-chart">
            <div class="chart-title">Répartition des sexes des étudiants</div>
            <canvas id="studentGenderChart"></canvas>
        </div>
        <div class="chart-container small-chart">
            <div class="chart-title">Répartition des sexes des professeurs</div>
            <canvas id="professorGenderChart"></canvas>
        </div>
    </div>
</div>

<!-- Include the navbar -->
<jsp:include page="../success.jsp" />
<jsp:include page="adminnavbar.jsp" />

<script>
document.addEventListener('DOMContentLoaded', function () {
    const totalChart = new Chart(document.getElementById('totalChart').getContext('2d'), {
        type: 'bar',
        data: {
            labels: ['Étudiants', 'Professeurs', 'Classes', 'Modules'],
            datasets: [{
                label: 'Totaux',
                data: [<%= stats.getNumberOfStudents() %>, <%= stats.getNumberOfProfessors() %>, <%= stats.getNumberOfClasses() %>, <%= stats.getNumberOfModules() %>],
                backgroundColor: ['#36A2EB', '#FF6384', '#FFCE56', '#4BC0C0'],
                hoverOffset: 4
            }]
        }
    });

    const studentGenderChart = new Chart(document.getElementById('studentGenderChart').getContext('2d'), {
        type: 'doughnut',
        data: {
            labels: ['Hommes', 'Femmes'],
            datasets: [{
                label: 'Répartition des sexes des étudiants',
                data: [<%= stats.getMaleStudents() %>, <%= stats.getFemaleStudents() %>],
                backgroundColor: ['#36A2EB', '#FF6384'],
                hoverOffset: 4
            }]
        }
    });

    const professorGenderChart = new Chart(document.getElementById('professorGenderChart').getContext('2d'), {
        type: 'doughnut',
        data: {
            labels: ['Hommes', 'Femmes'],
            datasets: [{
                label: 'Répartition des sexes des professeurs',
                data: [<%= stats.getMaleProfessors() %>, <%= stats.getFemaleProfessors() %>],
                backgroundColor: ['#36A2EB', '#FF6384'],
                hoverOffset: 4
            }]
        }
    });
});
</script>
</body>
</html>