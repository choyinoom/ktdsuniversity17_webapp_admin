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
	margin-left: 4em;
	width:40%;
}

#t1, #phoneNum {
	float: left;
	margin-left: 4em;
	width:40%;
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
</script>

<body>
	<div class="process"><h4>회원관리>회사관리>회사등록</h4></div>
	<h1 class="title">회사 등록</h1>
	<form method="post" action="${contextPath}/company/addCompany.do"
		id=registerCheck name=addFrm>
		<table id="company_add">
			<tr>
				<td width="10%" class="td1"><p align="right">상태</p></td>
				<td width="20%" class="td1"><select name="contractStat" id="sel1" required>
						<option value="">상태를 선택하세요</option>
						<option value="협력사">협력사</option>
						<option value="비협력사">비협력사</option>
						<option value="협약 진행중">협약 진행중</option>
				</select>
				<td width="10%" class="td1"><p align="right">회사명</p></td>
				<td width="20%" class="td1"><input type="text" name="name"
					id=t1 required></td>
			</tr>

			<tr>
				<td width="10%" class="td1"><p align="right">대표번호</p></td>
				<td width="20%" class="td1"><input type="text"
					name="companyTel" required id=t1 placeholder="예) 000-000-0000"
					onfocus="this.placeholder=''"
					onblur="this.placeholder='예) 000-000-0000'"
					pattern="\d{2,3}-\d{3,4}-\d{4}" title="예) 000-000-0000"></td>
				<td width="10%" class="td1"><p align="right">홈페이지</p></td>
				<td width="20%" class="td1"><input type="text" name="homePage"
					id=t1 required></td>
			</tr>

			<tr>
				<td width="10%" class="td1" rowspan="2"><p align="right">주소</p></td>
				<td width="20%" class="td1" colspan="3">
					<input type="text" id=zipCode name="zip" placeholder="우편번호" readonly>
					<button type="button" id=search onclick="openZipSearch()">검색</button>
				</td>
			</tr>
			
			<tr>
				<td width="20%" class="td1"><input type="text" id=address name="address" placeholder="주소" readonly> 
					<input type="text" id=address2 name="address" placeholder="상세 주소" required></td>
			</tr>

			<tr>
				<td width="10%" class="td1"><p align="right">사업자등록번호</p></td>
				<td width="20%" class="td1"><input type="text" name="id" id=t1
					required placeholder="예) 000-00-00000"
					onfocus="this.placeholder=''"
					onblur="this.placeholder='예) 000-00-00000'"
					pattern="\d{3}-\d{2}-\d{5}" title="예) 000-00-00000"></td>
				<td width="10%" class="td1"><p align="right">담당자</p></td>
				<td width="20%" class="td1"><input type="text"
					name="contractName" id=t1 required pattern="^[가-힣]{2,5}"></td>
			</tr>
			
			<tr>
				<td width="10%" class="td1"><p align="right">담당자전화번호</p></td>
				<td width="20%" class="td1"><input type="text"
					name="managerPhone" required id="phoneNum"
					placeholder="예) 010-0000-0000" onfocus="this.placeholder=''"
					onblur="this.placeholder='예) 010-0000-0000'"
					pattern="(010)-\d{3,4}-\d{4}" title="예) 010-0000-0000"></td>
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
							name="contractAgree" value="컨소시엄 비협약" id="radio2"> 컨소시엄 비협약</td>
				<td width="10%" class="td1"><p align="right">협약 상태 구분</p></td>
				<td width="20%" class="td1"><select name="contractType" id="sel2"
					required>
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
			<input type="submit" value="등록" style="width: 5%;" id="add">
			<button type="button" onclick="history.back()" style="width: 5%;">취소</button>
		</div>
	</form>
</body>
</html>