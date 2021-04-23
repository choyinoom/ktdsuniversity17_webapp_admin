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
<title>게시판 글 수정</title>
<style>
   .text_center{
     text-align:center;
   }
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
	
	
function fn_modify_article(obj){
	 obj.action="${contextPath}/board/modArticle.do";
	 obj.submit();
}

function fn_remove_article(url,artId){
	 var form = document.createElement("form");
	 form.setAttribute("method", "post");
	 form.setAttribute("action", url);
    var artIdInput = document.createElement("input");
    artIdInput.setAttribute("type","hidden");
    artIdInput.setAttribute("name","artIid");
    artIdInput.setAttribute("value", artId);
	 
    form.appendChild(artIdInput);
    document.body.appendChild(form);
    form.submit();

}

function fn_reply_form(url, id){
	 var form = document.createElement("form");
	 form.setAttribute("method", "post");
	 form.setAttribute("action", url);
    var idInput = document.createElement("input");
    idInput.setAttribute("type","hidden");
    idInput.setAttribute("name","id");
    idInput.setAttribute("value", id);
	 
    form.appendChild(idInput);
    document.body.appendChild(form);
	 form.submit();
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#preview').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}  
	
</script>

</head>
<body>
	<form method="post" action="${contextPath}/board/modArticle.do" id=updateFrm enctype="multipart/form-data">
	<h1  class="text_center">공지사항 수정</h1>
	<input type="hidden" name="id" value="${vo.id }">
	<table align="center" >
		
	<%--  	<tr>
			<td align="right">공지:</td>
			<td colspan="2"><select  colspan="2" name="important" value="${vo.important}"><option>공지</option><option>null</option></select></td>
		</tr> --%>
		<%-- <tr>
		<td width="200"><p align="left">중요도</td>
		<td width="400"><p align="left"><select type="text" name="important" id="important" value="${vo.important }">
			<c:if test="${vo.important == '공지'}">
				<option value="공지" selected>공지</option>
				<option value=""></option>
			</c:if>
			<c:if test="${vo.important == null}">
				<option value="공지">공지</option>
				<option value="" selected></option>
			</c:if>
		</select></td>
		</tr> --%>

		<tr align="left">
			<td>
				<c:if test="${vo.important == '공지'}">
					<td class="important_checkbox"><input type="checkbox" name="important" value="공지" checked>&nbsp;공지로 설정&nbsp;&nbsp;</td>
				</c:if>
				<c:if test="${vo.important == null}">
					<td class="important_checkbox"><input type="checkbox" name="important" value="공지">&nbsp;공지로 설정&nbsp;&nbsp;</td>
				</c:if>
			</td>
	   </tr>
		
		<br></br>
	   <tr>
	      <td width="200"><p align="left">제목</td>
	      <td width="400"><p align="left"><input type="text" name="title" id="title" style="width:500px;" maxlength="500" value="${vo.title }" oninvalid="this.setCustomValidity(제목을 쓰시오.)" oninput = "this.setCustomValidity('')" required></td>
	   </tr>
	   <tr>
	      <td width="200"><p align="left">내용</td>
	      <td width="400"><p align="left"><textarea type="text" name="contents" id="contents" rows="15" cols="80" maxlength="4000" >${vo.contents }</textarea></td>
	    </tr>
	    <tr>
	      <td width="200"><p align="left">첨부파일</td>
	      <td width="400"><p align="left"><input type="file" name="uploadFile" id="file">기존파일 :&nbsp;${filevo.name }</td>
	 	</tr>

	    <tr id="tr_btn_modify"  align="center"  >
	    <td colspan="2">
	       <td width="200"><p>&nbsp;</p></td>
	       <td width="400"><p align="left">
	       <input type="submit" value="수정" >
	       <input type="reset" value="다시입력" id="reset">
	       <input type="button" onclick="history.back()" value="취소">
	       </td>
	    </tr>
	</table>
	</form>
</body>
</html>