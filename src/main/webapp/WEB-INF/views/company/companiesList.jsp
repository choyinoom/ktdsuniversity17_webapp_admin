<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</style>
<meta charset="UTF-8">
<title>글목록창</title>
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
						url : '${contextPath}/company/removeCheckedCompanies.do',
						traditional : true, //Array 형태로 보내려면 설정 해줘야함
						data : {
							arr : arr
						},

						success : function(data) {
							alert('데이터 받기 성공');
							alert(data);
							window.location.href = "${contextPath}/company/listCompanies.do";
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
									location.href = "${contextPath}/company/listCompanies.do?perPage="
											+ perPage
											+ "&searchType="
											+ searchType
											+ "&searchText="
											+ searchText;
								})
			}) //function
</script>

<body>
	<%
	String searchType = request.getParameter("searchType");
	String searchText = request.getParameter("searchType");
	%>

	<form method="get" action="${contextPath }/company/listCompanies.do"
		id="searchFrm">

		<!-- 리시트 필터 값 적용 -->
		<div class="listFilter">
			<select name="perPage" id="listFilter">
				<c:if test="${perPage == '10' }">
					<option value='10' selected>10</option>
					<option value='20'>20</option>
					<option value='30'>30</option>

				</c:if>
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
			</select>
		</div>

		<div class="searchType">
			<select name="searchType" id="searchType">
				<c:if test="${searchType == 'name'}">
					<option value="name">선택</option>
					<option value="name" selected>회사명</option>
					<option value="contractName">담당자</option>
				</c:if>
				<c:if test="${searchType == 'contractName'}">
					<option value="name">선택</option>
					<option value="name">회사명</option>
					<option value="contractName" selected>담당자</option>
				</c:if>
				<c:if test="${empty searchType}">
					<option value="name" selected>선택</option>
					<option value="name">회사명</option>
					<option value="contractName">담당자</option>
				</c:if>
			</select>

			<c:choose>
				<c:when test="${not empty searchText}">
					<input type="text" name="searchText" id="searchText"
						value="${searchText }" style="width: 100px; margin-right: 20px;">
				</c:when>
				<c:otherwise>
					<input type="text" name="searchText" id="searchText"
						placeholder="검색어를 입력하세요." onfocus="this.placeholder=''"
						onblur="this.placeholder='검색어를 입력하세요.'"
						style="width: 100px; margin-right: 20px;">
				</c:otherwise>
			</c:choose>
			<input type="submit" value="검색">
		</div>
	</form>

	<table align="center" border="0" width="80%" id="dynamicCompany">
		<tr height="15" align="center" style="border-bottom: solid;">
			<td><input type="checkbox" id="selectAll"></td>
			<td><b>상태</b></td>
			<td><b>회사명</b></td>
			<td><b>담당자</b></td>
			<td><b>전화번호</b>
			<td><b>사업자등록번호</b></td>
			<td><b>등록 / 수정일</b></td>
		</tr>
		<c:choose>
			<c:when test="${empty companiesList }">
				<tr height="10">
					<td colspan="4">
						<p align="center">
							<b><span style="font-size: 9pt;">등록된 회사가 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${not empty companiesList }">
				<c:forEach var="company" items="${companiesList}"
					varStatus="articleNum">
					<tr align="center">
						<td><input type="checkbox" name="selectedCheckbox"
							id="${company.id }"></td>
						<td width="15%"><c:if test="${company.contractStat eq '협약사'}">
								<font color="blue">${company.contractStat }</font>
							</c:if> <c:if test="${company.contractStat eq '비협약사'}">
								<font color="red">${company.contractStat }</font>
							</c:if></td>
						<td align='center' width="20%"><span
							style="padding-right: 10px"></span> <a class='cls1'
							href="${contextPath}/company/companyForm.do?id=${company.id}">${company.name }</a>
						</td>
						<td>${company.contractName }</td>
						<td>${company.managerPhone }</td>
						<td>${company.id }</td>
						<td>${company.modDate }</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		</table>

		<!-- 전체 페이지 개수에 의한 페이지 리스트 띄우기 -->
		<div class="pageNumber" align="center"
			style="width: 80%; height: 10%;">
			<ul>
				<c:if test="${pageMaker.prev }">
					<c:choose>
						<c:when test="${not empty searchType and not empty searchText }">
							<li><a
								href="${contextPath}/company/listCompanies.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${contextPath}/company/listCompanies.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
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
								href="${contextPath }/company/listCompanies.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
							</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach begin="${pageMaker.startPage }"
							end="${pageMaker.endPage }" var="idx">
							<li
								<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }" />>
								<a
								href="${contextPath }/company/listCompanies.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
							</li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
					<c:choose>
						<c:when test="${not empty searchType and not empty searchText }">
							<li><a
								href="${contextPath}/company/listCompanies.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${contextPath}/company/listCompanies.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>
			</ul>
		</div>
		
		<div class="memberButton">
		<button type="button" id="enrollButton"
			onclick="location.href='${contextPath}/company/addCompanyForm.do'"
			style="width: 5%;">등록</button>
		<button type="button" onclick='getCheckList()' style="width: 5%;">삭제</button>
		</div>
</body>
</html>