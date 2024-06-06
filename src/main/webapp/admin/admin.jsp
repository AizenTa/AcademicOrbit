<%@ page language="java"%>
<%@ page import="java.sql.*"%>
<%@ page import="DAO.MaConnexion"%>
<%@ page import="DAO.AdminDAO" %>
<%@ page import="business.Statistics" %>
<%@ page import="business.Admin" %>

<%
    // Disable caching
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    // Check if the user is logged in
    String username = "";
    if (session.getAttribute("username") != null) {
        username = session.getAttribute("username").toString();
    } else {
        response.sendRedirect("../Login.jsp");
        return;
    }

    // Fetch admin details and statistics
    MaConnexion conn = new MaConnexion();
    AdminDAO dao = new AdminDAO(conn);
    Admin admin = dao.getAdminByUsername(username);
    Statistics stats = dao.getStatistics();
%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style>
:root {
    --primary-color: #A9D6B9;
    --secondary-color: #2A3652;
    --accent-color: #A98743;
    --background-color: #9D8CA1;
    --white-color: #9993B2;
    --dark-color: #9993B2;
    --box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
    --transition: all 0.3s ease;
}

body {
    font-family: 'Montserrat', sans-serif;
    background-color: var(--background-color);
    color: var(--dark-color);
    margin: 0;
    padding: 0;
}

h2.welcome {
    font-size: 2rem;
    color: var(--secondary-color);
    margin-bottom: 1rem;
    text-align: center;
    margin-top: 1rem;
}

h2.stats {
    font-size: 2rem;
    color: var(--secondary-color);
    margin-bottom: 1rem;
    text-align: center;
    margin-top: 1rem;
}
.container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
    align-items: center;
    margin-top: 110px;
}

.left, .right {
    flex: 1;
    min-width: 300px;
    padding: 20px;
    background: var(--white-color);
    border-radius: 15px;
    box-shadow: var(--box-shadow);
    margin-bottom: 20px;
    transition: var(--transition);
}

.left:hover, .right:hover {
    transform: translateY(-10px);
    box-shadow: 0 20px 40px rgba(0, 0, 0, 0.3);
}

form label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

form input {
    display: block;
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid var(--primary-color);
    border-radius: 5px;
    transition: var(--transition);
}

form input:focus {
    border-color: var(--secondary-color);
}

form button {
    padding: 10px 20px;
    background-color: var(--primary-color);
    color: var(--white-color);
    border: none;
    cursor: pointer;
    font-weight: bold;
    border-radius: 5px;
    transition: var(--transition);
}

form button:hover {
    background-color: var(--secondary-color);
}

.chart-container {
    width: 100%;
    margin: 20px 0;
}

.chart-group {
    display: flex;
    justify-content: space-between;
    gap: 20px;
}

.small-chart {
    width: 45%;
    max-width: px; /* Set a maximum width for the small charts */
}

.chart-title {
    text-align: center;
    font-size: 1.2rem;
    margin-bottom: 10px;
    color: var(--secondary-color);
}

.help-box {
    border: 1px solid var(--primary-color);
    padding: 10px;
    margin-bottom: 10px;
    background-color: var(--white-color);
    border-radius: 5px;
    box-shadow: var(--box-shadow);
}

@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}

.animate-fadeIn {
    animation: fadeIn 1s ease-in-out;
}
</style>
</head>
<body>
<div>
    <div class="container">
        <div class="left">
            <h2 class="welcome" style="color:#007BFF;">Bienvenue, <%= admin.getNom() %> <%= admin.getPrenom() %>!</h2>
        
            <h2 style="color:#007BFF;">Modifier vos informations</h2>
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
                <input type="password" id="password" name="password"><br>

                <label for="oldPassword">Ancien mot de passe:</label>
                <input type="password" id="oldPassword" name="oldPassword" required><br>

                <button type="submit">Mettre à jour</button>
            </form>
        </div>
        <div class="right">
            <h2 class="stats" style="color:#007BFF;">Statistiques</h2>
            <div class="chart-container">
                <canvas id="totalChart"></canvas>
            </div>
            <hr>
            <div class="chart-group">
                <div class="chart-container small-chart">
                    <div class="chart-title" style="color:#007BFF;">Répartition des sexes des étudiants</div>
                    <canvas id="studentGenderChart" width="200" height="200"></canvas>
                </div>
                <div class="chart-container small-chart">
                    <div class="chart-title" style="color:#007BFF;">Répartition des sexes des professeurs</div>
                    <canvas id="professorGenderChart" width="200" height="200"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>

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
