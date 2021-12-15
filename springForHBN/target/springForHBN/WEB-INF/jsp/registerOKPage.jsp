<%--
  Created by IntelliJ IDEA.
  User: Turtle
  Date: 2021/12/3
  Time: 下午 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>註冊成功</title>
</head>
<body>
<h1>成功!</h1>
<h3>註冊資料</h3>
<!-- 會員資料 -->
<div style="margin:5%; ">
    <table style="background-color: lightgray;height: 22px;width: 500px">
        <tr style="background-color: gainsboro">
            <td>姓名</td>
            <td>電話</td>
            <td>地址</td>
        </tr>
        <tr>
            <td>${customer.cusName}</td>
            <td>${customer.cusPhone}</td>
            <td>${customer.cusAddress}</td>
        </tr>
    </table>
</div>

<!--返回會員頁面-->
<form action="welcomeCustomer" method="post">
    <button type="submit" name="cusId" value="${customer.cusId}">進入會員頁面</button>
</form>
<a href="${pageContext.request.contextPath}/index.jsp">登出並返回首頁</a>
</body>
</html>
