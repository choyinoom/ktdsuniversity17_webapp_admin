<%@page import="com.ktdsuniversity.admin.manager.vo.ManagerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
	String searchType = request.getParameter("searchType");
	String searchText = request.getParameter("searchText");
%>


<html>
<head>
<meta charset="UTF-8">
<title></title>
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
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	//체크 된 걸 가져오는 함수
	function getCheckList() {
		var length = $("input:checkbox[name='selectedCheckbox']:checked").length;
		var arr = new Array();
		$("input:checkbox[name='selectedCheckbox']:checked").each(function(index) {
			/* alert($(this).attr('id')); */
			arr.push($(this).attr('id'));
		})

		if (length == 0) {
			alert("선택된 값이 없습니다.");
			return false;
		} else {
			if (confirm('회원 탈퇴하시겠습니까?')) {
				
				$.ajax({
					type : 'post',
					url : '${contextPath}/member/removeCheckedMembers.do',
					traditional : true, //Array 형태로 보내려면 설정 해줘야함
					data : {
						arr : arr
					},
				
					success : function(data) {
						alert('회원 탈퇴 되었습니다.');
						window.location.href = "${contextPath}/member/listMembers.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}";
					},
					error : function(data, request, status, error) {
						alert("잠시 후 다시 시도해주세요.");
				
					}
				})// ajax
			} else{
				return false;
			}
		}
	}

	// 전체 체크되게 하는 함수
	$(
			function() {
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
									location.href = "${contextPath}/member/listMembers.do?perPage="
											+ perPage
											+ "&searchType="
											+ searchType
											+ "&searchText="
											+ searchText;
								})

			}) //function
</script>

