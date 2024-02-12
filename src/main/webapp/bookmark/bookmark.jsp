<%@ page import="model.BookMark" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
</head>
<body>
<header>
    <div>
        <h1>북마크 그룹</h1>
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
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<BookMark> bookmarkList = (List<BookMark>) request.getAttribute("bookmarkList");
        if (bookmarkList == null || bookmarkList.isEmpty()) {
    %>
    <tr>
        <td colspan="16" style="text-align: center;">등록된 북마크가 존재하지 않습니다.</td>
    </tr>
    <%
    } else {
        for (BookMark bookMark : bookmarkList) {

    %>
    <tr>
        <td>
            <%= bookMark.getGroupId() %>
        </td>
        <td>
            <%= bookMark.getGroupName() %>
        </td>
        <td>
            <%= bookMark.getWifiName() %>
        </td>
        <td>
            <%= bookMark.getPostDate() %>
        </td>
        <td>
            <button type="button" class="to-delete-bookmark-btn" data-id="<%= bookMark.getBookmarkId() %>">삭제</button>
        </td>
    </tr>
    <% } %>
    <% } %>
    </tbody>
</table>

<script>
    const delButtons = document.querySelectorAll(".to-delete-bookmark-btn");
    delButtons.forEach(delButton => {
        // 수정
        delButton.addEventListener('click', function () {
            const bookmarkId = this.getAttribute('data-id');
            window.location.href = 'bookmark-del?bookmarkId=' + bookmarkId;
        });
    })
</script>
</body>
</html>
