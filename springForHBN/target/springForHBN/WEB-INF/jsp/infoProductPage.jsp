<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品介紹</title>
</head>
<script>
    function cartAdd() {
        if (document.getElementById("buyNum").value === "") {
            alert("沒有填入數量!")
            return "buyProductPage";
        }
        let i = parseInt(${product.proNum});
        let j = parseInt(document.getElementById("buyNum").value);
        if (j > i) {
            alert("購買數量超過存貨了!")
            return "buyProductPage";
        } else if (j === 0 || j < 0) {
            alert("購買數不能為0或小於0!")
            return "buyProductPage";
        }
        document.getElementById("send").submit();
        alert("成功加入購物車!")
    }
</script>
<boby>
    <h1>商品介紹</h1>
    <h2>歡迎會員${customer.cusName}!</h2>
    <!-- 會員頁面-->
    <form action="welcomeCustomer" method="post">
        <button type="submit" name="cusId" value="${customer.cusId}">進入會員頁面</button>
    </form>
    <h3>以下為商品資訊</h3>
    <!-- 各商品欄位名-->
    <div style="background-color: gainsboro ; width: auto ; height : auto">
        <table style="background-color: burlywood">
            <tr style="background-color: blanchedalmond;">
                <td>商品名稱</td>
                <td>商品價格</td>
                <td>剩餘數量</td>
                <td>商品圖片</td>
                <td>購買品數</td>
            </tr>
            <!--上架商品-->
            <tr style="background-color: lightcyan">
                <td>${product.proName}</td>
                <td>${product.proPrice}</td>
                <td>${product.proNum}</td>
                <td><img src="${pageContext.request.contextPath}/${product.proPicture}" width="150" height="150"></td>
                <%-- 購買數量--%>
                <form action="addMyCart" method="post" id="send">
                    <td><input type="number" id="buyNum" name="buyNum"></td>
                    <td>
                        <input id="cusId" name="cusId" value="${customer.cusId}" hidden="hidden">
                        <input id="proId" name="proId" value="${product.proId}" hidden="hidden">
                        <input id="proNum" name="proNum" value="${product.proNum}" hidden="hidden">
                        <input type="button" onclick="cartAdd()" value="加入購物車">
                    </td>
                </form>
            </tr>
        </table>
    </div>
    <h2 style="color: red">${buyError}</h2>
    <!-- 我的購物車-->
    <form action="checkMyCart" method="post">
        <input name="cusId" value="${customer.cusId}" hidden="hidden">
        <button name="button" value="checkMyCart" >查看購物車</button>
    </form>
    <!-- 購物商城 -->
    <form action="showProductPage" method="post">
        <button type="submit" name="cusId" value="${customer.cusId}">進入購物商城</button>
    </form>
</boby>
</html>