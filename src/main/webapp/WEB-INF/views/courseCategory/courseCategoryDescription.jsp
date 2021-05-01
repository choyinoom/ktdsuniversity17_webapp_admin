<%@page import="com.ktdsuniversity.admin.manager.vo.ManagerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
   request.setCharacterEncoding("UTF-8");
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<style>
	
	td {
	  	border-bottom: none;
	  	height: 40px;
	  	padding: 10px 0;
	  }
	  
	.process {
		text-align: left;
		color: #9C9D9D;
		margin-bottom: 2em;
	}
	
	.table_content{
		justify-content: center;
		display: flex;
	}
	
	#categoryTable{
		width: 500px;
		margin-top: 60px;
	}
</style>
<script>
	function categoryRemove() {
		if (confirm('삭제하시겠습니까?') == true) {
			window.location.href = '${contextPath}/courseCategory/removeCourseCategory.do?id=${courseCategoryVO.id}';
		} else {
			return false;
		}
	}
</script>
<body>
<%
	ManagerVO manager = (ManagerVO)session.getAttribute("manager");
	Boolean isLogOn = (Boolean)session.getAttribute("isLogOn");
	
	if (manager != null && isLogOn == true) {
		
	
%>
	
	<div class="process">
		<h5>
			<span onclick="location.href='${contextPath}/course/listCourses.do'"
			style="cursor: pointer;">과정관리</span> > <span
			onclick="location.href='${contextPath}/courseCategory/listAllCourseCategories.do'"
			style="cursor: pointer;">카테고리 관리</span> > <span
			onclick="location.href='${contextPath}/courseCategory/selectCourseCategory.do?id=${courseCategoryVO.id }'"
			style="cursor: pointer;">카테고리 정보</span>
		</h5>
	</div>
	<h1  class="text_center">카테고리 상세 정보</h1>
	
	<div class="table_content">
		<table  align="center" id="categoryTable">
			<tr>
				<td>분류 명</td>
				<td><input type="text" name="name" value="${courseCategoryVO.name}" readonly></td>
			</tr>
			<tr>
				<td colspan="2" style="padding-top: 5%;">
				<button id="enrollButton" onclick="location.href='${contextPath}/courseCategory/updateCourseCategoryForm.do?id=${courseCategoryVO.id }'">수정</button>
				<button onclick="categoryRemove()" >삭제</button>
				<button onclick="location.href='${contextPath}/courseCategory/listAllCourseCategories.do?'">목록</button></td>
			</tr>
		</table>
	</div>
	<%} else {  %>
	<script>
		window.location.href="${contextPath}";
	</script>
	<% } %>	
</body>
</html>