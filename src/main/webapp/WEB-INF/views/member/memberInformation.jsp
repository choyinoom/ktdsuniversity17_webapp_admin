	<%@page import="com.ktdsuniversity.admin.manager.vo.ManagerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="tel" value="${fn:split(vo.tel, '-')}"/>
<c:set var="phone" value="${fn:split(vo.phone, '-')}"/>
<c:set var="email" value="${fn:split(vo.email, '@')}"/>

<%
  request.setCharacterEncoding("UTF-8");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
   .text_center{
     text-align:center;
   }
   
   	.table_content td {
   	 border : none;
	}
   
   .process {
	text-align: left;
	color: #9C9D9D;
	margin-bottom: 2em;
	}
	
	.table_content{
		justify-content: center;
		display: inline-block;
		
	}
	
   #memberTable{
		width: 1000px;
	  	margin-top: 60px;
	  	justify-content: center;
		display: flex;
  	}
  	
  	#inputTel{
		width: 15%;
	}
	
	#inputEmail{
		width: 25%;
	}
   
	select:disabled{
		background: #ECECEC;
		color: black;
		opacity: 1;
		cursor: default;
	}

   
   
   
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="//cdn.jsdelivr.net/npm/promise-polyfill@8/dist/polyfill.js"></script>
<script>
	function checkInfo() {
		var memberId = '${vo.id}';
		
		Swal.fire({ 
			title: '${vo.id} 개인 정보 조회',
			text: "조회시 기록이 저장됩니다.",
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes',
			cancelButtonText: 'No',
			focusConfirm: false,
			input: 'select',
			  inputOptions: {
			    '사유': {
			      정보수정 : '정보 수정',
			      정보조회 : '정보 조회',
			      기타 : '기타'
			    }
			},
			//inputPlaceholder: '사유를 선택해주세요',
			inputValidator: (value) => {
				console.log(value)
				return new Promise((resolve) => {
					if( value == '기타') {
							Swal.fire({
								title: '사유 작성',
								html: '<input type="text" id="reason" class="swal2-input" placeholder="사유를 작성하시오." style="width: 250px;">',
								showCancelButton: true,
								focusConfirm: false,
								showConfirmButton: true,
								confirmButtonColor: '#3085d6',
								confirmButtonText: 'Yes',
								cancelButtonColor: '#d33',
								cancelButtonText: 'No',
								preConfirm: () => {
									const reason = Swal.getPopup().querySelector('#reason').value
									if (!reason) {
								      Swal.showValidationMessage(`사유를 작성해주세요`)
								    }
									return { reason: reason}
									}
							}).then((result) => {
								if(result.isConfirmed) {
									$.ajax({
										type: 'post',
										url : '${contextPath}/member/insertLogByInformationInquiry.do',
										data: { id : memberId,
												reason : result.value.reason},
										
										success: function(data) {
											Swal.fire( 
													'${vo.id} 조회 완료',
													'',
												)
										}, error:function(error){
											alert('잠시 후 시도해주세요.');
										}
									}) //ajax
									
									$('input[name=id]').attr('value', '${vo.id}');
									$('input[name=name]').attr('value', '${vo.name}');
									$('input[name=phone2]').attr('value', '${phone[1]}');
									$('input[name=phone3]').attr('value', '${phone[2]}');
									$('input[name=tel2]').attr('value', '${tel[1]}');
									$('input[name=tel3]').attr('value', '${tel[2]}');
									$('input[name=emailId]').attr('value', '${email[0]}');
									$('#inquiryInfo').attr('disabled', true);
									$('#enrollButton').removeAttr('onclick');
									$('#enrollButton').attr('onclick', "updateFormMember('T')");
								} else {
									Swal.fire("취소되었습니다.")
								}
						})	
					} else {
						resolve()
					}
				})//promise
			}
				
			}).then(function(result){
				if(result.isConfirmed) {
					$.ajax({
						type: 'post',
						url : '${contextPath}/member/insertLogByInformationInquiry.do',
						data: { id : memberId,
								reason : result.value},
						
						success: function(data) {
							Swal.fire( 
									'${vo.id} 조회 완료',
									'',
								)
						}, error:function(error){
							alert('잠시 후 시도해주세요.');
						}
					}) //ajax
					
					$('input[name=id]').attr('value', '${vo.id}');
					$('input[name=name]').attr('value', '${vo.name}');
					$('input[name=phone2]').attr('value', '${phone[1]}');
					$('input[name=phone3]').attr('value', '${phone[2]}');
					$('input[name=tel2]').attr('value', '${tel[1]}');
					$('input[name=tel3]').attr('value', '${tel[2]}');
					$('input[name=emailId]').attr('value', '${email[0]}');
					$('#inquiryInfo').attr('disabled', true);
					$('#enrollButton').removeAttr('onclick');
					$('#enrollButton').attr('onclick', "updateFormMember('T')");
				} else {
					Swal.fire("취소되었습니다.")
				}
		}) //then

		
		
		/* if (confirm('정보 조회 시 관리자 정보 기록이 저장됩니다. 동의하십니까?') == true) {
			
			alert('정보 조회되었습니다.');
			
			$.ajax({
				type: 'post',
				url : '${contextPath}/member/insertLogByInformationInquiry.do',
				data: { id : memberId},
				
				success: function(result) {
					alert('기록이 되었습니다.');
					
				}, error:function(error){
					alert('잠시 후 시도해주세요.');
				}
			}) //ajax
			
			$('input[name=id]').attr('value', '${vo.id}');
			$('input[name=name]').attr('value', '${vo.name}');
			$('input[name=phone]').attr('value', '${vo.phone}');
			$('input[name=tel]').attr('value', '${vo.tel}');
			$('input[name=email]').attr('value', '${vo.email}');
			$('#inquiryInfo').attr('disabled', true);
		} else {
			alert('취소되었습니다.');
			return false;
		} //if(confirm) */
		
	} //checkInfo
	
	function removeMember() {
		if (confirm('탈퇴시키겠습니까?') == true) {
			window.location.href = '${contextPath}/member/removeMember.do?id=${vo.id}&page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}';
		} else{
			return;
		}
	} //removeMember
	
	function updateFormMember(stat) {
		btn_stat = document.getElementById('enrollButton').disabled;
		if (stat == 'F') {
			alert('정보 조회 하신 이후에 수정 창으로 이동 가능합니다.');
			document.getElementById('inquiryInfo').focus();
		} else {
			window.location.href = '${contextPath}/member/modMemberForm.do?id=${vo.id }&page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}';			
		}
	}
