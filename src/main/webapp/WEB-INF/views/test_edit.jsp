<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/6/2022
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Test</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/styles/style.css">
    <link rel="stylesheet" href="/resources/styles/add-test.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="container">
        <div style="display: flex; justify-content: space-between">
            <h3>Information about test</h3>
            <a href="${pageContext.request.contextPath}/management/tests" class="btn btn-outline-dark" type="button">Back to List</a>
        </div>
        <hr>
        <div style="display: flex; justify-content: right">
            <p style="opacity: 75%">Created by ${test.createdBy.name} ${test.createdBy.surname} ${test.createdDate.format(formatter)}</p>
        </div>
        <form:form method="post" modelAttribute="test">
            <div class="row">
                <div class="column-label">
                    <p>Title</p>
                </div>
                <div class="column-content">
                    <form:input path="title" value="${test.title}" cssClass="form-control" required="required"/>
                    <small><form:errors path="title" cssClass="error"/></small>
                </div>
                <div class="column-label">
                    <p>Passing Score</p>
                </div>
                <div class="column-content">
                    <form:input type="number" path="passingScore" value="${test.passingScore}" cssClass="form-control" placeholder="0-100%"/>
                    <small><form:errors path="passingScore" cssClass="error"/></small>
                </div>
            </div>
            <div class="row">
                <div class="column-label">
                    <p>Category</p>
                </div>
                <div class="column-content">
                    <form:select path="category" multiple="false" cssClass="form-control">
                        <c:forEach var="c" items="${categories}">
                            <form:option value="${c}" label="${c.name()}"/>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="column-label">
                    <p>Active</p>
                </div>
                <div class="column-content">
                    <form:radiobutton path="active" value="true" cssStyle="margin-right: 5px"/><span>Yes</span>
                    <form:radiobutton path="active" value="false" cssStyle="margin: 0 5px 0 40px"/><span>No</span>
                </div>
            </div>
            <button type="submit" class="btn btn-outline-dark" style="width: 100%">Save Changes</button>
        </form:form>

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
