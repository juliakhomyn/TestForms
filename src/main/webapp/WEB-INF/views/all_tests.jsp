<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 6/28/2022
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        {
            box-sizing: border-box;
        }
        .test-card {
            float: left;
            width: 40%;
            background-color: rgba(153,153,153,0.25);
            text-align: center;
            border-radius: 5px;
            margin: 10px;
            padding: 20px;
        }
        .cards {
            margin: 10px auto 10px;
        }
    </style>
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="container">
        <h3>Tests</h3>
        <div class="cards">
                <c:forEach var="test" items="${tests}">
                    <div class="test-card">
                        <h2>${test.title}</h2>
                        <small>${test.questions.size()} questions from category ${test.category}</small>
                        <br>
                        <a href="tests/take/${test.id}" class="btn btn-outline-dark" type="button" style="margin: 10px; width: available">Start Test</a>
                    </div>
                </c:forEach>
        </div>
    </div>
</body>
</html>
