<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

</head>
<body>
<h1  class="text_center">강의 계획서 상세창</h1>
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
	      <td width="400"><p>
	      <select name="type">
	      <c:if test="${syllabusVO.type == '재직자' }">
		  <option value="재직자" selected>재직자</option>
	      </c:if>
	      <c:if test="${syllabusVO.type == '채용예정자' }">
		  <option value="채용예정자" selected>채용예정자</option>
	      </c:if>
	      <c:if test="${syllabusVO.type == '구직자' }">
		  <option value="구직자" selected>구직자</option>
	      </c:if>
	      <c:if test="${syllabusVO.type == '재직자향상' }">
		  <option value="재직자향상" selected>재직자향상</option>
	      </c:if>
	      </select>
	      
	      2차 분류
	      <select name="courseCategoryVO.id">
	      <option value="${syllabusVO.courseCategoryVO.name }" >${syllabusVO.courseCategoryVO.name }</option>
	      </select>
	      </td>
	      <td width="100"><p align="left">교육 일수</td>
	      <td width="400"><input type="text" name="days" class="input_text" style="width:30%;" readonly value="${syllabusVO.days }">일</td>
	   </tr>
	   <tr>
	      <td width="100"><p align="left">강의명</td>
	      <td width="400"><input type="text" name="name" class="input_text" style="width:60%;" readonly value="${syllabusVO.name }"></td>
	      <td width="100"><p align="left">교육 시간</td>
	      <td width="400"><input type="text" name="days" class="input_text" style="width:30%;" readonly value="${syllabusVO.time }">일</td>
	   </tr>
	   <tr>
	      <td width="100"><p align="left">보고용 강의명</td>
	      <td width="400"><input type="text" name="reportName" class="input_text" style="width:60%;" readonly value="${syllabusVO.reportName }"></td>
	      
	   </tr>
	    <tr>
	      <td colspan="4" width="1000"><p align="left">학습 개요</td>
	    </tr>
	    <tr>
	      <td colspan="4" width="1000"><p><textarea cols="50" rows="10" WRAP = "hard" readonly="readonly" class="input_text" style="width: 100%; height: auto; resize: none;" >${syllabusVO.overview }</textarea></td>
	    </tr>
	    <tr>
	      <td colspan="4" width="1000"><p align="left">학습 목표</td>
	   </tr>
	   <tr>
	      <td colspan="4" width="1000"><p><textarea cols="50" rows="10" WRAP = "hard" readonly="readonly" name="objectives" class="input_text" style="width: 100%; height: auto; resize: none;">${syllabusVO.objectives }</textarea></td>
	   </tr>
	    <tr>
	       <td colspan="4" width="1000"><p align="left">학습 대상</td>
	    </tr>
	    <tr>
	       <td colspan="4" width="1000"><p><textarea cols="50" rows="10" WRAP = "hard" readonly="readonly" name="target" class="input_text" style="width: 100%; height: auto; resize: none;">${syllabusVO.target }</textarea></td>
	    </tr>
	    <tr>
	       <td colspan="4" width="1000"><p align="left">학습 내용</td>
	    </tr>
	    <tr>
	       <td colspan="4" width="1000"><p><textarea cols="50" rows="10" WRAP = "hard" readonly="readonly" name="contents" class="input_text" style="width: 100%; height: auto; resize: none;">${syllabusVO.contents }</textarea></td>
	    </tr>
	    <tr>
	       <td colspan="4" width="1000"><button onclick="location.href='${contextPath}/syllabus/updateSyllabusForm.do?id=${syllabusVO.id }'">수정하기</button><input type="button" onclick="history.back()" value="취소"></td>
	    </tr>
	</table>
</body>
</html>