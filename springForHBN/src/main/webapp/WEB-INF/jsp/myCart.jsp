<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的購物車</title>
</head>
<body>
<h1>我的購物車</h1>
<h2>會員${customer.cusName} 請確認您想購買的商品</h2>
<!--返回會員頁面-->
<form action="welcomeCustomer" method="post">
    <button type="submit" name="cusId" value="${customer.cusId}">返回會員頁面</button>
</form>
<!-- 購物商城 -->
<form action="showProductPage" method="post">
    <button type="submit" name="cusId" value="${customer.cusId}">進入購物商城</button>
</form>
<h2>以下是放入購物車內的商品</h2>
<<<<<<< HEAD
<div style="width: auto ;height: auto">
    <table style="background-color: burlywood">
        <tr style="background-color: blanchedalmond;">
            <td>商品名稱</td>
            <td>購買數量</td>
            <td>商品圖片</td>
            <td>商品賣家</td>
            <td>數量與金額</td>
        </tr>
        <c:forEach var="buyCustomer" items="${buyCustomerList}">
            <tr style="background-color: lightcyan">
                <td>${buyCustomer.product.proName}</td>
                <td>${buyCustomer.buyNum}</td>
                <td><img src="${pageContext.request.contextPath}/${buyCustomer.product.proPicture}"
                         width="100" height="100"></td>
                <td>${buyCustomer.product.customer.cusName}</td>
                <td>${buyCustomer.buyNum}*${buyCustomer.product.proPrice}</td>
=======
<div>
    <table style="background-color: burlywood">
        <tr style="background-color: blanchedalmond;">
            <td>商品名稱</td>
            <td>商品數量</td>
            <td>商品圖片</td>
            <td>總額</td>
        </tr>
        <c:forEach var="productList" items="${productList}">
            <tr style="background-color: lightcyan">
                <td>${allProduct.proName}</td>
                <td>${allProduct.proNum}</td>
                <td>${allProduct.proPicture}</td>
                <td>${allProduct.proPrice}</td>
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
            </tr>
        </c:forEach>
    </table>
</div>
<<<<<<< HEAD
<p><input type="button" value="確認結帳">
<p>
=======
<p><input type="button" value="確認結帳"><p>
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
</body>
</html>