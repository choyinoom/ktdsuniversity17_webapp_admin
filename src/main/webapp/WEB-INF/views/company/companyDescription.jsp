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
<title>회사 상세창</title>
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
	width: 40%;
}

#t1 {
	float: left;
	margin-left: 4em;
	width: 40%;
	padding-left: 7px;
}

#address {
	float: left;
	margin-left: 4em;
	width: 85%;
	padding-left: 7px;
}

.td1 {
	border-bottom: none;
}

#company_mod {
	width: 1150px;
	border-spacing: 22px;
	height: 350px;
	margin: auto;
	margin-top: 70px;
}

.buttonZip {
	margin-top: 70px;
}

#rd {
	padding-left: 52px
}

#mod {
	background-color: #E91B23;
	color: #EFEFEF;
	cursor: pointer;
}
</style>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	/* jquery로 상세창은 처음 들어왔을 땐 읽기 전용으로 만들고
	 수정을 클릭하면 읽기 전용에서 수정이 가능하도록 만들기 */

	/* 카운트 변수 선언*/
	var count = 0;

	$(document).ready(function() {
		$('input').prop('readonly', true);
		$('radio').prop('disabled', true);
		$('#sel1').prop('disabled', true);
		$('#sel2').prop('disabled', true);
		$("input[id=radio1]:radio").attr("disabled", "true");
		$("input[id=radio2]:radio").attr("disabled", "true");
		$('input').css('border', 'none');
	});
	
	/* 수정 메소드 */
	function modify() {
		count++;
		if (count == 1) {
			$('input').prop('readonly', false);
			$('radio').prop('disabled', false);
			$('#sel1').prop('disabled', false);
			$('#sel2').prop('disabled', false);
			$("input[id=radio1]:radio").attr("disabled", "false");
			$("input[id=radio1]:radio").removeAttr("disabled");
			$("input[id=radio2]:radio").attr("disabled", "false");
			$("input[id=radio2]:radio").removeAttr("disabled");
			$("#mod").text("저장");
			$('input').css('border', "solid 1px");
		} else {
			alert("저장되었습니다.");
			document.companyForm.submit();
		}
	};
	
	function del() {
		if(confirm("정말 삭제하시겠습니까?") == true) {
			location.href='${contextPath}/company/removeCompany.do?id=${companyVO.id }';
		} else {
			return false;
		}
	}
	
	/* 취소 메소드 */
	function cancel(){
		$('input').prop('readonly', true);
		$('radio').prop('disabled', true);
		$('#sel1').prop('disabled', true);
		$('#sel2').prop('disabled', true);
		$("input[id=radio1]:radio").attr("disabled", "true");
		$("input[id=radio2]:radio").attr("disabled", "true");
		$('input').css('border', 'none');
		$("#mod").text("수정");
		if(confirm("정말 수정을 취소하시겠습니까?") == true){
			history.back(-1);	
		} else {
			$('input').prop('readonly', false);
			$('radio').prop('disabled', false);
			$('#sel1').prop('disabled', false);
			$('#sel2').prop('disabled', false);
			$("input[id=radio1]:radio").attr("disabled", "false");
			$("input[id=radio1]:radio").removeAttr("disabled");
			$("input[id=radio2]:radio").attr("disabled", "false");
			$("input[id=radio2]:radio").removeAttr("disabled");
			$("#mod").text("저장");
			$('input').css('border', "solid 1px");
			return false;
		}
	}
