<%@ page import="model.WifiInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="model.WifiSpot" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/general.css">
    <%--    <link rel="stylesheet"--%>
    <%--          href="${pageContext.request.contextPath}/resources/css/home.css">--%>
</head>
<body>
<header>
    <div>
        <h1>내 근처 공공 WIFI 찾기</h1>
    </div>
    <div>
        <ul class="menu">
            <li><a href="${pageContext.request.contextPath}/seoul-wifi/home">홈</a></li>
            <li><a href="${pageContext.request.contextPath}/seoul-wifi/history">위치 히스토리 목록</a></li>
            <li><a href="${pageContext.request.contextPath}/seoul-wifi/getWifiData">공공 와이파이 정보 가져오기</a></li>
            <li><a href="${pageContext.request.contextPath}/seoul-wifi/bookmark">북마크 보기</a></li>
            <li><a href="${pageContext.request.contextPath}/seoul-wifi/bookmarkGroup">북마크 그룹 관리</a></li>
        </ul>
    </div>
</header>

<div class="container">
    <form action="${pageContext.request.contextPath}/seoul-wifi/searchWifi" method="get">
        <div class="form-group">
            <label for="lat">위도(LAT) </label>
            <input type="text" id="lat" name="lat" required>
            <label for="lnt">경도(LNT) </label>
            <input type="text" id="lnt" name="lnt" required>
        </div>
        <button type="submit">근처 WIFI 찾기</button>
        <button type="button" id="getCurrentGeolocation">내 위치정보 가져오기</button>
    </form>
    <div id="messageContainer"></div>
</div>

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
        <th>설치 기관</th>
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
    <tbody>
    <%
        List<WifiSpot> wifiSpots = (List<WifiSpot>) request.getAttribute("wifiSpots");
        if (wifiSpots == null || wifiSpots.isEmpty()) {
    %>
    <tr>
        <td colspan="16" style="text-align: center;">위치 정보를 입력하고, 근처 와이파이를 조회해 보세요.</td>
    </tr>
    <%
    } else {
        for (WifiSpot spot : wifiSpots) {
            WifiInfo wifiInfo = spot.getWifiInfo();
    %>
    <tr>
        <td>
            <%= String.format("%.4f", spot.getDistance()) %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiMgrNo() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiWrdofc() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiMainNm() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiAdres1() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiAdres2() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiInstlFloor() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiInstlTy() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiInstlMby() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiSvcSe() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiCmcwr() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiCnstcYear() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiInOutDoor() %>
        </td>
        <td>
            <%= wifiInfo.getXSwifiRemars3() %>
        </td>
        <td>
            <%= wifiInfo.getLat() %>
        </td>
        <td>
            <%= wifiInfo.getLnt() %>
        </td>
        <td>
            <%= wifiInfo.getWorkDttm() %>
        </td>
    </tr>
    <% } %>
    <% } %>
    </tbody>
</table>

<script>
    window.onload = function () {
        const message = "<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>";
        const messageContainer = document.getElementById("messageContainer");
        if (message !== "") {
            messageContainer.style.display = "block";
            messageContainer.textContent = message;
        } else {
            messageContainer.style.display = "none";
            messageContainer.textContent = "";
        }

        document.getElementById("getCurrentGeolocation").addEventListener("click", () => {
            navigator.geolocation.getCurrentPosition((position) => {
                console.log(position.coords);
                document.getElementById("lat").value = String(position.coords.latitude);
                document.getElementById("lnt").value = String(position.coords.longitude);
            })
        })
    };
</script>

</body>
</html>