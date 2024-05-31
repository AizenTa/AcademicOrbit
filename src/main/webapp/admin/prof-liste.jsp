<%@page import="DAO.MaConnexion"%>
<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.AdminDAO" %>
<%@ page import="business.Professors" %>
<%

	MaConnexion conn = new MaConnexion();
    List<Professors> professors = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    professors = dao.getAllProfessors();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Professeurs</title>
</head>
<body>
    <h1>Liste des Professeurs</h1>
    <a href="add-prof.jsp">Add New Professor</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Sex</th>
            <th>Age</th>
            <th>CNE Prof</th>
            <th>Actions</th>
        </tr>
        <%
            for (Professors prof : professors) {
        %>
        <tr>
            <td><%= prof.getId() %></td>
            <td><%= prof.getName() %></td>
            <td><%= prof.getLast_name() %></td>
            <td><%= prof.getAddress() %></td>
            <td><%= prof.getSex() %></td>
            <td><%= prof.getAge() %></td>
            <td><%= prof.getCne_prof() %></td> 
            <td>
                <a href="edit-prof.jsp?id=<%= prof.getId() %>">Edit</a> 
                <a href="delete-prof?id=<%= prof.getId() %>">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    

<jsp:include page="adminnavbar.jsp" />
</body>
</html>
