<%@page import="com.example.springboard.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.example.springboard.post.vo.PostVO, com.example.springboard.member.vo.MemberVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	<!-- 주요 로직 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- 포맷팅 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 작성</title>
		
		<!-- Bootstrap을 사용하려고 해요! library가 있어야 해요! -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
		<link rel="stylesheet" href="./css/post.css">
		<!--제이쿼리-->
	    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

	</head>

<body>
	<div class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
	    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="/mzboard/board">${loginMember.nickname}님 환영합니다.</a>
	    <form class="d-flex" action="/springboard/logout" method="POST">
			<button class="btn btn-dark btn-sm" type="submit" onclick="return confirm('로그아웃 하시겠습니까?')">로그아웃</button>
	    </form>
	</div>

	<div class="container-fluid">
		<div class="row"">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-body-tertiary sidebar collapse">
				<div class="position-sticky pt-3 sidebar-sticky">
					<ul class="nav flex-column">
					</ul>

					<ul class="nav flex-column mb-2">
					</ul>
				</div>
			</nav>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4" >
			  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			    <h1 class="h2">게시글 작성</h1>			
				<div class="btn-toolbar mb-2 mb-md-0"></div>
			  </div>
			  <form method="POST" action="/springboard/post/new">
			    <div class="mb-3" style="margin: 0 auto">
			      <label for="exampleFormControlInput1" class="form-label">글 제목</label>
			      <input type="text" class="form-control" id="exampleFormControlInput1" name="title" required>
			    </div>
			    <div class="mb-3" style="margin: 0 auto">
			      <label for="exampleFormControlInput1" class="form-label">글 작성자</label>
			      <input type="text" disabled value="${loginMember.nickname}" class="form-control" id="exampleFormControlInput1">
			    </div>
			    <div class="mb-3">
			      <label for="exampleFormControlTextarea1" class="form-label">글 내용</label>
			      <textarea class="form-control" id="exampleFormControlTextarea1" rows="10" name="content"  maxlength="1000" id="postContent" required></textarea>
			    </div>
			    
			    <div class="d-flex justify-content-center" style="margin-right: 10px;">
			      <div class="btn-group" role="group">
			        <input class="btn btn-dark btn-sm" type="submit" value="저장" style="margin-right: 5px;" onclick="alert('글 작성이 완료되었습니다.');">
			        <a class="btn btn-danger btn-sm" href="/springboard/board">취소</a>
			      </div>
			    </div>
			  </form>
			</main>
		</div>
	</div>

</body>
</html>