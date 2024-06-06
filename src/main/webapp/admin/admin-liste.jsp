<%@page import="DAO.MaConnexion"%>
<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.AdminDAO" %>
<%@ page import="business.Admin" %>
<%
    MaConnexion conn = new MaConnexion();
    List<Admin> admins = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    admins = dao.getAllAdminsInfos();
    
    String filterValue = request.getParameter("filterValue");
    List<Admin> filteredAdmins = new ArrayList<>();
    
    if (filterValue != null && !filterValue.isEmpty()) {
        for (Admin admin : admins) {
            if (admin.getNom().toLowerCase().contains(filterValue.toLowerCase()) || 
                admin.getPrenom().toLowerCase().contains(filterValue.toLowerCase())) {
                filteredAdmins.add(admin);
            }
        }
    } else {
        filteredAdmins = admins; // If no filter value, show all admins
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admins List</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
            animation: fadeIn 0.5s ease-in-out;
            color: #333;
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
            margin-top: 50px;
            color: #007bff;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 2px;
            font-size: 32px;
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
            animation: fadeInUp 1s ease-in-out;
        }
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(-50px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            animation: fadeInUp 0.7s ease-in-out;
        }
        th {
            background-color: #007bff;
            color: #fff;
            text-transform: uppercase;
        }
        tr:hover {
            background-color: #f5f5f5;
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
            display: inline-flex;
            align-items: center;
        }
        .add-link a:hover {
            background-color: #0056b3;
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
        .filter-form {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
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
        .filter-form input[type="text"] {
            padding: 12px;
            border-radius: 5px;
            border: 1px solid #ddd;
            margin-right: 10px;
            transition: all 0.3s;
            width: 100%;
            max-width: 400px;
        }
        .filter-form input[type="text"]:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
        .filter-form button {
            padding: 12px 20px;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .filter-form button:hover {
            background-color: #0056b3;
        }
        .hint-box {
            padding: 10px 15px;
            border-radius: 5px;
            margin-bottom: 10px;
            display: flex;
            align-items: center;
            animation: fadeIn 0.5s ease-in-out;
        }
        .hint-box i {
        	
        	margin-bottom:30px;
            margin-right: 10px;
            font-size: 25px;
            margin-top:13px
        }
    </style>
</head>
<body>
    <h1 style="margin-top:100px">Admins Liste</h1>      
    <div class="add-link">
        <a href="add-admin.jsp" style="margin-top:70px; margin-right:20px"><i class="fas fa-plus-circle"></i> Add Admin</a>
    </div>
    <form action="" method="GET" class="filter-form">
        <input type="text" name="filterValue" placeholder="Recherche ( Prenom, Nom ).">
        <button type="submit"><i class="fas fa-search"></i> Filter</button>
    </form>

    <table border="1">
        <tr>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Actions</th>
        </tr>
        <% for (Admin admin : filteredAdmins) { %>
            <tr>
                <td style="width: 30%;"><%= admin.getNom() %></td>
                <td style="width: 30%;"><%= admin.getPrenom() %></td> 
                <td style="width: 40%;">
                    <a class="edit-link" href="${pageContext.request.contextPath}/ModifierAdmin?id=<%= admin.getId() %>"><i class="fas fa-edit"></i> Edit</a>
                    <a class="delete-link" href="${pageContext.request.contextPath}/SupprimerAdmin?id=<%= admin.getId() %>"><i class="fas fa-trash-alt"></i> Delete</a>
                </td>
            </tr>
        <% } %>
    </table>
    <jsp:include page="adminnavbar.jsp" /> 
</body>
</html>