</script>
<body>
	<div class="process">
		<h4>회원관리>회사관리>회사수정</h4>
	</div>
	<h1 class="title">회사 관리</h1>
	<form method="post" name="companyForm"
		action="${contextPath}/company/modCompany.do">
		<table id="company_mod">
			<tr>
				<td width="10%" class="td1"><p align="right">상태</p></td>
				<td width="20%" class="td1"><select name="contractStat" id=sel1>
						<option value="">상태를 선택하세요</option>
						<option value="협력사"
							<c:if test="${companyVO.contractStat eq '협력사' }"> selected</c:if>>협력사</option>
						<option value="비협력사"
							<c:if test="${companyVO.contractStat eq '비협력사' }"> selected</c:if>>비협력사</option>
						<option value="협약 진행중"
							<c:if test="${companyVO.contractStat eq '협약 진행중' }"> selected</c:if>>협약 진행중</option>
				</select>
				<td width="10%" class="td1"><p align="right">회사명</p></td>
				<td width="20%" class="td1"><input type="text" name="name"
					id=t1 value="${companyVO.name}"></td>
			</tr>

			<tr>
				<td width="10%" class="td1"><p align="right">대표번호</p></td>
				<td width="20%" class="td1"><input type="text"
					name="companyTel" id=t1 value="${companyVO.companyTel}"></td>
				<td width="10%" class="td1"><p align="right">홈페이지</p></td>
				<td width="20%" class="td1"><input type="text" name="homePage"
					id=t1 value="${companyVO.homePage }"></td>
			</tr>

			<tr>
				<td width="10%" class="td1"><p align="right">주소</p></td>
				<td width="20%" class="td1"><input type="text" name="address"
					id=address value="${companyVO.address }"></td>
				<td width="10%" class="td1"><p align="right">사업자등록번호</p></td>
				<td width="20%" class="td1"><input type="text" name="id" id=t1
					value="${companyVO.id }"></td>
			</tr>

			<tr>
				<td width="10%" class="td1"><p align="right">담당자</p></td>
				<td width="20%" class="td1"><input type="text"
					name="contractName" id=t1 value="${companyVO.contractName }"></td>
				<td width="10%" class="td1"><p align="right">담당자전화번호</p></td>
				<td width="20%" class="td1"><input type="text"
					name="managerPhone" id=t1 value="${companyVO.managerPhone }"></td>
			</tr>

			<tr>
				<td width="10%" class="td1"><p align="right">담당자이메일</p></td>
				<td width="20%" class="td1"><input type="text"
					name="managerEmail" id=t1 value="${companyVO.managerEmail }"></td>
				<td width="10%" class="td1"><p align="right">협약업체 동의 구분</p></td>
				<td width="20%" class="td1" align="left"><p id="rd">
						<input type="radio" name="contractAgree" value="컨소시엄 협약"
							id="radio1"
							<c:if test="${companyVO.contractAgree eq '컨소시엄 협약' }">checked</c:if>>
						컨소시엄 협약 <input type="radio" name="contractAgree" value="컨소시엄 비협약"
							id="radio2"
							<c:if test="${companyVO.contractAgree eq '컨소시엄 비협약' }">checked</c:if>>
						컨소시엄 비협약</td>
			</tr>

			<tr>
				<td width="10%" class="td1"><p align="right">협약 상태 구분</p></td>
				<td width="20%" class="td1"><select name="contractType" id=sel2>
						<option value="">상태를 선택하세요</option>
						<option value="협약서 없음"
							<c:if test="${companyVO.contractType eq '협약서 없음' }"> selected</c:if>>협약서
							없음</option>
						<option value="상호 변경"
							<c:if test="${companyVO.contractType eq '상호 변경' }"> selected</c:if>>상호
							변경</option>
						<option value="협약 완료"
							<c:if test="${companyVO.contractType eq '협약 완료' }">selected</c:if>>협약
							완료</option>
						<option value="협약서 사본"
							<c:if test="${companyVO.contractType eq '협약서 사본' }"> selected</c:if>>협약서
							사본</option>
						<option value="탈퇴"
							<c:if test="${companyVO.contractType eq '탈퇴' }"> selected</c:if>>탈퇴</option>
				</select></td>
				<td width="10%" class="td1"><p align="right">수정일</p></td>
				<td width="20%" class="td1"><input type="text" readOnly
					name="modDate" id=t1 value="${companyVO.modDate }"></td>
			</tr>
		</table>

		<!-- 수정, 삭제 버튼 만들기 -->
		<div class=buttonZip>
			<button type="button" onclick="modify()" style="width: 5%;" id="mod">수정</button>
			<button type="button" onclick="del()" style="width: 5%;">삭제</button>
			<button type="button" onclick="cancel()" style="width: 5%;">취소</button>
		</div>
	</form>
</body>
</html>