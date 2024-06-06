<%@page import="DAO.MaConnexion"%>
<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.AdminDAO" %>
<%@ page import="business.Professeur" %>
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
    List<Professeur> professeurs = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    professeurs = dao.getAllProfsInfos();

    String searchQuery = request.getParameter("searchQuery");
    List<Professeur> filteredProfesseurs = new ArrayList<>();
    
    if (searchQuery != null && !searchQuery.isEmpty()) {
        for (Professeur professeur : professeurs) {
            if (professeur.getNom().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredProfesseurs.add(professeur);
            }else if(professeur.getPrenom().toLowerCase().contains(searchQuery.toLowerCase())){
            	filteredProfesseurs.add(professeur);
            }else if(professeur.getCne_prof().toLowerCase().contains(searchQuery.toLowerCase())){
            	filteredProfesseurs.add(professeur);
            }
        }
    } else {
        filteredProfesseurs = professeurs; // If no search query, show all professors
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Professional Professors Dashboard</title>
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

        .search-form {
            margin-bottom: 20px;
            text-align: left;
        }

        .search-form input[type="text"] {
            padding: 12px;
            border-radius: 5px;
            border: 1px solid #ddd;
            margin-right: 10px;
            transition: all 0.3s;
            width: 100%;
            max-width: 400px;
        }

        .search-form input[type="text"]:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        .search-form button {
            padding: 12px 20px;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .search-form button:hover {
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
        .search-form button i,
        .edit-link i,
        .delete-link i {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <h1 style="margin-top:100px">Professeurs Liste</h1>
    <div class="add-link">
        <a href="add-prof.jsp"><i class="fas fa-plus-circle"></i> Ajouter Professeurs</a>
    </div>
    <div class="search-form">
        <form action="" method="GET">
            <input type="text" name="searchQuery" placeholder="Recherche ( Prenom, Nom ou CNE ) ." required>
            <button type="submit"><i class="fas fa-search"></i> Search</button>
        </form>
    </div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Addresse</th>
            <th>Sexe</th>
            <th>Age</th>
            <th>Professeur ID</th>
            <th>Actions</th>
        </tr>
        <% for (Professeur prof : filteredProfesseurs) { %>
            <tr>
                <td><%= prof.getId() %></td>
                <td><%= prof.getPrenom() %></td>
                <td><%= prof.getNom() %></td>
                <td><%= prof.getAddress() %></td>
                <td><%= prof.getSex() %></td>
                <td><%= prof.getAge() %></td>
                <td><%= prof.getCne_prof() %></td>
                <td>
                    <a class="edit-link" href="edit-prof.jsp?id=<%= prof.getId() %>"><i class="fas fa-edit"></i> Edit</a>
                    <a class="delete-link" href="${pageContext.request.contextPath}/Supprimer?id=<%= prof.getId() %>"><i class="fas fa-trash-alt"></i> Delete</a>
                </td>
            </tr>
        <% } %>
    </table>
    <jsp:include page="adminnavbar.jsp" /> 
</body>
</html>
