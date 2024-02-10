<%--
  Created by IntelliJ IDEA.
  User: yang7
  Date: 2024-02-10
  Time: 오후 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
</head>
<body>
<p><%= request.getAttribute("message") %>
<p><a href="${pageContext.request.contextPath}/seoul-wifi/home">홈으로</a></p>
</body>
</html>
