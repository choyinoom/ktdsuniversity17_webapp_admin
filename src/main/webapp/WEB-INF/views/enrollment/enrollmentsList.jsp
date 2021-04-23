<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<style>
	.cls1 {
		text-decoration: none;
	}
	
	.cls2 {
		text-align: center;
		font-size: 30px;
	}
	
	a:link {
		text-decoration: none;
		color: black;
	}
	
	a:visited {
		text-decoration: none;
		color: black;
	}
	
	#herfId:hover {
		color: #c2c2c2;
	}
</style>
<meta charset="UTF-8">
<title>글목록창</title>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	// 전체 체크되게 하는 함수
	$(function() {
		$('#selectAll').click(function() {
			if ($("input:checkbox[id='selectAll']").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}
		})

		$('#listFilter')
				.on(
						'change',
						function() {
							var perPage = $(this).val();
							var searchType = document
									.getElementById('searchType').value;
							var searchText = document
									.getElementById('searchText').value;
							/* alert(perPage+"씩 리스트 출력");
							alert(searchType);
							alert(searchText); */
							location.href = "${contextPath}/enrollment/listEnrollments.do?perPage="
									+ perPage
									+ "&searchType="
									+ searchType
									+ "&searchText=" + searchText;
						})
	})

	//체크 된 걸 가져오는 함수
	function getCheckList(stat) {
		var length = $("input:checkbox[name='selectedCheckbox']:checked").length;
		//alert(length);
		var arr = new Array();
		$("input:checkbox[name='selectedCheckbox']:checked").each(
				function(index) {
					/* alert($(this).attr('id')); */
					arr.push($(this).attr('id'));
					//alert($(this).attr('id'));	
				})

		if (length == 0) {
			alert("선택된 값이 없습니다.");
			return false;
		} else {
			if (stat == "delete") { // 상태 '삭제' 로 변경
				$
						.ajax({
							type : 'post',
							url : '${contextPath}/enrollment/modDeleteEnrollments.do',
							traditional : true, //Array 형태로 보내려면 설정 해줘야함
							data : {
								arr : arr
							},

							success : function(data) {
								//alert('데이터 받기 성공');
								//alert(data);
								window.location.href = "${contextPath}/enrollment/listEnrollments.do";
							},
							error : function(data, request, status, error) {
								alert("code:" + request.status + "\n"
										+ "message:" + request.responseText
										+ "\n" + "error:" + error);
							}
						})
			} else if (stat == "approve") { // 상태 '승인' 으로 변경
				$
						.ajax({
							type : 'post',
							url : '${contextPath}/enrollment/modApproveEnrollments.do',
							traditional : true,
							data : {
								arr : arr
							},

							success : function(data) {
								window.location.href = "${contextPath}/enrollment/listEnrollments.do";
							},
							error : function(data, request, status, error) {
								alert("code:" + request.status + "\n"
										+ "message:" + request.responseText
										+ "\n" + "error:" + error);
							}
						})
			} else if (stat == "complete") { // 상태 '수료' 로 변경
				$
						.ajax({
							type : 'post',
							url : '${contextPath}/enrollment/modCompleteEnrollments.do',
							traditional : true,
							data : {
								arr : arr
							},

							success : function(data) {
								window.location.href = "${contextPath}/enrollment/listEnrollments.do";
							},
							error : function(data, request, status, error) {
								alert("code:" + request.status + "\n"
										+ "message:" + request.responseText
										+ "\n" + "error:" + error);
							}
						})
			}
		}
	}

	$(document).on("click", '#selectAll', function() {
		$('#selectAll').click(function() {
			if ($("input:checkbox[id='selectAll']").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}
		})
	});
