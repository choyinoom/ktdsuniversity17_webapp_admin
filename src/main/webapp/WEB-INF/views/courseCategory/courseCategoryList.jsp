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
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<body>
<h1>카테고리 목록 창</h1>
<table align="center" border="0"  width="80%"  >
  <tr height="10" align="center">
     <td><input type="checkbox" id="selectAll"></td>
     <td><b>아이디</b></td>
     <td><b>2차분루</b></td>
  </tr>
	<c:choose>
	  <c:when test="${empty courseCategoryList }" >
	    <tr  height="10">
	      <td colspan="4">
	         <p align="center">
	            <b><span style="font-size:9pt;">등록된 범주가 없습니다.</span></b>
	        </p>
	      </td>  
	    </tr>
	  </c:when>
	  <c:when test="${not empty courseCategoryList }" >
		    <c:forEach  var="courseCategory" items="${courseCategoryList }" varStatus="categoryNum" >
			    <tr align="center">
					<td><input type="checkbox"></td>
					<td >${courseCategory.id }</td>
					<td align='left'  width="30%">
					  <span style="padding-right:10px"></span>
					  <a href="#">${courseCategory.name }</a>
					 </td>
				</tr>
		    </c:forEach>
	   </c:when>
	</c:choose>
</table>
<button type="button" onclick="location.href='${contextPath}/course/courseForm.do'" style="width: 5%;">등록</button>
<button type="button" onclick="location.href='#'" style="width: 5%;">삭제</button>
 <%-- <td><a href="${contextPath}/course/updateCourseForm.do?id=${course.id}&slbId=${course.slbId}">수정하기</a></td>
	 <td><a href="${contextPath}/course/removeCourse.do?id=${course.id}">삭제하기</a></td> --%>
</body>
</html>