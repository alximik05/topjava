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
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.UserMealWithExceed" scope="page"/>
            <c:choose>
                <c:when test="${meal.exceed == true}">
                    <tr style="color: red">
                </c:when>
                <c:when test="${meal.exceed == false}">
                    <tr style="color: green">
                </c:when>
            </c:choose>
                        <td>${meal.description}</td>
                        <td>${meal.dateTime}</td>
                        <td>${meal.calories}</td>
                    </tr>
        </c:forEach>
    </table>
    <form method="post" action="meals">
        <br> Описание <input type="text">
        <br> Время <input type="datetime">
        <br> Каллории <input type="text">
        <br>  <input type="submit">
    </form>
</html>
