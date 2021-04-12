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
<title></title>
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
	
	function setCompanyName(name) {
		var name = $(name).text();

		//document.getElementById('search').value = name;
		opener.document.getElementById('companyName').value = name;
		window.close();
	}
</script>
<body>
<div style="width: 469.6px; height: 500px;">
	<div style="width: 100%; height: 10%; background: red; text-align: center;">
		회사 리스트
	</div>
	<div style="width: 98%; height: 20%; background: green; margin: 2% 1%; ">
		<form action="#">
			<table>
				<tr>
					<td> 회사 유형 <select name="searchType"><option>회사명</option><option>사업 번호</option></select></td>
				</tr>
				<tr>
					<td> 단어 검색 <input type="text" name="searchText" id="search" value="${searchText }" style="width: 100px; margin-right: 20px;">&nbsp;<input type="submit" value="검색"></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="width: 98%; height: auto; background: yellow; margin: 2% 1%">
		<table>
		<tr height="15" align="center" style="border-bottom: solid;">
		     <td><b>번호</b></td>     
		     <td ><b>사업자번호</b></td>
		     <td><b>회사명</b></td>
		  </tr>
			<c:choose>
				  <c:when test="${companiesList ==null }" >
				    <tr  height="10">
				      <td>
				         <p align="center">
				            <b><span style="font-size:9pt;">등록된 회사가 없습니다.</span></b>
				        </p>
				      </td>  
				    </tr>
				  </c:when>
				  <c:when test="${companiesList !=null }" >
				    <c:forEach  var="company" items="${companiesList }" varStatus="companyNum" >
				     <tr align="center">
						<td>${companyNum.count }</td>
						<td>${company.id }</td>
						<td align='left'  width="25%">
							<span style="padding-right: 10px"></span>
						  <a href='javascript:void(0);' onclick="setCompanyName(this);">${company.name }</a>
						</td>
					</tr>
				    </c:forEach>
			      </c:when>
			    </c:choose>
			</table>
	</div>
	<div style="width: 98%; height: 10%; background: gray; margin: 2% 1%;">
		<input type="button" value="창 닫기" onclick="window.close()">
	</div>
</div>
</body>
</html>