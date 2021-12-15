<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 2021/12/11
  Time: 下午 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>text</title>
</head>
<body>
<h1>一開始用座號排序</h1>
<form action="hw" method="POST" >
    <select name="formType">
        <option value="" selected>請選排序對象</option>
        <option value="chi">國文</option>
        <option value="eng">英文</option>
        <option value="math">數學</option>
        <option value="EOne">專一</option>
        <option value="EOne">專二</option>
        <option value="total">總分</option>
    </select>
    <p><input type="radio" name="orderBy" value="orderBy" >由小到大</p>
    <p><input type="radio" name="des"  value="des">由大到小</p>
    <input type="submit" value="排序">
</form>
<div>
    <table style="background-color: lightgray">
        <tr style="background-color: burlywood">
            <td>座號</td>
            <td>國文</td>
            <td>英文</td>
            <td>數學</td>
            <td>專一</td>
            <td>專二</td>
            <td>總分</td>
        </tr>
<c:forEach var="show" items="${show}">
    <tr>
        <td>${show.id}</td>
        <td>${show.chi}</td>
        <td>${show.eng}</td>
        <td>${show.math}</td>
        <td>${show.EOne}</td>
        <td>${show.ETwo}</td>
        <td>${show.total}</td>
    </tr>
</c:forEach>

    </table>
</div>
</body>
</html>
