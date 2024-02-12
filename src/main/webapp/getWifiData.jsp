<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공공 Wifi 정보 가져오기</title>
</head>
<style>
    * {
        text-align: center;
    }
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
<body>
<h1>공공 Wifi 정보 가져오기</h1>
<%
    boolean success = (boolean) request.getAttribute("success");
    int totalCount = (int) request.getAttribute("totalCount");
    if (success) {
%>
<p><%=request.getAttribute("message")%>
<p><%=totalCount%>건의 공공 Wifi 정보를 가져왔습니다.</p>
<%
} else {
%>
<p><%=request.getAttribute("message")%>
</p>
<%
    }
%>


<a href="${pageContext.request.contextPath}/seoul-wifi/home">홈으로</a>

</body>
</html>