<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.AdminDAO, business.Etudiant" %>
<%@page import="DAO.MaConnexion"%>

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
    Etudiant etudiant = null;

	MaConnexion conn = new MaConnexion();
    List<Etudiant> etudiants = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    etudiants = dao.getAllEtudiantInfos();
    
    for (Etudiant E : dao.getAllEtudiantInfos()) {
        if (E.getId() == id) {
            etudiant = E;	
            break;
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier Etudiant</title>
</head>
<body>
    <h1>Modifier Etudiant</h1>
    <form action="${pageContext.request.contextPath}/ModifierEtudiant" method="post">
        <input type="hidden" name="id" value="<%= etudiant.getId() %>">
        <label>Username:</label>
        <input type="text" name="username" required><br>
        <label>Password:</label>
        <input type="password" name="password" required><br>
        <label>Name:</label>
        <input type="text" name="name" value="<%= etudiant.getNom() %>" required><br>
        <label>Last Name:</label>
        <input type="text" name="lastName" value="<%= etudiant.getPrenom() %>" required><br>
        <label>Address:</label>
        <input type="text" name="address" value="<%= etudiant.getAddress() %>" required><br>
        
           <div class="radio-group">
                <label for="male">
                    <input type="radio" id="male" name="sex" value="Homme" <%= "Homme".equals(etudiant.getSex()) ? "checked" : "" %>> Homme
                </label>
                <label for="female">
                    <input type="radio" id="female" name="sex" value="Femme" <%= "Femme".equals(etudiant.getSex()) ? "checked" : "" %>> Femme
                </label>
            </div>
        
        <label>Age:</label>
        <input type="number" name="age" value="<%= etudiant.getAge() %>" required><br>
        <label>CNE Etudiant:</label>
        <input type="text" name="cneEtudiant" value="<%= etudiant.getCne_student() %>" required><br>
        <label>Abscence Etudiant:</label>
        <input type="text" name="abscenceHours" value="<%= etudiant.getAbscence_hours() %>" required><br>
        <button type="submit">Update</button>
    </form>
    <jsp:include page="adminnavbar.jsp" />
</body>
</html>