</script>
<body>
	<!-- controller에서 보낸 값 받아서 저장 -->
	<%
	String searchType = request.getParameter("searchType");
	String searchText = request.getParameter("searchType");
	%>

	<h4 align="left"> 수강신청내역</h4>
	<form method="get"
		action="${contextPath}/enrollment/listEnrollments.do" id="searchFrm">

		<!-- 리시트 필터 값 적용 -->
		<div class="listFilter">
			<select name="perPage" id="listFilter">
				<c:if test="${perPage == '10' }">
					<option value='10' selected>10</option>
					<option value='20'>20</option>
					<option value='50'>50</option>
					<option value='100'>100</option>
				</c:if>
				<c:if test="${perPage == '20' }">
					<option value='10'>10</option>
					<option value='20' selected>20</option>
					<option value='50'>50</option>
					<option value='100'>100</option>
				</c:if>
				<c:if test="${perPage == '50' }">
					<option value='10'>10</option>
					<option value='20'>20</option>
					<option value='50' selected>50</option>
					<option value='100'>100</option>
				</c:if>
				<c:if test="${perPage == '100' }">
					<option value='10'>10</option>
					<option value='20'>20</option>
					<option value='50'>50</option>
					<option value='100' selected>100</option>
				</c:if>
			</select>
		</div>

		<!-- 검색 유형 값에 따라 셀렉트 띄우는 값 설정 -->
		<div class="searchType">
			<select name="searchType" id="searchType">
				<c:if test="${searchType == 'name' }">
					<option value="">검색 종류</option>
					<option value="name" selected>이름</option>
					<option value="companyName">회사명</option>
					<option value="slbName">과정명</option>
					<option value="stat">상태</option>
				</c:if>
				<c:if test="${searchType == 'companyName' }">
					<option value="">검색 종류</option>
					<option value="name">이름</option>
					<option value="companyName" selected>회사명</option>
					<option value="slbName">과정명</option>
					<option value="stat">상태</option>
				</c:if>
				<c:if test="${searchType == 'slbName' }">
					<option value="">검색 종류</option>
					<option value="name">이름</option>
					<option value="companyName">회사명</option>
					<option value="slbName" selected>과정명</option>
					<option value="stat">상태</option>
				</c:if>
				<c:if test="${searchType == 'stat' }">
					<option value="">검색 종류</option>
					<option value="name">이름</option>
					<option value="companyName">회사명</option>
					<option value="slbName">과정명</option>
					<option value="stat" selected>상태</option>
				</c:if>
				<c:if test="${empty searchType }">
					<option value="" selected>검색 종류</option>
					<option value="name">이름</option>
					<option value="companyName">회사명</option>
					<option value="slbName">과정명</option>
					<option value="stat">상태</option>
				</c:if>
			</select>

			<!-- 검색 값이 있냐 없냐에 따라 값 뛰우는거 설정 -->
			<c:choose>
				<c:when test="${not empty searchText }">
					<input type="text" name="searchText" id="searchText"
						value="${searchText }">
				</c:when>
				<c:otherwise>
					<input type="text" name="searchText" id="searchText">
				</c:otherwise>
			</c:choose>
			<input type="submit" id="searchSubmit" value="검색">
		</div>

	</form>

	<table align="center" border="0" width="80%" id="dynamicCompany">
		<tr height="15" align="center" id="attr">
			<td><input type="checkbox" id="selectAll"></td>
			<td><b>아이디</b></td>
			<td><b>이름</b></td>
			<td><b>소속회사</b></td>
			<td><b>과정명</b></td>
			<td><b>승인</b></td>
			<td><b>신청일</b></td>
		</tr>
		<c:choose>
			<c:when test="${enrollmentsList == null }">
				<tr height="10">
					<td colspan="4">
						<p align="center">
							<b><span style="font-size: 9pt;">등록된 회사가 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${enrollmentsList !=null }">
				<c:forEach var="enrollment" items="${enrollmentsList }"
					varStatus="enrdNum">
					<tr align="center">
						<td><input type="checkbox" name="selectedCheckbox"
							id="${enrollment.id }"></td>
						<td>${enrollment.memId }</td>
						<td align='center'><span style="padding-right: 10px"></span>
							<a id="herfId"
							href="${contextPath}/enrollment/informationEnrollment.do?id=${enrollment.id }">${enrollment.memberVO.name }</a>
						</td>
						<td>${enrollment.memberVO.companyName }</td>
						<td>${enrollment.syllabusVO.name }</td>
						<td><c:if test="${enrollment.stat == '신청' }">
								<font color="red">${enrollment.stat }</font>
							</c:if> <c:if test="${enrollment.stat == '승인' }">
								<font color="black">${enrollment.stat }</font>
							</c:if> <c:if test="${enrollment.stat eq '수료' }">
								<font color="black">${enrollment.stat }</font>
							</c:if></td>
						<td>${enrollment.joinDate }</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>

	<!-- 전체 페이지개수에 의한 페이지 리스트 띄우기 -->
	<div class="pageNumber" align="center" style="width: 80%; height: 10%;">
		<ul>
			<c:if test="${pageMaker.prev }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/enrollment/listEnrollments.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/enrollment/listEnrollments.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:choose>
				<c:when test="${not empty searchType and not empty searchText }">
					<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="idx">
						<li
							<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }" />>
							<a
							href="${contextPath }/enrollment/listEnrollments.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="idx">
						<li
							<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }"/>>
							<a
							href="${contextPath }/enrollment/listEnrollments.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/enrollment/listEnrollments.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/enrollment/listEnrollments.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:otherwise>
				</c:choose>
			</c:if>
		</ul>
	</div>

	<%-- <button type="button" onclick="location.href='${contextPath}/enrollment/enrollmentForm.do'" style="width: 5%;">등록</button>
<button type="button" style="width: 5%;">승인</button>
<button type="button" onclick='getCheckList()' style="width: 5%;">삭제</button>
<button type="button" style="width: 5%;">수료</button> --%>

	<!-- 버튼 모음집 -->
	<div class="memberButton">
		<button type="button" id="enrollButton"
			onclick="location.href='${contextPath}/enrollment/enrollmentForm.do'"
			style="width: 5%;">등록</button>
		<button type="button" onclick='getCheckList("approve")'
			style="width: 5%;">승인</button>
		<button type="button" onclick='getCheckList("complete")'
			style="width: 5%;">수료</button>
		<button type="button" onclick='getCheckList("delete")'
			style="width: 5%;">삭제</button>
	</div>
	<!-- 등록 버튼 추가해서 함수 실행하게 만들어야함 -->
	<%-- 
      <td><a href="${contextPath}/member/removeMember.do?id=${member.id }">삭제</a></td> --%>

</body>
</html>