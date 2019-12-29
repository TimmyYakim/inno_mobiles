<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="user" class="com.inno.pojo.User"/>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Login page</title>
</head>
<body>
<div class="container">
    <h1>Entrance page</h1>
    <form method="post" action="${pageContext.request.contextPath}/login" autocomplete="off">
        <div class="form-group col-xs-3">
            <div>
                <label for="login">Login</label>
                <input name="login" type="text" class="form-control" id="login"
                       value="<jsp:getProperty name="user" property="login" />">
            </div>
            <div>
                <%--        <div class="form-group col-xs-3">--%>
                <label for="password">Password</label>
                <input name="password" type="text" class="form-control" id="password"
                       value="<jsp:getProperty name="user" property="password" />">
            </div>
            <button type="submit" class="btn btn-primary">Sign In</button>
        </div>
    </form>
</div>
</body>
</html>