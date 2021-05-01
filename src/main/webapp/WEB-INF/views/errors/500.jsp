<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:set var="contextPath" value="${pageContext.request.contextPath}" />

		<% request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
	<meta charset="UTF-8">
	<style>
		.error_container {
			display: flex;
			flex-direction: column;
			margin: auto;
			width:100%;
			height: 100%;
			justify-content: center;
		}
		
		.error_img {
			width: 400px;
			margin-top: -20px;
			margin-left: auto;
			margin-right: auto;
			margin-bottom: 10px;
		}
		
		.error_img img {
			width: 400px;
		}
		
		.error_button {
			margin: 20px 0px;
			align-items: center;
		}
		
		.error_button a {
			border-radius: 12px;
		    font-weight: bold;
		    text-decoration: none;
		    padding: 20px;
		    width: 145px;
		    height: 22px;
		    font-size: 17px;
		    color: #EFEFEF;
		    background: #E91B23;
		    cursor: pointer;
		    display: block;
		    margin-left: auto;
		    margin-right: auto;
		}
		
		
	</style>
<title>ERROR</title>
</head>
<body>
	<div class="error_container">
	<div class="error_img">
	<img src="${contextPath}/resources/image/errorpage.png">
	</div>
	<div class="error_button">
		<a  onclick="history.back()">이전으로 돌아가기</a>
	</div>
	</div>
</body>
</html>
