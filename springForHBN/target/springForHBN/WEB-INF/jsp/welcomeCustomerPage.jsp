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
    <input type="text" name="cusId" value="${customer.cusId}" hidden="hidden">
    <input type="submit" value="新增商品">
</form>
<!-- 購物商城 -->
<form action="showProductPage" method="post">
    <button type="submit" name="cusId" value="${customer.cusId}">進入購物商城</button>
</form>
<!-- 我的購物車 -->
<form action="checkMyCart" method="post">
    <input name="cusId" value="${customer.cusId}" hidden="hidden">
    <button name="button" value="checkMyCart" >查看購物車</button>
</form>

<form action="myProduct" method="post">
    <input name="cusId" value="${customer.cusId}" hidden="hidden">
    <input name="findMyProduct" value="findMyProduct" hidden="hidden">
    <button name="proCheck" value="proCheck" >我賣的商品</button>
</form>
<a href="${pageContext.request.contextPath}/index.jsp">登出並返回首頁</a>
</body>