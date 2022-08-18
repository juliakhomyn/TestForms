<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 6/25/2022
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Tests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="container">
        <div style="display: flex; justify-content: space-between">
            <h3>Tests</h3>
            <div>
                <a href="${pageContext.request.contextPath}/management/tests/add" class="btn btn-outline-dark" type="button">New Test</a>
                <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-dark" type="button" style="margin-left: 10px">Back</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>№</th>
                <th>Title</th>
                <th>Category</th>
                <th>Created By</th>
                <th>Created On</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="number" value="0"/>
            <c:forEach var="test" items="${tests}">
                <c:set var="number" value="${number + 1}"/>
                <tr>
                    <td>${number}</td>
                    <td>${test.title}</td>
                    <td>${test.category}</td>
                    <td>${test.createdBy.name} ${test.createdBy.surname}</td>
                    <td>${test.createdDate.format(formatter)}</td>
                    <td>
                        <c:if test="${test.active}">
                            <a href="/tests/take/${test.id}">Start</a>
                        </c:if>
                        <c:if test="${!test.active}">
                            <a>Start</a>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${test.sessions.size() == 0}">
                            <a href="tests/edit/${test.id}">Edit</a>
                        </c:if>
                        <c:if test="${test.sessions.size() != 0}">
                            <a>Edit</a>
                        </c:if>
                    </td>
                    <td><a href="tests/${test.id}">Details</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
