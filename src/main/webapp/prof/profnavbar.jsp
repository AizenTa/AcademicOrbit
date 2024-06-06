<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java"%>
<%@ page import="java.sql.*"%>
<%@ page import="DAO.MaConnexion"%>
<%@ page import="DAO.ProfessorsDAO" %>
<%@ page import="business.Statistics" %>
<%@ page import="business.Professeur" %>
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
	    MaConnexion conn = new MaConnexion();
	    ProfessorsDAO dao = new ProfessorsDAO(conn);
	    Professeur prof = dao.getProfByUsername(username);

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Navbar and Sidebar Example</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" rel="stylesheet">
    <style>
        @import url("https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap");
        :root {
            --header-height: 3rem;
            --nav-width: 68px;
            --first-color: #A9D6B9;
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
        a {
            text-decoration: none;
        }
        .header {
            width: 100%;
            height: var(--header-height);
            position: fixed;
            top: 0;
            left: 0;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 1rem;
            background-color: var(--white-color);
            z-index: var(--z-fixed);
            transition: .5s;
        }
        .header_toggle {
            color: var(--first-color);
            font-size: 1.5rem;
            cursor: pointer;
        }
        .header_img {
            width: 35px;
            height: 35px;
            display: flex;
            justify-content: center;
            border-radius: 50%;
            overflow: hidden;
        }
        .header_img img {
            width: 40px;
        }
        .l-navbar {
            position: fixed;
            top: 0;
            left: -30%;
            width: var(--nav-width);
            height: 100vh;
            background-color: var(--first-color);
            padding: .5rem 1rem 0 0;
            transition: .5s;
            z-index: var(--z-fixed);
        }
        .nav {
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            overflow: hidden;
        }
        .nav_logo, .nav_link {
            display: grid;
            grid-template-columns: max-content max-content;
            align-items: center;
            column-gap: 1rem;
            padding: .5rem 0 .5rem 1.5rem;
        }
        .nav_logo {
            margin-bottom: 2rem;
        }
        .nav_logo-icon {
            font-size: 1.25rem;
            color: var(--white-color);
        }
        .nav_logo-name {
            color: var(--white-color);
            font-weight: 700;
        }
        .nav_link {
            position: relative;
            color: var(--first-color-light);
            margin-bottom: 1.5rem;
            transition: .3s;
        }
        .nav_link:hover {
            color: var(--white-color);
        }
        .nav_link {
            display: flex;
            align-items: center;
            padding: 10px;
            color: black;
            text-decoration: none;
        }
        
        .img-icon:hover {
            filter: invert(1);
        }
        
        .nav_icon {
            font-size: 1.25rem;
        }
        .show {
            left: 0;
        }
        .body-pd {
            padding-left: calc(var(--nav-width) + 1rem);
        }
        .active {
            color: var(--white-color);
        }
        .img-white {
            filter: invert(1);
        }
        .active::before {
            content: '';
            position: absolute;
            left: 0;
            width: 2px;
            height: 32px;
            background-color: var(--white-color);
        }
        .height-100 {
            height: 100vh;
        }
        @media screen and (min-width: 768px) {
            body {
                margin: calc(var(--header-height) + 1rem) 0 0 0;
                padding-left: calc(var(--nav-width) + 2rem);
            }
            .header {
                height: calc(var(--header-height) + 1rem);
                padding: 0 2rem 0 calc(var(--nav-width) + 2rem);
            }
            .header_img {
                width: 40px;
                height: 40px;
            }
            .header_img img {
                width: 45px;
            }
            .l-navbar {
                left: 0;
                padding: 1rem 1rem 0 0;
            }
            .show {
                width: calc(var(--nav-width) + 156px);
            }
            .body-pd {
                padding-left: calc(var(--nav-width) + 188px);
            }
        }
    </style>
</head>
<body id="body-pd">
    <header class="header" id="header">
        <div class="header_toggle"> <i class='bx bx-menu' id="header-toggle"></i> </div>
        <div class="header_img"> <img src="../img/professorpfp.png" alt="Profile Image"> </div>
    </header>
    <div class="l-navbar" id="nav-bar">
        <nav class="nav">
            <div>
                <a href="#" class="nav_logo"> 
                    <img src="../img/logodash.png" class="" alt="dashboard icon" style="width: 30px; height: 30px;">
                    <span class="nav_logo-name">Academic Orbit</span> 
                </a>
                <div class="nav_list">
                    <a href="dashbordProf.jsp" id="prof-link" class="nav_link"> 
                        <img src="../img/dashboard.png" class="img-icon" alt="dashboard icon" style="width: 30px; height: 30px; margin-left: 5%">
                        <span class="nav_name">Dashboard</span> 
                    </a>
                    <a href="profEmplois.jsp?id=<%= prof.getId() %>" id="emplois-link" class="nav_link"> 
                        <img src="../img/admin.png" class="img-icon" alt="admins icon" style="width: 30px; height: 30px; margin-left: 5%">
                        <span class="nav_name">Emplois De Temps</span> 
                    </a>
                    <a href="listeModules.jsp?id=<%= prof.getId() %>" id="classes-link" class="nav_link"> 
                        <img src="../img/class.png" class="img-icon" alt="classes icon" style="width: 30px; height: 30px; margin-left: 5%">
                        <span class="nav_name">Modules</span> 
                    </a> 
                </div>
            </div> 
            <a href="${pageContext.request.contextPath}/Logout" id="signout-link" class="nav_link"> 
                <img src="../img/signout.png" class="img-icon" alt="signout icon" style="width: 30px; height: 30px; margin-left: 5%">
                <span class="nav_name">SignOut</span> 
            </a>
        </nav>
    </div>
    <!--Container Main start-->
    <!--Container Main end-->
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function(event) { 
            const showNavbar = (toggleId, navId, bodyId, headerId) =>{
                const toggle = document.getElementById(toggleId),
                nav = document.getElementById(navId),
                bodypd = document.getElementById(bodyId),
                headerpd = document.getElementById(headerId)
                
                if(toggle && nav && bodypd && headerpd){
                    toggle.addEventListener('click', ()=>{
                        nav.classList.toggle('show')
                        toggle.classList.toggle('bx-x')
                        bodypd.classList.toggle('body-pd')
                        headerpd.classList.toggle('body-pd')
                    })
                }
            }

            showNavbar('header-toggle','nav-bar','body-pd','header')
                 
            const currentPage = window.location.pathname.split('/').pop();

            // Fonction pour ajouter la classe img-white à l'icône active
            function setActiveLink(linkId) {
                const link = document.getElementById(linkId);
                if (link) {
                    link.classList.add('active');
                    const img = link.querySelector('.img-icon');
                    if (img) {
                        img.classList.add('img-white');
                    }
                }
            }

            // Appliquer les styles en fonction de la page actuelle
            if (currentPage === 'dashbordProf.jsp') {
                setActiveLink('prof-link');
            } else if (currentPage === 'profEmplois.jsp') {
                setActiveLink('emplois-link');
            } // Ajoutez d'autres conditions pour chaque page

        });
    </script>
</body>
</html>