<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>我賣的商品</title>
</head>
<script>
</script>
<body>
<h1>您好${customer.cusName}</h1>
<h2>以下是您賣的商品</h2>

<div style="background-color: gainsboro;width: auto ;height: auto">
    <table style="background-color: burlywood">
        <tr style="background-color: blanchedalmond">
            <td></td>
            <td>商品名稱</td>
            <td>商品價格</td>
            <td>商品數量</td>
            <td>圖片如下</td>
            <td>修改金額</td>
            <td>修改庫存量</td>
        </tr>
        <c:forEach var="myProducts" items="${myProductsList}">
            <form action="myProduct" method="post" >
                <tr style="background-color: lightcyan">
                <td><input type="checkbox" name="drop" value="drop"></td>
                <td>${myProducts.proName}</td>
                <td>${myProducts.proPrice}</td>
                <td>${myProducts.proNum}</td>
                <td><img src="${pageContext.request.contextPath}/${myProducts.proPicture}" width="150" height="150"></td>
                <td> <input type="number" id="alterPrice" name="alterPrice" value="${myProducts.proPrice}"></td>
                <td>
                    <input type="number" id="alterNum" name="alterNum" value="${myProducts.proNum}">
                    <input name="cusId" value="${myProducts.customer.cusId}" hidden="hidden">
                    <input name="proId" value="${myProducts.proId}" hidden="hidden">
                    <input name="findMyProduct" value="alter" hidden="hidden">
                </td>
                <td><input type="submit" id="alterForm" name="button" value="修改" ></td>
                <td><input type="submit" id="dropForm" name="button" value="移除" ></td>
                </tr>
            </form>
        </c:forEach>
    </table>
    <form action="welcomeCustomer" method="post">
        <button type="submit" name="cusId" value="${customer.cusId}">進入會員頁面</button>
    </form>
</div>
</body>
</html>