<%@ page import="model.History" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/general.css">
</head>
<body>
<header>
    <div>
        <h1>위치 히스토리 목록</h1>
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
        <th>위도</th>
        <th>경도</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<History> historyList = (List<History>) request.getAttribute("historyList");
        if (historyList == null || historyList.isEmpty()) {
    %>
    <tr>
        <td colspan="16" style="text-align: center;">히스토리가 존재하지 않습니다.</td>
    </tr>
    <%
    } else {
        for (History history : historyList) {

    %>
    <tr>
        <td>
            <%= history.getHistoryId() %>
        </td>
        <td>
            <%= history.getLat() %>
        </td>
        <td>
            <%= history.getLnt() %>
        </td>
        <td>
            <%= history.getSearchDate() %>
        </td>
        <td style="text-align: center">
            <button type="button" class="delete-history-btn" data-id="<%= history.getHistoryId() %>">삭제</button>
        </td>
    </tr>
    <% } %>
    <% } %>
    </tbody>
</table>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const deleteButtons = document.querySelectorAll('.delete-history-btn');
        deleteButtons.forEach(button => {
            button.addEventListener('click', function () {
                const historyId = this.getAttribute('data-id');

                if (confirm('해당 히스토리를 삭제하시겠습니까?')) {
                    fetch('${pageContext.request.contextPath}/seoul-wifi/history-del', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                        },
                        body: 'historyId=' + historyId
                    })
                        .then(response => response.json())
                        .then(data => {
                            if (data.success) {
                                alert(data.message);
                                window.location.reload();
                            } else {
                                alert(data.message);
                            }
                        })
                        .catch(error => console.error('Error:', error));
                }
            });
        });
    });
</script>
</body>
</html>
