<%@ page import="javafx.scene.control.Alert" %>
<%@ page import="java.net.URL" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>註冊頁面</title>
</head>
<body>
<h1>註冊系統</h1>
<div style="border-color: black;background-color: gray ; solid-color: white">
    <form action="register" method="POST" name="cusForm">
        姓名: <input type="text" name="name" ><br>
        電話: <input type="text" name="phone" ><br>
        地址: <input type="text" name="address" ><br>
        <input type="submit" name="submit" value="註冊">
        <input type="reset" name="reset" value="重設" >
    </form>
</div>
<a href="${pageContext.request.contextPath}/index.jsp">系統目錄</a>
</body>
</html>
