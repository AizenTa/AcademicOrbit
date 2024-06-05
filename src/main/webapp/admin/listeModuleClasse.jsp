<%@page import="DAO.MaConnexion"%>
<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.AdminDAO" %>
<%@ page import="business.Classe" %>
<%@ page import="business.Module" %>

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
	 
	int id = Integer.parseInt(request.getParameter("id"));
	MaConnexion conn = new MaConnexion();
    List<Classe> classes = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    classes = dao.getAllModuleClasse(id);
    
%>
<!DOCTYPE html>
<html>
<head>
    <title>Module Liste</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
            animation: fadeIn 0.5s ease-in-out;
        }
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 2px;
            animation: slideInDown 0.7s ease-in-out;
        }
        @keyframes slideInDown {
            from {
                transform: translateY(-50%);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            animation: slideInLeft 0.7s ease-in-out;
        }
        @keyframes slideInLeft {
            from {
                transform: translateX(-50%);
                opacity: 0;
            }
            to {
                transform: translateX(0);
                opacity: 1;
            }
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            animation: fadeInUp 0.7s ease-in-out;
        }
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        th {
            background-color: #f2f2f2;
            color: #555;
            text-transform: uppercase;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        a {
            text-decoration: none;
            color: #007bff;
            margin-right: 10px;
            transition: color 0.3s;
        }
        a:hover {
            color: #0056b3;
        }
        .add-link {
            text-align: right;
            margin-bottom: 20px;
            animation: slideInRight 0.7s ease-in-out;
        }
        @keyframes slideInRight {
            from {
                transform: translateX(50%);
                opacity: 0;
            }
            to {
                transform: translateX(0);
                opacity: 1;
            }
        }
        .add-link a {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s;
        }
        .add-link a:hover {
            background-color: #0056b3;
        }
        .edit-link, .delete-link {
            display: inline-block;
            padding: 6px 10px; /* Adjusted padding */
            border-radius: 3px; /* Rounded corners */
            transition: background-color 0.3s;
            margin-right: 5px; /* Smaller margin between buttons */
        }
        .edit-link {
            background-color: #007bff; /* Blue color for edit */
            color: #fff;
        }
        .edit-link:hover {
            background-color: #0056b3;
        }
        .delete-link {
            background-color: #dc3545; /* Red color for delete */
            color: #fff;
        }
        .delete-link:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <h1>Professional Etudiant List</h1>
    <div class="add-link">
        <a href="add-etudiant.jsp">Ajouter Etudiant</a>
    </div>
    <form action="" method="GET" style="margin-bottom: 20px;">
        <input type="text" name="cne" placeholder="Search by Professor's CNE" style="padding: 8px; border-radius: 5px;">
        <button type="submit" style="padding: 8px 20px; border-radius: 5px; background-color: #007bff; color: #fff; border: none;">Search</button>
    </form>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Filliere</th>
            <th>Annee</th>
            <th>Actions</th>
        </tr>
        <% for (Classe classe : classes) { %>
            <tr>
                <td><%= classe.getId() %></td>
                <td><%= classe.getName() %></td>
                <td><%= classe.getFilliere() %></td>
                <td><%= classe.getGrade() %></td>
                <td>
                    <a class="edit-link" href="edit-classe.jsp?id=<%= classe.getId() %>">Edit</a>
                    <a class="delete-link" href="${pageContext.request.contextPath}/SupprimerClasse?id=<%= classe.getId() %>">Supprimer</a>                    
                    <a class="add-link" href="listeEtudiantClasse.jsp?id=<%= classe.getId() %>">Voire Classe</a>                                   
                </td>
            </tr>
        <% } %>
    </table>
    <jsp:include page="adminnavbar.jsp" />
</body>
</html>