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
		<style>
		    .login-wrapper {
		        text-align: center;
		    }
		    .login-heading {
		        text-align: center;
		    }
		</style>
	</head>
	<body>
		<c:if test="${showAlert}">
	        <script>
	            alert("${errorMessage} : ${errorMessage}");
	        </script>
	    </c:if>
	
	    <div class="login-wrapper">
		    <h2 class="login-heading">Login</h2>
		    <form method="post" action="login" id="login-form">
		        <input type="text" name="username" placeholder="아이디">
		        <input type="password" name="password" placeholder="비밀번호">
		        <input type="submit" value="로그인">
		    </form>   
		    <p><a href="join" style="color: green;" onclick="alert('회원가입 페이지로 이동합니다.');">아직 계정이 없으십니까? 회원가입하기</a></p>
		</div>
	</body>                                                                
</html>