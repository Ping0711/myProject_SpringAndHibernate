<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--正確圖片顯示路徑--%>
<%--<img src="${pageContext.request.contextPath}/images/c91775279e5c4cf78b7829a166751cf7.jpg" height="360" width="240">--%>

<html>
<head>
<title>新增商品成功</title>
</head>
<body>
<h1>新增商品成功</h1>
<h2>${customer.cusName} 您的商品已推上架!</h2>
<p>以下是購物城預覽您商品樣貌</p>
<div style="background-color: gainsboro;width: auto ;height: auto" >
    <p>商品名稱 : ${product.proName}</p>
    <p>商品價格 : ${product.proPrice}</p>
    <p>商品圖片如下 : </p>
    <p><img src="${pageContext.request.contextPath}/${product.proPicture}"></p>
    <form action="newProduct" method="post" >
        <input type="text" name="cusName" value="${customer.cusName}" hidden="hidden">
        <input type="text" name="cusPhone" value="${customer.cusPhone}" hidden="hidden">
        <input type="submit" value="繼續新增商品">
    </form>
</div>
<form action="signIn" method="post" >
    <input type="text" name="cusName" value="${customer.cusName}" hidden="hidden">
    <input type="text" name="cusPhone" value="${customer.cusPhone}" hidden="hidden">
    <input type="submit" value="返回會員頁面">
</form>
<form action="showProductPage" method="post" >
    <input type="text" name="cusName" value="${customer.cusName}" hidden="hidden">
    <input type="text" name="cusPhone" value="${customer.cusPhone}" hidden="hidden">
    <input type="submit" value="進入購物商城">
</form>

</body>
</html>