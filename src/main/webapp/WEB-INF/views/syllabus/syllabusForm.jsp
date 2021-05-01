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

<title>Insert title here</title>
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
	
	#imgViewArea{
		height: 140px; 
		padding-top: 10px;
	}
	
	#imgArea{
	width: 10em;
    height: 10em;
    position: relative;
    left: -4em;
    background: #ececec;
	}
	
	.slb_col2 {
	text-align: start;
	width: 24em;
	}
	
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	
	
	$(function() {
		$('#syllabusRegister').submit(function(){
			if (confirm('최종 등록하시겠습니까?') == true) {
				
				var frm = document.slbFrm;
				var type = frm.type.value;
				var days = frm.days.value;
				var courseCategory = document.getElementById("courseCategory").value;
				var name = frm.name.value;
				var time = frm.time.value;
				var reportName = frm.reportName.value;
				var overview = frm.overview.value;
				var objectives = frm.objectives.value;
				var target = frm.target.value;
				var contents = frm.contents.value;
				
				
				if (type == "") {
					alert("1차 분류를 선택해 주세요.");
					frm.type.focus();
					return false;
				} else if (courseCategory == "") {
					alert("2차 분류를 선택해 주세요.");
					document.getElementById("courseCategory").focus();
					return false;
				} else if (days == "") {
					alert("교육 일을 입력해 주세요.")
					frm.days.focus();
					return false;
				} else if (name == "") {
					alert("강의명을 입력해주세요.");
					frm.name.focus();
					return false;
				} else if (time == "") {
					alert("교육 시간을 입력해주세요.");
					frm.time.focus();
					return false;
				} else if (reportName == "") {
					alert("보고용 강의명을 입력해주세요.");
					frm.reportName.focus();
					return false;
				} else if (overview == "") {
					alert("학습 개요를 입력해주세요.");
					frm.overview.focus();
					return false;
				} else if (objectives == "") {
					alert("학습 목표를 입력해주세요.");
					frm.objectives.focus();
					return false;
				} else if (target == "") {
					alert("학습 대상을 입력해주세요.");
					frm.target.focus();
					return false;
				} else if (contents == "") {
					alert("학습 내용을 입력해주세요.");
					frm.contents.focus();
					return false;
				}
				return true;
			} else {
				return false;
			}
		})
		
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#imgArea').attr('src', e.target.result); 
				}
				reader.readAsDataURL(input.files[0]);
			}
		}

		$(":input[name='uploadFile']").change(function() {
			if( $(":input[name='uploadFile']").val() == '' ) {
				$('#imgArea').attr('src' , '');  
			}
			$('#imgViewArea').css({ 'display' : '' });
			readURL(this);
		});

		// 이미지 에러 시 미리보기영역 미노출
		function imgAreaError(){
			$('#imgViewArea').css({ 'display' : 'none' });
		}
	}) // function
</script>
	  
<body>
	<div class="process">
		<h5>
			<span onclick="location.href='${contextPath}/course/listCourses.do'"
			style="cursor: pointer;">과정관리</span> > <span
			onclick="location.href='${contextPath}/syllabus/listSyllabuses.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'"
			style="cursor: pointer;"> 강의계획서 관리</span> > <span
			onclick="location.href='${contextPath}/syllabus/syllabusForm.do?page=${page }&searchText=${searchText }&searchType=${searchType }&perPage=${perPage }'"
			style="cursor: pointer;"> 강의계획서 등록</span> 
		</h5>
	</div>
	
	<div style="width: 85%; display: flex; flex-direction: column;">
	<div class="slb_title">
		<h1 class="text_center">강의 계획서 등록</h1>
	</div>
	
	<form method="post"   action="${contextPath}/syllabus/addSyllabus.do?page=${page}&searchText=${searchText}&searchType=
	${searchType}&perPage=${perPage}" id="syllabusRegister" name="slbFrm" enctype="multipart/form-data"> 
	
		<table style="position: relative; width: 58%; left: 18em; margin-bottom: 3em;">
		   <tr>
		      <td><p align="left">1차 분류</td>
		      <td class="slb_col2">
		      <select name="type">
		        <option value="">유형 선택</option>
		      	<option value="채용예정자">채용예정자</option>
		      	<option value="재직자향상">재직자향상</option>
		      </select>
		            2차 분류
		      <select name="courseCategoryVO.id" id="courseCategory">
		      	<option value="" >유형 선택</option>
		      	<c:forEach var="courseCategory" items="${courseCategoryList }" >
		      		<option value="${courseCategory.id }">${courseCategory.name }</option>
		      	</c:forEach>
		      </select>
		      </td>
		      <td><p align="left">교육 일수</td>
		      <td><input type="text" name="days" style="position: relative; left: -0.5em;" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" >일</td>
		   </tr>
		   <tr>
		      <td ><p align="left">강의명</td>
		      <td class="slb_col2"><input type="text" name="name"></td>
		      <td ><p align="left">교육 시간</td>
		      <td><input type="text" name="time" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" >시간</td>
		   </tr>
		   <tr>
		      <td ><p align="left">보고용 강의명</td>
		      <td class="slb_col2"><input type="text" name="reportName"></td>
		      <td ><p align="left">파일 업로드</td>
		      <td><input type="file" name="uploadFile" accept="image/png, image/jpeg"><input type="hidden" name="originalReportName" value="이성현"></td>
		   </tr>
		   <tr>
		   	<td></td>
		   	<td></td>
		   	<td height="150"></td>
		   	<td><div id="imgViewArea">
		   		<img id="imgArea" onerror="imgAreaError()" ></div></td>
		   </tr>
		    <tr>
		      <td colspan="4" ><p align="left">학습 개요</td>
		    </tr>
		    <tr>
		      <td colspan="4" ><p align="left" ><textarea cols="50" rows="10" WRAP = "hard" name="overview" class='input_text' placeholder="학습 개요를 작성해주세요"></textarea></td>
		    </tr>
		    <tr>
		      <td colspan="4" ><p align="left">학습 목표</td>
		   </tr>
		   <tr>
		      <td colspan="4" ><p align="left"><textarea cols="50" rows="10" WRAP = "hard" name="objectives" class='input_text' placeholder="학습 목표를 작성해주세요"></textarea></td>
		   </tr>
		    <tr>
		       <td colspan="4" ><p align="left">학습 대상</td>
		    </tr>
		    <tr>
		       <td colspan="4" ><p align="left"><textarea cols="50" rows="10" WRAP = "hard"  name="target" class='input_text' placeholder="학습 대상을 작성해주세요"></textarea></td>
		    </tr>
		    <tr>
		       <td colspan="4" ><p align="left">학습 내용</td>
		    </tr>
		    <tr>
		       <td colspan="4" ><p align="left"><textarea cols="50" rows="10" WRAP = "hard"  name="contents" class='input_text' placeholder="학습 내용을 작성해주세요"></textarea></td>
		    </tr>
		    <tr>
		       <td></td>
		       <td style="text-align: right; padding-right: 20px;"><input type="submit" id="enrollButton" value="등록"><input type="button" style="margin-left: 1em;" onclick="history.back()" value="취소"></td>
		       <td></td>
		       <td></td>
		    </tr>
		</table>
		</form>
</body>
</html>