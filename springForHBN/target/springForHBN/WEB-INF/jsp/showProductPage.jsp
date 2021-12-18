<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>購物商城</title>
</head>
<script>
    function sendForm() {
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
    <button name="proCheck" value="proCheck">查看購物車</button>
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
        <c:forEach var="products" items="${productList}">
            <form action="infoProduct" method="post" name="ProductForm">
                <tr style="background-color: lightcyan">
                    <td>${products.proName}</td>
                    <td>${products.proPrice}</td>
                    <td>${products.proNum}</td>
                    <td><img src="${pageContext.request.contextPath}/${products.proPicture}" width="100" height="100">
                    </td>
                    <td>${products.customer.cusName}</td>
                    <td><input name="cusId" value="${customer.cusId}" hidden="hidden">
                        <input name="proId" value="${products.proId}" hidden="hidden">
                        <input type="submit" id="send" value="商品介紹" onclick="sendForm()"></td>
                </tr>
            </form>
        </c:forEach>
    </table>
</div>
<a href="${pageContext.request.contextPath}/index.jsp">系統目錄</a>
</body>
</html>