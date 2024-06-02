<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="DAO.AdminDAO, business.Classe" %>
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
    Classe classe = null;

	MaConnexion conn = new MaConnexion();
    List<Classe> classes = new ArrayList<>();
    AdminDAO dao = new AdminDAO(conn);
    classes = dao.getAllClasseInfos();
    
    for (Classe c : dao.getAllClasseInfos()) {
        if (c.getId() == id) {
            classe = c;	
            break;
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier Classe</title>
</head>
<body>
    <h1>Modifier Classe</h1>
    <form action="${pageContext.request.contextPath}/Modifier" method="post">
        <input type="hidden" name="id" value="<%= classe.getId() %>">
        <label>Name : </label>
        <input type="text" name="name" value="<%= classe.getName() %>" required><br>
        <label>Filliere : </label>
        <input type="text" name="filliere" value="<%= classe.getFilliere() %>" required><br>
        <label>Annee :</label>
        <input type="text" name="grade" value="<%= classe.getGrade() %>" required><br>
        
        <button type="submit">Update</button>
    </form>
    <jsp:include page="adminnavbar.jsp" />
</body>
</html>
