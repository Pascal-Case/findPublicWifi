<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
</head>
<body>
<header>
    <div>
        <h1>북마크 그룹 추가</h1>
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

<div class="formContainer">
    <form class="form bookmark-group-form">
        <div class="form-group">
            <label for="bookmarkGroupName">북마크 그룹명</label>
            <input type="text" id="bookmarkGroupName" name="bookmarkGroupName" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="bookmarkGroupOrder">북마크 순서</label>
            <input type="number" id="bookmarkGroupOrder" name="bookmarkGroupOrder" class="form-control" required
                   min="0">
        </div>
        <button type="button" class="add-bookmarkGroup-btn">
            추가
        </button>
    </form>


</div>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const addButton = document.querySelector('.add-bookmarkGroup-btn');
        addButton.addEventListener('click', function () {
            const bookmarkGroupName = document.getElementById("bookmarkGroupName").value;
            const bookmarkGroupOrder = document.getElementById("bookmarkGroupOrder").value;

            fetch('${pageContext.request.contextPath}/seoul-wifi/bookmarkGroup-add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                },
                body: 'bookmarkGroupName=' + bookmarkGroupName + "&bookmarkGroupOrder=" + bookmarkGroupOrder
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
