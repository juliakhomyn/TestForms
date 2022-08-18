<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 6/26/2022
  Time: 5:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Test</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/styles/style.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="container">
        <p hidden>${session.id}</p>
        <h3>${test.title} Test</h3>
        <p style="opacity: 50%; font-size: medium">${test.category}</p>
        <form:form action="/tests/result/${session.id}" method="post" modelAttribute="session">
            <input type="hidden" name="session" value="${session.id}">
            <c:set var="number" value="0"/>
            <ol>
                <c:forEach var="question" items="${questions}">
                    <c:set var="number" value="${number + 1}"/>
                    <li class="question">${question.title}
                        <ul class="options-list">
                            <c:forEach var="option" items="${question.options}">
                                <li>
                                    <c:if test="${question.type.name().equals('SINGLE_ANSWER')}">
                                        <input type="radio" id="${option.id}" name="question_${number}" value="${option.id}">
                                        <label for="${option.id}"> ${option.title} </label>
                                    </c:if>
                                    <c:if test="${question.type.name().equals('MULTIPLE_ANSWERS')}">
                                        <input type="checkbox" id="${option.id}" name="question_${number}" value="${option.id}">
                                        <label for="${option.id}"> ${option.title} </label>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ol>
            <button type="submit" class="btn btn-outline-dark" style="width: 100%">Submit</button>
        </form:form>
    </div>
</body>
</html>
