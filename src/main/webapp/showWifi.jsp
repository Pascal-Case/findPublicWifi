<!-- showWifi.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>서울시 공공 와이파이 정보</title>
</head>
<body>
<h2>서울시 공공 와이파이 위치 정보</h2>
<%-- 서블릿으로부터 받은 데이터 출력 --%>
<pre><%= request.getAttribute("wifiData") %></pre>
</body>
</html>
