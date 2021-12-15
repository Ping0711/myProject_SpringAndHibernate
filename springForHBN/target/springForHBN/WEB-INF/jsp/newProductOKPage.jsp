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

<!-- 預覽上傳商品 -->
<div style="background-color: gainsboro;width: auto ;height: auto;" >
    <table style="background-color: burlywood">
    <tr style="background-color: blanchedalmond;">
        <td>商品名稱</td>
        <td>商品價格</td>
        <td>數量</td>
        <td>商品圖片</td>
    </tr>
    <tr style="background-color: lightcyan">
        <td>${product.proName}</td>
        <td>${product.proPrice}</td>
        <td>${product.proNum}</td>
        <td><img src="${pageContext.request.contextPath}/${product.proPicture}" height="100" width="100"></td>
    </tr>
    </table>
    <form action="newProduct" method="post">
        <button type="submit" name="cusId" value="${customer.cusId}">繼續新增商品</button>
    </form>
</div>
<!-- 返回會員頁面 -->
<form action="welcomeCustomer" method="post">
    <button type="submit" name="cusId" value="${customer.cusId}">返回會員頁面</button>
</form>
<!-- 購物商城 -->
<form action="showProductPage" method="post">
    <button type="submit" name="cusId" value="${customer.cusId}">進入購物商城</button>
</form>

</body>
</html>