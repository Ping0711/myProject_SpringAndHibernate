<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>我賣的商品</title>
</head>
<<<<<<< HEAD
<script>
    // function sendAlter() {
    //     var alterNum2Int = parseInt(document.getElementById("alterNum").value);
    //     if(alterNum2Int<=0){
    //         window.alert("輸入數字錯誤! 您輸入的數字 : " +alterNum2Int)
    //     }
    // }
    // function sendDrop() {
    //     var alterNum = document.getElementById("alterNum");
    //         alert("即將刪除商品...")
    //         if(window.confirm("確認移除商品嗎?") !== true){
    //             window.alert("取消移除")
    //             window.location.assign(window.location.href);
    //         }
    // }
</script>
=======
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
<body>
<h1>您好${customer.cusName}</h1>
<h2>以下是您賣的商品</h2>

<div style="background-color: gainsboro;width: auto ;height: auto">
    <table style="background-color: burlywood">
        <tr style="background-color: blanchedalmond">
<<<<<<< HEAD
            <td></td>
=======
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
            <td>商品名稱</td>
            <td>商品價格</td>
            <td>商品數量</td>
            <td>圖片如下</td>
<<<<<<< HEAD
            <td>修改數量</td>
        </tr>
        <c:forEach var="myProducts" items="${myProductsList}">
            <form action="myProduct" method="post" >
                <tr style="background-color: lightcyan">
                <td><input type="checkbox" name="drop" value="drop"></td>
                <td>${myProducts.proName}</td>
                <td>${myProducts.proPrice}</td>
                <td>${myProducts.proNum}</td>
                <td><img src="${pageContext.request.contextPath}/${myProducts.proPicture}" width="150" height="150"></td>
                <td>
                    <input type="number" id="alterNum" name="alterNum" value="1">
                    <input name="cusId" value="${myProducts.customer.cusId}" hidden="hidden">
                    <input name="proId" value="${myProducts.proId}" hidden="hidden">
                    <input name="findMyProduct" value="alter" hidden="hidden">
                </td>
                <td><input type="submit" id="alterForm" name="button" value="修改" onclick="sendAlter()"></td>
                <td><input type="submit" id="dropForm" name="button" value="移除" onclick="sendDrop()"></td>
                </tr>
            </form>
=======
        </tr>
        <c:forEach var="myProduct" items="${productList}">
        <tr style="background-color: lightcyan">
            <td>${myProduct.proName}</td>
            <td>${myProduct.proPrice}</td>
            <td>${myProduct.proNum}</td>
            <td><img src="${pageContext.request.contextPath}/${myProduct.proPicture}" width="150" height="150"></td>
        </tr>
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
        </c:forEach>
    </table>
    <form action="welcomeCustomer" method="post">
        <button type="submit" name="cusId" value="${customer.cusId}">進入會員頁面</button>
    </form>
</div>
</body>
</html>