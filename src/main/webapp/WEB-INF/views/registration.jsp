<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/styles/style.css">
</head>
<body>
<nav class="navbar navbar-dark bg-dark" style="margin-bottom: 20px">
    <div class="container-fluid">
        <a class="navbar-brand" style="font-size: x-large; font-weight: bold">TestForms</a>
        <div class="d-flex">
            <a href="/login" class="btn btn-outline-light" type="button" >Log in</a>
        </div>
    </div>
</nav>
<div class="container" align="center">
    <div class="card" style="width: 50%; padding: 30px; margin: 30px">
        <h1>Registration</h1>
        <form:form action="registration" method="post" modelAttribute="user" style="margin: 0px">
            <p class="error">${message}</p>
            <div>
                <form:input path="name" placeholder="First Name" class="form-control"/>
                <small><form:errors path="name" cssClass="error"/></small>
            </div>
            <div>
                <form:input path="surname" placeholder="Last Name" class="form-control"/>
                <small><form:errors path="surname" cssClass="error"/></small>
            </div>
            <div>
                <form:input path="email" type="email" placeholder="Email" class="form-control"/>
                <small><form:errors path="email" cssClass="error"/></small>
            </div>
            <div>
                <form:input path="password" type="password" placeholder="Password" class="form-control"/>
                <small><form:errors path="password" cssClass="error"/></small>
            </div>
            <div>
                <button type="submit" class="btn btn-outline-dark" style="width: 100%">Register</button>
                <a href="/login">I have an account</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
