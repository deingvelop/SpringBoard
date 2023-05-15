<%@page import="com.example.springboard.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*, com.example.springboard.post.vo.PostVO, com.example.springboard.member.vo.MemberVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 주요 로직 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 포맷팅 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 조회</title>
		<link rel="stylesheet" href="/springboard/WEB-INF/views/css/post.css">
		<link
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
			rel="stylesheet">
		
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css"
			rel="stylesheet">
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
			crossorigin="anonymous">
		
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src="./js/post.js"></script>
		<script>
			function deletePost(postId) {
				if (confirm('게시글을 삭제하시겠습니까?')) {
					$.ajax({
						type : 'DELETE',
						url : '/springboard/post/' + postId,
						success : function(result) {
							alert('게시글이 삭제되었습니다.');
							window.location.href = '/springboard/board';
						},
						error : function(err) {
							alert('게시글 삭제에 실패하였습니다.');
						}
					});
				}
			}
		
			function deleteComment(postId, commentId) {
				if (confirm('댓글을 삭제하시겠습니까?')) {
					$.ajax({
						type : 'DELETE',
						url : '/springboard/post/' + postId + '/comment/' + commentId,
						success : function(result) {
							alert('댓글이 삭제되었습니다.');
							window.location.reload("/springboard/post/" + postId);
						},
						error : function(err) {
							console.log(err.responseText); // 에러 응답 내용을 콘솔에 출력
							alert('댓글 삭제가 실행 되었습니다. (단, 작은 오류 있음)');
							window.location.reload("/springboard/post/" + postId);
						}
					});
				}
			}
		
			function showEditForm(commentId, commentContent) {
	            document.getElementById("editCommentId").value = commentId;
	            document.getElementById("editCommentContent").value = commentContent;
	            document.getElementById("editForm").style.display = "block";
	        }

	        function cancelEditForm() {
	            document.getElementById("editCommentId").value = "";
	            document.getElementById("editCommentContent").value = "";
	            document.getElementById("editForm").style.display = "none";
	        }
		</script>
		<style>
		.edit-comment-textarea {
			display: block !important;
		}
		.hidden-textarea {
		   display: none;
		 }	
		</style>
	</head>
<body>
	<div class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
	    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="/springboard/board">${loginMember.nickname}님 환영합니다.</a>
	    <form class="d-flex" action="logout" method="POST">
			<button class="btn btn-dark btn-sm" type="submit" onclick="return confirm('로그아웃 하시겠습니까?')">로그아웃</button>
	    </form>
	</div>


	<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-body-tertiary sidebar collapse">
				<div class="position-sticky pt-3 sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/springboard/board"
							style="text-decoration: none; color: inherit;">게시글 목록</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/springboard/post/new"
							style="text-decoration: none; color: inherit;">새 글 작성</a></li>
					</ul>

					<ul class="nav flex-column mb-2">
					</ul>
				</div>
			</nav>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">게시글 조회</h1>
					<div class="btn-toolbar mb-2 mb-md-0"></div>
				</div>
				<div class="card mb-3">
					<div class="card-header">
						<h3 class="card-title" style="font-weight: bolder;">${post.title}</h3>
						<div class="card-subtitle mb-2 text-muted">${post.memberNickname} &nbsp;|&nbsp; ${post.postDate}</div>
						<div style="display: flex; flex-direction: row; justify-content: flex-end; align-items: center;">
							<div class="card-subtitle mb-2 text-muted" style="margin-right: 10px;">좋아요 ${post.likeCnt}</div>
								<form name="likeForm" action="like?postId=${post.id}" method="POST">
									<c:choose>
										<c:when test="${post_liked == true}">
											<input type="submit" onclick="javascript:document.likeForm.submit();" class="btn btn-dark btn-sm" value="좋아요 취소">
										</c:when>
										<c:when test="${post_liked == false}">
											<input type="submit" onclick="javascript:document.likeForm.submit();" class="btn btn-outline-dark btn-sm" value="좋아요">
										</c:when>
									</c:choose>
								</form>
						</div>
					</div>


					<div class="card-body">
						<p class="card-text">${post.content}</p>
						<c:if test="${post.memberId eq loginMember.id}">
							<div style="display: flex; flex-direction: row; align-items: center; justify-content: flex-end;">
							  	<button type="button" class="btn btn-outline-secondary btn-sm mx-2">
							  		<a href="/springboard/post/${post.id}/edit" style="text-decoration: none; color: inherit;">수정</a>
					  			</button>
							  <button type="button" class="btn btn-outline-danger btn-sm mx-2" onclick="deletePost('${post.id}')">삭제</button>	
							</div>
						</c:if>
					</div>
				</div>

				<div class="card mb-3">
					<div class="card-header">
						<h6 class="card-title">댓글</h6>
					</div>
					<div class="card-body">
						<form method="POST" action="/springboard/post/${post.id}/comment">
							<div class="mb-3">
								<label for="exampleFormControlTextarea1" class="form-label">댓글 내용</label>
								<textarea class="form-control" id="exampleFormControlTextarea1"
									rows="3" name="content" onkeydown="if(event.keyCode == 13){ this.form.submit(); return false;}" maxlength="500" required></textarea>
							</div>
							<div class="d-flex justify-content-end">
								<input class="btn btn-dark btn-sm" type="submit" value="작성"
									onclick="javascript:alert('댓글 작성 완료!');">
							</div>
						</form>
					</div>
					<div class="card-footer">
						<table class="table table-sm">
							<thead>
								<tr>
									<th scope="col">작성자</th>
									<th scope="col">내용</th>
									<th scope="col">작성일</th>
									<th scope="col">수정</th>
									<th scope="col">삭제</th>
								</tr>
							</thead>
							<tbody>
								<!-- 수정된 댓글 목록 부분 -->
								<c:forEach var="comment" items="${comments}">
								  <tr>
								    <td width='80px'>${comment.memberNickname}</td>
								    <td style="word-break: break-all">${comment.content}</td>
								    <td width='100px'>${comment.commentDate}</td>
								    <%-- 현재 세션의 로그인 멤버와 댓글 작성자가 동일한 경우에만 수정 및 삭제 버튼 표시 --%>
								        <c:if test="${loginMember.id eq comment.memberId}">
								       		<td><a class="btn btn-outline-secondary btn-sm" onclick="showEditForm(${comment.id}, '${comment.content}')">수정</a></td>
											<td><button type="button" class="btn btn-outline-danger btn-sm mx-2" onclick="deleteComment('${post.id}', '${comment.id}')">삭제</button></td>
										</c:if>
								  </tr>
									<%-- 댓글 수정 폼 --%>
									<div id="editForm" style="display: none;">
										<h2>댓글 수정</h2>
										<form id="editCommentForm" action="/springboard/post/${post.id}/comment/${comment.id}"
											method="post">
											<input type="hidden" name="commentId" id="editCommentId">
											<textarea name="content" id="editCommentContent"
												rows="4" cols="50" class="form-control"></textarea>
											<br> <input type="submit" value="저장">
											<button type="button" onclick="cancelEditForm()">취소</button>
										</form>
									</div>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</main>
		</div>


	</div>
</body>
</html>