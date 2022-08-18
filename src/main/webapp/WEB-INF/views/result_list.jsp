<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 6/28/2022
  Time: 11:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Results</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/styles/style.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="container">
        <h3>Results</h3>
        <hr>
        <div>
            <form method="post" action="results">
                <div style="display: flex; justify-content: space-between">
                    <div style="display: flex">
                        <h6>See results from </h6>
                        <input name="start" type="date" class="form-control" style="margin: 0 10px 0 10px; width: fit-content">
                        <h6>to</h6>
                        <input name="end" type="date" class="form-control" style="margin: 0 10px 0 10px; width: max-content">
                    </div>
                    <input type="submit" value="See Results" class="btn btn-outline-dark" style="width: 50%">
                </div>
            </form>
        </div>
        <hr>
        <table class="table">
            <thead>
            <tr>
                <th>№</th>
                <th>User</th>
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
                    <td>${number}</td>
                    <td>${session.user.name} ${session.user.surname}</td>
                    <td>${session.test.title}</td>
                    <td>${session.startTime.format(formatter)}</td>
                    <td>${timeSpent.get(number - 1 )}</td>
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
