<%@page import="java.util.List"%>
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
        <title>Main Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="CSS/MainPagecss.css">
    </head>
    <body>
        <%UserDTO currentUser = (UserDTO) session.getAttribute("usersession");
            List<BookDTO> list = (List<BookDTO>) request.getAttribute("BookmarkList");
        %>
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
                        <% out.print("Hello, "+currentUser.getName()); %>
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
                <a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><strong>Toggle Menu</strong></a>
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
                <section>
                    <div class="container">								
                        <h2 class="title_spectial">Has just been uploaded</h2>	
                        <div class="row text-center">
                            <c:forEach var="n" items="${newestupload}" end="3">                    
                                <div class="col-lg-3 col-sm-3 col-xs-12 wow fadeInUp">
                                    <div class="our-team">
                                        <div class="single-team">
                                            <a href="BookController?action=detail&id=${n.id}"><img src="${n.cover}" alt=""></a>
                                            <h3>${n.name}</h3>
                                            <p>${n.score}</p>
                                        </div>							
                                    </div>	
                                </div><!--- END COL -->	
                            </c:forEach>                                 	  
                        </div><!--- END ROW -->			
                    </div><!--- END CONTAINER -->
                </section>
                <%
                    if (currentUser != null && list.size() > 0) {
                %>
                <section id="team" class="team_area section-padding">
                    <div class="container">								
                        <h2 class="title_spectial">In your list</h2>	
                        <div class="row text-center">
                            <c:forEach var="b" items="${BookmarkList}" end="3">                    
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
                <%}%>
                <section id="bod" class="bod_area">
                    <div class="container">								
                        <h2 class="title_spectial">Highly rated by readers</h2>
                        <div class="row text-center">
                            <c:forEach var="p" items="${PopularList}" end="7">
                                <div class="col-lg-3 col-sm-3 col-xs-12 wow fadeInLeft">
                                    <div class="our-bod">
                                        <div class="single-bod">
                                            <a href="BookController?action=detail&id=${p.id}"><img src="${p.cover}" alt=""></a>
                                            <h3>${p.name}</h3>
                                            <p>${p.score}</p>
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
