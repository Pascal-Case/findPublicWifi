<jsp:useBean id="wifiData" scope="request" type="model.WifiData"/>

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
<h1>Wifi Data</h1>
<p>${wifiData.tbPublicWifiInfo.totalListCount}건의 공공 Wifi 정보를 가져왔습니다.</p>

<a href="${pageContext.request.contextPath}/seoul-wifi/home">홈으로</a>

</body>
</html>