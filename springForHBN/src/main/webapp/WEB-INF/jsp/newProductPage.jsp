<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>新增商品頁面</title>
</head>

<body>
<h1>新增商品</h1>
<h2>${customer.cusName}您好~ 請填寫以下資料 就可以添加商品至市場中</h2>
<!--返回會員頁面-->
<form action="signIn" method="post" >
    <input type="text" name="cusName" value="${customer.cusName}" hidden="hidden">
    <input type="text" name="cusPhone" value="${customer.cusPhone}" hidden="hidden">
    <input type="submit" value="返回會員頁面">
</form>
<!--表單新增商品 -->
<form action="forNewProduct" method="post" enctype="multipart/form-data"
      style="background-color: lightgray;width: 300px;height: 200px">
    <p>商品名稱 : <input type="text" name="proName"></p>
    <p>商品價格 : <input type="text" name="proPrice"></p>
    <p>商品圖片 : <input type="file" name="file"></p>
    <input type="text" name="cusName" value="${customer.cusName}" hidden="hidden">
    <input type="text" name="cusPhone" value="${customer.cusPhone}" hidden="hidden">
    <input type="submit" value="投入商品">
</form>
</body>

</html>