<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>購物商城</title>
</head>
<script>
<<<<<<< HEAD
    function sendForm() {
=======
    function sendForm(){
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
        document.getElementById("send").submit();
    }
</script>
<body>
<h1>歡迎${customer.cusName}!請趕快購買想要的商品吧!</h1>
<!--返回會員頁面-->
<form action="welcomeCustomer" method="post">
    <button type="submit" name="cusId" value="${customer.cusId}">進入會員頁面</button>
</form>
<!-- 我的購物車 -->
<form action="checkMyCart" method="post">
    <input name="cusId" value="${customer.cusId}" hidden="hidden">
<<<<<<< HEAD
    <button name="proCheck" value="proCheck">查看購物車</button>
=======
    <button name="proCheck" value="proCheck" >查看購物車</button>
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
</form>
<!-- 商品全覽 -->
<div style="background-color: gainsboro;width: auto ;height: auto">
    <table style="background-color: burlywood">
        <tr style="background-color: blanchedalmond">
            <td>商品名稱</td>
            <td>商品價格</td>
            <td>剩餘數量</td>
            <td>圖片如下</td>
            <td>賣家名字</td>
        </tr>
<<<<<<< HEAD
        <c:forEach var="products" items="${productList}">
            <form action="infoProduct" method="post" name="ProductForm">
                <tr style="background-color: lightcyan">
=======

        <c:forEach var="products" items="${product}">
            <tr style="background-color: lightcyan">
                <form action="buyProduct" method="post" name="ProductForm">
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
                    <td>${products.proName}</td>
                    <td>${products.proPrice}</td>
                    <td>${products.proNum}</td>
                    <td><img src="${pageContext.request.contextPath}/${products.proPicture}" width="100" height="100">
                    </td>
<<<<<<< HEAD
                    <td>${products.customer.cusName}</td>
                    <td><input name="cusId" value="${customer.cusId}" hidden="hidden">
                        <input name="proId" value="${products.proId}" hidden="hidden">
                        <input type="submit" id="send" value="商品介紹" onclick="sendForm()"></td>
                </tr>
            </form>
        </c:forEach>
=======
                    <td>${products.cusName}</td>
                    <td><input name="cusId" value="${customer.cusId}" hidden="hidden">
                        <input name="proId" value="${products.proId}" hidden="hidden">
                        <input type="submit" id="send" value="商品介紹" onclick="sendForm()"></td>
                </form>
            </tr>
        </c:forEach>

>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
    </table>
</div>
<a href="${pageContext.request.contextPath}/index.jsp">系統目錄</a>
</body>
</html>