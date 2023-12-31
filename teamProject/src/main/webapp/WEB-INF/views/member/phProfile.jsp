<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>사업자 페이지</h1>

<form id='phProfileForm' name='phProfile' action='/member/phProfile_Update' method='post'>

	<c:set var="pensionVO" value="${pension }"/>

	<table border='1px' width='400px' height='300px'>

		<tr>
			<th>펜션번호</th>
			<td>${pension.p_id }</td>
		</tr>
		<tr>
			<th>회원번호</th>
			<td>${pension.m_id }</td>
		</tr>
		<tr>
			<th>펜션명</th>
			<td>${pension.getPName() }</td>
		</tr>
		<tr>
			<th>도로명주소</th>
			<td>${pension.getAddr() }</td>
		</tr>
		<tr>
			<th>운영시간</th>
			<td>${pension.openHour}</td>
		</tr>
		<tr>
			<th>주차여부</th>
			<td>${pension.getParkYN()}</td>
		</tr>
		<tr>
			<th>승인여부</th>
			<td>${pension.getCheckYN()}</td>
		</tr>
	</table>
														
	<input type="submit" value="수정하기" class="btn" ><br><br>
		
	<!-- TODO : 삭제 버튼 클릭시 기존 등록된 업체글 모두 삭제될 수 있도록  처리 매퍼에서 쿼리문 조인으로 작성-->
	<input type="button" value="삭제">
</form>

</body>
</html>