<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />



<%
  request.setCharacterEncoding("UTF-8");
%>    

<!DOCTYPE html>
<html>
<head>

<style>
.article_category{
text-align : left;
margin-bottom : 3em;
color : grey
}
</style>

<meta charset="UTF-8">
<title >게시판 상세 설명</title>
</head>

<body>

<div class= "article_category">
	<h4>게시판관리 > 공지사항 > ${vo.id}</h4>
</div>

	<h1 class="text_center">공지사항</h1>
	<br></br>
	<table align="center">
		<tr>
			<td width="200">번호 : </td>
			<td width="400"><p align="left">${vo.id}</td>
		</tr>
		<tr>
			<td width="200">중요도 : </td>
			<td width="400"><p align="left">${vo.important}</td>
		</tr>
		<tr>
			<td width="200">제목 : </td>
			<td width="400"><p align="left">${vo.title}</td>
		</tr>
		<tr>
			<td width="200">내용 : </td>
			<td width="400"><p align="left">${vo.contents}</td>
		</tr>
		<tr>
			<td width="200">파일 : </td>
			<td width="400"><p align="left"><a href="${contextPath}/fileDown.do?filename=${filevo.name}&id=${vo.id}">${filevo.name }</a></td>
		</tr>
		<tr>
			<td width="200">작성일 : </td>
			<td width="400"><p align="left">${vo.joinDate}</td>
		</tr>
	</table>
		<tr>
	 		<td>
		 		<button onclick="location.href='${contextPath}/board/modArticleForm.do?id=${vo.id }'">수정</button>&nbsp;&nbsp;&nbsp;
		 		<button onclick="location.href='${contextPath}/board/removeArticle.do?id=${vo.id }'">삭제</button>&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="history.back()" value="취소">
	 		</td>
		</tr>
</body>
</html>