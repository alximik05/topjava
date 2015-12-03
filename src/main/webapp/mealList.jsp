<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 03.12.2015
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h1>Meals</h1>
    <jsp:useBean id="mealList" type="java.util.List" scope="request"/>
    <table>
        <c:forEach var="meal" items="${mealList}">
            <tr>
                <td>${meal.getDescription()}</td>
            </tr>
            <tr>
                <td>${meal.deteTime}</td>
            </tr>
            <tr>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</html>
