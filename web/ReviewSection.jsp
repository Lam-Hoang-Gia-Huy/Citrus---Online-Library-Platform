<%@page import="User.UserDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : CommentSection
    Created on : Jul 13, 2023, 5:03:54 PM
    Author     : legion
--%>
<%@page import="Review.ReviewDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css">
        <link rel="stylesheet" href="CSS/CSS.css">
    </head>
    <body>

        <%UserDTO currentUser = (UserDTO) session.getAttribute("usersession");%>

        <div class="container justify-content-center mt-5 border-left border-right">
            <%
                if (currentUser != null) {
            %>
            <form action="ReviewController">
                <div class="d-flex justify-content-center pt-3 pb-2"> <input type="text" name="content" placeholder="+ Add a review" required="" class="form-control addtxt"> </div>
                <div class="button">
                    <input type="hidden" value="<%out.print(request.getAttribute("bookId"));%>" name="bookId">  
                    <input type="hidden" value="Review" name="action">                    
                    <span>Score: </span><select name="score" required>
                        <option value="">Please select</option>                   
                        <% for (int i = 0; i <= 10; i++) {%>
                        <option><%=i%></option> <%}%>                        
                    </select>
                    <input type="submit" value="Ok" >
                </div>
            </form>
            <%}%>
            <c:forEach var="review" items="${list}">            
                <div class="d-flex justify-content-center py-2">
                    <div class="second py-2 px-2"> <span class="text1">${review.content}</span>
                        <div class="d-flex justify-content-between py-1 pt-2">
                            <div><img src="Image/user_icon.png" width="18"><span class="text2">${review.owner}</span></div>
                            <div><span class="text3">Score</span><span class="thumbup"><i class="fa fa-thumbs-o-up"></i></span><span class="text4">${review.score}</span></div>
                        </div>
                    </div>
                </div>                        
            </c:forEach>
        </div>
    </body>
</html>
