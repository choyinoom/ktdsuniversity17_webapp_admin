<%@page import="com.ktdsuniversity.admin.manager.vo.ManagerVO"%>
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
<meta charset="UTF-8">
<title>회원 가입창</title>
<style>
  .text_center{
    text-align:center;
  }
  
  .process {
	text-align: left;
	color: #9C9D9D;
	margin-bottom: 2em;	
	}
	
	.table_content{
		justify-content: center;
		display: flex;
		
	}
	
	.table_content td {
   	 border : none;
	}
	
	#memberTable{
		width: 1000px;
	  	margin-top: 60px;
	  	justify-content: center;
		display: flex;
  	}
  
  #button {
		text-decoration: none;
		color: black;
		border: none;
		border-radius: 5px;
		background-color: #ECECEC;
		font-size: 13px;
		font-weight : 800;
		cursor: pointer;
		width: 90px;
		height: 35px;
		padding : 7px 20px;
		margin-left: 10px;
	}
	
	#inputTel{
		width: 15%;
	}
	
	#inputEmail{
		width: 25%;
	}
	
	#tdPw{
		display: flex;
		flex-direction: column;
	}
	
	#registerFrm input[type=submit]{
		margin-left: 20px;
	    width: 90px;
	    height: 35px;
	    border: none;
	    border-radius: 5px;
	    background-color: red;
	    font-size: 15px;
	    cursor: pointer;
	}
	
	#registerFrm input[type=button]{
		margin-left: 20px;
	    width: 90px;
	    height: 35px;
	    border: none;
	    border-radius: 5px;
	    background-color: #969696;
	    color: #EFEFEF;
	    font-size: 15px;
	    cursor: pointer;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
