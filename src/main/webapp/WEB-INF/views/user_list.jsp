<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/4/2022
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/styles/style.css">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <div style="display: flex; justify-content: space-between">
        <h3>Users</h3>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-dark" type="button">Back</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr class="clickable" onclick="window.location = `users/${user.id}`">
                <td>${user.id}</td>
                <td>${user.name} ${user.surname}</td>
                <td>${user.email}</td>
<%--                <td><a href="users/${user.id}">Details</a></td>--%>
<%--                <td><a href="users/edit/${user.id}">Edit</a></td>--%>
<%--                <td><a href="users/delete/${user.id}">Delete</a></td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
