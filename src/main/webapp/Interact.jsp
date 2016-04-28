<%-- 
    Document   : Interact.jsp
    Created on : Apr 27, 2016, 9:06:56 AM
    Author     : jbeagle
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script>
            var addDatabaseOpen = false;
            var removeDatabaseOpen = false;
            var addTableOpen = false;
            var removeTableOpen = false;
            var insertDataOpen = false;
            var removeDataOpen = false;
            function openAddDatabase() {                
                addDatabaseOpen = true;
                $(".addDatabaseArea").show();                
            }
            function openRemoveDatabase() {
                removeDatabaseOpen = true;                
            }
            function openAddTable() {
                addTableOpen = true;
            }
            function openRemoveTable() {
                removeTableOpen = true;
            }
            function openInsertData() {
                insertDataOpen = true;
            }
            function openRemoveData() {
                removeDataOpen = true;
            }
            function function getTables(databaseId){
                if (/\S/.test(newName)) {
                    var url = "GetTables";
                    var data = {'databaseId', databaseId};
                    $.post(url, data, function (response) {
                        
                    });
                }
            }
            
        </script>
        <style>
            .addDatabaseArea {
                position: fixed;
                top: 8%;
                width: 80%;
                left: 8%;
                height: 80%;
                background-color: rgba(133, 133, 133, 0.95);
                padding: 2%;
                display: none;
            }
            .centered, th {
                text-align:center;
            }
            .infoTable {
                width: 95%;
            } 
            th, td, table {
                border: solid 1px;
                vertical-align: top;
            }
            th {
                background-color: #CFCFCF;
            }
            ul {
                list-style-type: none;
                margin: 0px;
                padding: 0px;
            }
            li:hover {
                background-color: #BFBFBF;
            }
            li:active {
                background-color: #ADADAD;
            }
            li {
                margin: 0px;
                padding: 5px;
            }
            .dropTop {
                margin-top: 50px;
            }
        </style>
        <title>JQL</title>
    </head>
    <body>
        <div class="row dropTop">
            <div class="centered col-sm-3">
                <input type="button" value="Add Database" onclick="openAddDatabase()"><br><br>
                <input type="button" value="Remove Database"><br><br>
                <input type="button" value="Add Table"><br><br>
                <input type="button" value="Remove/Alter Table"><br><br>
                <input type="button" value="Insert Data"><br><br>
                <input type="button" value="Remove/Alter Data">
            </div>
            <div class="col-sm-9">
                <table class="infoTable">
                    <tr>
                        <th>Database</th>
                        <th>Tables</th>
                        <th>Description</th>
                    </tr>
                    <tr>
                        <td>
                            <ul>
                                <c:if test="${databasesExist == true}">
                                    <c:forEach items="${databases}" var="database">
                                        <li onclick="getTables(${database.id})">${database.name}</li>
                                    </c:forEach>                                    
                                </c:if>                                
                            </ul>
                        </td>
                        <td>
                            <ul>
                                <li>movie</li>
                                <li>user</li>
                                <li>movie_user</li>
                            </ul>
                        </td>
                        <td>Description of Tables<br><br><br><br></td>
                    </tr>
                    <tr>
                        <th colspan="3">
                            Data
                        </th>
                    </tr>
                    <tr>
                        <td colspan="3">
                            Data in selected table...
                            <br>
                            <br>
                            <br>
                            <br>
                            <br>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="addDatabaseArea">
            <form method="POST" action="CreateDatabase">
                Database Name:&nbsp;
                <input type="text" id="newDatabaseName" placeholder="Enter database name here" name="databaseName">
                <br><br>
                <input type="submit" value="Add">
            </form>
        </div>
    </body>
</html>
