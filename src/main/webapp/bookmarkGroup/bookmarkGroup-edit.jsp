<%@ page import="model.BookmarkGroup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
</head>
<body>
<header>
    <div>
        <h1>북마크 그룹 수정</h1>
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

<%
    BookmarkGroup bookmarkGroup = (BookmarkGroup) request.getAttribute("bookmarkGroup");
    boolean success = (boolean) request.getAttribute("success");
%>
<div class="formContainer">

    <%
        if (success && bookmarkGroup != null) {
    %>
    <form class="form bookmark-group-form">
        <div class="form-group">
            <label for="bookmarkGroupName">북마크 그룹명</label>
            <input type="text" id="bookmarkGroupName" name="bookmarkGroupName" class="form-control"
                   value="<%= bookmarkGroup.getGroupName() %>" required>
        </div>
        <div class="form-group">
            <label for="bookmarkGroupOrder">북마크 순서</label>
            <input type="number" id="bookmarkGroupOrder" name="bookmarkGroupOrder" class="form-control"
                   value="<%= bookmarkGroup.getGroupOrder() %>" required min="0">
        </div>
        <div class="form-group">
            <label for="postDate">등록 날짜</label>
            <input type="text" id="postDate" name="postDate" class="form-control"
                   value="<%= bookmarkGroup.getPostDate() %>" readonly>
        </div>
        <button type="button" class="update-bookmarkGroup-btn" data-id="<%= bookmarkGroup.getGroupId()%>">
            수정
        </button>
    </form>
    <%
    } else {
    %>

    <p><% request.getAttribute("message"); %></p>
    <p><a href="${pageContext.request.contextPath}/seoul-wifi/home">홈으로</a></p>

    <%
        }
    %>


</div>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const updateButton = document.querySelector('.update-bookmarkGroup-btn');
        updateButton.addEventListener('click', function () {
            const bookmarkGroupId = this.getAttribute('data-id');
            const bookmarkGroupName = document.getElementById("bookmarkGroupName").value;
            const bookmarkGroupOrder = document.getElementById("bookmarkGroupOrder").value;
            const postDate = document.getElementById("postDate").value;

            fetch('${pageContext.request.contextPath}/seoul-wifi/bookmarkGroup-edit', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                },
                body:
                    "bookmarkGroupId=" + bookmarkGroupId +
                    "&bookmarkGroupName=" + bookmarkGroupName +
                    "&bookmarkGroupOrder=" + bookmarkGroupOrder +
                    "&postDate=" + postDate
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert(data.message);
                        window.location.replace("/seoul-wifi/bookmarkGroup");
                    } else {
                        alert(data.message);
                    }
                })
                .catch(error => console.error('Error:', error));
        });
    });
</script>
</body>
</html>
