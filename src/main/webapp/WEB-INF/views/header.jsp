<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/7/2022
  Time: 11:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-dark bg-dark" style="margin-bottom: 20px">
    <div class="container-fluid">
        <a class="navbar-brand" style="font-size: x-large; font-weight: bold">TestForms</a>
        <div class="d-flex">
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light" type="button" >Log Out</a>
            <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-light" type="button" style="margin-left: 10px">Home</a>
        </div>
    </div>
</nav>
