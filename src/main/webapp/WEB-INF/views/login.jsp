<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
		<link rel="stylesheet" href="./css/login.css">
	</head>
	<body>
	    <div class="login-wrapper">
	        <h2>Login</h2>
	        <form method="post" action="login" id="login-form">
	            <input type="text" name="username" placeholder="아이디">
	            <input type="password" name="password" placeholder="비밀번호">
	            <input type="submit" value="로그인">
	        </form>
	    </div>
	</body>
</html>