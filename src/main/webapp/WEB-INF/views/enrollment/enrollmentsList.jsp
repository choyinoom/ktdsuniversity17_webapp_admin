<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	a:link {
		text-decoration: none;
		color: black;
	}
	
	a:visited {
		text-decoration: none;
		color: black;
	}
	
	#herfId:hover {
		color: #c2c2c2;
	}
	
	.process {
	text-align: left;
	color: #9C9D9D;
	margin-bottom: 2em;
}
	
	#enrollmentButton {
		position: relative;
		margin-top: 5px;
		margin-bottom: 20px;
		display: flex;
		justify-content: flex-end;
		width: 1500px;
		margin-right: 5px;
	}
	
	#enrollButton, #approveButton, #completeButton, #cancelButton {
   		margin-right: 0.7%;
	}
	
	#typeColor {
		position: absolute;
		left: 0;
	}
	
	#excel {
		width: 100px;
	}
	
</style>
<meta charset="UTF-8">
<title>글목록창</title>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	// 전체 체크되게 하는 함수
	$(function() {
		$('#selectAll').click(function() {
			if ($("input:checkbox[id='selectAll']").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}
		})

		$('#listFilter').on(
			'change',
			function() {
				var perPage = $(this).val();
				var searchType = document
						.getElementById('searchType').value;
				var searchText = document
						.getElementById('searchText').value;
				location.href = "${contextPath}/enrollment/listEnrollments.do?perPage="
						+ perPage
						+ "&searchType="
						+ searchType
						+ "&searchText=" + searchText;
			})
	})
	
	//체크 된 걸 가져오는 함수
	function getCheckList(stat) {
		var length = $("input:checkbox[name='selectedCheckbox']:checked").length;
		//alert(length);
		var arr = new Array();
		$("input:checkbox[name='selectedCheckbox']:checked").each(
			function(index) {
				arr.push($(this).attr('id'));
			})

		if (length == 0) {
			alert("선택된 값이 없습니다.");
			return false;
		} else {
			if (stat == "delete") { // 상태 '취소완료' 로 변경
				
				if(!confirm("취소 하시겠습니까?")){
					return false;
				}else{
					$.ajax({
						type : 'post',
						url : '${contextPath}/enrollment/modDeleteEnrollments.do',
						traditional : true, //Array 형태로 보내려면 설정 해줘야함
						data : {
							arr : arr
						},
	
						success : function(data) {
							window.location.href = "${contextPath}/enrollment/listEnrollments.do";
						},
						error : function(data, request, status, error) {
							alert("code:" + request.status + "\n"
									+ "message:" + request.responseText
									+ "\n" + "error:" + error);
						}
					})
				}
						
			} else if (stat == "approve") { // 상태 '승인' 으로 변경
				$.ajax({
					type : 'post',
					url : '${contextPath}/enrollment/modApproveEnrollments.do',
					traditional : true,
					data : {
						arr : arr
					},

					success : function(data) {
						window.location.href = "${contextPath}/enrollment/listEnrollments.do";
					},
					error : function(data, request, status, error) {
						alert("code:" + request.status + "\n"
								+ "message:" + request.responseText
								+ "\n" + "error:" + error);
					}
				})
						
			} else if (stat == "complete") { // 상태 '수료' 로 변경
				$.ajax({
					type : 'post',
					url : '${contextPath}/enrollment/modCompleteEnrollments.do',
					traditional : true,
					data : {
						arr : arr
					},

					success : function(data) {
						window.location.href = "${contextPath}/enrollment/listEnrollments.do";
					},
					error : function(data, request, status, error) {
						alert("code:" + request.status + "\n"
								+ "message:" + request.responseText
								+ "\n" + "error:" + error);
					}
				})
			}
		}
	}

	$(document).on("click", '#selectAll', function() {
		$('#selectAll').click(function() {
			if ($("input:checkbox[id='selectAll']").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}
		})
	});
