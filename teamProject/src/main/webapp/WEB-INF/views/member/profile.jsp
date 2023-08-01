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
<h2>마이페이지</h2>


<form id='profileForm' name='profile' action='/member/profile_Update' method='post'>
	<c:set var="memberVO" value="${member }"/>

	<table border='1px' width='400px' height='300px'>

		<tr>
			<th>회원번호</th>
			<td>${member.m_id }</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${member.id }</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${member.pw }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${member.getMName()}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${member.getMPhone()}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.email}</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>${member.nickname}</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${member.age}</td>
		</tr>
	</table>
														
	<input type="submit" value="수정하기" class="btn" ><br><br>
</form>
	<button id="P&H"  onclick="location.href='/member/phProfile.jsp'">내 업체 관리</button>

</body>
</html>