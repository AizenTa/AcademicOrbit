<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.AdminDAO, business.Professeur" %>
<%@page import="DAO.MaConnexion"%>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Professeur prof = null;

	MaConnexion conn = new MaConnexion();
    List<Professeur> professeur = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    professeur = dao.getAllProfsInfos();
    
    for (Professeur p : dao.getAllProfsInfos()) {
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
    <form action="${pageContext.request.contextPath}/Modifier" method="post">
        <input type="hidden" name="id" value="<%= prof.getId() %>">
        <label>Username:</label>
        <input type="text" name="username" value="<%= prof.getUsername() %>" required><br>
        <label>Password:</label>
        <input type="password" name="password" value="<%= prof.getPassword() %>" required><br>
        <label>Name:</label>
        <input type="text" name="name" value="<%= prof.getNom() %>" required><br>
        <label>Last Name:</label>
        <input type="text" name="lastName" value="<%= prof.getPrenom() %>" required><br>
        <label>Address:</label>
        <input type="text" name="address" value="<%= prof.getAddress() %>" required><br>
        
         <label>Sexe :</label>
            <div class="radio-group">
            	<c:if test="${prof.getSex()==Homme}">
                	<label for="female"><input type="radio" id="female" name="sex" value="Femme" > Femme</label>
                	<label for="male"><input type="radio" id="male" name="sex" value="Homme" checked> Homme</label>
                </c:if>
                  	<c:if test="${prof.getSex()==Femme}">
                	<label for="female"><input type="radio" id="female" name="sex" value="Femme" checked> Femme</label>
                	<label for="male"><input type="radio" id="male" name="sex" value="Homme" > Homme</label>
                </c:if>
            </div>
        
        <label>Age:</label>
        <input type="number" name="age" value="<%= prof.getAge() %>" required><br>
        <label>CNE Prof:</label>
        <input type="text" name="cneProf" value="<%= prof.getCne_prof() %>" required><br>
        <button type="submit">Update</button>
    </form>
    <jsp:include page="adminnavbar.jsp" />
</body>
</html>
