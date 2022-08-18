<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/13/2022
  Time: 7:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Test</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/styles/style.css">
    <link rel="stylesheet" href="/resources/styles/add-test.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <div style="display: flex; justify-content: space-between">
        <h3>Add test</h3>
        <a href="${pageContext.request.contextPath}/management/tests" class="btn btn-outline-dark" type="button">Back to List</a>
    </div>
    <hr>
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

        <div class="question-add-form" id="question-from">
            <div id="questionTable1" class="card">
                <h5>Question 1</h5>
                <input class="form-control" type="text" minlength="2" maxlength="255" placeholder="Enter question" name="question" required>
                <table class="table table-borderless" id="optionsTable1">
                    <thead>
                    <th class="checkbox">Answer</th>
                    <th>Options</th>
                    </thead>
                    <tbody id="tbody1">
                    <tr>
                        <td class="checkbox"><input class="form-checkbox" type="checkbox" name="checkbox" value="1" id="option1"></td>
                        <td colspan="2">
                            <input class="form-control" type="text" placeholder="Enter option" name="option" required>
                            <input hidden name="questionId" value="1"></td>
                    </tr>
                    <tr>
                        <td class="checkbox"><input class="form-checkbox" type="checkbox" name="checkbox" value="2" id="option2"></td>
                        <td colspan="2">
                            <input class="form-control" type="text" placeholder="Enter option" name="option" required>
                            <input hidden name="questionId" value="1"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <button class="btn btn-outline-dark" id="addOption" type="button" style="width: max-content; margin-bottom: 10px">New Option</button>
            <button class="btn btn-outline-dark" id="addQuestion" type="button" style="width: max-content; margin-bottom: 10px; float: right">New Question</button>
        </div>
        <button type="submit" class="btn btn-outline-dark" style="width: 100%">Add Test</button>
    </form:form>
</div>

