<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>新增商品成功</title>
</head>
<body>
<h1>新增商品成功</h1>
<h2>${customer.cusName} 您的商品已推上架!</h2>
<form action="signIn" method="post" >
    <input type="text" name="cusName" value="${customer.cusName}" hidden="hidden">
    <input type="text" name="cusPhone" value="${customer.cusPhone}" hidden="hidden">
    <input type="submit" value="進入會員頁面">
</form>
</body>
</html>