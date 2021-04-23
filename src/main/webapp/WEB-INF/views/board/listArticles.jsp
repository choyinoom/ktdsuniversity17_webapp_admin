<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

.listArticle_button {
display : flex;
justify-content: flex-end;
}

.listArticles_title{
text-align : left;
padding-top : 10px;
padding-bottom : 10px;
}

</style>
<meta charset="UTF-8">
<title>글목록창</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>

/* 	function fn_articleForm(isLogOn,articleForm,loginForm){
	  if(isLogOn != '' && isLogOn != 'false'){
	    location.href=articleForm;
	  }else{
	    alert("로그인 후 글쓰기가 가능합니다.")
	    location.href=loginForm+'?action=/board/articleForm.do';
	  }
	} */

	//체크 된 걸 가져오는 함수
	function getCheckList() {
		var length = $("input:checkbox[name='selectedCheckbox']:checked").length;
		/* alert(length); */
 		var arr = new Array();
		$("input:checkbox[type=checkbox]:checked").each(function(index) {
			/* alert($(this).attr('id')); */
 			arr.push($(this).attr('id'));
		})
		
		if(length == 0){
			alert("선택된 값이 없습니다.");
			return false;
		} else {
			$.ajax({
				type: 'post',
				url: '${contextPath}/board/removeCheckedArticles.do',
				traditional : true, //Array 형태로 보내려면 설정 해줘야함 */
				data: {arr: arr},

				
				success: function(data) {
					alert('데이터 받기 성공');
					alert(data+"개의 데이터가 삭제됩니다.");
					window.location.href = "${contextPath}/board/listArticles.do";
				}, error:function(data,request,status,error){
		             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		             
		        }
			})
		}
	}
	
	// 전체 체크되게 하는 함수
	$(function(){
		$('#selectAll').click(function(){
			if ($("input:checkbox[id='selectAll']").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else{
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
									location.href = "${contextPath}/board/listArticles.do?perPage="
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
 String searchText = request.getParameter("searchText");
%>

<div class= "article_category">
	<h4>게시판관리 > 공지사항</h4>
</div>

<form method="get" action="${contextPath}/board/listArticles.do" id="searchFrm" >
	
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
	
	<!-- 검색 유형 값(title만)에 따라 셀렉트 띄우는 값 설정 -->
	<div class="searchType" align="right">
		<select name="searchType" id="searchType">
			<c:if test="${searchType == 'title'}">
				<option value="">검색 종류</option>
				<option value="title" selected>제목</option>
			</c:if>
			<c:if test="${empty searchType }">
				<option value="" selected>검색 종류</option>
				<option value="title">제목</option>
			</c:if>
		</select>
	
	<!-- 검색 값이 있냐 없냐에 따라 값 뛰우는거 설정 -->
	<c:choose>
		<c:when test="${searchText != null }">
			<input type="text" name="searchText" id="searchText" value="${searchText }" style="width: 200px; margin-right: 10px;">
		</c:when>
		<c:otherwise>
			<input type="text" name="searchText" id="searchText" style="width: 200px; margin-right: 10px;">
		</c:otherwise>
	</c:choose>
	<input type="submit" value="검색" >
	</div>
</form>

<table border="0" align="center"  width="80%">
	<tr>
		<td><input type="checkbox" id="selectAll"></td>
     	<td><b>번호</b></td>
     	<td style="text-align: center;"><b>제목</b></td>  
     	<td><b>조회수</b></td>
     	<td><b>등록일</b></td>
     </tr>
     
     <c:choose>
      <c:when test="${articlesList == null }" >
    		<tr  height="10">
      			<td colspan="4">
         			<p align="center">
            		<b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        			</p>
      			</td>  
    		</tr>
  		</c:when>
  		<c:when test="${articlesList != null }" >
  			<c:forEach var="board" items="${impArticlesList }">
  				<tr align="center">
  					<td><input type="checkbox" name="selectedCheckbox" id="${board.id }"></td>
  					
  					<td width="10%"><h4 style="color: red;">공지</h4>${board.important}</td>
  					
      					<td class='listArticles_title'><a href="${contextPath}/board/selectArticle.do?id=${board.id}">${board.title}</a>
      						<c:if test="${board.file == 'T'}">
      							<a href="${contextPath}/board/selectArticle.do?id=${board.id}"><img style="width: 20px; margin-bottom: -7px;" src="${contextPath}/resources/image/diskette.png"></a>
      						</c:if>
      						<c:if test="${board.file == 'F'}">
      						</c:if>
      					</td>
      				
      				<td>${board.hits}</td>
      				
      				<td>${board.joinDate}</td>
      			</tr>
  			</c:forEach>
  			
   			<c:forEach 	var="board" items="${articlesList }" varStatus="boardNum" >  
   				<tr align="center">
   				<td><input type="checkbox" name="selectedCheckbox" id="${board.id }"></td>
      				<td width="10%">${board.id}</td>
      				
      					<td class='listArticles_title'><a href="${contextPath}/board/selectArticle.do?id=${board.id}">${board.title}</a>
      						<c:if test="${board.file == 'T'}">
      							<a href="${contextPath}/board/selectArticle.do?id=${board.id}"><img style="width: 20px; margin-bottom: -7px;" src="${contextPath}/resources/image/diskette.png"></a>
      						</c:if>
      						<c:if test="${board.file == 'F'}">
      						</c:if>
      					</td>

      				<td>${board.hits}</td>
      				<td>${board.joinDate}</td>
    		</tr>
  			</c:forEach>  
  		</c:when>
	</c:choose>
</table>
     	
     	<%-- 
     	      				<td align='center' width="30%">
	  					<span style="padding-right:10px" ></span>
      					<a class='cls1' href="${contextPath}/board/selectArticle.do?id=${board.id}">${board.title}</a>
      				</td>
      					<c:if test="${board.file == 'T'}">
      						<td><a href="${contextPath}/board/selectArticle.do?id=${board.id}"><img src="${contextPath}/resources/image/diskette.png" style="max-width: 6%; height: auto;" ></a></td>
      					</c:if>
      					<c:if test="${board.file == 'F'}">
      						<td></td>
      					</c:if> --%>
     	
     	
     	
     	
     	
     <!-- 전체 페이지개수에 의한 페이지 리스트 띄우기 -->
	<div class="pageNumber" align="center" style="width: 80%; height: 10%;">
		<ul>
			<c:if test="${pageMaker.prev }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/board/listArticles.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/board/listArticles.do?page=${pageMaker.startPage - 1 }&searchText=${searchText}&searchType=${searchType}">이전</a></li>
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
							href="${contextPath }/board/listArticles.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="idx">
						<li
							<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : '' }"/>>
							<a
							href="${contextPath }/board/listArticles.do?page=${idx}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}">${idx }</a>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
				<c:choose>
					<c:when test="${not empty searchType and not empty searchText }">
						<li><a
							href="${contextPath}/board/listArticles.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${contextPath}/board/listArticles.do?page=${pageMaker.endPage + 1 }&searchText=${searchText}&searchType=${searchType}">다음</a></li>
					</c:otherwise>
				</c:choose>
			</c:if>
		</ul>
	</div>
     	
     <div class="listArticle_button">
     	<button type="button" onclick="location.href='${contextPath}/board/articleForm.do'">글쓰기</button>
     	<button type="button" onclick='getCheckList()' style="width: 5%;">삭제</button>
     </div>
</body>
</html>


<%-- <body>
<table align="center" border="0"  width="80%"  >
  <tr height="10" align="center"  bgcolor="lightgreen">
     <td ><b>글번호</b></td>
     <td ><b>작성자</b></td>              
     <td ><b>제목</b></td>
     <td ><b>작성일</b></td>
  </tr>
<c:choose>
  <c:when test="${articlesList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${articlesList !=null }" >
    <c:forEach  var="article" items="${articlesList }" varStatus="articleNum" >
     <tr align="center">
	<td width="5%">${articleNum.count}</td>
	<td width="10%">${article.title }</td>
	<td width="10%">${article.hits }</td>
	<td width="10%">${article.joinDate }</td>
	<td align='left'  width="35%">
	  <span style="padding-right:30px"></span>
	   <c:choose>
	      <c:when test='${article.level > 1 }'>  
	         <c:forEach begin="1" end="${article.level }" step="1">
	              <span style="padding-left:20px"></span>    
	         </c:forEach>
	         <span style="font-size:12px;">[답변]</span>
                   <a class='cls1' href="${contextPath}/board/viewArticle.do?id=${article.id}">${article.title}</a>
	          </c:when>
	          <c:otherwise>
	            <a class='cls1' href="${contextPath}/board/viewArticle.do?id=${article.id}">${article.title }</a>
	          </c:otherwise>
	        </c:choose>
	  </td>
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>
<!-- <a  class="cls1"  href="#"><p class="cls2">글쓰기</p></a> -->
<a  class="cls1"  href="javascript:fn_articleForm('${isLogOn}','${contextPath}/board/articleForm.do', 
                                                    '${contextPath}/member/loginForm.do')"><p class="cls2">글쓰기</p></a>
</body>
</html> --%>