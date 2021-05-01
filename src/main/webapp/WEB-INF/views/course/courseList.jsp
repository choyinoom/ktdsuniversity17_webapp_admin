<%@page import="com.ktdsuniversity.admin.manager.vo.ManagerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
	request.setCharacterEncoding("UTF-8");
	String searchType = request.getParameter("searchType");
	String searchText = request.getParameter("searchText");
%>  
<!DOCTYPE html>
<html>
<head>
<style>
</style>
  <meta charset="UTF-8">
  <title>글목록창</title>
</head>
<style>
	.cls1:link {
		text-decoration: none;
		color: black;
	}
	
	.cls1:visited {
		text-decoration: none;
		color: black;
	}
	
	.cls1:hover {
		color: #c2c2c2;
	}
	
	.process {
		text-align: left;
		color: #9C9D9D;
		margin-bottom: 2em;
	}
	
	.com_search {
		padding-left: 7px;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>

	function getCheckList(type) {
		
			var stat = type;
			
			var length = $("input:checkbox[name='selectedCheckbox']:checked").length;
			var arr = new Array();
			$("input:checkbox[name='selectedCheckbox']:checked").each(function(index) {
				arr.push($(this).attr('id'));
			})
			
			if(length == 0){
				alert("선택된 값이 없습니다.");
				return false;
			} 
			
			if (stat == 'remove') {
				if (confirm('최종 삭제하시겠습니까?') == true ){
				$.ajax({
					type: 'post',
					url: '${contextPath}/course/removeCheckedCourses.do',
					traditional : true, 
					data: {arr : arr},
					
					success: function(data) {
						alert('삭제되었습니다.');
						window.location.href = "${contextPath}/course/listCourses.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}";
					}, error:function(data,request,status,error){
			             alert("수강신청한 회원부터 취소해주세요."); 
			             
			        }
				})
			} else {
				return false;
					}
			} else{
				if (confirm('최종 변경하시겠습니까?') == true ){
				$.ajax({
					type: 'post',
					url: '${contextPath}/course/updateCheckedCourses.do',
					traditional : true, 
					data: {arr : arr, stat : stat},
					
					success: function(data) {
						alert('변경되었습니다.');
						window.location.href = "${contextPath}/course/listCourses.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}";
					}, error:function(data,request,status,error){
			             alert("변경 안됌");
			             
			        }
				})
			} else {
				return false;
			}
		}
		
	} // getCheckListRemove()

	$(function(){
		$('#selectAll').click(function(){
			if ($("input:checkbox[id='selectAll']").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else{
				$("input[type=checkbox]").prop("checked", false);
			}
		}) // 체크 박스 전체 선택	 // selectAll.click
		
		$('#listFilter').on('change',
			function() {
				var perPage = $(this).val();
				var searchType = document
						.getElementById('searchType').value;
				var searchText = document
						.getElementById('searchText').value;
				location.href = "${contextPath}/course/listCourses.do?perPage="
						+ perPage
						+ "&searchType="
						+ searchType
						+ "&searchText="
						+ searchText;
			})//listFilter
	})
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
			onclick="location.href='${contextPath}/course/listCourses.do??page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'"
			style="cursor: pointer;"> 과정관리</span>
		</h5>
	</div>
	
	<form method = "get" action="${contextPath }/course/listCourses.do"
		id="searchFrm">
		
		<div class="searchType" style="float: right;">
			<select name="perPage" id="listFilter">
				<c:if test="${perPage == '10' || empty perPage }">
					<option value='10' selected>10</option>
					<option value='20'>20</option>
					<option value='50'>50</option>
				</c:if>
				
				<c:if test="${perPage == '20' }">
					<option value='10'>10</option>
					<option value='20' selected>20</option>
					<option value='50'>50</option>
				</c:if>
				
				<c:if test="${perPage == '50' }">
					<option value='10'>10</option>
					<option value='20'>20</option>
					<option value='50' selected>50</option>
				</c:if>
			</select>
		
		
			<select name="searchType" id="searchType">
				<c:if test="${searchType == 'nameOfCourse' }">
					<option value="nameOfCourse" selected>과정명</option>
					<option value="status">접수 상태</option>
					<option value="classRoom">강의실</option>
					<option value="startDate">교육시작일</option>
					<option value="endDate">교육종료일</option>
				</c:if>
				<c:if test="${searchType == 'status' }">
					<option value="nameOfCourse">과정명</option>
					<option value="status" selected>접수 상태</option>
					<option value="classRoom">강의실</option>
					<option value="startDate">교육시작일</option>
					<option value="endDate">교육종료일</option>
				</c:if>
				<c:if test="${searchType == 'classRoom' }">
					<option value="nameOfCourse">과정명</option>
					<option value="status">접수 상태</option>
					<option value="classRoom" selected>강의실</option>
					<option value="startDate">교육시작일</option>
					<option value="endDate">교육종료일</option>
				</c:if>
				<c:if test="${searchType == 'startDate' }">
					<option value="nameOfCourse">과정명</option>
					<option value="status">접수 상태</option>
					<option value="classRoom">강의실</option>
					<option value="startDate" selected>교육시작일</option>
					<option value="endDate">교육종료일</option>
				</c:if>
				<c:if test="${searchType == 'endDate' }">
					<option value="nameOfCourse">과정명</option>
					<option value="status">접수 상태</option>
					<option value="classRoom">강의실</option>
					<option value="startDate">교육시작일</option>
					<option value="endDate" selected>교육종료일</option>
				</c:if>
				<c:if test="${empty searchType }">
					<option value="nameOfCourse" selected>과정명</option>
					<option value="status">접수 상태</option>
					<option value="classRoom">강의실</option>
					<option value="startDate">교육시작일</option>
					<option value="endDate">교육종료일</option>
				</c:if>
			</select>
			
			<c:choose>
				<c:when test="${not empty searchText }">
					<input type="text" name="searchText" id="searchText"
						value="${searchText }">
				</c:when>
				<c:otherwise>
					<input type="text" name="searchText" id="searchText"
						class="com_search" placeholder="검색어를 입력하세요."
						onfocus="this.placeholder=''"
						onblur="this.placeholder='검색어를 입력하세요.'">
				</c:otherwise>
			</c:choose>
			<input type="submit" id="searchSubmit" value="검색">
		</div>
	</form>
	<div class="memberButton">
			<button type="button" onclick="getCheckList('applicable')" style="width: 5%;">신청가능</button>
			<button type="button" onclick="getCheckList('earlyClosing')" style="width: 5%;">조기마감</button>
			<button type="button" onclick="getCheckList('deadline')" style="width: 5%;">마감</button>
			<button type="button" id="enrollButton" onclick="location.href='${contextPath}/course/courseForm.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'" style="width: 5%;">등록</button>
			<button type="button" onclick="getCheckList('remove')" style="width: 5%;">삭제</button>
	</div>
	
	<table align="center" >
		<tr id="attr">
		    <td><input type="checkbox" id="selectAll"></td>
		    <td><b>번호</b></td>
		    <td><b>과정명</b></td>
		    <td ><b>수강 기간</b></td>              
		    <td ><b>신청 인원</b></td>
		    <td><b>강의실</b></td>
		    <td><b>접수 상태</b></td>
		    <td><b>등록일</b></td>
		</tr>
		<c:choose>
			<c:when test="${empty coursesList }" >
				<tr>
					<td colspan="8">찾으시는 등록된 과정이 없습니다.</td>
				</tr>
			</c:when>
			<c:when test="${not empty coursesList}" >
				
				<c:forEach  var="course" items="${coursesList }" varStatus="articleNum" >
					<tr align="center" >
						<td><input type="checkbox" name="selectedCheckbox" id="${course.id }"></td>
						<td width="5%">${course.id - 20000 + 1}</td>
						<td align='left'  width="20%">
						<span style="padding-right:10px"></span>${course.syllabusVO.type } > ${course.courseCategoryVO.name}<p>
						<a class='cls1' href="${contextPath}/course/selectCourse.do?id=${course.id}&page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${course.syllabusVO.name }</a>
						</td>
						<td>${course.startDate } ~ ${course.endDate }</td>
						<td><c:if test="${course.applyNum > course.capacity}"><font color="red"><b>${course.applyNum } / ${course.capacity }</b></font></c:if>
							<c:if test="${course.applyNum <= course.capacity}"><font color="black"><b>${course.applyNum } / ${course.capacity }</b></font></c:if>
						</td>
						<td>${course.classroom }호</td>
						<td><c:if test="${course.stat eq '신청가능'}"><font color="black"><b>${course.stat }</b></font></c:if>
							<c:if test="${course.stat eq '조기마감'}"><font color="red"><b>${course.stat }</b></font></c:if>
							<c:if test="${course.stat eq '마감' }"><font color="red"><b>${course.stat }</b></font></c:if>
						</td>
						<td>${course.joinDate }</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<div class="under memberButton" style="margin-bottom: 3em;">
		<button type="button" onclick="getCheckList('applicable')" style="width: 5%;">신청가능</button>
		<button type="button" onclick="getCheckList('earlyClosing')" style="width: 5%;">조기마감</button>
		<button type="button" onclick="getCheckList('deadline')" style="width: 5%;">마감</button>
		<button type="button" id="enrollButton" onclick="location.href='${contextPath}/course/courseForm.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'" style="width: 5%;">등록</button>
		<button type="button" onclick="getCheckList('remove')" style="width: 5%;">삭제</button>
	 </div>
	<div class="pageNumber" align="center">
		<ul>
			<c:if test="${pageMaker.prev }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/course/listCourses.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/course/listCourses.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:choose>
				<c:when test="${not empty searchType and not empty searchText }">
					<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="idx">
						<li
							<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }"/>>
							<a
							href="${contextPath }/course/listCourses.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="idx">
						<li
							<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }"/>>
							<a
							href="${contextPath }/course/listCourses.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/course/listCourses.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/course/listCourses.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:otherwise>
				</c:choose>
			</c:if>
		</ul>
	</div>
	 <%} else {  %>
	<script>
		window.location.href="${contextPath}";
	</script>
	<% } %>
</body>
</html>
