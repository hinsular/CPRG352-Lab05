<%-- 
    Document   : home
    Created on : 20-Apr-2022, 3:58:33 AM
    Author     : honey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyLogin</title>
    </head>
    <body>

        <h1>Home Page</h1>
        <h3>Hello ${user}.</h3>

        <div>
            <a href="login?logout">Log out</a>
        </div>

    </body>
</html>