</script>
</head>
<body>
<%
	ManagerVO manager = (ManagerVO)session.getAttribute("manager");
	Boolean isLogOn = (Boolean)session.getAttribute("isLogOn");
	
	if (manager != null && isLogOn == true) {
		
	
%>
	<div class='process' >
		<h5>
			<span onclick="location.href='${contextPath}/member/listMembers.do'"
			style="cursor: pointer;">회원관리</span> > <span
			onclick="location.href='${contextPath}/member/listMembers.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'"
			style="cursor: pointer;"> 학생관리</span> > <span
			onclick="location.href='${contextPath}/member/informationMemberForm.do?id=${vo.id }&page=${page}&searchText=${searchText }&searchType=${searchType }&perPage=${perPage }'"
			style="cursor:pointer;"> 학생정보</span> 
		</h5>
	</div>
	
	<div style="width: 90%;">
		<h1  class="text_center">회원 정보</h1>
	</div>
	
	<div class="table_content">
		<table  align="center" id="memberTable">
		   <tr>
		      <td width="20%" ><p align="left">아이디</td>
		      <td width="80%" align="left" ><input type="text" name="id" value="${fn:substring(vo.id, 0, fn:length(vo.id)-3)}***" readonly><button onclick="checkInfo()" id="inquiryInfo">정보 조회</button></td>
		   </tr>
		   <tr>
		      <td s><p align="right"><p align="left">비밀번호</td>
		      <td align="left" ><input type="text" name="pw" value="${fn:replace(vo.pw, vo.pw, '********')}" readonly></td>
		    </tr>
		    <tr>
		      <td ><p align="right"><p align="left">비밀번호 확인</td>
		      <td align="left" ><input type="text" value="${fn:replace(vo.pw, vo.pw, '********') }" readonly></td>
		    </tr>
		    <tr>
		       <td ><p align="right"><p align="left">이름</td>
		       <td align="left" ><input type="text" name="name" value="${fn:substring(vo.name, 0, fn:length(vo.name) - 1)}*" readonly></td>
		    </tr>
		    <tr>
		       <td style="padding-right: 3%;"><p align="left">전화번호</td>
		       <td align="left"><p>
		       <c:choose>
		       	<c:when test="${empty vo.tel }">
	       			<select name="tel1" id="inputTel" class="tel1" disabled>
                        <option value="" selected="selected" >선택</option>
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
                  <input id="inputTel" type="tel" name="tel2" minlength='3' maxlength='4' pattern="[0-9]+" readonly>- 
                  <input id="inputTel" type="tel" name="tel3" minlength='4' maxlength='4' pattern="[0-9]+" readonly>
	       		</c:when>
	       		<c:otherwise>
	       			<select name="tel1" id="inputTel" class="tel1" disabled>
                        <option value="" selected="selected">선택</option>
                        <option value="02" <c:if test="${tel[0] eq '02' }">selected</c:if>>02</option>
                        <option value="031" <c:if test="${tel[0] eq '031' }">selected</c:if>>031</option>
                        <option value="032" <c:if test="${tel[0] eq '032' }">selected</c:if>>032</option>
                        <option value="033" <c:if test="${tel[0] eq '033' }">selected</c:if>>033</option>
                        <option value="041" <c:if test="${tel[0] eq '041' }">selected</c:if>>041</option>
                        <option value="042" <c:if test="${tel[0] eq '042' }">selected</c:if>>042</option>
                        <option value="043" <c:if test="${tel[0] eq '043' }">selected</c:if>>043</option>
                        <option value="051" <c:if test="${tel[0] eq '051' }">selected</c:if>>051</option>
                        <option value="052" <c:if test="${tel[0] eq '052' }">selected</c:if>>052</option>
                        <option value="053" <c:if test="${tel[0] eq '053' }">selected</c:if>>053</option>
                        <option value="054" <c:if test="${tel[0] eq '054' }">selected</c:if>>054</option>
                        <option value="055" <c:if test="${tel[0] eq '055' }">selected</c:if>>055</option>
                        <option value="061" <c:if test="${tel[0] eq '061' }">selected</c:if>>061</option>
                        <option value="062" <c:if test="${tel[0] eq '062' }">selected</c:if>>062</option>
                        <option value="063" <c:if test="${tel[0] eq '063' }">selected</c:if>>063</option>
                        <option value="064" <c:if test="${tel[0] eq '064' }">selected</c:if>>064</option>
                  </select>-
                  <input id="inputTel" type="tel" name="tel2" minlength='3' maxlength='4' pattern="[0-9]+" value="${fn:substring(tel[1], 0, 2)}**" readonly>- 
                  <input id="inputTel" type="tel" name="tel3" minlength='4' maxlength='4' pattern="[0-9]+" value="${fn:substring(tel[2], 0, 2)}**" readonly>
                  <input type="hidden" name="tel">
	       		</c:otherwise>
		       </c:choose></td>
		       
		    </tr>
		    <tr>
		       <td ><p align="left">연락처</td>
		       <td align="left"><p>
		       <c:choose>
		       	<c:when test="${empty vo.phone }">
					<select name="phone1" id="inputTel" class="tel2">
                        <option value="" selected="selected" disabled>선택</option>
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="016">016</option>
                        <option value="018">018</option>
                        <option value="017">017</option>
                        <option value="019">019</option>
	                  </select>- 
	                  <input id="inputTel" type="tel" name="phone2" minlength='3' maxlength='4' pattern="[0-9]+" readonly>- 
	                  <input id="inputTel" type="tel" name="phone3" minlength='4' maxlength='4' pattern="[0-9]+" readonly>
		       	</c:when>
		       	<c:otherwise>
		       		<select name="phone1" id="inputTel" class="tel2" disabled>
                        <option value="" selected="selected">선택</option>
                        <option value="010" <c:if test="${phone[0] eq '010' }">selected</c:if>>010</option>
                        <option value="011" <c:if test="${phone[0] eq '011' }">selected</c:if>>011</option>
                        <option value="016" <c:if test="${phone[0] eq '016' }">selected</c:if>>016</option>
                        <option value="018" <c:if test="${phone[0] eq '018' }">selected</c:if>>018</option>
                        <option value="017" <c:if test="${phone[0] eq '017' }">selected</c:if>>017</option>
                        <option value="019" <c:if test="${phone[0] eq '019' }">selected</c:if>>019</option>
	                  </select>- 
	                  <input id="inputTel" type="tel" name="phone2" minlength='3' maxlength='4' pattern="[0-9]+" value="${fn:substring(phone[1], 0, 2)}**" readonly>- 
	                  <input id="inputTel" type="tel" name="phone3" minlength='4' maxlength='4' pattern="[0-9]+" value="${fn:substring(phone[2], 0, 2)}**" readonly>
 		       	</c:otherwise> 
 		       </c:choose>
		       </td>
		    </tr>
		    <tr>
			       <td ><p align="left">이메일</td>
			       <td align="left"><p>
			       	<c:choose>
			       		<c:when test="${empty vo.email }">
			       			<input type="text" name="emailId" id="inputEmail" readonly>@
					       <input type="text" name="emailAddress" id="inputEmail" readonly>
			       		</c:when>
			       		<c:otherwise>
			       			<input type="text" name="emailId" id="inputEmail" value="${fn:replace(email[0], fn:substring(email[0], 0, 3), '***')}" readonly>@
					       <input type="text" name="emailAddress" id="inputEmail" value="${email[1]}" readonly>
			       		</c:otherwise>
			       	</c:choose>
			       </td>
			    </tr>
		    <tr>
		       <td ><p align="left"><p align="left">수신동의 여부</td>
		       <td align="left" >
		       <c:choose>
			       	<c:when test="${vo.subscription == 'T' }">
			       		<input type="text" value="동의" readonly>
			       	</c:when>
			       	<c:when test="${vo.subscription == 'F' }">
			       		<input type="text" value="미동의" readonly>
			       	</c:when>
			       	<c:otherwise>
			       		<input type="text" value="" readonly>
			       	</c:otherwise>
		       </c:choose>
		       </td>
		    </tr>
		    <tr>
		       <td ><p align="left"><p align="left">회사명</td>
		       <td align="left" ><input type="text" value="${vo.companyName }" readonly></td> 
		    </tr>
		</table>
		<div class="text_center" style="width: 85%; margin-top: 3em; margin-bottom: 4em;">
		    <button id="enrollButton" onclick="updateFormMember('F')">수정</button>
		    <button onclick="removeMember()">탈퇴</button>
		    <button onclick="history.back()" >목록</button>
	    </div>
    </div>
	<%} else {  %>
	<script>
		window.location.href="${contextPath}";
	</script>
	<% } %>
</body>
</html>