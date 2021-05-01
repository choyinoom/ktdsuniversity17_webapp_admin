<%@page import="com.ktdsuniversity.admin.manager.vo.ManagerVO"%>
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
<style>
	.text_center{
  	padding-bottom: 30px;
  	border-bottom: 0.3px solid;
	border-color: #9C9D9D;
  }
  
  table {
	margin-top: 60px;
	position: absolute;
	left: 250px;
	width: 1100px;
  }
  
  td {
  	border-bottom: none;
  	height: 40px;
  	padding: 10px 0;
  }
	
	select {
	width: 120px;
	vertical-align: middle;
	text-align: center;
	text-align-last: center;
	}
	
	.input_text{
		width: 100%; 
		height: auto;
		 resize: none; 
		 background-color: #ECECEC; 
		 border: none; 
		 border-radius: 5px; 
		 padding: 10px 20px;
	}
	
	.process {
		text-align: left;
		color: #9C9D9D;
		margin-bottom: 2em;
	}
	
	.slb_button {
	width: 93%;
    margin: 3em;
	}
	
	.slb_button button {
	background-color: #ECECEC;
	color : #333333;
	}
	
	.slb_col2 {
	text-align: start;
	width: 24em;
	}
</style>
<script>

	function syllabusRemove() {
		if (confirm('삭제 하시겠습니까?') == true) {
			window.location.href = '${contextPath}/syllabus/removeSyllabus.do?id=${syllabusVO.id }&page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}';
		} else {
			return false;
		}
	}
</script>
<body>
	<div class="process">
		<h5>
			<span onclick="location.href='${contextPath}/course/listCourses.do'"
			style="cursor: pointer;">과정관리</span> > <span
			onclick="location.href='${contextPath}/syllabus/listSyllabuses.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'"
			style="cursor: pointer;"> 강의계획서 관리</span> > <span
			onclick="location.href='${contextPath}/syllabus/selectSyllabus.do?id=${syllabusVO.id }&page=${page }&searchText=${searchText }&searchType=${searchType }&perPage=${perPage }'"
			style="cursor: pointer;"> 강의계획서 상세</span> 
		</h5>
	</div>
		
	<div style="width: 85%; display: flex; flex-direction: column;">
	<div class="slb_title">
		<h1  class="text_center">상세 정보</h1>
	</div>
	<table style="position: relative; width: 58%; left: 18em;" >
	   <tr>
	      <td style="width: 8em;"><p align="left">1차 분류</td>
	      <td class="slb_col2"><p>
	      <select name="type">
		      <c:if test="${syllabusVO.type == '채용예정자' }">
			  	<option value="채용예정자" selected>채용예정자</option>
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
	      <td><p align="left">교육 일수</td>
	      <td><input type="text" name="days" readonly value="${syllabusVO.days }">일</td>
	   </tr>
	   <tr>
	      <td><p align="left">강의명</td>
	      <td class="slb_col2"><input type="text" name="name" readonly value="${syllabusVO.name }"></td>
	      <td><p align="left">교육 시간</td>
	      <td><input type="text" name="days" readonly value="${syllabusVO.time }">일</td>
	   </tr>
	   <tr>
	      <td><p align="left">보고용 강의명</td>
	      <td class="slb_col2"><input type="text" name="reportName" readonly value="${syllabusVO.reportName }"></td>
	      <td><p align="left">파일</td>
	      <td><a href="${contextPath}/fileDown.do?filename=${syllabusVO.bannerImg}&folderName=${syllabusVO.id }">${syllabusVO.bannerImg }</a></td>
	   </tr>
	    <tr>
	      <td colspan="4"><p align="left">학습 개요</td>
	    </tr>
	    <tr>
	      <td colspan="4"><p align="left"><textarea cols="50" rows="10" WRAP = "hard" readonly="readonly" class='input_text'>${syllabusVO.overview }</textarea></td>
	    </tr>
	    <tr>
	      <td colspan="4"><p align="left">학습 목표</td>
	   </tr>
	   <tr>
	      <td colspan="4"><p align="left"><textarea cols="50" rows="10" WRAP = "hard" readonly="readonly" name="objectives" class='input_text' >${syllabusVO.objectives }</textarea></td>
	   </tr>
	    <tr>
	       <td colspan="4"><p align="left">학습 대상</td>
	    </tr>
	    <tr>
	       <td colspan="4"><p align="left"><textarea cols="50" rows="10" WRAP = "hard" readonly="readonly" name="target" class='input_text' >${syllabusVO.target }</textarea></td>
	    </tr>
	    <tr>
	       <td colspan="4"><p align="left">학습 내용</td>
	    </tr>
	    <tr>
	       <td colspan="4"><p align="left"><textarea cols="50" rows="10" WRAP = "hard" readonly="readonly" name="contents" class='input_text' >${syllabusVO.contents }</textarea></td>
	    </tr>
	</table>
	    <div class="slb_button">
		       <button onclick="location.href='${contextPath}/syllabus/updateSyllabusForm.do?id=${syllabusVO.id }&page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'" id="enrollButton">수정</button>
		       <button onclick="syllabusRemove()">삭제</button>
		       <button onclick="history.back()">목록</button>
	    </div>
	</div>
</body>
</html>