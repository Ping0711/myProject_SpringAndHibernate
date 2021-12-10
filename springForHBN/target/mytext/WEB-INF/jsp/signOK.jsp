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
<h3>註冊資料<h3>
        <div style="margin:5%; ">
            <table style="margin: 10px;">
                <tr style="font-size: 22px;padding-bottom: 2px">
                    <td>姓名</td>
                    <td>電話</td>
                    <td>地址</td>
                </tr>
            </table>
        </div>
        <a href="${pageContext.request.contextPath}/index.jsp" style="font-size: 14px">系統目錄</a>
</body>
</html>
