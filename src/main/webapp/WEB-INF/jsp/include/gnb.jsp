<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="h-100 d-flex bg-info justify-content-between align-items-center">
	<div class="logo">
		<h1>sns 게시판</h1>
	</div>
	<div class="Login-info">
	    <c:if test="${not empty userName}">
			<span class="text-white">${userName}님 안녕하세요</span>
			<a href="/user/sign_out" class="ml-2 text-white font-weight-bold">로그아웃</a>
		</c:if>
		<c:if test="${empty userName}">
			<a href="/user/sign_in_view" class="ml-2 text-white font-weight-bold">로그인</a>
		</c:if>
	</div>
</div>