<%@ page import="com.javarush.by.vdavdov.repository.AlienQuestRepository" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alien Quest</title>
</head>
<body>
<c:set var="score" value="${sessionScope.get('score')}"/>
    <form method="post">
        <p><c:choose>
            <c:when test="${score == 0}">
                Ты потерял память. Принять вызов НЛО?
            </c:when>
            <c:when test="${score == 1}">
                Ты принял вызов. Поднимешься на мостик к капитану?
            </c:when>
            <c:when test="${score == 2}">
                Ты поднялся на мостик. Ты кто?
            </c:when>
            <c:otherwise>Все плохо...</c:otherwise>
        </c:choose></p>
        <p><input type="radio" name="answer" value="true">
            <c:choose>
                <c:when test="${score == 0}">
                    Принять вызов НЛО
                </c:when>
                <c:when test="${score == 1}">
                    Подняться на мостик
                </c:when>
                <c:when test="${score == 2}">
                    Рассказать правду о себе
                </c:when>
                <c:otherwise>Все плохо...</c:otherwise>
            </c:choose>
        </p>
        <p><input type="radio" name="answer" value="false">
            <c:choose>
                <c:when test="${score == 0}">
                    Отклонить вызов
                </c:when>
                <c:when test="${score == 1}">
                    Отказаться подниматься на мостик
                </c:when>
                <c:when test="${score == 2}">
                    Солгать о себе
                </c:when>
                <c:otherwise>Все плохо...</c:otherwise>
            </c:choose>
        </p>
        <p><input type="submit" name="answer" value="Ответить"></p>
    </form>
    <div>
        <p>Инфориусcc: </p>
        <p>Ip address: ${sessionScope.get("address")}</p>
        <p>Name: ${sessionScope.get("name")}</p>
        <p>ID: ${sessionScope.get("id")}</p>
        <p>Curr score: ${sessionScope.get("score")}</p>
    </div>
</body>
</html>
