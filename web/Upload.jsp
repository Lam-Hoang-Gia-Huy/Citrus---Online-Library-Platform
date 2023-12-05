<%-- 
    Document   : Upload
    Created on : Jul 17, 2023, 10:22:13 PM
    Author     : legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Page</title>
    </head>
    <body style="background-color: antiquewhite">
        <a href="BookController"><img style="width: 15%;min-width: 186px;display: block;margin: 0px 650px" src="Image/Logo.png" alt="Logo"></a>
        <form method="post" action="BookController" style="font-size: 200%;margin: 50px 550px">
            <input type="hidden" name="id" value="${requestScope.object.id}">
            <input type="hidden" name="action" value="${requestScope.action}">
            <input type="hidden" name="uploadid" value="${requestScope.uploadid}">
            <div>Book name: <input type="text" name="name" value="${requestScope.object.name}" required></div>
            <div>Cover: <input type="text" name="cover" value="${requestScope.object.cover}" style="width: 120%" required></div>
            <div>Author: <input type="text" name="author" value="${requestScope.object.author}" required></div>
            <div>Genre: <select name="genre" required> 
                    <option value="">Please select</option>
                    <option>Fantasy</option>
                    <option>Thriller</option>
                    <option>Romance</option>
                    <option>Self-help</option>
                    <option>Science Fiction</option>
                </select>
            </div>
            <div>Synopsis: <br><textarea name="summary" required style="width:500px;height:150px;">${requestScope.object.summary}</textarea></div>                
            <div>Content (.pdf file only): <input type="text" name="content" value="${requestScope.object.content}" style="width: 120%" required></div>
            <input type="submit" value="Upload" />
        </form>
    </body>
</html>
