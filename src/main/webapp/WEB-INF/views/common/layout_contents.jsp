<%@page import="com.ktdsuniversity.admin.manager.vo.ManagerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap')
	;

* {
	margin: 0px;
	padding: 0px;
	box-sizing: border-box;
}

#sideContent {
	display: flex;
}

#sidebar-left {
	width: 11%;
	min-height: 88.5vh;
	overflow: hidden;
	background-color: #e3e3e3;
	border-right: 0.5px solid #bcbcbc;
	font-size: 10px;
	text-align: center;
	justify-content: center;
	background-color: #e3e3e3;
	border-right: 0.5px solid #bcbcbc;
	font-size: 10px;
	text-align: center;
	display: flex;
	justify-content: center;
	background-color: #e3e3e3;
}

.side ul {
	margin-top: 30px;
	font-family: 'Noto Sans KR', sans-serif;
}

.side ul li {
	list-style: none;
}

.side ul li a {
	font-size: 1.5em;
	color: black;
}

.side_title {
	padding: 1.7em;
	width: 9em;
	border-bottom: 0.5px solid #bcbcbc;
}

#container {
	margin: 0px;
	padding: 0px;
	display: block;
}

#content {
	width: 89%;
	text-align: center;
	padding-left: 5em;
	padding-top: 5em;
}

#footer {
	background-color: #F2F2F2;
	text-align: center;
	height: 5vh;
	align-items: center;
	justify-content: center;
	display: flex;
}

#attr {
	border-bottom: 2px solid #bcbcbc;
}

#button {
	text-decoration: none;
	color: black;
	border: none;
	padding: 2px 4px;
	border-radius: 5px;
	background-color: #ECECEC;
	font-size: 13px;
	font-weight: 800;
	cursor: pointer;
	width: 90px;
	height: 35px;
	padding: 5px;
}

a {
	text-decoration: none;
}

table {
	width: 90%;
	border-collapse: collapse;
}

td {
	padding: 1.3em 0px 1.3em 0px;
	border-bottom: 0.3px solid;
	border-color: rgba(156, 157, 157, 0.2);
	font-size: 14px;
}

.pageNumber {
	display: flex;
	align-items: center;
	justify-content: center;
	text-align: center;
	position: relative;
	width: 90%;
	margin-bottom: 2em;
}

.pageNumber ul {
	list-style-type: none;
	display: flex;
}

.pageNumber ul li a {
	color: black;
	font-weight: 400;
	padding: 5px 15px;
}

.pageNumber ul .active a{
   font-weight: 1000;
}

input[type=submit], [type=reset], [type=button] {
	border: none;
	width: 60px;
	height: 35px;
	font-size: 15px;
	background-color: #969696;
	color: #EFEFEF;
	cursor: pointer;
	border-radius: 5px;
	margin: 0px;
}

input[type=text], [type=password], [type=tel], [type=email] {
	border: none;
	width: 200px;
	height: 35px;
	background-color: #ECECEC;
	margin-right: 5px;
	border-radius: 5px;
}

select {
	border: none;
	width: 90px;
	height: 35px;
	background-color: #ECECEC;
	font-size: 13px;
	margin-right: 5px;
	cursor: pointer;
	border-radius: 5px;
	text-align-last: center;
	text-align: center;
	-ms-text-align-last: center;
	-moz-text-align-last: center;
}

#enrollButton {
	background-color: #E91B23;
	color: #EFEFEF;
	cursor: pointer;
}

button {
	margin-left: 20px;
	width: 90px;
	height: 35px;
	border: none;
	border-radius: 5px;
	background-color: #ECECEC;
	font-size: 15px;
	cursor: pointer;
}

.memberButton {
	position: relative;
	margin-top: 5px;
	display: flex;
	justify-content: flex-end;
	width: 90%;
}

.memberButton button {
	margin-left: 1em;
}

#searchFrm {
	display: flex;
	justify-content: flex-end;
	align-items: center;
	width: 90%;
	margin-bottom: 20px;
}

.under {
	position: relative;
    top: 0.5em;
}
</style>
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
<%
	ManagerVO manager = (ManagerVO)session.getAttribute("manager");
	Boolean isLogOn = (Boolean)session.getAttribute("isLogOn");
	
	if (manager != null && isLogOn == true) {
		
	
%>
	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="sideContent">
			<div id="sidebar-left">
				<tiles:insertAttribute name="side" />
			</div>
			<div id="content">
				<tiles:insertAttribute name="body" />
			</div>
		</div>

		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
	<%} else {  %>
	<script>
		window.location.href="${contextPath}";
	</script>
	<% } %>
</body>
</html>