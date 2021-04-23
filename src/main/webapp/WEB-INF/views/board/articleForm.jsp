<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
<meta charset="UTF-8">
<style>
.articleForm_category{
text-align : left;
margin-bottom : 1em;
color : grey
}

.articleForm_Bigtitle{
padding-bottom : 70px;
}

.important_checkbox{
text-align : left;
margin-bottom : 1em;
}

.articleForm_title{

padding-top : 10px;
padding-bottom : 10px;
}

.title_box{
text-align : left;
padding-top : 10px;
padding-bottom : 10px;
}

.articleForm_contents{
text-align : left;

}

.contentsBox{
padding-top : 10px;
padding-bottom : 10px;
}

.attachFile{
text-align : left;
padding-top : 10px;
padding-bottom : 10px;
}

</style>
<title>글쓰기창</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

function readURL(input) {
    if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    reader.onload = function (e) {
	       $('#preview').attr('src', e.target.result);
         }
        reader.readAsDataURL(input.files[0]);
      }
  }  
function backToList(obj){
    obj.action="${contextPath}/board/listArticles.do";
    obj.submit();
  }
  
</script>
 <title>글쓰기창</title>
</head>
<body>

	<div class= "articleForm_category">
		<h4>게시판관리 > 공지사항 > 공지사항 등록</h4>
	</div>

	<div class="articleForm_BigTitle">
		<h2>공지사항 등록</h2>
	</div>
	
  	<form name="articleForm" method="post" action="${contextPath}/board/addNewArticle.do" enctype="multipart/form-data">
    
    <table class="articleForm_table">
	    
	    <tr>
	    	<td class="articleForm_important">공지</td>
			<!-- <td><select name="important"><option>공지</option><option></option></select></td> -->
			<td class="important_checkbox"><input type="checkbox" name="important" value="공지">&nbsp;공지로 설정&nbsp;&nbsp;</td>
	   </tr>
	   
	    <tr class = "articleForm_title">
			<td>제목</td>
			<td class= "title_box"><input type="title" size="100"  maxlength="500" name="title" oninvalid="this.setCustomValidity(제목을 쓰시오.)" oninput = "this.setCustomValidity('')" required /></td>
		</tr>
		
	 	<tr class = "articleForm_contents">
			<td>글내용</td>
			<td class="contentsBox"><textarea name="contents" rows="10" cols="65" maxlength="4000" wrap="hard"></textarea></td>
     	</tr>	
     	
     	 <tr class = "articleForm_file">
			<td>파일첨부</td>
			<td class = "attachFile"><input type="file" size="67"  maxlength="500" name="fileName" /></td>
		</tr>
     	
  <!--    <tr>
			  <td align="right">이미지파일 첨부:  </td>
			  <td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
			  <td><img  id="preview" src="#"   width=200 height=200/></td>
			  
			  
			  <td align="right">이미지파일 첨부</td>
				<td align="left"> <input type="button" value="파일 추가" onClick="fn_addFile()"/></td>
				
				
	   </tr> -->
	<!--    <tr>
	      <td colspan="4"><div id="d_file"></div></td>
	   </tr> -->
	   
	    <tr>
	      <td align="right"></td>
	      <td colspan="2">
	       <input type=submit value="글쓰기"/>
	       <input type=button value="목록보기" onClick="backToList(this.form)" />
	      </td>
    	</tr>
    </table>
  </form>
</body>
