<%@ page import="model.WifiInfo" %>
<%@ page import="model.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">

    <title>WiFi 상세 정보</title>
    <style>

        /* 테이블 기본 스타일 */
        table {
            border-collapse: collapse;
            width: 100%;
            margin: auto;
        }

        th {
            font-weight: bold;
            background-color: #f2f2f2;
        }

        th:first-child, td:first-child {
            width: 300px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        td:nth-child(odd) {
            background-color: #f2f2f2;
        }

        #bookMarkGroupList {
            margin-bottom: 10px;
        }
    </style>

</head>
<body>
<header>
    <div>
        <h1>와이파이 상세 정보</h1>
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


<label for="bookMarkGroupList"></label>
<select id="bookMarkGroupList">
    <%
        List<BookmarkGroup> bookmarkGroupList = (List<BookmarkGroup>) request.getAttribute("bookmarkGroupList");

    %>
    <option value="">북마크 그룹 이름 선택</option>

    <%
        if (bookmarkGroupList != null) {
            for (BookmarkGroup bookmarkGroup : bookmarkGroupList) {
    %>
    <option value="<%= bookmarkGroup.getGroupId() %>">
        <%= bookmarkGroup.getGroupName() %>
    </option>
    <%
            }
        }
    %>

</select>

<button type="button" class="bookmark-add-btn">북마크 등록</button>
<table>
    <%
        WifiInfo wifiInfo = (WifiInfo) request.getAttribute("wifiDetail");
        if (wifiInfo != null) {

    %>
    <tr>
        <th>항목</th>
        <th>정보</th>
    </tr>
    <tr>
        <td>관리 번호</td>
        <td><%= wifiInfo.getXSwifiMgrNo() %>
        </td>
    </tr>
    <tr>
        <td>자치구</td>
        <td><%= wifiInfo.getXSwifiWrdofc() %>

        </td>
    </tr>
    <tr>
        <td>WiFi명</td>
        <td><%= wifiInfo.getXSwifiMainNm() %>
        </td>
    </tr>
    <tr>
        <td>도로명 주소</td>
        <td><%= wifiInfo.getXSwifiAdres1() %>
        </td>
    </tr>
    <tr>
        <td>상세 주소</td>
        <td><%= wifiInfo.getXSwifiAdres2() %>
        </td>
    </tr>
    <tr>
        <td>설치 위치(층)</td>
        <td><%= wifiInfo.getXSwifiInstlFloor() %>
        </td>
    </tr>
    <tr>
        <td>설치 유형</td>
        <td><%= wifiInfo.getXSwifiInstlTy() %>
        </td>
    </tr>
    <tr>
        <td>설치 기관</td>
        <td><%= wifiInfo.getXSwifiInstlMby() %>
        </td>
    </tr>
    <tr>
        <td>서비스 구분</td>
        <td><%= wifiInfo.getXSwifiSvcSe() %>
        </td>
    </tr>
    <tr>
        <td>망 종류</td>
        <td><%= wifiInfo.getXSwifiCmcwr() %>
        </td>
    </tr>
    <tr>
        <td>설치년도</td>
        <td><%= wifiInfo.getXSwifiCnstcYear() %>
        </td>
    </tr>
    <tr>
        <td>실내외 구분</td>
        <td><%= wifiInfo.getXSwifiInOutDoor() %>
        </td>
    </tr>
    <tr>
        <td>WIFI 접속환경</td>
        <td><%= wifiInfo.getXSwifiRemars3() %>
        </td>
    </tr>
    <tr>
        <td>위도</td>
        <td><%= wifiInfo.getLat() %>
        </td>
    </tr>
    <tr>
        <td>경도</td>
        <td><%= wifiInfo.getLnt() %>

        </td>
    </tr>
    <tr>
        <td>작업일자</td>
        <td><%= wifiInfo.getWorkDttm() %>
        </td>
    </tr>
    <%
    } else {
    %>
    <tr>
        <td colspan="2" style="text-align: center;">상세 정보를 가져오는데 실패했습니다.</td>
    </tr>
    <%
        }
    %>
</table>
</body>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const addBookmarkBtn = document.querySelector(".bookmark-add-btn");
        addBookmarkBtn.addEventListener('click', function () {
            const bookmarkGroupId = document.getElementById("bookMarkGroupList").value;
            const wifiMgrNo = "<%= wifiInfo != null ? wifiInfo.getXSwifiMgrNo() : "" %>"; // 서버로부터 받은 WiFi 정보의 관리 번호

            if (bookmarkGroupId === "") {
                alert("북마크 그룹을 선택해주세요.");
                return;
            } else if (wifiMgrNo === "") {
                alert("WiFi 관리 번호가 유효하지 않습니다.");
                return;
            }

            // 서버에 북마크 등록 요청
            fetch('${pageContext.request.contextPath}/seoul-wifi/bookmark-add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'bookmarkGroupId=' + bookmarkGroupId + '&wifiMgrNo=' + wifiMgrNo
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert(data.message);
                    } else {
                        alert(data.message);
                    }
                })
                .catch(error => console.error('Error:', error));
        });
    });
</script>
</html>
