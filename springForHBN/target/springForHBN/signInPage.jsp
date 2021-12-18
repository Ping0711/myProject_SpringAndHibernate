<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登入頁面</title>
</head>
<body>
<h1>登入頁面</h1>
<p>請輸您的姓名與手機，以登入會員。</p>

<!-- 登入系統 -->
<div style="background-color: darkgray;width: auto;height: auto">
    <form action="signIn" method="post">
        <p>姓名 : <input type="text" name="cusName"></p>
        <p>手機 : <input type="text" name="cusPhone"></p>
        <input type="submit" name="submit" value="登入"><br>
        <input type="reset" name="reset" value="重設"><br>
    </form>
</div>


<a href="${pageContext.request.contextPath}/index.jsp">系統目錄</a>
</body>
</html>