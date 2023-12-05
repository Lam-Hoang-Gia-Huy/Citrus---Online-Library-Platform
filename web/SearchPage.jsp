<%@page import="User.UserDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : MainPage.jsp
    Created on : Jul 17, 2023, 8:27:51 AM
    Author     : legion
--%>
<%@page import="Book.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="CSS/MainPagecss.css">
    </head>
    <body>
        <%UserDTO currentUser = (UserDTO) session.getAttribute("usersession");%>
        <div id="wrapper">
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav">
                    <li class="sidebar-brand">
                        <a href="BookController"><img class="logo" src="Image/LogoBlue.png" alt="Logo"></a>
                    </li>                                                                               
                    <%
                        if (currentUser != null) {
                    %>
                    <li>
                        <% out.print("Hello, " + currentUser.getName()); %>
                    </li>
                    <li>
                        <a href="BookController?action=upload">Upload</a>
                    </li> 
                    <li>
                        <a href="BookController?action=uploadlist">Your upload</a>
                    </li>
                    <li>
                        <a href="BookController?action=bookmarklist">Your bookmark</a>
                    </li>
                    <li>
                        <a href="LoginController?action=logout">Log out</a>                     
                    </li>
                    <%} else {%>   
                    <li>

                        <a href="LoginController">Log in</a>
                    </li>
                    <li>
                        <a href="Register.jsp">Register</a>
                    </li>
                    <%}%>
                </ul>
            </div>

            <div id="page-content-wrapper">
                <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>
                <form style="float: right;margin-right: 15px" action="BookController" method="get">
                    <select name="genre"> 
                        <option value="">Select Genre</option>
                        <option>Fantasy</option>
                        <option>Thriller</option>
                        <option>Romance</option>
                        <option>Self-help</option>
                        <option>Science Fiction</option>
                    </select>
                    <input type="text" name="searchbar" placeholder="Search" style="font-size: 90%">
                    <input type="hidden" name="action" value="search">
                    <input type="submit" value="Search" style="font-size: 90%">      
                </form>

                <section id="team" class="team_area section-padding">
                    <div class="container">								
                        <h2 class="title_spectial"><% out.print(request.getAttribute("searchvalue"));%></h2>
                        <h2 class="title_spectial"><% out.print(request.getAttribute("searchgenre"));%></h2>
                        <div class="row text-center">
                            <c:forEach var="b" items="${searchlist}">                    
                                <div class="col-lg-3 col-sm-3 col-xs-12 wow fadeInUp">
                                    <div class="our-team">
                                        <div class="single-team">
                                            <a href="BookController?action=detail&id=${b.id}"><img src="${b.cover}" alt=""></a>
                                            <h3>${b.name}</h3>
                                            <p>${b.score}</p>
                                        </div>							
                                    </div>	
                                </div><!--- END COL -->	
                            </c:forEach>                                 	  
                        </div><!--- END ROW -->			
                    </div><!--- END CONTAINER -->
                </section>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
        <script type="text/javascript" src="JS/Javascript.js"></script>
    </body>
</html>
