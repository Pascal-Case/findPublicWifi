<%@ page import="model.BookMark" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
    <style>
        th:first-child, td:first-child {
            width: 300px;
        }
    </style>
</head>
<body>
<header>
    <div>
        <h1>북마크 삭제</h1>
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
<table>
    <%
        BookMark bookMark = (BookMark) request.getAttribute("bookMark");
        if (bookMark != null) {
    %>
    <tr>
        <th>항목</th>
        <th>정보</th>
    </tr>
    <tr>
        <td>북마크 ID</td>
        <td><%= bookMark.getBookmarkId() %>
        </td>
    </tr>
    <tr>
        <td>북마크 이름</td>
        <td><%= bookMark.getGroupName() %>
        </td>
    </tr>
    <tr>
        <td>북마크 순서</td>
        <td><%= bookMark.getGroupOrder() %>
        </td>
    </tr>
    <tr>
        <td>와이파이 관리번호</td>
        <td><%= bookMark.getMgrNo() %>
        </td>
    </tr>
    <tr>
        <td>와이파이명</td>
        <td><%= bookMark.getWifiName() %>
        </td>
    </tr>
    <tr>
        <td>등록일</td>
        <td><%= bookMark.getPostDate() %>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center;">
            <button type="button" class="delete-history-btn" data-id="<%= bookMark.getBookmarkId() %>">삭제</button>
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
    const delButton = document.querySelector(".delete-history-btn");
    delButton.addEventListener('click', function () {
        const bookmarkId = this.getAttribute('data-id');

        if (confirm('해당 북마크를 삭제하시겠습니까?')) {
            fetch('${pageContext.request.contextPath}/seoul-wifi/bookmark-del', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                },
                body: 'bookmarkId=' + bookmarkId
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert(data.message);
                        window.location.assign('/seoul-wifi/bookmark');
                    } else {
                        alert(data.message);
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    })


</script>
</html>
