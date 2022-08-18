<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 6/29/2022
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Test</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="container">
        <div style="display: flex; justify-content: space-between">
            <h3>Information about test</h3>
            <a href="${pageContext.request.contextPath}/management/tests" class="btn btn-outline-dark" type="button">Back to List</a>
        </div>
        <hr>
        <div style="display: flex; justify-content: space-between">
            <h4>${test.title}</h4>
            <p style="opacity: 75%">Created by ${test.createdBy.name} ${test.createdBy.surname} ${test.createdDate.format(formatter)}</p>
        </div>
        <ol>
            <c:forEach var="question" items="${test.questions}">
                <c:set var="number" value="0"/>
                <li>
                    ${question.title}
                    <ul style="list-style-type: none">
                        <c:forEach var="option" items="${question.options}">
                            <c:set var="style" value=""/>
                            <c:if test="${option.answer}">
                                <c:set var="style" value="background-color: rgba(101,228,145,0.29); padding-left: 30px; margin-left: -30px"/>
                            </c:if>
                            <li style="${style}">${option.title}</li>
                            <c:set var="number" value="${number + 1}"/>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ol>

    </div>
</body>
</html>
