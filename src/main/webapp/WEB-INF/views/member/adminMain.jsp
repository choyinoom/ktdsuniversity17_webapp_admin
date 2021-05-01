<%@page import="com.ktdsuniversity.admin.manager.vo.ManagerVO"%>
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
<style>
	.adminMain ul{
        list-style-type: none;
        float: left;
    }

    .content_li{
        margin: 30px 10px;
    }
    
    .content_ul{
    	padding-left: 5%;

    }

    .contents {
        width: 18em;
   		height: 9em;
   		background: #f2f2f2;
    }

    .content_count {
        font-size: 50px; 
        font-weight: 1000;
        margin-bottom: 0;
        margin-top: 20px;
        color: #1A1A1A;
        text-align: left;
        position : relative;
        transform : translateX(20px);
        
    }

    .content_head {
        font-size: 20px; 
        font-weight: 700;
        margin-bottom: 0;
        margin-top: 55px;
        color: #666666;
        text-align: left;
        position : relative;
        transform : translateX(20px);
    }

    .content_title{
        font-size: 20px; font-weight: 700;
        color: #666666;
        width: 150px;
        text-align: left;
        position : relative;
        transform : translateX(20px);
    }

    .content_img_ul {
        position: relative;
  	  	left: 1em;
    	top: 1.9em;
    }
    
     .content_img_ul img {
        width: 5em;
    	height: 5em;
    	object-fit: cover;
    }
    
    .adminMain {
    position : relative;
    transform : translateY(-50px);
    }
    
    .mainContent {
    position: relative;
    left: 6em;
    top: 4em;
    width: 80%;
    }
    
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>	
	var color_old;
	var image_old;
	var count_color;
	var title_color;
	var head_color;
	var image_old2
	$(document).ready(function() {
	  $(".contents").bind('mouseover', function(event){
	    color_old = $(this).css('background-color');
	    image_old = $(this).find('img').attr('src');
	    count_color = $(this).find('.content_count').css('color');
	    title_color = $(this).find('.content_title').css('color');
	    head_color = $(this).find('.content_head').css('color');
	    image_old2 = image_old.split('.');
	    $(this).css('background-color', '#666666');
	    $(this).find('img').attr('src', image_old2[0]+'2.'+image_old2[1]);
	    $(this).find('.content_count').css('color', '#f2f2f2');
	    $(this).find('.content_title').css('color', '#f2f2f2');
	    $(this).find('.content_head').css('color', '#f2f2f2');
	  });
	  $(".contents").bind('mouseout',  function(event){
	    $(this).css('background-color', color_old);
	    $(this).find('img').attr('src', image_old);
	    $(this).find('.content_count').css('color', count_color);
	    $(this).find('.content_title').css('color', title_color);
	    $(this).find('.content_head').css('color', head_color);
	  });
	}); 
