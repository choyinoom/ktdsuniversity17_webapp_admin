<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("UTF-8");
%>


<html>
<head>
<meta charset=UTF-8">
<title>회원 정보 목록창</title>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	//체크 된 걸 가져오는 함수
	function getCheckList() {
		var length = $("input:checkbox[name='selectedCheckbox']:checked").length;
		alert(length);
		var arr = new Array();
		$("input:checkbox[type=checkbox]:checked").each(function(index) {
			/* alert($(this).attr('id')); */
			arr.push($(this).attr('id'));
		})

		if (length == 0) {
			alert("선택된 값이 없습니다.");
			return false;
		} else {
			$
					.ajax({
						type : 'post',
						url : '${contextPath}/member/removeCheckedMembers.do',
						traditional : true, //Array 형태로 보내려면 설정 해줘야함
						data : {
							arr : arr
						},

						success : function(data) {
							alert('데이터 받기 성공');
							alert(data);
							window.location.href = "${contextPath}/member/listMembers.do";
						},
						error : function(data, request, status, error) {
							alert("code:" + request.status + "\n" + "message:"
									+ request.responseText + "\n" + "error:"
									+ error);

						}
					})
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

	<!-- controller에서 보낸 값 받아서 저장 -->
	<%
	String searchType = request.getParameter("searchType");
	String searchText = request.getParameter("searchText");
	%>
	<form method="get" action="${contextPath}/member/listMembers.do"
		id="searchFrm">

		<!-- 리시트 필터 값 적용 -->
		<div class="listFilter">
			<select name="perPage" id="listFilter">
				<c:if test="${perPage == '20' }">
					<option value='10'>10</option>
					<option value='20' selected>20</option>
					<option value='30'>30</option>

				</c:if>
				<c:if test="${perPage == '30' }">
					<option value='10'>10</option>
					<option value='20'>20</option>
					<option value='30' selected>30</option>
				</c:if>
				<c:if test="${perPage == '10' }">
					<option value='10' selected>10</option>
					<option value='20'>20</option>
					<option value='30'>30</option>
				</c:if>
			</select>
		</div>

		<!-- 검색 유형 값에 따라 셀렉트 띄우는 값 설정 -->
		<div class="searchType">
			<select name="searchType" id="searchType">
				<c:if test="${searchType == 'name'}">
					<option value="">검색 종류</option>
					<option value="name" selected>이름</option>
					<option value="companyName">회사명</option>
				</c:if>
				<c:if test="${searchType == 'companyName' }">
					<option value="">검색 종류</option>
					<option value="name">이름</option>
					<option value="companyName" selected>회사명</option>
				</c:if>
				<c:if test="${empty searchType }">
					<option value="" selected>검색 종류</option>
					<option value="name">이름</option>
					<option value="companyName">회사명</option>
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



	<table border="0" align="center">
		<tr align="center" id="attr">
			<td><input type="checkbox" id="selectAll"></td>
			<td><b>아이디</b></td>
			<td><b>회사명</b></td>
			<td><b>이름</b></td>
			<td><b>이메일</b></td>
			<td><b>가입일</b></td>
		</tr>
		<c:choose>
			<c:when test="${not empty membersList}">
				<c:forEach var="member" items="${membersList}" varStatus="memberNum">
					<tr align="center">
						<td><input type="checkbox" name="selectedCheckbox"
							id="${member.id }"></td>
						<td><a
							href="${contextPath}/member/informationMemberForm.do?id=${member.id }">${member.id}</a></td>
						<td>${member.companyName}</td>
						<td>${member.name}</td>
						<td>${member.email}</td>
						<td>${member.joinDate}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${empty membersList}">
				<c:forEach var="member" items="${membersList}" varStatus="memberNum">
					<h1>데이터가 없습니다.</h1>
				</c:forEach>
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

	<div class="memberButton">
		<button type="button" id="enrollButton"
			onclick="location.href='${contextPath}/member/memberForm.do'">등록</button>
		<button type="button" onclick='getCheckList()'>삭제</button>
	</div>
</body>
</html>
