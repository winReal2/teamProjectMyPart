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
<table border="1">
	<tr>
		<td>아이디</td>
		<td>${memberVO.id }</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>${memberVO.pw }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${memberVO.mName }</td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td>${memberVO.nickname }</td>
	</tr>
	<tr>
		<td>나이</td>
		<td>${memberVO.age }</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${memberVO.email }</td>
	</tr>
</table>
<input type="button" value="수정하기" class="btn" onclick="location.href='/MyPage_Update'">


</body>
</html>