<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>失敗頁面</title>
</head>
<body>
    <h3 style="color: red">失敗!</h3>
    <h1>可能原因 : ${registerError} ${singInError} ${buyError}</h1>

    <a href="${pageContext.request.contextPath}/index.jsp">系統目錄</a>
</body>
</html>