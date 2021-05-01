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
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title></title>
</head>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <style>
  .text_center{
  	padding-bottom: 30px;
  	border-bottom: 0.3px solid;
	border-color: #9C9D9D;
  }
  
  table {
	margin-top: 60px;
  }
  
  td {
  	border-bottom: none;
  	height: 40px;
  	padding: 10px 0;
  }
  
  .searchButton {
	  background-color: #969696;
	  border: none;
	  border-radius: 5px;
	  color: #EFEFEF;
	  padding: 5px 12px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 15px;
	  margin: 5px;
	  cursor: pointer;
	}
	
	input [type='radio'] {
		padding-left: 20px;
	}
	
	#syllabusName{
		width: 300px;
	}
	
	select {
		width: 120px;
		vertical-align: middle;
		text-align: center;
		text-align-last: center;
	}
	
	.process {
		text-align: left;
		color: #9C9D9D;
		margin-bottom: 2em;
	}
	
	select:disabled{
		background: #ECECEC;
		color: black;
		opacity: 1;
		cursor: default;
	}
	
	.ui-datepicker-trigger {
		width: 1.8em;
	}
	
	#imgArea {
		height: 7.7em;
	}
  </style>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
	  $(function() {
          //모든 datepicker에 대한 공통 옵션 설정
          $.datepicker.setDefaults({
              dateFormat: 'yymmdd' //Input Display Format 변경
              ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
              ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
              ,changeYear: true //콤보박스에서 년 선택 가능
              ,changeMonth: true //콤보박스에서 월 선택 가능                
              ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
              ,buttonImage: "${contextPath}/resources/image/calendar.png" //버튼 이미지 경로
              ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
              ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
              ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
              ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
              ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
              ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
              ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
              ,minDate: "-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
              ,maxDate: "+5Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                    
          });

          //datepicker로 선언
          $("#datepicker1").datepicker();                    
          $("#datepicker2").datepicker();
          $("#datepicker3").datepicker();
          $("#datepicker4").datepicker();
          
          //시작 날짜 초기값을 오늘 날짜로 설정
          $('#datepicker1').datepicker('setDate', '${courseVO.startDate}'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
          $('#datepicker3').datepicker('setDate', '${courseVO.rsDate}');
          //종료 날짜 초기값을 내일로 설정
          $('#datepicker2').datepicker('setDate', '${courseVO.endDate}'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
          $('#datepicker4').datepicker('setDate', '${courseVO.reDate}');
             
          
          $("#courseTime1").on("click", function(){
        	  var timeArray = $("input:radio[name=courseTime]:checked").val().split('~');
        	  var startTime = timeArray[0];
        	  var endTime = timeArray[1];
        	  $('#startTime').val(startTime);
        	  $('#endTime').val(endTime);
        	  $('#startTime').attr('disabled', true);
        	  $('#endTime').attr('disabled', true);
       	  })
          
       	  $("#courseTime2").on("click", function(){
        	  // alert($("input:radio[name=courseTime]:checked").val());
        	  var timeArray = $("input:radio[name=courseTime]:checked").val().split('~');
        	  var startTime = timeArray[0];
        	  var endTime = timeArray[1];
        	  $('#startTime').val(startTime);
        	  $('#endTime').val(endTime);
        	  $('#startTime').attr('disabled', true);
        	  $('#endTime').attr('disabled', true);
       	  })
       	  
       	  $("#courseTime3").on("click", function(){
       		  $('#startTime').attr('disabled', false);
       		  $('#endTime').attr('disabled', false);
       		  $('#startTime').val("");
       		  $('#endTime').val("");
       	  })
       	  
       	  $('#free').on("click", function(){
       		  var free = $("input:radio[name=charged]:checked").val();
       		  $('#eduExpenses').val(free);
       		  $('#eduExpenses').attr('disabled', true);
       		  
       	  })
       	  
       	  $('#charged').on("click", function(){
       		  var charged = $("input:radio[name=charged]:checked").val();
       		  $('#eduExpenses').attr('disabled', false);
       		  $('#eduExpenses').focus();
       		  $('#eduExpenses').val('');
       		  $('#eduExpenses').attr('placeholder', '교육비를 입력해주세요.');
       	  })
       	  
       	  $('#courseRegister').submit(function(){
       		  if (confirm ('최종 수정하시겠습니까?') == true) {
       			  
	       		  var frm = document.crsFrm;
	       		  var id = frm.id.value;
	       		  var slbName = frm.slbName.value;
	       		  var rsDate = frm.rsDate.value;
	       		  var reDate = frm.reDate.value;
	       		  var startTime = frm.startTime.value;
	       		  var endTime = frm.endTime.value;
	       		  var capacity = frm.capacity.value;
	       		  var eduExpenses = frm.eduExpenses.value;
	       		  var classroom = frm.classroom.value;
	       		  var startDate = frm.startDate.value;
	       		  var endDate = frm.endDate.value;
				  var startHour = frm.startTime.value.split(':')[0];
				  var endHour = frm.endTime.value.split(':')[0];
				  
	       		  if (slbName == '') {
	       			  alert('강좌명을 선택해주세요.');
	       			  frm.slbName.focus();
	       			  return false;
	       		  } else if (startDate == '') {
	       			  alert('교육 시작일을 입력해주세요.');
	       			  frm.startDate.focus();
	       			  return false;
	       		  } else if (endDate == '') {
	       			  alert('교육 종료일을 입력해주세요.');
	       			  frm.endDate.focus();
	       			  return false;
	       		  } else if (startDate > endDate) {
	       			  alert('교육 기간 날짜 입력을 잘못하셨습니다.');
	       			  frm.startDate.focus();
	       			  return false;
	       		  } else if (rsDate == '') {
	       			  alert('수강신청 시작일을 입력해주세요.');
	       			  frm.rsDate.focus();
	       			  return false;
	       		  } else if (reDate == '') {
	       			  alert('수강신청 종료일을 입력해주세요.');
	       			  frm.reDate.focus();
	       			  return false;
	       		  } else if (rsDate > reDate) {
	       			  alert('수강신청 날짜를 잘못 입력하셨습니다.');
	       			  frm.rsDate.focus();
	       			  return false;
	       		  } else if (reDate > startDate) {
	       			  alert('수강신청 종료 날짜보다 교육 시작 날짜가 빠릅니다.');
	       			  frm.reDate.focus();
	       			  return false;
	       		  } else if (startHour > endHour) {
	       			  alert('시간 선택을 잘못 하셨습니다.');
	       			  frm.startTime.focus();
	       			  return false;
	       		  } else if (startTime == '') {
	       			  alert('강의 시간 시작 시간을 선택해주세요.');
	       			  frm.startTime.focus();
	       			  return false;
	       		  } else if (endTime == '') {
	       			  alert('강의 시간 종료 시간을 선택해주세요.');
	       			  frm.endTime.focus();
	       			  return false;
	       		  } else if (capacity == '') {
	       			  alert('수강 정원을 입력해주세요.');
	       			  frm.capacity.focus();
	       			  return false;
	       		  } else if (eduExpenses == '') {
	       			  alert('교육비를 입력해주세요.');
	       			  frm.eduExpenses.focus();
	       			  return false;
	       		  } else if (classroom == '') {
	       			  alert('강의실 정보를 입력해주세요.');
	       			  frm.classroom.focus();
	       			  return false;
	       		  } else if (startTime > endTime) {
	       			  alert('시작일이 종료일보다 커요!!');
	       			  frm.startTime.focus();
	       			  return false;
	       		  }
	       		  
	       		  
	       		var startTimeHour = startTime.split(':')[0];
	     		  var endTimeHour = endTime.split(':')[0];
	     		  
	     		  var data = {};
	     		  var countNum = 0;
	     		  data.id = id;
	     		  data.startDate = startDate;
	     		  data.endDate = endDate;
	     		  data.classroom = classroom;
	     		  data.startTime = startTimeHour;
	     		  data.endTime = endTimeHour;
	     		  console.log(data);
	     		  
	     		  $.ajax({
	     			type: 'post',
	     			url: '${contextPath}/course/checkClassRoomOfCourses.do',
	     			traditional : true,
	     			data: JSON.stringify(data),
	     			contentType : "application/json; charset=UTF-8",
	     			async : false,
	     			success : function(data) {
	     				countNum = data;
	     			},
	     			error : function(error) {
	     				alert(error+'오류가 발생했습니다. 새로 고침 후 다시 시도해주세요.');
	     			}
	     		  }); // ajax
	     		  
	     		  if (countNum == 0) {
	     			  
	     			$('#startTime').attr('disabled', false);
	     		  	$('#endTime').attr('disabled', false);
	     		  	$('#eduExpenses').attr('disabled', false);
	       			return true;
	       		
	     		  } else {
	     			$('#startTime').attr('disabled', true);
	     		  	$('#endTime').attr('disabled', true);
	     		  	$('#eduExpenses').attr('disabled', true);
	     		  	alert('선택하신 날짜에 배정된 강의실이 이미 존재하네요. 다시 선택해주세요.')
	     		  	return false;
	     		  }
     		  
       		  } else {
       			  return false;
       		  } //confirm
       		  
       	  })
       	  
       	  $(document).ready(function() {
       		 if ($("input:radio[id='courseTime3']").is(":checked") == true){
       			$('#startTime').attr('disabled', false);
       		  $('#endTime').attr('disabled', false);
       		 }
       	  });
       	  
      }); //function

      
    //회사 리스트 팝업창 뛰우기
  	function popup(){
  	    var url = "${contextPath}/syllabus/searchSyllabusByPopUp.do";
  	    var name = "popup test";
  	    var option = "width = 500, height = 500, top = 100, left = 200, location = no"
  	    window.open(url, name, option);
  	    
  	}
  </script>
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
			onclick="location.href='${contextPath}/course/listCourses.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'"
			style="cursor: pointer;"> 과정관리</span> > <span
			onclick="location.href='${contextPath}/course/updateCourseForm.do?id=${courseVO.id }&page=${page }&searchText=${searchText }&searchType=${searchType }&perPage=${perPage }'"
			style="cursor: pointer;"> 과정 수정</span>
		</h5>
	</div>
	
	<div style="width: 85%;">
		<h1  class="text_center">과정 수정</h1>
	</div>
	
	<form method="post"   action="${contextPath}/course/updateCourse.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}" name="crsFrm" id="courseRegister" >
		
		<table  align="center">
		   <tr>
		      <td width="10%"><p align="right">강좌명</td>
		      <td width="40%" style="text-align:left; padding-left: 20px;">
		      <input type="text" id="syllabusName" name="slbName" value="${courseVO.syllabusVO.name }" >
		      <input type="hidden" name="slbId" id="slbId" value="${courseVO.slbId }">
		      <input type="hidden" name="id" value=${courseVO.id }>
		      <!-- <a class="searchButton" onclick="popup()">검 색</a> -->
		      </td>
		      <td width="10%"><p align="right">수강 정원</td>
		      <td width="40%" style="text-align:left; padding-left: 20px;">
		      <input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" name="capacity" value="${courseVO.capacity }" >명
		      </td>
		   </tr>
		   
		    <tr>
		      <td>
		      <p align="right">수강신청일
		      </td>
		      <td style="padding-left: 20px;">
		      <p align="left">
		      <input type="text" name="rsDate" id="datepicker3" > - <input type="text" name="reDate" id="datepicker4" >
		      </td>
	   	      <td>
		      <p align="right">교육비
		      </td>
		      <td style="padding-left: 20px;">
		      <p align="left">
		      <input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" name="eduExpenses" id="eduExpenses" value="0" >원&nbsp;&nbsp;&nbsp;&nbsp;
		      <input type="radio" name="charged" id="free" value="0" checked="checked" >무료&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="charged" id="charged" >유료
		      </td>
		   </tr>
		    <tr>
		       <td>
		       <p align="right">교육기간
		       </td>
		      <td style="padding-left: 20px;">
		      <p align="left">
		      <input type="text" name="startDate" id="datepicker1" > - <input type="text" name="endDate" id="datepicker2" > </td>
		      <td>
		      <p align="right">강의실 정보</td>
		      <td style="padding-left: 20px;">
		      <p align="left">
		      <input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" name="classroom" value="${courseVO.classroom }" >호 
		      </td>
		    </tr>
		    <tr>
		       <td><p align="right" style="position : relative; transform : translateY(-17px);">강의 시간</td>
		       <td style="padding-left: 20px;"><p align="left" style="padding-bottom: 5px;">
			       <select name="startTime" id="startTime" disabled>
			       <option value="" >선택</option>
			       <option value="09:00" <c:if test="${courseVO.startTime eq '09:00' }">selected</c:if>> 09:00 </option>
			       <option value="10:00" <c:if test="${courseVO.startTime eq '10:00' }">selected</c:if>> 10:00 </option>
			       <option value="11:00" <c:if test="${courseVO.startTime eq '11:00' }">selected</c:if>> 11:00 </option>
			       <option value="12:00" <c:if test="${courseVO.startTime eq '12:00' }">selected</c:if>> 12:00 </option>
			       <option value="13:00" <c:if test="${courseVO.startTime eq '13:00' }">selected</c:if>> 13:00 </option>
			       <option value="14:00" <c:if test="${courseVO.startTime eq '14:00' }">selected</c:if>> 14:00 </option>
			       <option value="15:00" <c:if test="${courseVO.startTime eq '15:00' }">selected</c:if>> 15:00 </option>
			       <option value="16:00" <c:if test="${courseVO.startTime eq '16:00' }">selected</c:if>> 16:00 </option>
			       <option value="17:00" <c:if test="${courseVO.startTime eq '17:00' }">selected</c:if>> 17:00 </option>
			       <option value="18:00" <c:if test="${courseVO.startTime eq '18:00' }">selected</c:if>> 18:00 </option>
			       <option value="19:00" <c:if test="${courseVO.startTime eq '19:00' }">selected</c:if>> 19:00 </option>
			       <option value="20:00" <c:if test="${courseVO.startTime eq '20:00' }">selected</c:if>> 20:00 </option>
			       <option value="21:00" <c:if test="${courseVO.startTime eq '21:00' }">selected</c:if>> 21:00 </option>
			       <option value="22:00" <c:if test="${courseVO.startTime eq '22:00' }">selected</c:if>> 22:00 </option>
			       </select>&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;
			       <select  name="endTime"  id="endTime" disabled>
			       <option value="" >선택</option>
			       <option value="09:00" <c:if test="${courseVO.endTime eq '09:00' }">selected</c:if>> 09:00 </option>
			       <option value="10:00" <c:if test="${courseVO.endTime eq '10:00' }">selected</c:if>> 10:00 </option>
			       <option value="11:00" <c:if test="${courseVO.endTime eq '11:00' }">selected</c:if>> 11:00 </option>
			       <option value="12:00" <c:if test="${courseVO.endTime eq '12:00' }">selected</c:if>> 12:00 </option>
			       <option value="13:00" <c:if test="${courseVO.endTime eq '13:00' }">selected</c:if>> 13:00 </option>
			       <option value="14:00" <c:if test="${courseVO.endTime eq '14:00' }">selected</c:if>> 14:00 </option>
			       <option value="15:00" <c:if test="${courseVO.endTime eq '15:00' }">selected</c:if>> 15:00 </option>
			       <option value="16:00" <c:if test="${courseVO.endTime eq '16:00' }">selected</c:if>> 16:00 </option>
			       <option value="17:00" <c:if test="${courseVO.endTime eq '17:00' }">selected</c:if>> 17:00 </option>
			       <option value="18:00" <c:if test="${courseVO.endTime eq '18:00' }">selected</c:if>> 18:00 </option>
			       <option value="19:00" <c:if test="${courseVO.endTime eq '19:00' }">selected</c:if>> 19:00 </option>
			       <option value="20:00" <c:if test="${courseVO.endTime eq '20:00' }">selected</c:if>> 20:00 </option>
			       <option value="21:00" <c:if test="${courseVO.endTime eq '21:00' }">selected</c:if>> 21:00 </option>
			       <option value="22:00" <c:if test="${courseVO.endTime eq '22:00' }">selected</c:if>> 22:00 </option>
			       </select>
			       <p align="left" >
			       <c:choose>
			       <c:when test="${courseVO.startTime eq '09:00' }">
			       <input type="radio" name="courseTime" id="courseTime1" value="19:00~22:00">일반 (19:00 ~ 22:00)&nbsp;&nbsp;&nbsp;&nbsp;   
			       <input type="radio" name="courseTime" id="courseTime2" value="09:00~18:00" checked="checked">집체 (09:00 ~ 18:00)&nbsp;&nbsp;&nbsp;&nbsp;
			       <input type="radio" name="courseTime" id="courseTime3">기타 (직접 선택)
			       </c:when>
			       <c:when test="${courseVO.startTime eq '19:00' }">
			       <input type="radio" name="courseTime" id="courseTime1" value="19:00~22:00" checked="checked">일반 (19:00 ~ 22:00)&nbsp;&nbsp;&nbsp;&nbsp;   
			       <input type="radio" name="courseTime" id="courseTime2" value="09:00~18:00">집체 (09:00 ~ 18:00)&nbsp;&nbsp;&nbsp;&nbsp;
			       <input type="radio" name="courseTime" id="courseTime3">기타 (직접 선택)
			       </c:when>
			       <c:otherwise>
			       <input type="radio" name="courseTime" id="courseTime1" value="19:00~22:00">일반 (19:00 ~ 22:00)&nbsp;&nbsp;&nbsp;&nbsp;   
			       <input type="radio" name="courseTime" id="courseTime2" value="09:00~18:00">집체 (09:00 ~ 18:00)&nbsp;&nbsp;&nbsp;&nbsp;
			       <input type="radio" name="courseTime" id="courseTime3" checked="checked">기타 (직접 선택)
			       </c:otherwise>
			       </c:choose>
			       </td>
		       <td><p align="right">이미지 배너 </td>
		      <td rowspan="2"><div  id="imgViewArea" style="height: 140px; padding-top: 10px;"><img id="imgArea" src="/server/resources/image/${courseVO.syllabusVO.id}/${courseVO.syllabusVO.bannerImg}" width="120" height="120" style="position : relative; transform : translateX(-170px);"/></div></td>
		    </tr>
		    <tr>
		       <td>상태</td>
		       <td style="padding-left: 20px;"><p align="left">
		       <c:if test="${courseVO.stat eq '신청가능' }"><font color="blue"><b>${courseVO.stat}</b></font></c:if>
		       <c:if test="${courseVO.stat eq '조기마감' }"><font color="black"><b>${courseVO.stat}</b></font></c:if>
		       <c:if test="${courseVO.stat eq '마감' }"><font color="black"><b>${courseVO.stat}</b></font></c:if>
		       </td>
		       <td></td>
		    </tr>
		    <tr>
		       <td colspan="4"><input type="submit" value="수정" id="enrollButton"><input type="button" style="margin-left: 1em;" onclick="history.back()" value="취소"></td>
		    </tr>
		</table>
	</form>
	<%} else {  %>
	<script>
		window.location.href="${contextPath}";
	</script>
	<% } %>
</body>
</html>