//회사 리스트 팝업창 뛰우기
	function popup(){
	    var url = "${contextPath}/company/popUp.do";
	    var name = "popup test";
	    var option = "width = 500, height = 500, top = 100, left = 200, location = no"
	    window.open(url, name, option);
	    
	}
	
	$(function() {
		$('#id').on('keyup', function(){
			var id = this.value;
			
			if(id.length < 4 ||  id.length >12) {
				$('#idCheck').html('아이디는 4자이상, 12자 이하').css('color','red');
				return;
			} else{
				
				$.ajax({
					type:'post',
					url:'${contextPath}/member/idCheckMember.do',
					data: 'id='+id,
					
					success: function(result){
						if(result==1){
							$('#idCheck').html('아이디 중복').css('color','red');
						}else {
							$('#idCheck').html('적절한 아이디').css('color','blue');
						}
						
					}, error:function(result,request,status,error){
			             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			             
			        }
				})
			}
		})
		
		$('#pw2').on('keyup', function() {
			var pw = document.getElementById('pw').value;
			var pw2 = this.value;
			
			if (pw2.length < 8 || pw2.length > 16) {
				$('#pwCheck').html('비밀번호는 8자 이상, 16자 이하').css('color', 'red');
			} else if (pw != pw2) {
				$('#pwCheck').html('비밀번호가 틀립니다.').css('color','red');
				return;
			} else {
				$('#pwCheck').html('비밀번호 확인').css('color','blue');
			}
			
		})
		
		$('#registerFrm').submit(function() {
			
			var frm = document.memberFrm;
			var id = frm.id.value;
			var pw = frm.pw.value;
			var pw2 = frm.pw2.value;
			var idCheck = document.getElementById("idCheck").innerHTML;
			var pwCheck = document.getElementById("pwCheck").innerHTML;
			var name = document.getElementById("name").value;
			
			// 전화번호 세팅
			var tel1 = frm.tel1.value;
			var tel2 = frm.tel2.value;
			var tel3 = frm.tel3.value;
			var tel = tel1 + '-' + tel2 + '-' + tel3;
			
			if(tel1 != "" && tel2 != "" && tel3 != ""){
				frm.tel.value = tel;
			} else{
				frm.tel.value = "";
			}
			
						
			// 연락처 세팅
			var phone1 = frm.phone1.value;
			var phone2 = frm.phone2.value;
			var phone3 = frm.phone3.value;
			var phone = phone1 + '-' + phone2 + '-' + phone3;
			
			frm.phone.value = phone;
			
			//이메일 세팅
			var emailId = frm.emailId.value;
			var emailAddress = frm.emailAddress.value;
			var email = emailId + '@' + emailAddress;
			
			frm.email.value = email;
			
			
			if (id == "") {
				alert("아이디를 입력해주세요.");
				frm.id.focus();
				return false;
			} else if(idCheck != '적절한 아이디'){
				alert('아이디 중복 확인이 안되었습니다.');
				frm.id.focus();
				return false;
			} else if(pw == "") {
				alert("비밀번호를 입력해주세요.");
				frm.pw.focus();
				return false;
			} else if(pw2 == "") {
				alert('비밀번호 확인을 입력해주세요.');
				frm.pw2.focus();
				return false;
			} else if(pwCheck != '비밀번호 확인'){
				alert('비밀번호 확인이 되지 않았습니다.');
				return false;
			} else if(name == "") {
				alert("이름을 입력하세요.");
				return false;
			} else if(emailId == "") {
				alert("이메일 아이디를 입력하세요.");
				return false;
			} else if(emailAddress == ""){
				alert('이메일 주소를 입력해주세요.');
				frm.emailAddress.focus();
				return false;
			} else if (phone1 == "" || phone2 =="" || phone3 == "") {
				alert('연락처를 기입하지 않았습니다.');
				if (phone1 == "") {
					frm.phone1.focus();
					return false;
				} else if (phone2 == "") {
					frm.phone2.focus();
					return false;
				} else if (phone3 == "") {
					frm.phone3.focus();
					return false;
				}
			} else if (tel1 != "" || tel2 != "" || tel3 != "") {
				if (tel1 == "" || tel2 == "" || tel3 == "") {
					alert("전화번호 입력을 정확히 해주세요.");
					if (tel1 == "") {
						frm.tel1.focus();
						return false;
					} else if (tel2 == "") {
						frm.tel2.focus();
						return false;
					} else if (tel3 == "") {
						frm.tel3.focus();
						return false;
					}
				}
			}
			
			return true;
		})
		
		//이메일 주소 선택
		$("select[name='emailAddresList']").on('change', function() {
			
			selectedAddress = $("select[name='emailAddresList'] option:selected").val();
			if (selectedAddress != "") {
				$("input[name='emailAddress']").attr('readonly', true);
			} else{
				$("input[name='emailAddress']").attr('readonly', false);
			}
			var frm = document.memberFrm;
			frm.emailAddress.value = selectedAddress;
			
		})

	})
	
	// 체크 하마나 가능
	function checkOnlyOne(element) {
		const checkboxes = document.getElementsByName("subscription");
		checkboxes.forEach((cb) => {
			  cb.checked = false;
			})
		element.checked = true;
	}
	
