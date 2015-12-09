<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 09.12.2015
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <form action="/edit" method="post">
        <fieldset>
            <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.UserMeal"/>
            <input hidden type="text" name="id" value=${meal.id}>
            <input type="datetime-local" name="date" value=${meal.dateTime}>Дата<br>
            <input type="text" name="description" value=${meal.description}>Описание<br>
            <input type="number" name="calories" value=${meal.calories}>Каллории
            <p><input type="submit"></p>
        </fieldset>
    </form>
</body>
</html>
