<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>結帳系統</title>
    <script>
        function sendConFirm() {
            document.getElementById("confirm").submit();
        }

        function sendCheckOut() {
            document.getElementById("checkMyCart").submit();
        }
    </script>
</head>
<body>
<h1>歡迎來到結帳系統!</h1>
<h2>會員:${customer.cusName}</h2>
<div style="background-color: lightcyan;width: 70%">
    <h3>請確認以下是您的資料</h3>
    <form action="#" method="post">
        <div style="background-color: lightgray;padding-left: 5px">
            <p>會員名稱 : <input type="text" value="${customer.cusName}"></p>
            <p>會員手機 : <input type="text" value="${customer.cusPhone}"></p>
            <p>會員地址 : <input type="text" value="${customer.cusAddress}"></p>
        </div>
    </form>
    <h3>請確認您購買的商品</h3>
    <div>
        <table style="background-color: blanchedalmond" width="100%">
            <tr align="center">
                <td>商品名稱</td>
                <td>商品圖片</td>
                <td>商品單價</td>
                <td>購買數量</td>
                <td>總額</td>
            </tr>
            <c:forEach var="checkProducts" items="${checkProductList}">
                <tr bgcolor="#deb887" align="center">
                    <td>${checkProducts.product.proName}</td>
                    <td><img src="${pageContext.request.contextPath}/${checkProducts.product.proPicture}" width="70"
                             height="70"></td>
                    <td>${checkProducts.product.proPrice}</td>
                    <td>${checkProducts.buyNum}</td>
                    <td>${checkProducts.buyNum*checkProducts.product.proPrice}</td>
                </tr>
            </c:forEach>
            <tr align="center">
                <td></td>
                <td></td>
                <td></td>
                <td>合計</td>
                <td>${total}</td>
            </tr>
        </table>
    </div>
    <div style="vertical-align:bottom;display: flex;justify-content: space-between">
        <form action="checkMyCart" method="post" id="checkMyCart">
            <input name="cusId" value="${customer.cusId}" hidden="hidden">
            <button name="button" value="返回購物車" onclick="sendCheckOut()">返回購物車</button>
        </form>
        <form action="conFirm" method="post" id="conFirm">
            <input name="cusId" value="${customer.cusId}" hidden="hidden">
            <button name="button" value="確認" onclick="sendConFirm()">確認</button>
        </form>
    </div>
</div>
</body>
</html>
