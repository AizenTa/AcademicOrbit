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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modules List</title>
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
    </style>
</head>
<body>
    <h1 style="margin-top:100px">Modules Liste</h1>
    <div class="botona">
        <a href="add-module.jsp" style=""><i class="fas fa-plus-circle" style="text-align: right;"></i> Add Module</a>
    </div>
    <form action="" method="GET">
        <input type="text" name="search" placeholder="Search" required>
        <button type="submit"><i class="fas fa-search"></i> Search</button>
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
                    <a class="edit-link" href="edit-module.jsp?id=<%= module.getId() %>"><i class="fas fa-edit"></i> Edit</a>
                    <a class="delete-link" href="${pageContext.request.contextPath}/SupprimerModule?id=<%= module.getId() %>"><i class="fas fa-trash-alt"></i> Delete</a>
                    <a class="add-link" href="listeModuleClasse.jsp?id=<%= module.getId() %>"><i class="fas fa-list"></i> View Classes</a>
                </td>
            </tr>
        <% } %>
    </table>
    <jsp:include page="adminnavbar.jsp" />
</body>
</html>
