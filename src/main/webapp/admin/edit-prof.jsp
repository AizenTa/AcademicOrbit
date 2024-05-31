<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.AdminDAO, business.Professors" %>
<%@page import="DAO.MaConnexion"%>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Professors prof = null;

	MaConnexion conn = new MaConnexion();
    List<Professors> professors = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    professors = dao.getAllProfessors();
    
    for (Professors p : dao.getAllProfessors()) {
        if (p.getId() == id) {
            prof = p;	
            break;
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier Professeur</title>
</head>
<body>
    <h1>Modifier Professeur</h1>
    <form action="edit-prof" method="post">
        <input type="hidden" name="id" value="<%= prof.getId() %>">
        <label>Username:</label>
        <input type="text" name="username" value="<%= prof.getUsername() %>" required><br>
        <label>Password:</label>
        <input type="password" name="password" value="<%= prof.getPassword() %>" required><br>
        <label>Name:</label>
        <input type="text" name="name" value="<%= prof.getName() %>" required><br>
        <label>Last Name:</label>
        <input type="text" name="lastName" value="<%= prof.getLast_name() %>" required><br>
        <label>Address:</label>
        <input type="text" name="address" value="<%= prof.getAddress() %>" required><br>
        <label>Sex:</label>
        <input type="text" name="sex" value="<%= prof.getSex() %>" required><br>
        <label>Age:</label>
        <input type="number" name="age" value="<%= prof.getAge() %>" required><br>
        <label>CNE Prof:</label>
        <input type="text" name="cneProf" value="<%= prof.getCne_prof() %>" required><br>
        <button type="submit">Update</button>
    </form>
</body>
</html>