</script>
</head>
<body>
	<div class='process' >
		<h5>
			<span onclick="location.href='${contextPath}/member/listMembers.do'"
			style="cursor: pointer;">회원관리</span> > <span
			onclick="location.href='${contextPath}/member/listMembers.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'"
			style="cursor: pointer;"> 학생관리</span> > <span
			onclick="location.href='${contextPath}/member/memberForm.do?page=${page}&searchText=${searchText }&searchType=${searchType }&perPage=${perPage }'"
			style="cursor:pointer;"> 학생 등록</span> 
		</h5>
	</div>
	
	<div style="width: 90%;">
		<h1  class="text_center">학생 등록</h1>
	</div>
	
	<div class="table_content">
		<form method="post"   action="${contextPath}/member/addMember.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}" id=registerFrm name="memberFrm">
		<table  align="center" id="memberTable" >
		   <tr>
		      <td width="20%" style="padding-right: 3%;"><p align="left">아이디</td>
		      <td width="80%" align="left" ><input minlength="4" maxlength="12" type="text" name="id" id="id" pattern="[0-9a-zA-z]+">
		      <span id="idCheck"></span></td> 
		   </tr>
		   <tr>
		      <td style="padding-right: 3%;"><p align="left">비밀번호</td>
		      <td align="left" id="tdPw"><input type="password" name="pw" id="pw" pattern="^(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}$">
		      <span style="color: #4D4D4D;  margin-top: 10px; font-size: 12px;">8~16자리의 영문, 숫자를 조합하여 입력해주세요.</span></td>
		    </tr>
		    <tr>
		      <td style="padding-right: 3%;"><p align="left">비밀번호 확인</td>
		      <td align="left" ><input type="password" name="pw2" id="pw2" pattern="^(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}$"><span id="pwCheck"></span></td>
		    </tr>
		    <tr>
		       <td style="padding-right: 3%;"><p align="left">이름</td>
		       <td align="left" ><p><input type="text" name="name" id="name"></td>
		    </tr>
		    <tr>
		       <td style="padding-right: 3%;"><p align="left">전화번호</td>
		       <td align="left"><p>
		       <select name="tel1" id="inputTel" class="tel1">
                        <option value="" selected="selected">선택</option>
                        <option value="02">02</option>
                        <option value="031">031</option>
                        <option value="032">032</option>
                        <option value="033">033</option>
                        <option value="041">041</option>
                        <option value="042">042</option>
                        <option value="043">043</option>
                        <option value="051">051</option>
                        <option value="052">052</option>
                        <option value="053">053</option>
                        <option value="054">054</option>
                        <option value="055">055</option>
                        <option value="061">061</option>
                        <option value="062">062</option>
                        <option value="063">063</option>
                        <option value="064">064</option>
                  </select>-
                  <input id="inputTel" type="tel" name="tel2" minlength='3' maxlength='4' pattern="[0-9]+" >- 
                  <input id="inputTel" type="tel" name="tel3" minlength='4' maxlength='4' pattern="[0-9]+" >
                  <input type="hidden" name="tel"></td>
		    </tr>
		    <tr>
		       <td style="padding-right: 3%;"><p align="left">연락처</td>
		       <td align="left"><p>
		       <select name="phone1" id="inputTel" class="tel2">
                        <option value="" selected="selected">선택</option>
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="016">016</option>
                        <option value="018">018</option>
                        <option value="017">017</option>
                        <option value="019">019</option>
                  </select>- 
                  <input id="inputTel" type="tel" name="phone2" minlength='3' maxlength='4' pattern="[0-9]+">- 
                  <input id="inputTel" type="tel" name="phone3" minlength='4' maxlength='4' pattern="[0-9]+">
                  <input type="hidden" name="phone"></td>
		    </tr>
		    <tr>
		       <td style="padding-right: 3%;"><p align="left">이메일</td>
		       <td align="left"><p>
		       <input type="text" name="emailId" id="inputEmail">@
		       <input type="text" name="emailAddress" id="inputEmail">
		       <select name="emailAddresList">
		       	<option value="">직접입력</option>
		       	<option value="naver.com">naver.com</option>
		       	<option value="hanmail.net">hanmail.net</option>
		       	<option value="gmail.com">gmail.com</option>
		       	<option value="kakao.com">kakao.com</option>
		       	<option value="nate.com">nate.com</option>
		       	<option value="yahoo.co.kr">yahoo.co.kr</option>
		       </select>
		       <input type="hidden" name="email" ></td>
		    </tr>
		    <tr>
		       <td style="padding-right: 3%;"><p align="left">수신동의 여부</td>
		       <td align="left"><p><input type="checkbox" name="subscription" id="subscription" value="T" onclick='checkOnlyOne(this)'>동의&nbsp;&nbsp;
		       					  <input type="checkbox" name="subscription" id="subscription" value="F" onclick='checkOnlyOne(this)' checked>미동의</td>
		    </tr>
		    <tr>
		       <td style="padding-right: 3%;"><p align="left">회사명</td>
		       <td align="left"><p><input type="text" name="companyName" id="companyName" placeholder="검색을 이용해주세요." style="padding-left: 15px;" readonly><a onclick="popup()" id="button">검색</a></td>
		    </tr>
		</table>
		<div class="text_center" style="width: 85%; margin-top: 30px;">
			<input type="submit" id="enrollButton" value="등록">&nbsp;<input type="button" onclick="history.back()" value="취소">
		</div>
		</form>
	</div>
</body>


