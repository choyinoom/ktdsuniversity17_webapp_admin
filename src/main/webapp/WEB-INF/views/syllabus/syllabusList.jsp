<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    

<html>
<head>
<style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
</style>
<meta charset=UTF-8">
<title>회원 정보 목록창</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>

	function getCheckListRemove() {
		var length = $("input:checkbox[name='selectedCheckbox']:checked").length;
		alert(length);
		var arr = new Array();
		$("input:checkbox[type=checkbox]:checked").each(function(index) {
			/* alert($(this).attr('id')); */
			arr.push($(this).attr('id'));
		})
		
		if(length == 0){
			alert("선택된 값이 없습니다.");
			return false;
		} else{
			$.ajax({
				type: 'post',
				url: '${contextPath}/syllabus/removeCheckedSyllabuses.do',
				traditional : true, //Array 형태로 보내려면 설정 해줘야함
				data: {arr : arr},
				
				success: function(data) {
					alert('데이터 받기 성공');
					alert(data);
					window.location.href = "${contextPath}/syllabus/listSyllabuses.do";
				}, error:function(data,request,status,error){
		             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		             
		        }
			})
		}
	}
	
	$(function(){
		$('#selectAll').click(function(){
			if ($("input:checkbox[id='selectAll']").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else{
				$("input[type=checkbox]").prop("checked", false);
			}
		})	
		
	})
</script>
<body>
<table border="0"  align="center"  width="80%">
    <tr align="center">
      <td><input type="checkbox" id="selectAll"></td>
      <td><b>강의명</b></td>
      <td><b>1차 분류</b></td>
      <td><b>2차 분류</b></td>
      <td><b>가입일</b></td>
   </tr>
   
 <c:forEach var="syllabus" items="${syllabusesList}" >     
   <tr align="center">
      <td><input type="checkbox" name="selectedCheckbox" id="${syllabus.id }"></td>
      <td><a class='cls1' href="${contextPath}/syllabus/selectSyllabus.do?id=${syllabus.id}">${syllabus.name}</a></td>
      <td>${syllabus.type }</td>
      <td>${syllabus.courseCategoryVO.name }</td>
      <td>${syllabus.joinDate}</td>
    </tr>
  </c:forEach>   
</table>
<button onclick="location.href='${contextPath}/syllabus/syllabusForm.do'" style="width: 5%;">등록</button>
<button onclick="getCheckListRemove()" style="width: 5%;">삭제</button>
</body>
</html>
