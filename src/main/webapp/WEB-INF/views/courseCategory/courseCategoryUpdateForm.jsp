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
			onclick="location.href='${contextPath}/courseCategory/updateCourseCategoryForm.do?id=${courseCategoryVO.id }'"
			style="cursor: pointer;">카테고리 수정</span>
		</h5>
	</div>
	
	<h1  class="text_center">카테고리 수정</h1>
	
	<div class="table_content">
		<form method="post"   action="${contextPath}/courseCategory/updateCourseCategory.do">
			<input type="hidden" name="id" value="${courseCategoryVO.id }">
			<table  align="center" id="categoryTable">
				<tr>
					<td>분류 명</td>
					<td><input type="text" name="name" value="${courseCategoryVO.name}"></td>
				</tr>
				<tr>
					<td colspan="2" style="padding-top: 5%;"><input type="submit" id="enrollButton" value="수정"><input type="button" onclick="history.back()" value="취소"></td>
				</tr>
			</table>
		</form>
	</div>
<%} else {  %>
<script>
	window.location.href="${contextPath}";
</script>
<% } %>	
</body>
</html>