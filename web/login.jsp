<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="CSS/cssLogin.css">
    </head>
    <body style="background-color: #6e9aaf">        
        <div id="login">
            <div class="d-flex justify-content-center mt-2">
                <a href="BookController"><img src="Image/Logo.png" width="200"></a></div>
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <form id="login-form" class="form" action="LoginController" method="post">
                                <h3 class="text-center text-info">Login</h3>
                                <div class="form-group">
                                    <label for="username" class="text-info">Username:</label><br>
                                    <input type="text" name="user" id="username" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="password" class="text-info">Password:</label><br>
                                    <input type="password" name="password" id="password" class="form-control">
                                </div>
                                <div class="form-group">                                   
                                    <input type="submit"class="btn btn-info btn-md" value="Login">
                                    <%! String err;%>
                                    <% err = (String) request.getAttribute("error");
                                        if (err != null) {
                                            out.print("<span class='text-info'>" + err + "</span");
                                        }%>
                                </div>
                                <div id="register-link" class="text-right">
                                    <a href="Register.jsp" class="text-info">Register here</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
