<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.addEventListener('load', function(){
	btnUdate.addEventListener('click', function(e){
		
		e.preventDefault();
		
		let my_m_id = m_id.value;
		let my_id = id.value;
		let my_pw = pw.value;
		let my_name = mName.value;
		let my_nickname = nickname.value
		let my_emailValue  = email.value;
		let my_mage = age.value;
		let my_mPhone = mPhone.value;

		obj = {
				m_id : my_m_id
				,id : my_id
				,pw : my_pw
				,mname : my_name
				,age : my_mage
				,mphone  : my_mPhone
				,email : my_emailValue 
				,nickname : my_nickname
		}
		
		console.log('회원기입 obj : ', obj);
		
		fetchPost('/peco/profile', obj, (map)=>{
			if(map.result == 'success'){
				document.querySelector('#m_id').value = map.m_id;
				
				let formData = new FormData(profileUpdateForm);
				 
				
				console.log("formData : ", formData);
				
				for(var pair of formData.entries()){
					console.log(pair);
					if(typeof(pair[1]) == 'object'){
						let fileName = pair[1].name;
						let fileSize = pair[1].size;
						// 파일 확장자, 크기 체크
						// 서버에 전송 가능한 형식인지 확인
						// 최대 전송가능한 용량을 초과하지 않는지
						if(!checkExtension(fileName,fileSize)){
							return false;
						}
						
						console.log('fileName',pair[1].name);
						console.log('fileSize',pair[1].size);
					
					}
				}
				
				fetch('/peco/ProfileloadActionFetch'
						,{ 
							method : 'post'
							, body : formData
				})
				.then(response=>response.json())
				.then(map => fileuploadRes(map));
			}else{
				updateMsg.innerHTML = map.msg;
			}
	});
	
	})
	
})

// post방식 요청
	function fetchPost(url, obj, callback){
		try{
			// url 요청
			fetch(url
					, {
						method : 'post'
						, headers : {'Content-Type' : 'application/json'}
						, body : JSON.stringify(obj)
					})
				// 요청결과 json문자열을 javascript 객체로 반환
				.then(response => response.json())
				// 콜백함수 실행
				.then(map => callback(map));			
		}catch(e){
			console.log('fetchPost', e);
		}
		
	}
	
//get방식 요청
function fetchGet(url, callback){
	try{
		// url 요청
		fetch(url)
			// 요청결과 json문자열을 javascript 객체로 반환
			.then(response => response.json())
			// 콜백함수 실행
			.then(map => callback(map));			
	}catch(e){
		console.log('fetchGet',e);
	}
}

function checkExtension(fileName, fileSize) {
	let MaxSize = 1024 * 1024 *10;
	// .exe, .sh, .zip, .alz 끝나는 문자열
	// 정규표현식 : 특정 규칙을 가진 문자열을 검색하거나 치환 할때 사용
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	if(MaxSize <= fileSize){
		alert("파일 사이즈 초과");
		return false;
	}
	
	// 문자열에 정규식 패턴을 만족하는 값이 있으면 true, 없으면 ㄹ먀ㅣ
	if(regex.test(fileName)){
		alert("해당 종류의 파일은 업로드 할 수 없습니다");
		return false;
	}
	return true;
}

function fileuploadRes(map){
	if(map.result == 'success'){
		alert(map.msg);
	}else{
		alert(map.msg);
	}
}
</script>
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
<input type="text" id="updateMsg" name="updateMsg">
<form id='profileUpdateForm' name='profileUpdateForm' >
	<c:set var="memberVO" value="${member }"/>
		<table border='1px' width='400px' height='450px'>
		<tr>
			<th>프로필사진</th>
			<td style="height:150px">
				<input type="file" name='files' />
			</td>
		</tr>
		<tr>
			<th>회원번호</th>
			<td><input type='text' name='m_id' id="m_id" readonly value='${member.m_id }' style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td><input type='text' name='id' id="id" readonly value='${member.id }'  style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type='text' name='pw' id="pw" readonly value='${member.pw }' style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type='text' name='mName' id="mName" value='${member.getMName() }' style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type='text' name='mPhone' id="mPhone" value='${member.getMPhone() }' style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type='text' name='email' id="email" value='${member.email }'  style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td><input type='text' name='nickname' id="nickname" value='${member.nickname }' style="height:100%; width:99%"></td>
		</tr>
		<tr>
			<th>나이</th>
			<td><input type='text' name='age' id="age" value='${member.age }'  style="height:100%; width:99%" ></td>
		</tr>
	</table>
	
	<input type="submit" id = "btnUdate" value="확인" class="btn">
	<input type="reset" value="초기화">
</form>

</body>
</html>