<%-- 
    Document   : ShowCurrent
    Created on : Apr 15, 2016, 2:02:21 PM
    Author     : jbeagle
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Current</title>
    </head>
    <body>
        <div><c:out value="${savedData}" /></div>
        <div><c:out value="${test}" /></div>

    </body>
</html>
