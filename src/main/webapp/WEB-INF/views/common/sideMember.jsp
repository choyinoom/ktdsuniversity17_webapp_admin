<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<div class="side">
	<h1 class="side_title">회원관리</h1>
	<ul>
		<li><a href="${contextPath}/company/listPartners.do" class="no-underline">협력회사</a></li><br>
		<li><a href="${contextPath}/member/listMembers.do"  class="no-underline">학생관리</a></li><br>
		<li><a href="${contextPath}/company/listCompanies.do" class="no_unerline">회사관리</a></li><br>
		<li><a href="#"  class="no-underline">계정관리</a></li><br>
	</ul>
</div>
