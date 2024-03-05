<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<body>
<c:forEach var="user" items="${requestScope.users}">
    <a href="cmd-edit-user?id=${user.id}">${user.login}</a>
</c:forEach>

</body>

