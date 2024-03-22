<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вы проиграли</title>
    <link type="image/x-icon" href="${pageContext.request.contextPath}/static/favicon.png" rel="shortcut icon">
</head>
<body>
    <c:set var="name" value="${sessionScope.get('name')}"/>
    <h1>Сожалеем, <c:out value="${name}"/></h1>
    <h2>Вы проиграли. Хотите начать заново?</h2>
    <form method="post">
        <input type="submit" value="Рестарт" name="restart">
    </form>
</body>
</html>
