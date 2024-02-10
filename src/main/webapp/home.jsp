<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
</head>
<body>
<header>
    <div>
        <h1>서울시 공공 Wifi</h1>
        <p><%= request.getAttribute("message") %>
        </p>
    </div>
    <div>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/seoul-wifi/home">홈</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/seoul-wifi/history">위치 히스토리 목록</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/seoul-wifi/getWifiData">공공 와이파이 정보 가져오기</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/seoul-wifi/bookmark">북마크 보기</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/seoul-wifi/bookmarkGroup">북마크 그룹 관리</a>
            </li>
        </ul>
    </div>
</header>

<table>
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리 번호</th>
        <th>자치구</th>
        <th>WiFI명</th>
        <th>도로명 주소</th>
        <th>상세 주소</th>
        <th>설치 위치(층)</th>
        <th>설치 유형</th>
        <th>서비스 구분</th>
        <th>망 종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI 접속환경</th>
        <th>위도</th>
        <th>경도</th>
        <th>작업일자</th>
    </tr>
    </thead>

    <tbody></tbody>
</table>

<table>
    <thead>
    <tr>
        <th>Manager No</th>
        <th>Office</th>
        <th>Main Name</th>
        <th>Address</th>
        <th>Service</th>
        <!-- Add more table headers if needed -->
    </tr>
    </thead>
    <tbody>
    <!-- Iterate through each WifiInfo object in row list -->
    <%--    <jsp:useBean id="wifiSpot" scope="request" type="model.WifiSpot"/>--%>
    <%--    <c:forEach var="wifiSpot" items="${wifiSpot}">--%>
    <%--        <tr>--%>
    <%--            <td>${wifiSpot.X_SWIFI_MGR_NO}</td>--%>
    <%--            <td>${wifiSpot.X_SWIFI_WRDOFC}</td>--%>
    <%--            <td>${wifiSpot.X_SWIFI_MAIN_NM}</td>--%>
    <%--            <td>${wifiSpot.X_SWIFI_ADRES1}, ${wifiSpot.X_SWIFI_ADRES2}</td>--%>
    <%--            <td>${wifiSpot.X_SWIFI_SVC_SE}</td>--%>
    <%--            <!-- Add more table cells if needed -->--%>
    <%--        </tr>--%>
    <%--    </c:forEach>--%>
    </tbody>
</table>

</body>
</html>