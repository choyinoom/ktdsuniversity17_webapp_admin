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

.com_search {
	padding-left: 7px;
}

.process {
	text-align: left;
	margin-bottom: 2em;
	color: #9C9D9D;
}

#mem1 {
	margin-bottom: 1em;
}

#type_color {
	font-size: 15px;
    width: 90%;
    display: flex;
    justify-content: space-between;
}

#type_color span{
	font-size: 0.9em;
	margin-right: 7px;
}

#excel {
	width: 7em;
    margin-left: 1em;
}

#under_excelButton {
	width: 90%;
    justify-content: flex-end;
    display: flex;
    margin-bottom: 3em;
    margin-top: -1em;
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
		alert(length + '개 선택하였습니다.');
		var arr = new Array();
		$("input:checkbox[type=checkbox]:checked").each(function(index) {
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
							window.location.href = "${contextPath}/company/listCompanies.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}";
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
							location.href = "${contextPath}/company/listCompanies.do?perPage="
									+ perPage
									+ "&searchType="
									+ searchType
									+ "&searchText=" + searchText;
						})
	}) //function
</script>

<body>
	<!-- 파라미터 -->
	<%
	String searchType = request.getParameter("searchType");
	String searchText = request.getParameter("searchType");
	%>
	<div class="process">
		<h5>
			<span onclick="location.href='${contextPath}/member/listMembers.do'"
				style="cursor: pointer;">회원관리</span> > <span
				onclick="location.href='${contextPath}/company/listCompanies.do?&page=${criteria.page}&searchText=${criteria.searchText}&searchType=${criteria.searchType}&perPageNum=${criteria.perPageNum}&type=company'"
				style="cursor: pointer;"> 회사관리</span>
		</h5>
	</div>

	<form method="get" action="${contextPath }/company/listCompanies.do"
		id="searchFrm" name="searchZip">

		<!-- 리시트 필터 값 적용 -->
		<div class="listFilter">
			<select name="perPage" id="listFilter">
				<c:if test="${perPage == '10' }">
					<option value='10' selected>10개</option>
					<option value='20'>20개</option>
					<option value='50'>50개</option>
					<option value='100'>100개</option>
				</c:if>
				<c:if test="${perPage == '20' }">
					<option value='10'>10개</option>
					<option value='20' selected>20개</option>
					<option value='50'>50개</option>
					<option value='100'>100개</option>
				</c:if>
				<c:if test="${perPage == '50' }">
					<option value='10'>10개</option>
					<option value='20'>20개</option>
					<option value='50' selected>50개</option>
					<option value='100'>100개</option>
				</c:if>
				<c:if test="${perPage == '100' }">
					<option value='10'>10개</option>
					<option value='20'>20개</option>
					<option value='50'>50개</option>
					<option value='100' selected>100개</option>
				</c:if>
			</select>
		</div>

		<!-- 검색 유형 값에 따라 셀렉트 띄우는 값 설정 -->
		<div class="searchType">
			<select name="searchType" id="searchType">
				<c:if test="${empty searchType}">
					<option value="name">회사명</option>
					<option value="contractName">담당자</option>
					<option value="contractStat">상태</option>
				</c:if>
				<c:if test="${searchType == 'name'}">
					<option value="name" selected>회사명</option>
					<option value="contractName">담당자</option>
					<option value="contractStat">상태</option>
				</c:if>
				<c:if test="${searchType == 'contractName'}">
					<option value="name">회사명</option>
					<option value="contractName" selected>담당자</option>
					<option value="contractStat">상태</option>
				</c:if>
				<c:if test="${searchType == 'contractStat'}">
					<option value="name">회사명</option>
					<option value="contractName">담당자</option>
					<option value="contractStat" selected>상태</option>
				</c:if>
			</select>

			<!-- 검색 값이 있냐 없냐에 따라 값 띄우는거 설정 -->
			<c:choose>
				<c:when test="${not empty searchText}">
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
			<input type="submit" value="검색">
		</div>
	</form>


	<!-- 엑셀 다운로드 버튼 -->
	<form action="${contextPath}/company/excelDownload.do" method="post">
		<div id="type_color">
			<div style="width: 40.3em;">
			<span style="color: black">협약상태 구분:</span><span style="color: black"> ●협약완료</span><span style="color: blue"> ●협약서사본</span><span style="color: red"> ●협약서없음</span><span style="color: green"> ●상호변경</span>
			</div>
			<div class="memberButton" id="mem1">
				<button type="button" id="enrollButton"	onclick="location.href='${contextPath}/company/addCompanyForm.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'">등록</button>
				<button type="button" onclick='getCheckList()'>삭제</button>
				<input type="submit" value='엑셀 다운로드' id="excel">
			</div>
		</div>
	</form>

	<table>
		<tr id="attr" align="center">
			<td><input type="checkbox" id="selectAll"></td>
			<td><b>상태</b></td>
			<td><b>회사명</b></td>
			<td><b>담당자</b></td>
			<td><b>전화번호</b>
			<td><b>사업자등록번호</b></td>
			<td><b>등록일</b></td>
		</tr>
		<c:choose>
			<c:when test="${empty companiesList }">
				<tr height="10">
					<td colspan="7">
						<p align="center">
							<b><span style="font-size: 9pt;">등록된 회사가 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${not empty companiesList }">
				<c:forEach var="company" items="${companiesList}"
					varStatus="articleNum">
					<tr>
						<td><input type="checkbox" name="selectedCheckbox"
							id="${company.id }"></td>
						<td width="15%">${company.contractStat }</td>
						<td align='center' width="15%"><a class='cls1'
							href="${contextPath}/company/companyForm.do?id=${company.id}&page=${criteria.page}&searchText=${criteria.searchText}&searchType=${criteria.searchType}&perPageNum=${criteria.perPageNum}&type=company">
								<c:if test="${company.contractType eq '협약서 없음'}">
									<font color="red">${company.name }</font>
								</c:if> <c:if test="${company.contractType eq '상호 변경'}">
									<font color="green">${company.name }</font>
								</c:if> <c:if test="${company.contractType eq '협약 완료'}">
									<font color="black">${company.name }</font>
								</c:if> <c:if test="${company.contractType eq '협약서 사본'}">
									<font color="blue">${company.name }</font>
								</c:if>
						</a></td>
						<td width="15%">${company.contractName }</td>
						<td width="15%">${company.managerPhone }</td>
						<td width="15%">${company.id }</td>
						<td width="15%">${company.regDate }</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>

	<form class="under" action="${contextPath}/company/excelDownload.do" method="post">
		<div class="memberButton">
			<button type="button" id="enrollButton"
				onclick="location.href='${contextPath}/company/addCompanyForm.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'">등록</button>
			<button type="button" id="cancelButton" onclick='getCheckList()'>삭제</button>
			<input type="submit" value='엑셀 다운로드' id="excel">
		</div>
	</form>
	
	<!-- 전체 페이지 개수에 의한 페이지 리스트 띄우기 -->
	<div class="pageNumber" align="center">
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
</body>
</html>