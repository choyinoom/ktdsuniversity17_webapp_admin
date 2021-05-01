<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회사 등록창</title>
<style>
.title {
	padding-bottom: 30px;
	border-bottom: 0.3px solid;
	border-color: #9C9D9D;
}

input:focus {
	outline: none;
}

.process {
	text-align: left;
	padding-bottom: 50px;
	color: #9C9D9D;
}

#sel1, #sel2 {
	float: left;
	margin-left: 14%;
	width: 40%;
}

#t1, #phoneNum {
	float: left;
	margin-left: 4em;
	width: 40%;
	padding-left: 7px;
}

#zipCode {
	float: left;
	margin-left: 4em;
	width: 10%;
	padding-left: 7px;
}

#search {
	float: left;
	width: 47px;
}

#address {
	float: left;
	margin-left: 4em;
	padding-left: 7px;
	width: 40%;
}

#address2 {
	float: left;
	padding-left: 7px;
	width: 40%;
}

.td1 {
	border-bottom: none;
}

.td2 {
	border-bottom: none;
}

#company_add {
	width: 1150px;
	border-spacing: 22px;
	height: 350px;
	margin: auto;
	margin-top: 70px;
}

#rd {
	padding-left: 52px
}

#add {
	background-color: #E91B23;
	color: #EFEFEF;
	cursor: pointer;
}

.buttonZip {
	margin-top: 70px;
}

#num, #id {
	width: 15%;
	padding-left: 7px;
}

.oNum {
	text-align: left;
	padding-left: 14%;
}
</style>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	/* 주소 찾기 기능 사용하는 메소드 */
		function openZipSearch() {
			new daum.Postcode({
				oncomplete : function(data) {

					var roadAddr = data.roadAddress; // 도로명 주소 변수
					var jibunAddr = data.jibunAddress; // 지번 주소 변수

					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					document.getElementById('zipCode').value = data.zonecode;
					if (roadAddr !== '') {
						document.getElementById("address").value = roadAddr;
					} else if (jibunAddr !== '') {
						document.getElementById("address").value = jibunAddr;
					}
				}
			}).open();
		}

		/* 취소 메소드 */
		function cancel() {
			if (confirm("정말 등록을 취소하시겠습니까?") == true) {
				history.back(-1);
			} else {
				return false;
			}
		}

		/* 등록할 때 텍스트박스 값 합쳐서 db에 저장할 메소드 */
		$(function(){
				$('#registerCheck').submit(function() {
					var registercheckfrm = document.addFrm;
					
					var tel1 = registercheckfrm.companyTel1.value;
					var tel2 = registercheckfrm.companyTel2.value;
					var tel3 = registercheckfrm.companyTel3.value;
					var companyTel = tel1 + '-' + tel2 + '-' + tel3;
					
					var id1 = registercheckfrm.id1.value;
					var id2 = registercheckfrm.id2.value;
					var id3 = registercheckfrm.id3.value;
					
					var mPhone1 = registercheckfrm.managerPhone1.value;
					var mPhone2 = registercheckfrm.managerPhone2.value;
					var mPhone3 = registercheckfrm.managerPhone3.value;
					var managerPhone = mPhone1 + '-' + mPhone2 + '-' + mPhone3;
					
					var address1 = registercheckfrm.address1.value;
					var address2 = registercheckfrm.address2.value;
					
					registercheckfrm.companyTel.value = companyTel;
					registercheckfrm.managerPhone.value = managerPhone;
					
					var page = $(this).val();
					var perPage = $(this).val();
					var searchType = document.getElementById('searchType').value;
					var searchText = document.getElementById('searchText').value;
					
					if (confirm("등록하시겠습니까?") == true){
						return true;
					} else {
						return false;
					}
				})
		})
		//아이디 중복체크
		function fn_idChk() {
			var registercheckfrm = document.addFrm;
			var id1 = registercheckfrm.id1.value;
			var id2 = registercheckfrm.id2.value;
			var id3 = registercheckfrm.id3.value;
			var id = id1 + '-' + id2 + '-' + id3;
			if (id1.length < 3 || id2.length < 2 || id3.length < 5) {
				alert("사업자 등록번호를 입력해주세요.");
			} else {
				$.ajax({
					url : "${contextPath}/company/idCheck.do",
					type : "post",
					dataType : "json",
					data : {
						id : id
					},
					success : function(data) {
						if (data == 1) {
							alert("이미 등록된 사업자 등록번호입니다.");
						} else if (data == 0) {
							$("#idChk").attr("value", "Y");
							alert("등록 가능한 사업자 등록번호입니다.");
						}
					}

				})
			}
		}

</script>

