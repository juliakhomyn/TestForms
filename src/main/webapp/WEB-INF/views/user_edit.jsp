<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/5/2022
  Time: 5:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/styles/style.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="container">
        <div style="display: flex; justify-content: space-between">
            <h3>Edit information about user</h3>
            <div class="d-flex">
                <c:if test="${currentUser.role.roleTitle.equals('ADMIN')}">
                    <a href="${pageContext.request.contextPath}/management/users" class="btn btn-outline-dark" type="button">Users List</a>
                </c:if>
                <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-dark" type="button" style="margin-left: 10px">Home</a>
            </div>
        </div>
        <hr>
        <form:form method="post" modelAttribute="user">
            <div class="info">
                <div class="row">
                    <div class="column-label">
                        <p style="font-weight: bold">Name</p>
                    </div>
                    <div class="column-content">
                        <form:input path="name" value="${user.name}" cssClass="form-control"/>
                    </div>
                    <div class="column-label">
                        <p style="font-weight: bold">Surname</p>
                    </div>
                    <div class="column-content">
                        <form:input path="surname" value="${user.surname}" cssClass="form-control"/>
                    </div>
                </div>
                <div class="row">
                    <div class="column-label">
                        <p style="font-weight: bold">Email</p>
                    </div>
                    <div class="column-content">
                        <form:input path="email" value="${user.email}" cssClass="form-control"/>
                    </div>
                    <div class="column-label">
                        <p style="font-weight: bold">Role</p>
                    </div>
                    <div class="column-content">
                        <c:if test="${user.id == currentUser.id}">
                            <p>${user.role.roleTitle}</p>
                        </c:if>
                        <c:if test="${user.id != currentUser.id}">
                            <form:select path="role" multiple="false" cssClass="form-control">
                                <c:forEach var="r" items="${roles}">
                                    <form:option value="${r.id}" label="${r.roleTitle}" selected="${r.id == user.role.id ? 'selected' : ''}"/>
                                </c:forEach>
                            </form:select>
                        </c:if>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-outline-dark" style="width: 100%">Save Changes</button>
        </form:form>
    </div>
</body>
</html>
