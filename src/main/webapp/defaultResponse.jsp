<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page Not Found</title>
    <style>
        .message {
            text-align: center;
            margin: 20px auto;
            font-size: 24px;
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
</head>
<body>
<div class="message">
    <p>존재하지 않는 페이지 입니다.</p>
    <p><a href="${pageContext.request.contextPath}/seoul-wifi/home">홈으로</a></p>
</div>
</body>
</html>
