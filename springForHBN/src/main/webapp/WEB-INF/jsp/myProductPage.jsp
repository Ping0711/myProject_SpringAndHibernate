<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>我賣的商品</title>
</head>
<body>
<h1>您好${customer.cusName}</h1>
<h2>以下是您賣的商品</h2>

<div style="background-color: gainsboro;width: auto ;height: auto">
    <table style="background-color: burlywood">
        <tr style="background-color: blanchedalmond">
            <td>商品名稱</td>
            <td>商品價格</td>
            <td>商品數量</td>
            <td>圖片如下</td>
        </tr>
        <c:forEach var="myProduct" items="${productList}">
        <tr style="background-color: lightcyan">
            <td>${myProduct.proName}</td>
            <td>${myProduct.proPrice}</td>
            <td>${myProduct.proNum}</td>
            <td><img src="${pageContext.request.contextPath}/${myProduct.proPicture}" width="150" height="150"></td>
        </tr>
        </c:forEach>
    </table>
    <form action="welcomeCustomer" method="post">
        <button type="submit" name="cusId" value="${customer.cusId}">進入會員頁面</button>
    </form>
</div>
</body>
</html>