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
<title>회원 정보 수정창</title>
<style>
   .text_center{
     text-align:center;
   }
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#pw2').on('keyup', function() {
			var pw = document.getElementById('pw').value;
			var pw2 = this.value;
			if (pw != pw2) {
				$('#pwCheck').html('비밀번호가 틀립니다.').css('color','red');
				return;
			} else {
				$('#pwCheck').html('비밀번호 확인').css('color','blue');
				return;
			}
			
		})
		
		$('#updateFrm').submit(function() {
			
			var pwCheck = document.getElementById("pwCheck").innerHTML;
			/* alert(pwCheck); */ 
			
			var name = document.getElementById("name").value;
			/* alert(name); */
			
			var email = document.getElementById("email").value;
			/* alert(email); */
			
			if (pwCheck == "비밀번호 확인" && name != "" && email != "") {
				return true;
			} else if(pwCheck != "비밀번호 확인") {
				alert("비밀번호 최종 확인 해주세요.");
				return false;
			} else if(name == "") {
				alert("이름을 입력하세요.");
				return false;
			} else if(email == "") {
				alert("이메일을 입력하시오.");
				return false;
			} 
			
			return false;
		})
		
		$('#reset').on('click', function() {
			$('#pwCheck').html('');
			
		})
		
		$('#id').on('click', function() {
			alert('아이디는 변경 불가합니다.');
		})
		
		
		
		$('input:checkbox[name="subscription"]').each(function() {
		    if(this.value == "${vo.subscription}"){ 
		            this.checked = true;
	        }
		});
		
 });
	
	//체크 하나만 함수
	function checkOnlyOne(element) {
		const checkboxes = document.getElementsByName("subscription");
		checkboxes.forEach((cb) => {
			  cb.checked = false;
			})
		element.checked = true;
	}
	
	//회사 리스트 팝업창 뛰우기
	function popup(){
	    var url = "${contextPath}/company/popUp.do";
	    var name = "popup test";
	    var option = "width = 500, height = 500, top = 100, left = 200, location = no"
	    window.open(url, name, option);
	    
	}
</script>

</head>
<body>
	<form method="post"   action="${contextPath}/member/modMember.do" id=updateFrm>
	<h1  class="text_center">회원 정보 수정창</h1>
	<table  align="center" >
	   <tr>
	      <td width="200"><p align="right">아이디</td>
	      <td width="400"><input type="text" name="id" id="id" value="${vo.id }" readonly></td>
	   </tr>
	   <tr>
	      <td width="200"><p align="right">비밀번호</td>
	      <td width="400"><input type="password" name="pw" id="pw" value="${vo.pw }"></td>
	    </tr>
	    <tr>
	      <td width="200"><p align="right">비밀번호 확인</td>
	      <td width="400"><input type="password" name="pw2" id="pw2"></td>
	    </tr>
	    <tr>
	      <td width="200"><p align="right">비밀번호 최종 확인</td>
	      <td><span id="pwCheck"></span></td>
	   </tr>
	    <tr>
	       <td width="200"><p align="right">이름</td>
	       <td width="400"><p><input type="text" name="name" id="name" value="${vo.name }"></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">연락처</td>
	       <td width="400"><p> <input type="tel" id="phone" name="phone" placeholder="010-1234-5678" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" value="${vo.phone}"></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">이메일</td>
	       <td width="400"><p><input type="text" name="email" id="email" value="${vo.email }"></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">수신동의 여부</td>
	       <td width="400"><p><input type="checkbox" name="subscription" id="subscription" value="T" onclick='checkOnlyOne(this)'>동의&nbsp;&nbsp;
	       					  <input type="checkbox" name="subscription" id="subscription" value="F" onclick='checkOnlyOne(this)'>미동의</td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">회사명</td>
	       <td width="400"><p><input type="text" name="companyName" id="companyName" value="${vo.companyName }" readonly>&nbsp;&nbsp;&nbsp;
	       <a onclick="popup()" style=cursor:pointer >회사 수정</a></td>
	    </tr>
	    <tr>
	       <td width="200"><p>&nbsp;</p></td>
	       <td width="400"><input type="submit" value="수정하기"><input type="reset" value="다시입력" id="reset"><input type="button" onclick="history.back()" value="취소"></td>
	    </tr>
	</table>
	</form>
</body>
</html>