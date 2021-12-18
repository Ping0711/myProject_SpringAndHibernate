<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 2021/12/10
  Time: 上午 09:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登入成功</title>
</head>
<body>
<h1>歡迎!會員 :${customer.cusName}</h1>
<p>已開放以下功能使用 : </p>

<!-- 表單新增商品 -->
<form action="newProduct" method="post">
    <input type="text" name="cusName" value="${customer.cusName}" hidden="hidden">
    <input type="text" name="cusPhone" value="${customer.cusPhone}" hidden="hidden">
    <input type="submit" value="新增商品">
</form>


<a href="showProductPage">購物商城</a><br>
<a href="#">我的購物車</a><br>
<a href="#">清除購物車</a><br>
<a href="${pageContext.request.contextPath}/index.jsp">登出並返回首頁</a>
</body>
</html>
