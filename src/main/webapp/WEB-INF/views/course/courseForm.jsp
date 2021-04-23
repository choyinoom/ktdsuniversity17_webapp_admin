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
<title>Insert title here</title>
</head>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
	  $(function() {
          //모든 datepicker에 대한 공통 옵션 설정
          $.datepicker.setDefaults({
              dateFormat: 'yy-mm-dd' //Input Display Format 변경
              ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
              ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
              ,changeYear: true //콤보박스에서 년 선택 가능
              ,changeMonth: true //콤보박스에서 월 선택 가능                
              ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
              ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
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
          
          //시작 날짜 초기값을 오늘 날짜로 설정
          $('#datepicker1').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
          //종료 날짜 초기값을 내일로 설정
          $('#datepicker2').datepicker('setDate', '+1D'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
      }); 

  </script>
<body>

<form method="post"   action="${contextPath}/course/addCourse.do" id="courseRegister">
	<h1  class="text_center">과정 등록</h1>
	<table  align="center">
	   <tr>
	      <td width="200"><p align="right">과정명</td>
	      <td width="400">
	      <select name="slbId">
	      		<option value="" selected></option>
	      		
	      		<c:forEach var="syllabus" items="${syllabusesList}" >
	      			<option  value="${syllabus.id }">${syllabus.name }</option>
	      		</c:forEach>
	      </select>
	      </td>
	   </tr>
	   
	    <tr>
	      <td width="200"><p align="right">교육 기간</td>
	      <td width="400"><input type="text" name="startDate" id="datepicker1" ></td>
	   </tr>
	    <tr>
	       <td width="200"><p align="right"></td>
	       <td width="400"><p><input type="text" name="endDate" id="datepicker2" ></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">강의 시간</td>
	       <td width="400"><p><input type="time" name="startTime" ></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right"> 시간</td>
	       <td width="400"><p><input type="time" name="endTime" ></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">수용 인원</td>
	       <td width="400"><p>
	       <select name="capacity">
	       		<option value="">인원 선택</option>
	       		<option value="18">18</option>
	       		<option value="20">20</option>
	       		<option value="30">30</option>
	       		<option value="35">35</option>
	       </select></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">강의실 정보</td>
	       <td width="400"><p>
	       <select name="classroom">
	       		<option value="">강의실 선택</option>
		        <option value="201호">201호</option>
		        <option value="202호">202호</option>
		        <option value="203호">203호</option>
		        <option value="204호">204호</option>
		        <option value="205호">205호</option>
		        <option value="206호">206호</option>
		        <option value="207호">207호</option>
		        <option value="207호">208호</option>
		        <option value="207호">209호</option>	 
	       </select></td>
	    </tr>
	    <tr>
	       <td width="200"><p>&nbsp;</p></td>
	       <td width="400"><input type="submit" value="등록하기"><input type="button" onclick="history.back()" value="취소"></td>
	    </tr>
	</table>
	</form>
</body>
</html>