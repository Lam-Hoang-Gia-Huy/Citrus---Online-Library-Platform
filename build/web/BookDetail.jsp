<%-- 
    Document   : BookDetail
    Created on : Jul 4, 2023, 9:05:48 PM
    Author     : legion
--%>
<%@page import="User.UserDTO"%>
<%@page import="Book.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Details</title>
        <link rel="stylesheet" href="CSS/BookDetailCSS.css">
    </head>
    <body>

        <%UserDTO currentUser = (UserDTO) session.getAttribute("usersession");
            BookDTO book = null;
            book = (BookDTO) request.getAttribute("object");
        %>


        <header style="display:flex">		
            <div>
                <a href="BookController"><img class="logo" src="Image/Logo.png" alt="Logo"></a>
                <form style="float: right;margin-right: 15px" action="BookController" method="get">
                    <select name="genre" style="font-size: 100%"> 
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
            </div>
        </header>
        <div class="top">
            <div style="display:inline-block;float: left; margin:20px 30px">
                <img class="cover" src="${requestScope.object.cover}" alt="${requestScope.object.name}">
            </div>
            <div class="information">
                <strong>${requestScope.object.name} </strong>
                <div class="score">
                    <p>Score</p>
                    <span>${requestScope.object.score}</span>
                </div>
                <%
                    if (currentUser != null) {
                %>
                <div><form style="display:inline" action="BookController">                       
                        <input type="hidden" name="bookId" value="${requestScope.object.id}">
                        <%String bookmarked;
                            bookmarked = (String) request.getAttribute("bookmarkDisabled");
                            if (bookmarked != null) {
                        %>
                        <input type="hidden" name="action" value="Unbookmark">
                        <input type="submit" value="Bookmarked" class="bookmarked button1">
                        <%} else {%> 
                        <input type="hidden" name="action" value="Bookmark">
                        <input type="submit" value="Bookmark" class="bookmark button1">
                        <%}%>      
                    </form>
                </div>
                <%}%>
                <div ><form style="display:inline" action="ReviewController">
                        <input type="hidden" name="bookId" value="${requestScope.object.id}">
                        <input type="submit" value="Review" class="review button1">      
                    </form>
                </div>
                <br>
                <ul>
                    <li>Author: ${requestScope.object.author}</li>
                    <li>Genre: <a href="BookController?searchbar=&action=search&genre=${requestScope.object.genre}">${requestScope.object.genre}</a></li>
                </ul>
            </div>
            <div class="line"></div>
            <div class="right">
                <strong class="synopsis">Synopsis</strong>
                <%if (currentUser != null) {
                        if (book.getUploader_id() == currentUser.getUserID()) {
                %><a href="BookController?action=editbook&id=${requestScope.object.id}">Edit</a>
                <%}
                }%>
                <p class="summary"> ${requestScope.object.summary}</p></div>
        </div>
        <details>
            <summary class="showcontent">Show content!</summary>
            <object data="${requestScope.object.content}"
                    width="1100"
                    height="700">   
            </object>
        </details>
    </body>
</html>
