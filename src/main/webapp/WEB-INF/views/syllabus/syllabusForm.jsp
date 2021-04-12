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

<title>Insert title here</title>
</head>
<style>
.input_text {
	width: 200px;
	height: 20px;
	font-size: 20px;
	border: 1px solid #000;
}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	function checkInputNum(){
	    if ((event.keyCode < 48) || (event.keyCode > 57)){
	        event.returnValue = false;
	    }
	}
</script>
	  
<body>

<form method="post"   action="${contextPath}/syllabus/addSyllabus.do" id="syllabusRegister">
	<h1  class="text_center">강의 계획서 등록</h1>
	<table  align="center" >
		<!-- <tr>
	      <td width="200"><p align="right">강좌유형 선택</td>
	      <td colspan="3" width="1000"><p><select name="type">
	        <option value="">유형 선택</option>
	      	<option value="재직자">재직자</option>
	      	<option value="채용예정자">채용예정자</option>
	      	<option value="구직자">구직자</option>
	      </select></td>
	   </tr> -->
	   <tr>
	      <td width="100"><p align="left">1차 분류</td>
	      <td width="400"><p><select name="type">
	        <option value="">유형 선택</option>
	      	<option value="재직자">재직자</option>
	      	<option value="채용예정자">채용예정자</option>
	      	<option value="구직자">구직자</option>
	      	<option value="재직자향상">재직자향상</option>
	      </select>
	            2차 분류
	      <select name="courseCategoryVO.id">
	      	<option value="" >유형 선택</option>
	      	<c:forEach var="courseCategory" items="${courseCategoryList }" >
	      		<option value="${courseCategory.id }">${courseCategory.name }</option>
	      	</c:forEach>
	      </select>
	      </td>
	      <td width="100"><p align="left">교육 일수</td>
	      <td width="400"><input type="text" name="days" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" class="input_text" style="width:30%;">일</td>
	   </tr>
	   <tr>
	      <td width="100"><p align="left">강의명</td>
	      <td width="400"><input type="text" name="name" class="input_text" style="width:60%;"></td>
	      <td width="100"><p align="left">교육 시간</td>
	      <td width="400"><input type="text" name="time" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" class="input_text" style="width:30%;">일</td>
	   </tr>
	   <tr>
	      <td width="100"><p align="left">보고용 강의명</td>
	      <td width="400"><input type="text" name="reportName" class="input_text" style="width:60%;"></td>
	      
	   </tr>
	    <tr>
	      <td colspan="4" width="1000"><p align="left">학습 개요</td>
	    </tr>
	    <tr>
	      <td colspan="4" width="1000"><p><textarea cols="50" rows="10" WRAP = "hard" name="overview" class="input_text" style="width: 100%; height: auto; resize: none;" ></textarea></td>
	    </tr>
	    <tr>
	      <td colspan="4" width="1000"><p align="left">학습 목표</td>
	   </tr>
	   <tr>
	      <td colspan="4" width="1000"><p><textarea cols="50" rows="10" WRAP = "hard" name="objectives" class="input_text" style="width: 100%; height: auto; resize: none;"></textarea></td>
	   </tr>
	    <tr>
	       <td colspan="4" width="1000"><p align="left">학습 대상</td>
	    </tr>
	    <tr>
	       <td colspan="4" width="1000"><p><textarea cols="50" rows="10" WRAP = "hard"  name="target" class="input_text" style="width: 100%; height: auto; resize: none;"></textarea></td>
	    </tr>
	    <tr>
	       <td colspan="4" width="1000"><p align="left">학습 내용</td>
	    </tr>
	    <tr>
	       <td colspan="4" width="1000"><p><textarea cols="50" rows="10" WRAP = "hard"  name="contents" class="input_text" style="width: 100%; height: auto; resize: none;"></textarea></td>
	    </tr>
	    <tr>
	       <td colspan="4" width="1000"><input type="submit" value="등록하기"><input type="button" onclick="history.back()" value="취소"></td>
	    </tr>
	</table>
	</form>
</body>
</html>