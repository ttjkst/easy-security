<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
</head>
<body>
        <form action="${ctx}/login/process" method="post">
            <input type="text" name="username"/>
            <input type="password" name="password"/>
            <button type="submit">提交</button>
        </form>

         <form action="${ctx}/shortCode/process" method="post">
                    <input type="hidden" name="_unique_code" value="test_1"/>
                    <label>短码登入</label>
                    <input type="password" name="_short_code"/>
                    <button type="submit">提交</button>
          </form>
</body>
</html>