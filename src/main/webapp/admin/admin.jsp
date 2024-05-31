<%@ page language="java"%>
<%@ page import="java.sql.*"%>
<%
String username = ""; 
 if(session.getAttribute("username")!=null){
	 username = session.getAttribute("username").toString();
 }else{
	 response.sendRedirect("../Login.jsp");
 }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
<style>
        @import url("https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap");
        :root {
            --header-height: 3rem;
            --nav-width: 68px;
            --first-color:  #A9D6B9;
            --first-color-light: #0000000;
            --white-color: #F7F6FB;
            --body-font: 'Nunito', sans-serif;
            --normal-font-size: 1rem;
            --z-fixed: 100;
        }
        *, ::before, ::after {
            box-sizing: border-box;
        }
        body {
            position: relative;
            margin: var(--header-height) 0 0 0;
            padding: 0 1rem;
            font-family: var(--body-font);
            font-size: var(--normal-font-size);
            transition: .5s;
        }
        </style>
</head>
<body>

<!-- Include the navbar -->
<jsp:include page="../success.jsp" />
<jsp:include page="adminnavbar.jsp" />

</body>
</html>