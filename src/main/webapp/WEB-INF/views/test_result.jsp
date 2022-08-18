<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 6/26/2022
  Time: 6:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/styles/style.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="container">
        <div style="display: flex; justify-content: space-between">
            <h3>${test.title} Test Results</h3>
            <a href="${pageContext.request.contextPath}/tests" class="btn btn-outline-dark" type="button">Tests</a>
        </div>
        <p>${message}</p>
        <h5>Score: ${session.score} out of ${questions.size()}</h5>
        <hr>
        <ol>
            <c:forEach var="question" items="${questions}">
                <li class="question">
                    ${question.title}
                    <ul class="options-list">
                        <c:forEach var="option" items="${question.options}">
                            <c:if test="${option.answer && choices.contains(option)}">
                                <li><span><i class="bi bi-check"></i></span> ${option.title}</li>
                            </c:if>
                            <c:if test="${!option.answer && choices.contains(option)}">
                                <li><span><i class="bi bi-x"></i></span> ${option.title}</li>
                            </c:if>
                            <c:if test="${!choices.contains(option)}">
                                <li><span><i class="bi bi-stop"></i></span> ${option.title}</li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ol>
    </div>
</body>
</html>
