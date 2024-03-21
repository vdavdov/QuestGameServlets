<%@ page import="com.javarush.by.vdavdov.out.PrologOut" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prolog of Alien Quest</title>
    <script src=https://code.jquery.com/jquery-3.6.0.min.js></script>
</head>
<body>
    <div id="prolog">
        <h1>Пролог</h1>
        <p><%= new PrologOut().getTextForProlog()%>
        </p>
    </div>
    <div id="meetup">
        <h1>Знакомство с командой</h1>
        <p><%= new PrologOut().teamMeetUp()%></p>
    </div>
    <form method="post">
        <label>
            <input id="inputName" type="text" alt="Введите свое имя: " name="name">
        </label>
        <button type="submit">Представится и в путь!</button>
    </form>
</body>
</html>