<body>
	<div class="process">
		<h4>
			<span onclick="location.href='${contextPath}/member/listMembers.do'"
				style="cursor: pointer;">회원관리</span> > <span
				onclick="location.href='${contextPath}/company/listCompanies.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'"
				style="cursor: pointer;"> 회사관리</span> > <span
				onclick="location.href='${contextPath}/company/addCompanyForm.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}'"
				style="cursor: pointer;"> 회사등록</span>
		</h4>
	</div>
	<h1 class="title">회사 등록</h1>
	<form method="post" action="${contextPath}/company/addCompany.do?page=${page}&searchText=${searchText}&searchType=${searchType}&perPage=${perPage}"
		id=registerCheck name=addFrm>
		
		<input type="hidden" id="page" name="page" value="${criteria.page }"  />
		<input type="hidden" id="searchType" name="searchType" value="${criteria.searchType }"  /> 
		<input type="hidden" id="searchText" name="searchText" value="${criteria.searchText }"  />
		<input type="hidden" id="perPageNum" name="perPageNum" value="${criteria.perPageNum }" />
		
		<table id="company_add">
			<tr>
				<td width="10%" class="td1"><p align="right">상태</p></td>
				<td width="20%" class="td1"><select name="contractStat"
					id="sel1" required>
						<option value="">상태를 선택하세요</option>
						<option value="협력사">협력사</option>
						<option value="비협력사">비협력사</option>
						<option value="협약 진행중">협약 진행중</option>
				</select>
				<td width="10%" class="td1"><p align="right">회사명</p></td>
				<td width="20%" class="td1">
				<input type="text" name="name" id=t1 required></td>
			</tr>

			<tr>
				<td width="10%" class="td1"><p align="right">대표번호</p></td>
				<td width="10%" class="td2">
					<div class="oNum">
						<input type="text" id="num" name="companyTel1" maxLength="3" pattern="\d{2,3}" required>- 
						<input type="text" id="num" name="companyTel2" maxLength="4" pattern="\d{3,4}" required>-
						<input type="text" id="num" name="companyTel3" maxLength="4" pattern="\d{4}" required> 
						<input type="hidden" name="companyTel">
					</div>
				</td>
				<td width="10%" class="td1"><p align="right">홈페이지</p></td>
				<td width="20%" class="td1"><input type="text" name="homePage" id=t1 required pattern="^(http:\/\/|https:\/\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$" title="예) www.naver.com"></td>
			</tr>

			<tr>
				<td width="10%" class="td1" rowspan="2"><p align="right">주소</p></td>
				<td width="20%" class="td1" colspan="3">
				<input type="text" id=zipCode name="zipCode" placeholder="우편번호" required>
					<button type="button" id=search onclick="openZipSearch()">검색</button>
				</td>
			</tr>

			<tr>
				<td width="20%" class="td1">
				<input type="text" id=address name="address1" placeholder="주소" required>
				<input type="text" id=address2 name="address2" placeholder="상세 주소" required>
				<input type="hidden" name="address"> </td>
			</tr>

			<tr>
				<td width="10%" class="td1"><p align="right">사업자등록번호</p></td>
				<td width="10%" class="td2">
					<div class="oNum">
						<input type="text" id="id" name="id1" maxLength="3" pattern="\d{3}" required>- 
						<input type="text" id="id" name="id2" maxLength="2" pattern="\d{2}" required>- 
						<input type="text" id="id" name="id3" maxLength="5" pattern="\d{5}" required> 
						<input type="hidden" name="id" id="userId">
						<button class="idChk" type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
					</div>
				</td>
				<td width="10%" class="td1"><p align="right">담당자</p></td>
				<td width="20%" class="td1"><input type="text"
					name="contractName" id=t1 required pattern="^[가-힣]{2,5}"></td>
			</tr>

			<tr>
				<td width="10%" class="td1"><p align="right">담당자전화번호</p></td>
				<td width="20%" class="td2">
					<div class="oNum">
						<input type="text" id="num" name="managerPhone1" maxLength="3"
							pattern="(010)" required>- <input type="text" id="num"
							name="managerPhone2" maxLength="4" pattern="\d{3,4}" required>-
						<input type="text" id="num" name="managerPhone3" maxLength="4"
							pattern="\d{4}" required> <input type="hidden"
							name="managerPhone">
					</div>
				</td>
				<td width="10%" class="td1"><p align="right">담당자이메일</p></td>
				<td width="20%" class="td1"><input type="text"
					name="managerEmail" id=t1 required placeholder="예) xxxx@naver.com"
					onfocus="this.placeholder=''"
					onblur="this.placeholder='예) xxxx@naver.com'"
					pattern="^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$"
					title="예) xxxx@naver.com"></td>
			</tr>
			<tr>
				<td width="10%" class="td1"><p align="right">협약업체 동의 구분</p></td>
				<td width="20%" class="td1" align="left"><p id=rd>
						<input type="radio" name="contractAgree" value="컨소시엄 협약"
							id="radio1" required> 컨소시엄 협약 <input type="radio"
							name="contractAgree" value="컨소시엄 비협약" id="radio2"> 컨소시엄
						비협약</td>
				<td width="10%" class="td1"><p align="right">협약 상태 구분</p></td>
				<td width="20%" class="td1"><select name="contractType"
					id="sel2" required>
						<option value="">상태를 선택하세요</option>
						<option value="협약서 없음">협약서없음</option>
						<option value="상호 변경">상호 변경</option>
						<option value="협약 완료">협약 완료</option>
						<option value="협약서 사본">협약서 사본</option>
				</select></td>
			</tr>
		</table>

		<!-- 등록, 취소 버튼 만들기 -->
		<div class=buttonZip>
			<input type="submit" style="width: 5%;" value="등록" id="add">
			<button type="button" onclick="cancel()" style="width: 5%;">취소</button>
		</div>
	</form>

</body>
</html>