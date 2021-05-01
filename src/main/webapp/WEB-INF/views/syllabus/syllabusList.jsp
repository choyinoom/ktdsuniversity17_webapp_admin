<%@page import="com.ktdsuniversity.admin.manager.vo.ManagerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
	request.setCharacterEncoding("UTF-8");
	String searchType = request.getParameter("searchType");
	String searchText = request.getParameter("searchText");
%>    
<html>
<head>
<style>
</style>
<meta charset="UTF-8">
<title></title>
</head>
<style>
	.process {
		text-align: left;
		color: #9C9D9D;
		margin-bottom: 2em;
	}
	
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
	
	.com_search {
		padding-left: 7px;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>

	function getCheckListRemove() {
		var length = $("input:checkbox[name='selectedCheckbox']:checked").length;
		var arr = new Array();
		$("input:checkbox[name='selectedCheckbox']:checked").each(function(index) {
			arr.push($(this).attr('id'));
		})
		
		if(length == 0){
			alert("선택된 항목이 없습니다.");
			return false;
		} else{
			if (confirm('삭제하시겠습니까?') == true ){
				
				$.ajax({
					type: 'post',
					url: '${contextPath}/syllabus/removeCheckedSyllabuses.do',
					traditional : true, //Array 형태로 보내려면 설정 해줘야함
					data: {arr : arr},
					
					success: function(data) {
						window.location.href = "${contextPath}/syllabus/listSyllabuses.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}";
					}, error:function(data,request,status,error){
			             alert("등록된 과정부터 삭제해주시기 바랍니다.");
			        }
				}) //ajax
			
			} else{
				return false;
			} // confirm
		}
	}
	
	$(function(){
		$('#selectAll').click(function(){
			if ($("input:checkbox[id='selectAll']").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else{
				$("input[type=checkbox]").prop("checked", false);
			}
		})//selectAll
		
		$('#listFilter').on('change',
			function() {
				var perPage = $(this).val();
				var searchType = document
						.getElementById('searchType').value;
				var searchText = document
						.getElementById('searchText').value;
				location.href = "${contextPath}/syllabus/listSyllabuses.do?perPage="
						+ perPage
						+ "&searchType="
						+ searchType
						+ "&searchText="
						+ searchText;
			})//listFilter
		
	})
</script>
<body>
	<div class="process">
		<h5>
			<span onclick="location.href='${contextPath}/course/listCourses.do'"
			style="cursor: pointer;">과정관리</span> > <span
			onclick="location.href='${contextPath}/syllabus/listSyllabuses.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'"
			style="cursor: pointer;"> 강의계획서 관리</span>
		</h5>
	</div>
	
	<form method = "get" action="${contextPath }/syllabus/listSyllabuses.do"
		id="searchFrm">
		
		<div class="searchType">
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
				<c:if test="${searchType == 'nameOfLecture' }">
					<option value="nameOfLecture" selected>강의명</option>
					<option value="joinDate">등록일</option>
					<option value="category1">1차 분류</option>
					<option value="category2">2차 분류</option>
				</c:if>
				<c:if test="${searchType == 'joinDate' }">
					<option value="nameOfLecture">강의명</option>
					<option value="joinDate" selected>등록일</option>
					<option value="category1">1차 분류</option>
					<option value="category2">2차 분류</option>
				</c:if>
				<c:if test="${searchType == 'category1' }">
					<option value="nameOfLecture">강의명</option>
					<option value="joinDate">등록일</option>
					<option value="category1" selected>1차 분류</option>
					<option value="category2">2차 분류</option>
				</c:if>
				<c:if test="${searchType == 'category2' }">
					<option value="nameOfLecture">강의명</option>
					<option value="joinDate">등록일</option>
					<option value="category1">1차 분류</option>
					<option value="category2"  selected>2차 분류</option>
				</c:if>
				<c:if test="${empty searchType }">
					<option value="nameOfLecture" selected>강의명</option>
					<option value="joinDate">등록일</option>
					<option value="category1">1차 분류</option>
					<option value="category2">2차 분류</option>
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
		<button type="button" id="enrollButton" 
			onclick="location.href='${contextPath}/syllabus/syllabusForm.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'" style="width: 5%;">등록</button>
		<button type="button" onclick="getCheckListRemove()" style="width: 5%;">삭제</button>
	</div>
	
	<table >
		<tr align="center" id="attr">
		      <td width="2%"><input type="checkbox" id="selectAll"></td>
		      <td width="5%"><b>번호</b></td>
		      <td width="25%"><b>강의명</b></td>
		      <td width="10%"><b>1차 분류</b></td>
		      <td width="10%"><b>2차 분류</b></td>
		      <td width="5%"><b>교육일</b></td>
		      <td width="5%"><b>교육 시간</b></td>
		      <td width="13%"><b>등록일</b></td>
	      </tr>
		<c:choose>
			<c:when test="${not empty syllabusesList }">
				<c:forEach var="syllabus" items="${syllabusesList}" >     
					<tr>
						<td><input type="checkbox" name="selectedCheckbox" id="${syllabus.id }"></td>
						<td>${syllabus.id - 30000 + 1}</td>
						<td align="left"><a class='cls1' href="${contextPath}/syllabus/selectSyllabus.do?id=${syllabus.id}&page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${syllabus.name}</a></td>
						<td>${syllabus.type }</td>
						<td>${syllabus.courseCategoryVO.name }</td>
						<td>${syllabus.days }</td>
						<td>${syllabus.time }</td>
						<td>${syllabus.joinDate}</td>
					</tr>
				</c:forEach> 
			</c:when>
			<c:when test="${empty syllabusesList }">
				<tr>
					<td colspan="8">찾으시는 강의 계획서가 없습니다.</td>
				</tr>
			</c:when> 
		</c:choose> 
	</table>
	
	<div class="pageNumber" align="center" style="width: 80%; height: 10%;">
			<ul>
				<c:if test="${pageMaker.prev }">
					<c:choose>
						<c:when test="${not empty searchType and not empty searchText }">
							<li><a
								href="${contextPath}/syllabus/listSyllabuses.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${contextPath}/syllabus/listSyllabuses.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
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
								href="${contextPath }/syllabus/listSyllabuses.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
							</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach begin="${pageMaker.startPage }"
							end="${pageMaker.endPage }" var="idx">
							<li
								<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }"/>>
								<a
								href="${contextPath }/syllabus/listSyllabuses.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
							</li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
					<c:choose>
						<c:when test="${not empty searchType and not empty searchText }">
							<li><a
								href="${contextPath}/syllabus/listSyllabuses.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${contextPath}/syllabus/listSyllabuses.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>
			</ul>
		</div>
	
	<div class="memberButton">
		<button type="button" id="enrollButton" 
			onclick="location.href='${contextPath}/syllabus/syllabusForm.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'" style="width: 5%;">등록</button>
		<button type="button" onclick="getCheckListRemove()" style="width: 5%;">삭제</button>
	</div>
</body>
</html>
