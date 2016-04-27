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
        <table>
            <tr>
                <th>Database ID</th>
                <th>Database Name</th>
                <th>Tables</th>
            </tr>
            <c:forEach items="${savedDatabases}" var="databases">            
            <tr>
                <td>${databases.id}</td>
                <td>${databases.name}</td>
                <td>
                    <table>
                        <tr>
                            <th>Table ID</th>
                            <th>Table Name</th>
                            <th>Columns</th>
                        </tr>
                        <c:forEach items="${databases.tables}" var="table">                            
                        <tr>
                            <td>${table.tableId}</td>
                            <td>${table.title}</td>
                            <td>
                                <table>
                                    <tr>
                                        <th>Column ID</th>
                                        <th>Column Name</th>
                                        <th>Data Type</th>
                                        <th>Relationships</th>
                                    </tr>
                                    <c:forEach items="${table.columns}" var="column">                                            
                                    <tr>
                                        <td>${column.id}</td>
                                        <td>${column.columnName}</td>
                                        <td>${column.dataType}</td>
                                        <td>
                                            <c:forEach items="${column.relation}" var="relationship">
                                            <table>
                                                <tr>
                                                    <th>Table ID</th>
                                                    <th>Column ID</th>
                                                    <th>Relationship Type</th>
                                                </tr>
                                                <tr>
                                                    <td>${relationship.tableId}</td>
                                                    <td>${relationship.columnId}</td>
                                                    <td>${relationship.relationshipType}</td>
                                                </tr>
                                            </table>
                                            </c:forEach>
                                        </td>
                                    </tr>                                            
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>                    
                    </c:forEach>
                    </table>
                </td>
            </tr>            
            </c:forEach>
        </table>
    </body>
</html>
