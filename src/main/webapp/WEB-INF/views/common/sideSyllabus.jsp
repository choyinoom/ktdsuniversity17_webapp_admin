<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


	<h1>사이드 메뉴</h1>
	
	<h1>
		<a href="${contextPath}/course/listCourses.do"  class="no-underline">과정관리</a><br>
		<a href="${contextPath}/syllabus/listSyllabuses.do">강의계획서관리</a><br>
		<a href="${contextPath}/courseCategory/listAllCourseCategories.do"  class="no-underline">카테고리관리</a><br>
	</h1>
	 
