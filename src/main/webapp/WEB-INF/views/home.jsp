<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/7/2022
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/styles/style.css">
</head>
<body>
<nav class="navbar navbar-dark bg-dark" style="margin-bottom: 20px">
    <div class="container-fluid">
        <a class="navbar-brand" style="font-size: x-large; font-weight: bold">TestForms</a>
        <div class="d-flex">
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light" type="button" >Log Out</a>
        </div>
    </div>
</nav>
<div class="container">
    <h3>${user.name} ${user.surname}'s ${user.role.roleTitle} Panel</h3>
    <div class="panel">
        <c:if test="${user.role.roleTitle == 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/management/tests"> <button class="btn btn-outline-dark">Manage tests</button></a>
            <a href="${pageContext.request.contextPath}/management/users"> <button class="btn btn-outline-dark">Manage users</button></a>
            <a href="${pageContext.request.contextPath}/management/results"> <button class="btn btn-outline-dark">See all results</button></a>
        </c:if>
        <a href="${pageContext.request.contextPath}/tests"> <button class="btn btn-outline-dark">See all tests</button></a>
    </div>
    <h5 style="margin-top: 40px">Information</h5>
    <hr>
    <div class="user-info">
        <div class="row">
            <div class="column-label-user">
                <p style="font-weight: bold">Name</p>
            </div>
            <div class="column-content-user">
                <p>${user.name} ${user.surname}</p>
            </div>
            <div class="column-label-user">
                <p style="font-weight: bold">Email</p>
            </div>
            <div class="column-content-user">
                <p>${user.email}</p>
            </div>
            <div style="width: 10%">
                <a href="/home/edit/${user.id}" class="btn btn-outline-dark"><i class="bi bi-pencil-square"></i></a>
            </div>
        </div>
        <div class="row">
            <div class="column-label-user">
                <p style="font-weight: bold">Registered</p>
            </div>
            <div class="column-content-user">
                <p>${user.createdDate.format(formatter)}</p>
            </div>
            <div class="column-label-user">
                <p style="font-weight: bold">Role</p>
            </div>
            <div class="column-content-user">
                <p>${user.role.roleTitle}</p>
            </div>
        </div>
    </div>
    <hr>
    <h5 style="margin-top: 40px">Results</h5>
    <table class="table">
        <thead>
        <tr>
            <th>Test</th>
            <th>Date</th>
            <th>Time Spent</th>
            <th>Score</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="number" value="0"/>
        <c:forEach var="session" items="${sessions}">
            <c:set var="number" value="${number + 1}"/>
            <tr class="clickable" onclick="window.location = `/tests/result/${session.id}`">
                <td>${session.test.title}</td>
                <td>${session.startTime.format(formatter)}</td>
                <td>${timeSpent.get(number - 1)}</td>
                <td>${session.score}</td>
                <td>
                    <c:if test="${session.score >= session.test.passingScore}">
                        <span>passed</span>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
