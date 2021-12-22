<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>

</script>
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
<div style="background-color: honeydew;width: auto">
    <h2>以下是放入購物車內的商品</h2>
    <div style="width: auto">
        <div align="right">
            <form action="cleanCart" method="post">
                <input type="submit" value="清除購物車">
                <input name="cusId" value="${customer.cusId}" hidden="hidden">
            </form>
        </div>
        <table style="background-color: burlywood;width: 100%">
            <tr style="background-color: blanchedalmond;">
                <td>商品名稱</td>
                <td>商品圖片</td>
                <td>購買數量</td>
                <td>商品單價</td>
                <td>總額</td>
                <td>修改購買數量</td>
            </tr>
            <c:forEach var="buyCustomer" items="${buyCustomerList}">
                <tr style="background-color: lightcyan">
                    <td>${buyCustomer.product.proName}</td>
                    <td><img src="${pageContext.request.contextPath}/${buyCustomer.product.proPicture}"
                             width="100" height="100"></td>
                    <td>${buyCustomer.buyNum}</td>
                    <td>${buyCustomer.product.proPrice}</td>
                    <td>${buyCustomer.buyNum*buyCustomer.product.proPrice}</td>
                    <form action="alterProduct" method="post">
                        <input name="cusId" value="${customer.cusId}" hidden="hidden">
                        <input name="proId" value="${buyCustomer.product.proId}" hidden="hidden">
                        <td><input type="number" id="alterNum" name="alterNum"></td>
                        <td><input type="submit" name="choose" value="修改"></td>
                    </form>
                    <form action="dropProduct" method="post">
                        <input name="cusId" value="${customer.cusId}" hidden="hidden">
                        <input name="proId" value="${buyCustomer.product.proId}" hidden="hidden">
                        <td><input type="submit"  value="移除"></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div align="right">
        <form action="checkOut" method="post">
            <input type="submit" value="確認結帳">
            <input name="cusId" value="${customer.cusId}" hidden="hidden">
        </form>
        <p style="color: red">${Error}</p>
    </div>
</div>
</body>
</html>