<script type="text/javascript">
    var questionIndex = Number(1);
    var optionIndex = Number(2);

    $("#addOption").click(function () {
        optionIndex = optionIndex + 1;
        option = '<tr>' +
            '<td class="checkbox"><input class="form-checkbox" type="checkbox" name="checkbox" value="' + optionIndex  + '" id="option' + optionIndex  + '"></td>' +
            '<td>' +
            '<input class="form-control" type="text" placeholder="Enter option" name="option" required>' +
            '<input hidden name="questionId" value="' + questionIndex + '">' +
            '</td>' +
            '<td style="width: 5%"><input type="button" class="btn btn-outline-dark deleteOption" value="Del" onclick="deleteOption(this)"></td>' +
            '</tr>';
        console.log(questionIndex);
        $('#tbody' + questionIndex).append(option);
        console.log('new option added');
    });

    function deleteOption(r) {
        var i = r.parentNode.parentNode.rowIndex;
        document.getElementById("optionsTable" + questionIndex).deleteRow(i);
        optionIndex = optionIndex - 1;
        console.log('deleted option №' + i);
    }

    $('#addQuestion').click(function () {
        questionIndex = questionIndex + 1;
        optionIndex = optionIndex + 2;
        console.log(questionIndex + ' question');
        console.log(optionIndex + ' option');
        question =
            '<div id="questionTable' + questionIndex + '" class="card">' +
            '<div style="display: flex; justify-content: space-between">' +
            '<h5>Question ' + questionIndex + '</h5>' +
            '<button class="btn btn-outline-dark" id="deleteQuestion' + questionIndex + '" type="button" style="width: max-content; margin-bottom: 10px" onclick="deleteQuestion(this)"><span><i class="bi bi-trash"></i></span></button>' +
            '</div>' +
            '<input class="form-control" type="text" minlength="2" maxlength="255" placeholder="Enter question" name="question" required>' +
            '<table class="table table-borderless" id="optionsTable' + questionIndex + '">' +
            '<thead>' +
            '<th class="checkbox">Answer</th>' +
            '<th>Options</th>' +
            '</thead>' +
            '<tbody id="tbody' + questionIndex + '">' +
            '<tr>' +
            '<td class="checkbox"><input class="form-checkbox" type="checkbox" name="checkbox" value="' + (optionIndex - 1) + '" id="option' + (optionIndex - 1)  + '"></td>' +
            '<td colspan="2">' +
            '<input class="form-control" type="text" placeholder="Enter option" name="option" required>' +
            '<input hidden name="questionId" value="' + questionIndex + '">' +
            '</td>' +
            '</tr>' +
            '<tr>' +
            '<td class="checkbox"><input class="form-checkbox" type="checkbox" name="checkbox" value="' + optionIndex  + '" id="option' + optionIndex  + '"></td>' +
            '<td colspan="2">' +
            '<input class="form-control" type="text" placeholder="Enter option" name="option" required>' +
            '<input hidden name="questionId" value="' + questionIndex + '">' +
            '</td>' +
            '</tr>' +
            // '<tr>' +
            // '<td class="checkbox"><input class="form-checkbox" type="checkbox"></td>' +
            // '<td><input class="form-control" type="text" placeholder="Enter option"></td>' +
            // '<td style="width: 5%"><input type="button" class="btn btn-outline-dark deleteOption" value="Del" onclick="deleteOption(this)"></td>' +
            // '</tr>' +
            '</tbody>' +
            '</table>' +
            '</div>';
        $('#questionTable' + (questionIndex - 1)).after(question);
        console.log('new question added ' + questionIndex);

        var table = document.getElementById('optionsTable' + (questionIndex - 1)),
            buttons = table.getElementsByClassName("deleteOption");
        for (let i = 0; i < buttons.length; i++) {
            buttons[i].disabled = true;
        }
    });

    function deleteQuestion(q) {
        var table = q.parentNode.nextElementSibling.nextElementSibling.id;
        var rowCount = $("#" + table + " tbody tr").length;
        var del = q.id;
        var questionId = del.slice(14);
        $('#questionTable' + questionId).remove();

        if (Number(questionId) === questionIndex) {
            var optionTable = document.getElementById('optionsTable' + (questionId - 1));
                buttons = optionTable.getElementsByClassName("deleteOption");
            for (let i = 0; i < buttons.length; i++) {
                buttons[i].disabled = false;
            }
            console.log("Last question deleted");
        } else {
            console.log("qid ---- " + questionId);
            var questionTables = $("#question-from").children("div");
            for (i = questionId - 1; i < questionTables.length; i++) {
                document.getElementById(questionTables[i].id).getElementsByTagName("h5").item(0).innerText = "Question " + (i + 1);
                $("#optionsTable" + (i + 2)).attr("id", "optionsTable" + (i + 1));
                $("#tbody" + (i + 2)).attr("id", "tbody" + (i + 1));
                $("#" + questionTables[i].id).attr("id", "questionTable" + (i + 1));
            }
            console.log(questionTables);

            var optionCheckboxes = $("input[name='checkbox']");
            console.log(questionId);
            console.log(optionCheckboxes);
            var options = $("#tbody" + (questionId - 1) + " input[name='checkbox']");
            var lastOptionValue = options[options.length - 1].value;
            console.log("last option value - " + lastOptionValue);
            console.log(optionCheckboxes);

            for (i = lastOptionValue; i < optionCheckboxes.length; i++) {
                $("#" + optionCheckboxes[i].id).attr("value", Number(i) + 1).attr("id", "option" + (Number(i) + 1));
            }
            var getValue = $("input[name='questionId']");
            for (i = lastOptionValue; i < getValue.length; i++) {
                getValue[i].value = questionId;
                console.log(getValue[i].value + " -- " + questionId);
            }
        }
        questionIndex--;
        optionIndex -= rowCount;
        console.log(questionIndex + " -- " + optionIndex);

    }
</script>

</body>
</html>
