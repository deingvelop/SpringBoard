<%@page import="com.example.springboard.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.example.springboard.post.vo.PostVO, com.example.springboard.member.vo.MemberVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	<!-- 주요 로직 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- 포맷팅 -->
<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">
<title>게시판</title>

<!-- Bootstrap을 사용하려고 해요! library가 있어야 해요! -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	width: 100%;
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}

.btn-bd-primary { -
	-bd-violet-bg: #712cf9; -
	-bd-violet-rgb: 112.520718, 44.062154, 249.437846; -
	-bs-btn-font-weight: 600; -
	-bs-btn-color: var(- -bs-white); -
	-bs-btn-bg: var(- -bd-violet-bg); -
	-bs-btn-border-color: var(- -bd-violet-bg); -
	-bs-btn-hover-color: var(- -bs-white); -
	-bs-btn-hover-bg: #6528e0; -
	-bs-btn-hover-border-color: #6528e0; -
	-bs-btn-focus-shadow-rgb: var(- -bd-violet-rgb); -
	-bs-btn-active-color: var(- -bs-btn-hover-color); -
	-bs-btn-active-bg: #5a23c8; -
	-bs-btn-active-border-color: #5a23c8;
}

.bd-mode-toggle {
	z-index: 1500;
}
</style>


<!-- Custom styles for this template -->
<link href="./css/dashboard.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>

</head>
<body>

	<div class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
	    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="board">${loginMember.nickname}님 환영합니다.</a>
	    <form class="d-flex" action="logout" method="POST">
 		    <button class="btn btn-dark btn-sm" type="submit" onclick="return confirmLogout()">로그아웃</button>
		</form>
		
 		<script>
		    function confirmLogout() {
		        var confirmed = confirm("로그아웃 하시겠습니까?");
		        if (confirmed) {
		            alert("로그아웃되었습니다.");
		            return true; // 로그아웃 수행
		        } else {
		            return false; // 로그아웃 취소
		        }
		    }
		</script>
	</div>


	<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-body-tertiary sidebar collapse">
				<div class="position-sticky pt-3 sidebar-sticky">
					<ul class="nav flex-column">
					</ul>

					<ul class="nav flex-column mb-2">
					</ul>
				</div>
			</nav>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">자유 게시판</h1>
					<div class="btn-toolbar mb-2 mb-md-0"></div>
				</div>
				<div>
					<form method="GET" action="/springboard/post/new">
						<input class="btn btn-dark btn-sm" type="submit" value="게시글 작성" style="float:right;">
					</form>
				</div>
				<br><br>
				<div class="table-responsive">
					<table class="table table-striped table-sm">
						<thead>
							<tr>
								<th scope="col">글 번호</th>
								<th scope="col">글 제목</th>
								<th scope="col">작성자</th>
								<th scope="col">작성일</th>
								<th scope="col">좋아요</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="post" items="${posts}">
								<tr>
									<td>${post.id}</td>
									<td><a href="/springboard/post/${post.id}" style="text-decoration: none; color: inherit;">${post.title}</a></td>
									<td>${post.memberNickname}</td>
									<td>${post.postDate}</td>
									<td>${post.likeCnt}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</main>
		</div>
	</div>

</body>
