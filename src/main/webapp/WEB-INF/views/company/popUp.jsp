<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	window.onfocus = function() {
	}

	window.onload = function() {
		window.focus();
		window.moveTo(3, 0);
		window.resizeTo(510, 450);
		window.scrollTo(0, 0)
	}

	function setCompanyName(name) {
		var name = $(name).text();

		//document.getElementById('search').value = name;
		opener.document.getElementById('companyName').value = name;
		window.close();
	}
	$(
			function() {
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
									location.href = "${contextPath}/company/popUp.do?perPage="
											+ perPage
											+ "&searchType="
											+ searchType
											+ "&searchText="
											+ searchText;
								})
			})
</script>
<body>
	<!-- 파라미터 -->
	<%
	String searchType = request.getParameter("searchType");
	String searchText = request.getParameter("searchType");
	%>
	<h1 style= text-align:center;>회사 리스트</h1>
	<form method="get" action="${contextPath }/company/popUp.do"
		id="searchFrm">

		<!-- 리시트 필터 값 적용 -->
		<div class="listFilter">
			<label>개수 기준<select name="perPage" id="listFilter">
					<c:if test="${perPage == '10' }">
						<option value='10' selected>10개</option>
						<option value='20'>20개</option>
						<option value='30'>30개</option>

					</c:if>
					<c:if test="${perPage == '20' }">
						<option value='10'>10개</option>
						<option value='20' selected>20개</option>
						<option value='30'>30개</option>
					</c:if>
					<c:if test="${perPage == '30' }">
						<option value='10'>10개</option>
						<option value='20'>20개</option>
						<option value='30' selected>30개</option>
					</c:if>
			</select></label>
		</div>

		<!-- 검색 유형 값에 따라 셀렉트 띄우는 값 설정 -->
		<div class="searchType">
			<label>회사 유형<select name="searchType" id="searchType">
					<c:if test="${searchType == 'name' }">
						<option value="name">선택</option>
						<option value="name" selected>회사명</option>
						<option value="id">사업 번호</option>
					</c:if>
					<c:if test="${searchType == 'id' }">
						<option value="name">선택</option>
						<option value="name">회사명</option>
						<option value="id" selected>사업 번호</option>
					</c:if>
					<c:if test="${empty searchType}">
						<option value="name" selected>선택</option>
						<option value="name">회사명</option>
						<option value="id">사업번호</option>
					</c:if>
			</select></label> 
			<label>단어 검색 <c:choose>
					<c:when test="${not empty searchText }">
						<input type="text" name="searchText" id="searchText"
							value="${searchText }" style="width: 100px; margin-right: 20px;">
					</c:when>
					<c:otherwise>
						<input type="text" name="searchText" id="searchText"
							style="width: 100px; margin-right: 20px;">
					</c:otherwise>
				</c:choose></label> <input type="submit" value="검색">
		</div>
	</form>
		
		<!-- 회사 리스트 출력 -->
		<table border="0" align="center" width="80%">
			<tr height="15" align="center" style="border-bottom: solid;">
				<td><b>번호</b></td>
				<td><b>사업자번호</b></td>
				<td><b>회사명</b></td>
			</tr>
			<c:choose>
				<c:when test="${empty companiesList}">
					<tr height="10">
						<td>
							<p align="center">
								<b><span style="font-size: 9pt;">등록된 회사가 없습니다.</span></b>
							</p>
						</td>
					</tr>
				</c:when>
				<c:when test="${not empty companiesList }">
					<c:forEach var="company" items="${companiesList }" varStatus="companyNum">
						<tr align="center">
							<td>${companyNum.count }</td>
							<td>${company.id }</td>
							<td align='left' width="25%">
								<span style="padding-right: 10px"></span> 
								<a href='javascript:void(0);' onclick="setCompanyName(this);">${company.name }</a></td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>

	<!-- 전체 페이지 개수에 의한 페이지 리스트 띄우기 -->
	<div class="pageNumber" align="center">
		<ul>
			<c:if test="${pageMaker.prev }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/company/popUp.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/company/popUp.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
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
							href="${contextPath }/company/popUp.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="idx">
						<li
							<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }" />>
							<a
							href="${contextPath }/company/popUp.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/company/popUp.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/company/popUp.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:otherwise>
				</c:choose>
			</c:if>
		</ul>
	</div>
	<div class="memberButton">
		<button type="button" onclick="window.close()">창 닫기</button>
	</div>
</body>
</html>