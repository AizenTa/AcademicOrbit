<%@page import="DAO.MaConnexion"%>
<%@page import="java.util.*, java.sql.*" %>
<%@page import="DAO.AdminDAO" %>
<%@page import="business.Module" %>
<%
    MaConnexion conn = new MaConnexion();
    List<Module> modules = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    modules = dao.getAllModulesInfos();
    
    String search = request.getParameter("search");
    List<Module> filteredModules = new ArrayList<>();
    
    if (search != null && !search.isEmpty()) {
        for (Module module : modules) {
            if (module.getName().toLowerCase().contains(search.toLowerCase())) {
                filteredModules.add(module);
            }
        }
    } else {
        filteredModules = modules; // If no search term, show all modules
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Modules List</title>
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
            padding: 6px 10px;
            border-radius: 3px;
            transition: background-color 0.3s;
            margin-right: 5px;
        }
        .edit-link {
            background-color: #007bff;
            color: #fff;
        }
        .edit-link:hover {
            background-color: #0056b3;
        }
        .delete-link {
            background-color: #dc3545;
            color: #fff;
        }
        .delete-link:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <h1>Modules List</h1>
    <div class="add-link">
        <a href="add-module.jsp">Add Module</a>
    </div>
    <form action="" method="GET" style="margin-bottom: 20px;">
        <input type="text" name="search" placeholder="Search" style="padding: 8px; border-radius: 5px;">
        <button type="submit" style="padding: 8px 20px; border-radius: 5px; background-color: #007bff; color: #fff; border: none;">Search</button>
    </form>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Module Name</th>
            <th>Actions</th>
        </tr>
        <% for (Module module : filteredModules) { %>
            <tr>
                <td><%= module.getId() %></td>
                <td><%= module.getName() %></td>
                <td>
                    <a class="edit-link" href="edit-module.jsp?id=<%= module.getId() %>">Edit</a>
                    <a class="delete-link" href="${pageContext.request.contextPath}/SupprimerModule?id=<%= module.getId() %>">Supprimer</a>
                    <a class="add-link" href="listeModuleClasse.jsp?id=<%= module.getId() %>">voir les classes</a>
                </td>
            </tr>
        <% } %>
    </table>
    <jsp:include page="adminnavbar.jsp" />
</body>
</html>