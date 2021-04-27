<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강 등록</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	
	
	
	//아이디 검색 팝업
	function popup(){
		var url = "${contextPath}/member/memberSearchPopUp.do";
		var name = "popup test";
		var option = "width = 500, height = 500, top = 100, left = 200, location = no"
		window.open(url, name, option);
	}
	
</script>

</head>
<body>
	<h4 align="left"> 수강신청내역 > 수강등록</h4>
	<form method="post" action="${contextPath}/enrollment/addEnrollment.do" id=registerFrm>

		<table align="center">
			<tr>
				<td width="200"><p align="right">아이디</td>
				<!-- <td width="400"><input type="text" name="memberVO.id"></td> -->
				<td width="400"><p align="center">
						<input type="text" name="memberVO.id" id="memberId"
							value="${enrollmentVO.memberVO.id }" placeholder="아이디를 검색하세요." readonly> 
						<a type="button" onclick="popup()">아이디 검색</a>
			</tr>
			<tr>
				<td width="200"><p align="right">과정명</td>
				<!-- <td width="400"><input type="text" name="courseVO.id"></td> -->
				<br>
				<td width="400"><select style="width:300px;" name="courseVO.id" id="courseId">
						<option value="" disabled selected>과정명을 선택하세요</option>
						<c:forEach var="enrollmentVO" items="${syllabusesCoursesList}">
							<option value="${enrollmentVO.courseVO.id }">${enrollmentVO.syllabusVO.name }</option>
						</c:forEach>
				</select></td>
			</tr>
	   
		</table>

		<input type="submit" value="등록" style="width: 5%;">&nbsp;<input
			type="reset" value="다시입력" id="reset" style="width: 5%;">&nbsp;
		<button type="button"
			onclick="location.href = '${contextPath}/enrollment/listEnrollments.do' "
			style="width: 5%;">취소</button>

	</form>
</body>
</html>