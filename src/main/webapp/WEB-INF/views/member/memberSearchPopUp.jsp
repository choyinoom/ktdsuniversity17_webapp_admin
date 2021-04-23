<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 검색 팝업창</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	
	window.onfocus=function(){
	}

	window.onload=function(){
		window.focus();
		window.moveTo(3,0);
		window.resizeTo(510,450);
		window.scrollTo(0,0)
	}

	function setMemberId(id) {
		var id = $(id).text();

		//document.getElementById('search').value = name;
		opener.document.getElementById('memberId').value = id;
		window.close();
	}
	
</script>
<body>
	<%
	String searchType = request.getParameter("searchType");
	String searchText = request.getParameter("searchType");
	%>
	
	<div style="width: 469.6px; height: 500px;">
		<div style="width: 100%; height: 10%; background: red; text-align: center;">
			아이디 리스트
		</div>
	
		<div style="width: 98%; height: 20%; background: green; margin: 2% 1%; ">
			<form method="get" action="${contextPath}/member/memberSearchPopUp.do" id="searchFrm">
				<table>
					<tr>
						<td> 검색 유형 <select name="searchType" id="searchType">
							<option value="name">이름</option>
							<option value="id">아이디</option>
									</select>
							</td>
							
					</tr>
					<tr>
						<td> 단어 검색 
							<c:choose>
								<c:when test="${not empty searchText }">
								<input type="text" name="searchText" id="searchText"
										value="${searchText }">
								</c:when>
								<c:otherwise>
									<input type="text" name="searchText" id="searchText">
								</c:otherwise>
							</c:choose>
						
						&nbsp;<input type="submit" value="검색"></td>
					</tr>
				</table>
			</form>
		</div>
	
		<div style="width: 98%; height: auto; background: yellow; margin: 2% 1%">
			<table>
			<tr height="15" align="center" style="border-bottom: solid;">
				<td><b>번호</b></td>     
			    <td ><b>이름</b></td>
			    <td><b>아이디</b></td>
			</tr>
			
			<c:choose>
				<c:when test="${membersList == null }" >
					<tr  height="10">
						<td>
					    	<p align="center">
					            <b><span style="font-size:9pt;">등록된 아이디가 없습니다.</span></b>
					        </p>
					    </td>  
					</tr>
				</c:when>
					  
				<c:when test="${membersList !=null }" >
					<c:forEach  var="member" items="${membersList }" varStatus="memberNum" >
						<tr align="center">
							<td>${memberNum.count }</td>
							<td>${member.name }</td>
							<td align='left'  width="25%">
								<span style="padding-right: 10px"></span>
							  <a href='javascript:void(0);' onclick="setMemberId(this);">${member.id }</a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
			</table>
		</div>
		
		<!-- 전체 페이지개수에 의한 페이지 리스트 띄우기 -->
		<div class="pageNumber" align="center" style="width: 80%; height: 10%;">
			<ul>
				<c:if test="${pageMaker.prev }">
					<c:choose>
						<c:when test="${not empty searchType and not empty searchText }">
							<li><a
								href="${contextPath}/member/memberSearchPopUp.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${contextPath}/member/memberSearchPopUp.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
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
								href="${contextPath }/member/memberSearchPopUp.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
							</li>
						</c:forEach>
					</c:when>
					
					<c:otherwise>
						<c:forEach begin="${pageMaker.startPage }"
							end="${pageMaker.endPage }" var="idx">
							<li
								<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }"/>>
								<a
								href="${contextPath }/member/memberSearchPopUp.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
							</li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
					<c:choose>
						<c:when test="${not empty searchType and not empty searchText }">
							<li><a
								href="${contextPath}/member/memberSearchPopUp.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${contextPath}/member/memberSearchPopUp.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>
			</ul>
		</div>
	
		<div style="width: 98%; height: 10%; background: gray; margin: 2% 1%;">
			<input type="button" value="창 닫기" onclick="window.close()">
		</div>
	</div>
</body>
</html>