</script>
<body>
	<!-- controller에서 보낸 값 받아서 저장 -->
	<%
	String searchType = request.getParameter("searchType");
	String searchText = request.getParameter("searchType");
	%>
	
	<div class="process">
		<h4>
			<span onclick="location.href='${contextPath}/enrollment/listEnrollments.do'"
				style="cursor: pointer;">수강관리</span> > <span
				onclick="location.href='${contextPath}/enrollment/listEnrollments.do'"
				style="cursor: pointer;"> 수강신청내역</span>
		</h4>
	</div>

	<form method="get"
		action="${contextPath}/enrollment/listEnrollments.do" id="searchFrm">

		<!-- 리시트 필터 값 적용 -->
		<div class="listFilter">
			<select name="perPage" id="listFilter">
				<c:if test="${perPage == '10' }">
					<option value='10' selected>10</option>
					<option value='20'>20</option>
					<option value='50'>50</option>
					<option value='100'>100</option>
				</c:if>
				<c:if test="${perPage == '20' }">
					<option value='10'>10</option>
					<option value='20' selected>20</option>
					<option value='50'>50</option>
					<option value='100'>100</option>
				</c:if>
				<c:if test="${perPage == '50' }">
					<option value='10'>10</option>
					<option value='20'>20</option>
					<option value='50' selected>50</option>
					<option value='100'>100</option>
				</c:if>
				<c:if test="${perPage == '100' }">
					<option value='10'>10</option>
					<option value='20'>20</option>
					<option value='50'>50</option>
					<option value='100' selected>100</option>
				</c:if>
			</select>
		</div>
		
		<!-- 검색 유형 값에 따라 셀렉트 띄우는 값 설정 -->
		<div class="searchType">
			<select name="searchType" id="searchType">
				<c:if test="${searchType == 'name' }">
					<option value="">검색 종류</option>
					<option value="name" selected>이름</option>
					<option value="companyName">회사명</option>
					<option value="slbName">과정명</option>
					<option value="stat">상태</option>
				</c:if>
				<c:if test="${searchType == 'companyName' }">
					<option value="">검색 종류</option>
					<option value="name">이름</option>
					<option value="companyName" selected>회사명</option>
					<option value="slbName">과정명</option>
					<option value="stat">상태</option>
				</c:if>
				<c:if test="${searchType == 'slbName' }">
					<option value="">검색 종류</option>
					<option value="name">이름</option>
					<option value="companyName">회사명</option>
					<option value="slbName" selected>과정명</option>
					<option value="stat">상태</option>
				</c:if>
				<c:if test="${searchType == 'stat' }">
					<option value="">검색 종류</option>
					<option value="name">이름</option>
					<option value="companyName">회사명</option>
					<option value="slbName">과정명</option>
					<option value="stat" selected>상태</option>
				</c:if>
				<c:if test="${empty searchType }">
					<option value="" selected>검색 종류</option>
					<option value="name">이름</option>
					<option value="companyName">회사명</option>
					<option value="slbName">과정명</option>
					<option value="stat">상태</option>
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
	
	
	<div id="enrollmentButton">
      <p id="typeColor">
         <span style="color: black">협약상태 구분: </span> <span style="color: red">●협약서없음
         </span><span style="color: green"> ●상호변경 </span><span style="color: black">
            ●협약완료 </span><span style="color: blue"> ●협약서사본</span><span
            style="color: #dd42f5"> ●탈퇴</span>
      </p>
      <button type="button" id="enrollButton" onclick="location.href='${contextPath}/enrollment/enrollmentForm.do'"
         style="width: 5%;">등록</button>
      <button type="button" id="approveButton" onclick='getCheckList("approve")'
			style="width: 5%;">승인</button>
		<button type="button" id="completeButton" onclick='getCheckList("complete")'
			style="width: 5%;">수료</button>
      <button type="button" id="cancelButton" onclick='getCheckList("delete")'
         style="width: 5%;">취소</button>
	   <form action="${contextPath}/enrollment/excelDownload.do" method="post" id="excelForm">
	      <input type="submit" value='엑셀 다운로드' id="excel">
	   </form>
   </div> 
	
	<table align="center" border="0" width="80%" id="dynamicCompany">
		<tr height="15" align="center" id="attr">
			<td><input type="checkbox" id="selectAll"></td>
			<td><b>과정명</b></td>
			<td><b>교육기간</b></td>
			<td><b>이름</b></td>
			<td><b>소속회사</b></td>
			<td><b>상태</b></td>
			<td><b>신청일</b></td>
		</tr>
		
	<!-- 리스트 반복문 -->
		<c:choose>
			<c:when test="${empty enrollmentsList}">
				<tr height="10">
					<td colspan="4">
						<p align="center">
							<b><span style="font-size: 9pt;">등록된 회사가 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${enrollmentsList !=null }">
				<c:forEach var="enrollment" items="${enrollmentsList }" varStatus="enrdNum">
					<tr align="center">
						<td><input type="checkbox" name="selectedCheckbox" id="${enrollment.id }"></td>
							
						<!-- 과목별 상세 조회 / 인세 페이지 미구현 -->
						<td align="left" style="padding-left:20px"><a id="herfId" href="${contextPath}/enrollment/enrollmentCourse.do?
																id=${enrollment.courseVO.id }">${enrollment.syllabusVO.name }</a></td>
						<%-- <td>${enrollment.syllabusVO.name }</td> --%>
																
						<td>${enrollment.courseVO.startDate } ~ ${enrollment.courseVO.endDate }</td>
						
						<td><a id="herfId" href="${contextPath}/enrollment/informationEnrollment.do?
																id=${enrollment.id }">${enrollment.memberVO.name }</a></td>
						<td>
							<!-- 회사가 없는 경우 -->
							<c:if test="${enrollment.memberVO.companyName == null }">
								<font color="red">비협력사</font>
							</c:if>
							
							<!-- 회사가 있는 경우 -->
							<c:if test="${enrollment.memberVO.companyName != null }">
						
								<c:choose>
									<c:when test="${enrollment.companyVO.contractType eq '협약서 없음'}">
	                        			<font color="red">${enrollment.memberVO.companyName }</font>
	                        
	                     			</c:when> <c:when test="${enrollment.companyVO.contractType eq '상호 변경'}">
	                        			<font color="green">${enrollment.memberVO.companyName }</font>
	                        
	                     			</c:when> <c:when test="${enrollment.companyVO.contractType eq '협약 완료'}">
	                        			<font color="black">${enrollment.memberVO.companyName }</font>
	                        
	                     			</c:when> <c:when test="${enrollment.companyVO.contractType eq '협약서 사본'}">
	                        			<font color="blue">${enrollment.memberVO.companyName }</font>
	                    			 </c:when>
	                    			 <c:otherwise>
	                    			 	<font color="red">${enrollment.memberVO.companyName }</font>
	                    			 </c:otherwise>
                    			 </c:choose>
                    		</c:if>
						</td>
						
						<td>
							
							<c:choose>
								
								<c:when test="${enrollment.stat == '신청' }">
	                       			<font color="black">${enrollment.stat }</font></c:when>
	                       
	                    			 <c:when test="${enrollment.stat == '승인' }">
	                       			<font color="blue">${enrollment.stat }</font> </c:when>
	                   			 <c:otherwise>
	                   			 	<font color="red">${enrollment.stat }</font>
	                   			 </c:otherwise>
							</c:choose>
						
						
						
							<%-- <c:if test="${enrollment.stat == '신청' }">
								<font color="black">${enrollment.stat }</font>
							</c:if> <c:if test="${enrollment.stat == '승인' }">
								<font color="blue">${enrollment.stat }</font>
							</c:if> <c:if test="${enrollment.stat eq '수료' }">
								<font color="red">${enrollment.stat }</font>
							</c:if></td> --%>
							
							
							
							
							
							
						<td>${enrollment.joinDate }</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>

	<!-- 전체 페이지개수에 의한 페이지 리스트 띄우기 -->
	<div class="pageNumber" align="center" style="width: 80%; height: 10%;">
		<ul>
			<c:if test="${pageMaker.prev }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/enrollment/listEnrollments.do?page=${pageMaker.startPage - 1 }
								&searchText=${searchText}&searchType=${searchType}">이전</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/enrollment/listEnrollments.do?page=${pageMaker.startPage - 1 }
								&searchText=${searchText}&searchType=${searchType}">이전</a></li>
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
							href="${contextPath }/enrollment/listEnrollments.do?page=${idx}&searchText=${searchText}
								&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="idx">
						<li
							<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }"/>>
							<a
							href="${contextPath }/enrollment/listEnrollments.do?page=${idx}&searchText=${searchText}
								&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/enrollment/listEnrollments.do?page=${pageMaker.endPage + 1 }
								&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/enrollment/listEnrollments.do?page=${pageMaker.endPage + 1 }
								&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:otherwise>
				</c:choose>
			</c:if>
		</ul>
	</div>

	<!-- 버튼 모음집 -->
	<div id="enrollmentButton">
      <button type="button" id="enrollButton" onclick="location.href='${contextPath}/enrollment/enrollmentForm.do'"
         style="width: 5%;">등록</button>
      <button type="button" id="approveButton" onclick='getCheckList("approve")'
			style="width: 5%;">승인</button>
		<button type="button" id="completeButton" onclick='getCheckList("complete")'
			style="width: 5%;">수료</button>
      <button type="button" id="cancelButton" onclick='getCheckList("delete")'
         style="width: 5%;">취소</button>
	   <form action="${contextPath}/enrollment/excelDownload.do" method="post" id="excelForm">
	      <input type="submit" value='엑셀 다운로드' id="excel">
	   </form>
   </div> 
   
</body>
</html>