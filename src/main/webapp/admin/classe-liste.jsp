<%@page import="DAO.MaConnexion"%>
<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.AdminDAO" %>
<%@ page import="business.Classe" %>
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
    List<Classe> classes = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    classes = dao.getAllClasseInfos();
    
    String filterValue = request.getParameter("filterValue");
    List<Classe> filteredClasses = new ArrayList<>();
    
    if (filterValue != null && !filterValue.isEmpty()) {
        for (Classe classe : classes) {
            if (classe.getName().toLowerCase().contains(filterValue.toLowerCase()) || 
                classe.getFilliere().toLowerCase().contains(filterValue.toLowerCase()) || 
                classe.getGrade().toLowerCase().contains(filterValue.toLowerCase())) {
                filteredClasses.add(classe);
            }
        }
    } else {
        filteredClasses = classes; // If no filter value, show all classes
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Professors List</title>
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

        .edit-link, .delete-link, .add-link {
            display: inline-block;
            padding: 10px;
            border-radius: 5px;
            transition: background-color 0.3s;
            margin-right: 5px;
            color: #fff;
            text-decoration: none;
            font-size: 14px;
        }
  		.botona {
            text-align: right;
            margin-bottom: 20px;
        }

        .botona a {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s;
            display: inline-flex;
            align-items: center;
        }

        .botona a:hover {
            background-color: #0056b3;
        }
        .edit-link i, .delete-link i, .add-link i {
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

        .add-link {
            background-color: #007bff;
        }

        .add-link:hover {
            background-color: #0056b3;
        }

        .add-link a i,
        form button i,
        .edit-link i,
        .delete-link i,
        .add-link i {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <h1 style="margin-top:100px">Classes Liste</h1>
    <div class="botona">
        <a href="add-classe.jsp"><i class="fas fa-plus-circle"></i> Add Classe</a>
    </div>
    <form action="" method="GET">
        <input type="text" name="filterValue" placeholder="Recherche ( Nom , Filliere ou Annee )." required>
        <button type="submit"><i class="fas fa-search"></i> Search</button>
    </form>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Filliere</th>
            <th>Annee</th>
            <th>Actions</th>
        </tr>
        <% for (Classe classe : filteredClasses) { %>
            <tr>
                <td><%= classe.getId() %></td>
                <td><%= classe.getName() %></td>
                <td><%= classe.getFilliere() %></td>
                <td><%= classe.getGrade() %></td>
                <td>
                    <a class="edit-link" href="edit-classe.jsp?id=<%= classe.getId() %>"><i class="fas fa-edit"></i> Edit</a>
                    <a class="delete-link" href="${pageContext.request.contextPath}/SupprimerClasse?id=<%= classe.getId() %>"><i class="fas fa-trash-alt"></i> Delete</a>
                    <a class="add-link" href="listeEtudiantClasse.jsp?id=<%= classe.getId() %>"><i class="fas fa-external-link-alt"></i> View Classe</a>
                </td>
            </tr>
        <% } %>
    </table>
    <jsp:include page="adminnavbar.jsp" />
</body>
</html>
