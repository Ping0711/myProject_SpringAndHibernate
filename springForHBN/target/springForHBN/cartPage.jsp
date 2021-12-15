<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>購物商城</title>
</head>
<body>
<h1>歡迎${customer.cusName}!請趕快購買想要的商品吧!</h1>
<!-- 商品全覽 -->
<div style="background-color: gainsboro;width: auto ;height: auto">
    <c:forEach var="products" items="${product}">
        <form action="buyProduct" method="post" name="ProductForm">
            <p>商品名稱 : ${products.proName}</p>
            <p>商品價格 : ${products.proPrice}</p>
            <p>圖片如下 : </p>
            <p><img src="${pageContext.request.contextPath}/${products.proPicture}" width="150" height="150"></p>
            <button value="${products.proId}" name="proId">我想購買</button>
        </form>
    </c:forEach>
</div>
<a href="${pageContext.request.contextPath}/index.jsp">系統目錄</a>
</body>
</html>