</script>
<body>
<%
	ManagerVO manager = (ManagerVO)session.getAttribute("manager");
	Boolean isLogOn = (Boolean)session.getAttribute("isLogOn");
	
	if (manager != null && isLogOn == true) {
		
	
%>
<div class="mainContent">
<section class="adminMain">
        <div>
            <ul>
                <li class="content_li">
                	<a href="${contextPath }/course/listCourses.do?searchType=status&searchText=신청가능" style="display:block;">
	                    <div class="contents">
	                        <ul class="content_ul">
	                            <li class="content_count">
	                                ${numberOfCourses}<br>
	                            </li>
	                            <li class="content_title">
	                                모집중인 과정
	                            </li>
	                        </ul>
	                        <ul class="content_img_ul">
	                            <li class="content_img_li">
	                                <img src="${contextPath}/resources/image/035-calendar.png" width="90" height="90">
	                            </li>
	                        </ul>
	                    </div>
                    </a>
                </li>
                <li class="content_li">
                    <div class="contents">
                        <ul class="content_ul">
                            <li class="content_count">
                                8<br>
                            </li>
                            <li class="content_title">
                                문의수
                            </li>
                        </ul>
                        <ul class="content_img_ul">
                            <li>
                                <img src="${contextPath}/resources/image/001-headset.png" width="90" height="90">
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
            <ul>
                <li class="content_li">
                	<a href="${contextPath}/enrollment/listEnrollments.do?searchType=stat&searchText=신청">
	                    <div class="contents">
	                        <ul class="content_ul">
	                            <li class="content_count">
	                                ${numberOfApplicants }<br>
	                            </li>
	                            <li class="content_title">
	                                신청서 접수
	                            </li>
	                        </ul>
	                        <ul class="content_img_ul">
	                            <li>
	                                <img src="${contextPath}/resources/image/010-contract.png" width="90" height="90">
	                            </li>
	                        </ul>
	                    </div>
                    </a>
                </li>
                <li class="content_li">
                    <div class="contents">
                        <ul class="content_ul">
                            <li class="content_count">
                                0<br>
                            </li>
                            <li class="content_title">
                                이력서 접수
                            </li>
                        </ul>
                        <ul class="content_img_ul">
                            <li>
                                <img src="${contextPath}/resources/image/008-id-card.png" width="90" height="90">
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
            <ul>
                <li class="content_li">
                	<a href="${contextPath }/company/listCompanies.do?searchType=contractStat&searchText=협약 진행중" style="display:block;">
	                    <div class="contents">
	                        <ul class="content_ul">
	                            <li class="content_count">
	                                ${numberOfCompanies }<br>
	                            </li>
	                            <li class="content_title">
	                                협약 진행중
	                            </li>
	                        </ul>
	                        <ul class="content_img_ul">
	                            <li>
	                                <img src="${contextPath}/resources/image/031-link.png" width="90" height="90">
	                            </li>
	                        </ul>
	                    </div>
                    </a>
                </li>
                <li class="content_li">
                	<a href="${contextPath }/enrollment/listEnrollments.do?searchType=stat&searchText=수료대기" style="display: block;">
	                    <div class="contents">
	                        <ul class="content_ul">
	                            <li class="content_count">
	                                ${numberOfWaitingCompletion }<br>
	                            </li>
	                            <li class="content_title">
	                                수료 대기
	                            </li>
	                        </ul>
	                        <ul class="content_img_ul">
	                            <li>
	                                <img src="${contextPath}/resources/image/038-wall-clock.png" width="90" height="90">
	                            </li>
	                        </ul>
	                    </div>
                    </a>
                </li>
            </ul>
        </div>
    </section>
    <section class="adminMain">
        <div>
            <ul>
                <li class="content_li">
                	<a href="${contextPath }/course/courseForm.do?page=1&perPage=10&searchText=&searchType=" style="display:block;">
	                    <div class="contents">
	                        <ul class="content_ul">
	                            <li class="content_head">
	                                과정 등록하기<br>
	                            </li>
	                            <li class="content_title">
	                                
	                            </li>
	                        </ul>
	                        <ul class="content_img_ul">
	                            <li>
	                                <img src="${contextPath}/resources/image/007-documentation.png" width="90" height="90">
	                            </li>
	                        </ul>
	                    </div>
                    </a>
                </li>
            </ul>
            <ul>
                <li class="content_li">
                	<a href="${contextPath }/syllabus/syllabusForm.do?page=1&perPage=10&searchText=&searchType=" style="display:block;">
	                    <div class="contents">
	                        <ul class="content_ul">
	                            <li class="content_head">
	                                강의계획서<br>
	                            </li>
	                            <li class="content_title">
	                                등록하기
	                            </li>
	                        </ul>
	                        <ul class="content_img_ul">
	                            <li>
	                                <img src="${contextPath}/resources/image/003-sketchbook.png" width="90" height="90">
	                            </li>
	                        </ul>
	                    </div>
                    </a>
                </li>
            </ul>
            <ul>
                <li class="content_li">
                	<a href="${contextPath }/enrollment/enrollmentForm.do?page=1&perPage=10&searchText=&searchType=" style="display:block;">
	                    <div class="contents">
	                        <ul class="content_ul">
	                            <li class="content_head">
	                                수강신청<br>
	                            </li>
	                            <li class="content_title">
	                                등록하기
	                            </li>
	                        </ul>
	                        <ul class="content_img_ul">
	                            <li>
	                                <img src="${contextPath}/resources/image/036-teamwork.png" width="90" height="90">
	                            </li>
	                        </ul>
	                    </div>
                    </a>
                </li>
            </ul>
        </div>
    </section>
</div>
<%} else {  %>
	<script>
		window.location.href="${contextPath}";
	</script>
	<% } %>
</body>
</html>