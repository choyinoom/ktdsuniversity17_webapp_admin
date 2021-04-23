<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="side">

	<h1 class="side_title">게시판관리</h1>
	<ul>
		<li><a href="${contextPath}/board/listArticles.do" class="no-underline">공지사항</a></li><br>
		<li><a href="#" class="no-underline">FAQ</a></li> <br>
		<li><a href="#" class="no-underline">자료실</a></li> <br>
		<li><a href="#" class="no-underline">이벤트</a></li> <br>
		<li><a href="#" class="no-underline">이름</a></li> <br>
	</ul>
</div>

