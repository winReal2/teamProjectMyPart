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

<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String mName = request.getParameter("mName");
String mPhone = request.getParameter("mPhone");
String email = request.getParameter("email");
String nickname = request.getParameter("nickname");
String age = request.getParameter("age");

System.out.println("id : " + id);
System.out.println("pw : " + pw);

%>

<form id='profileUpdateForm' name='profileUpdate' action='/member/profile' method='post'>
	<c:set var="memberVO" value="${member }"/>
		<table border='1px' width='400px' height='450px'>
		<tr>
			<th>프로필사진</th>
			<td style="height:150px">
				<img alt="" src="">
				<input type="file" name='profileImg' value='사진변경'  >
			</td>
		</tr>
		<tr>
			<th>회원번호</th>
			<td><input type='text' name='m_id' readonly value='${member.m_id }' style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td><input type='text' name='id' readonly value='${member.id }'  style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type='text' name='pw' readonly value='${member.pw }' style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type='text' name='mName' value='${member.getMName() }' style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type='text' name='mPhone' value='${member.getMPhone() }' style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type='text' name='email' value='${member.email }'  style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td><input type='text' name='nickname' value='${member.nickname }' style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>나이</th>
			<td><input type='text' name='age' value='${member.age }'  style="height:100%; width:99%" ></td>
		</tr>
	</table>
	
	<input type="submit" value="확인" class="btn">
	<input type="reset" value="초기화">
</form>

</body>
</html>