<body>
	<div class='process' >
		<h5>
			<span onclick="location.href='${contextPath}/member/listMembers.do'"
			style="cursor: pointer;">회원관리</span> > <span
			onclick="location.href='${contextPath}/member/listMembers.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'"
			style="cursor: pointer;"> 학생관리</span>
		</h5>
	</div>
	
	<!-- controller에서 보낸 값 받아서 저장 -->
	<form method="get" action="${contextPath}/member/listMembers.do"
		id="searchFrm">

		<!-- 리시트 필터 값 적용 -->
		<div class="searchType">
			<select name="perPage" id="listFilter">
				<c:if test="${perPage == '20' }">
					<option value='10'>10개</option>
					<option value='20' selected>20개</option>
					<option value='50'>50</option>

				</c:if>
				<c:if test="${perPage == '50' }">
					<option value='10'>10개</option>
					<option value='20'>20개</option>
					<option value='50' selected>50개</option>
				</c:if>
				<c:if test="${perPage == '10' }">
					<option value='10' selected>10개</option>
					<option value='20'>20개</option>
					<option value='50'>50개</option>
				</c:if>
			</select>

		<!-- 검색 유형 값에 따라 셀렉트 띄우는 값 설정 -->
			<select name="searchType" id="searchType">
				<c:if test="${searchType == 'name'}">
					<option value="name" selected>이름</option>
					<option value="companyName">회사명</option>
					<option value="id">아이디</option>
				</c:if>
				<c:if test="${searchType == 'companyName' }">
					<option value="name">이름</option>
					<option value="companyName" selected>회사명</option>
					<option value="id">아이디</option>
				</c:if>
				<c:if test="${searchType == 'id' }">
					<option value="name">이름</option>
					<option value="companyName">회사명</option>
					<option value="id" selected>아이디</option>
				</c:if>
				<c:if test="${empty searchType }">
					<option value="name" selected>이름</option>
					<option value="companyName">회사명</option>
					<option value="id">아이디</option>
				</c:if>
			</select>

			<!-- 검색 값이 있냐 없냐에 따라 값 뛰우는거 설정 -->
			<c:choose>
				<c:when test="${not empty searchText }">
					<input type="text" name="searchText" id="searchText"
						value="${searchText }">
				</c:when>
				<c:otherwise>
					<input type="text" name="searchText" id="searchText"
					class="com_search" placeholder="검색어를 입력하세요."
					onfocus="this.placholder=''"
					onblur="this.placeholder='검색어를 입력하세요.'">
				</c:otherwise>
			</c:choose>
			<input type="submit" id="searchSubmit" value="검색">
		</div>
	</form>
	
	<div class="memberButton">
		<button type="button" id="enrollButton"
			onclick="location.href='${contextPath}/member/memberForm.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'">등록</button>
		<button type="button" onclick='getCheckList()'>탈퇴</button>
	</div>

	<table>
		<tr id="attr">
			<td width="5%"><input type="checkbox" id="selectAll"></td>
			<td width="10%"><b>아이디</b></td>
			<td width="10%"><b>이름</b></td>
			<td width="15%"><b>회사명</b></td>
			<td width="15"><b>연락처</b></td>
			<td width="20%"><b>이메일</b></td>
			<td width="10%"><b>수신동의</b></td>
			<td width="15%"><b>가입일</b></td>
		</tr>
		<c:choose>
			<c:when test="${not empty membersList}">
				<c:forEach var="member" items="${membersList}" varStatus="memberNum">
					<tr align="center">
						<td><input type="checkbox" name="selectedCheckbox"
							id="${member.id }"></td>
						<td align="left" style="padding-left: 2%;"><a class='cls1'
							href="${contextPath}/member/informationMemberForm.do?id=${member.id }&page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${fn:substring(member.id, 0, fn:length(member.id) - 3)}***</a></td>
						<td>${fn:substring(member.name, 0, fn:length(member.name) - 1)}*</td>
						<td align="left" style="padding-left: 5%">${member.companyName}</td>
	       				<td>
	       					<c:set var="memberPhone" value="${fn:split(member.phone, '-')}"/>
	       					<c:forEach var="phoneNumber" items="${memberPhone }" varStatus="phone">
	       						<c:if test="${phone.count == 1 }">${fn:substring(phoneNumber, 0, 2)}*</c:if>
	       						<c:if test="${phone.count == 2 }">- ${fn:substring(phoneNumber, 0, 2)}**</c:if>
	       						<c:if test="${phone.count == 3 }">- ${fn:substring(phoneNumber, 0, 2)}**</c:if>
	       					</c:forEach>
	       				</td>
	       				<td align="left" style="padding-left: 6%;"><c:set var="email" value="${fn:split(member.email, '@')}"/>
	       				<c:forEach var="eamil" items="${email }" varStatus="a">
				       		<c:if test="${a.count == 1 }">${fn:replace(eamil, fn:substring(eamil, 0, 3), '***')}</c:if>
				       		<c:if test="${a.count == 2 }">@${eamil }</c:if>
				       </c:forEach></td>
				       	<td>
							<c:choose>
								<c:when test="${member.subscription == 'T' }">동의</c:when>
								<c:otherwise>미동의</c:otherwise>
							</c:choose>
						</td>
						<td>${fn:substring(member.joinDate, 0, 10)}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${empty membersList}">
				<tr>
					<td colspan="8">찾으시는 데이터가 없습니다.</td>
				</tr>
			</c:when>
		</c:choose>
	</table>

	<!-- 전체 페이지개수에 의한 페이지 리스트 띄우기 -->
	<div class="pageNumber" align="center">
		<ul>
			<c:if test="${pageMaker.prev }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/member/listMembers.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/member/listMembers.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
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
							href="${contextPath }/member/listMembers.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="idx">
						<li
							<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }"/>>
							<a
							href="${contextPath }/member/listMembers.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/member/listMembers.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/member/listMembers.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:otherwise>
				</c:choose>
			</c:if>
		</ul>
	</div>

	<div class="memberButton" style="position: relative; top: -1em; margin-bottom: 2em;">
		<button type="button" id="enrollButton"
			onclick="location.href='${contextPath}/member/memberForm.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'">등록</button>
		<button type="button" onclick='getCheckList()'>탈퇴</button>
	</div>
</body>
</html>
