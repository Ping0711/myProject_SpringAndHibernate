<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 2021/12/10
  Time: 上午 09:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>登入成功</title>
</head>
<body>
<h1>歡迎!會員 :${cusSingIN.cusName}</h1>
<p>請進入購物車系統，選擇您要的功能</p>
<a href="${pageContext.request.contextPath}/cartPage.jsp">購物車系統目錄</a>
</body>
</html>
