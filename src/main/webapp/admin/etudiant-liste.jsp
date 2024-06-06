<%@page import="DAO.MaConnexion"%>
<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.AdminDAO" %>
<%@ page import="business.Etudiant" %>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	
	String username = ""; 
	 if(session.getAttribute("username")!=null){
		 username = session.getAttribute("username").toString();
	 }else{
		 response.sendRedirect("../Login.jsp");
	 }
	 
	MaConnexion conn = new MaConnexion();
    List<Etudiant> etudiants = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    etudiants = dao.getAllEtudiantInfos();

    String searchQuery = request.getParameter("searchQuery");
    List<Etudiant> filteredEtudiants = new ArrayList<>();
    
    if (searchQuery != null && !searchQuery.isEmpty()) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getNom().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredEtudiants.add(etudiant);
            }else if(etudiant.getPrenom().toLowerCase().contains(searchQuery.toLowerCase())){
                filteredEtudiants.add(etudiant);
            }else if(etudiant.getCne_student().toLowerCase().contains(searchQuery.toLowerCase())){
                filteredEtudiants.add(etudiant);
            }
        }
    } else {
        filteredEtudiants = etudiants; // If no search query, show all etudiants
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des étudiants</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #007bff;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 2px;
            font-size: 32px;
        }

        .add-link {
            text-align: right;
            margin-bottom: 20px;
        }

        .add-link a {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s;
            display: inline-flex;
            align-items: center;
        }

        .add-link a:hover {
            background-color: #0056b3;
        }

        form {
            margin-bottom: 20px;
            text-align: left;
        }

        form input[type="text"] {
            padding: 12px;
            border-radius: 5px;
            border: 1px solid #ddd;
            margin-right: 10px;
            transition: all 0.3s;
            width: 100%;
            max-width: 400px;
        }

        form input[type="text"]:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        form button {
            padding: 12px 20px;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        form button:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007bff;
            color: #fff;
            text-transform: uppercase;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .edit-link, .delete-link {
            display: inline-block;
            padding: 10px;
            border-radius: 5px;
            transition: background-color 0.3s;
            margin-right: 5px;
            color: #fff;
            text-decoration: none;
            font-size: 14px;
        }

        .edit-link i, .delete-link i {
            margin-right: 5px;
        }

        .edit-link {
            background-color: #28a745;
        }

        .edit-link:hover {
            background-color: #218838;
        }

        .delete-link {
            background-color: #dc3545;
        }

        .delete-link:hover {
            background-color: #c82333;
        }

        .add-link a i,
        form button i,
        .edit-link i,
        .delete-link i {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <h1 style="margin-top:100px">Liste des étudiants</h1>
    <div class="add-link">
        <a href="add-etudiant.jsp"><i class="fas fa-plus-circle"></i> Ajouter un étudiant</a>
    </div>
    <form action="" method="GET">
        <input type="text" name="searchQuery" placeholder="Recherche ( Prenom, Nom ou CNE ) ." required>
        <button type="submit"><i class="fas fa-search"></i> Rechercher</button>
    </form>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Prenom</th>
            <th>Nom</th>
            <th>Address</th>
            <th>Sexe</th>
            <th>Age</th>
            <th>CNE d'Etudiant</th>
            <th>Note finale d'Etudiant</th>
            <th>Abscence d'Etudiant</th>
            <th>Actions</th>
        </tr>
        <% for (Etudiant etudiant : filteredEtudiants) { %>
            <tr>
                <td><%= etudiant.getId() %></td>
                <td><%= etudiant.getPrenom() %></td>
                <td><%= etudiant.getNom() %></td>
                <td><%= etudiant.getAddress() %></td>
                <td><%= etudiant.getSex() %></td>
                <td><%= etudiant.getAge() %></td>
                <td><%= etudiant.getCne_student() %></td>
                <td><%= etudiant.getNote_finale() %></td>
                <td><%= etudiant.getAbscence_hours() %></td>
                <td>
                    <a class="edit-link" href="edit-etudiant.jsp?id=<%= etudiant.getId() %>"><i class="fas fa-edit"></i> Edit</a>
                    <a class="delete-link" href="${pageContext.request.contextPath}/SupprimerEtudiant?id=<%= etudiant.getId() %>"><i class="fas fa-trash-alt"></i> Supprimer</a>                                   
                </td>
            </tr>
        <% } %>
    </table>
    <jsp:include page="adminnavbar.jsp" />
</body>
</html>
