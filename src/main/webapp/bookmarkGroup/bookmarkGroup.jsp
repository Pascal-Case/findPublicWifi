<%@ page import="model.BookmarkGroup" %>
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

<div>
    <button type="button" class="to-add-bookmarkGroup-btn">북마크 그룹 추가</button>
</div>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<BookmarkGroup> bookmarkGroupList = (List<BookmarkGroup>) request.getAttribute("bookmarkGroupList");
        if (bookmarkGroupList == null || bookmarkGroupList.isEmpty()) {
    %>
    <tr>
        <td colspan="16" style="text-align: center;">북마크 그룹이 존재하지 않습니다.</td>
    </tr>
    <%
    } else {
        for (BookmarkGroup bookmarkGroup : bookmarkGroupList) {

    %>
    <tr>
        <td>
            <%= bookmarkGroup.getGroupId() %>
        </td>
        <td>
            <%= bookmarkGroup.getGroupName() %>
        </td>
        <td>
            <%= bookmarkGroup.getGroupOrder() %>
        </td>
        <td>
            <%= bookmarkGroup.getPostDate() %>
        </td>
        <td>
            <%= bookmarkGroup.getEditDate() %>
        </td>
        <td style="text-align: center">
            <button type="button" class="edit-bookmarkGroup-btn" data-id="<%= bookmarkGroup.getGroupId() %>">
                수정
            </button>
            <button type="button" class="delete-bookmarkGroup-btn" data-id="<%= bookmarkGroup.getGroupId() %>">
                삭제
            </button>
        </td>
    </tr>
    <% } %>
    <% } %>
    </tbody>
</table>
</body>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const addButton = document.querySelector('.to-add-bookmarkGroup-btn');
        const delButtons = document.querySelectorAll('.delete-bookmarkGroup-btn');
        const editButtons = document.querySelectorAll('.edit-bookmarkGroup-btn');

        addButton.addEventListener('click', function () {
            window.location.href = 'bookmarkGroup-add';
        });

        delButtons.forEach(delButton => {
            // 삭제
            delButton.addEventListener('click', function () {
                const bookmarkGroupId = this.getAttribute('data-id');

                if (confirm('북마크 그룹을 삭제하면 해당 북마크 그룹에 포함된 모든 북마크가 삭제됩니다.\n' +
                    '북마크 그룹을 삭제하시겠습니까?')) {
                    fetch('${pageContext.request.contextPath}/seoul-wifi/bookmarkGroup-del', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded;',
                        },
                        body: 'bookmarkGroupId=' + bookmarkGroupId
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
        })

        editButtons.forEach(editButton => {
            // 수정
            editButton.addEventListener('click', function () {
                const bookmarkGroupId = this.getAttribute('data-id');
                window.location.href = 'bookmarkGroup-edit?bookmarkGroupId=' + bookmarkGroupId;
            });
        })


    });
</script>

</html>
