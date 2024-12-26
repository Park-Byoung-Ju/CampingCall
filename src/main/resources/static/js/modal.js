/**
 *  모달 제어 + 회원가입 + 로그인 + 로그아웃
 */
	
$(document).ready(function(){
	
	// 중복체크 했을때 아이디를 추가 입력할때
	$("#id").on("input",function(){
		$("#joinBtn").attr("disabled", true);
	});
	
	// 아이디 중복체크
	$("#idCheck").on("click",function(){
		let loginId = $("#id").val();
		
		if(loginId.length == 0){
			alert();
			return false;
		}
		
		$.ajax({
			type:"get"
			,url:"/user/idCheck"
			,data:{"loginId" : loginId}
			,success:function(response){
				if(!response){
					alert("사용가능한 아이디입니다");
					$("#joinBtn").attr("disabled", false);
				}else{
					alert("중복된 아이디입니다");
				}
			}
			,error:function(){
				alert("중복검사 서버 실패");
			}
		});
		
	});
	
	// 회원가입 버튼 눌렀을때 실행
	$("#joinBtn").on("click", function(){
		let id = $("#id").val();
		let password = $("#password").val();
		let passwordCheck = $("#passwordCheck").val();
		let name = $("#name").val();
		let email = $("#email").val();
		let domain = $("#domain").val();
		
		if(id.length == 0){
			alert("아이디를 입력해주세요");
			return false;
		}
		
		if(password.length == 0){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		
		if(passwordCheck.length == 0){
			alert("비밀번호 확인을 입력해주세요");
			return false;
		}
		
		if(name.length == 0){
			alert("이름을 입력해주세요");
			return false;
		}
		
		if(email.length == 0){
			alert("이메일을 입력해주세요");
			return false;
		}
		
		if(password != passwordCheck){
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다");
			return false;
		}
				
		let emailString = email + "@" + domain;

		$.ajax({
			type:"post"
			,url:"/user/join"
			,data:{
				"loginId" : id
				,"password" : password
				,"name" : name
				,"email" : emailString
			}
			,success:function(response){
				if(response){
					alert("회원가입이 완료되었습니다");
					location.reload();					
				}else{
					alert("회원가입 실패");
				}			
			}
			,error:function(){
				alert("서버 문제로 회원가입 실패");
			}
		});
	});
	
	// 로그인 버튼 눌렀을때 실행
	$("#loginBtn").on("click", function(){
		let loginId = $("#loginId").val();
		let loginPassword = $("#loginPassword").val();
		
		if(loginId.length == 0){
			alert("아이디를 입력해주세요");
			return false;
		}
		
		if(loginPassword.length == 0){
			alert("비밀번호를 입력해주세요");
		}
		
		$.ajax({
			type:"post"
			,url:"/user/login"
			,data:{
				"loginId" : loginId
				,"password" : loginPassword
			}
			,success:function(response){
				if(response){
					alert("로그인이 완료되었습니다");
					location.reload();
				}else{
					alert("로그인 실패");
				}
			}
			,error:function(){
				alert("서버 문제로 로그인 실패");
			}
		});
	});
	
	// 로그인 눌렀을때 실행
	$("#login").on("click", function(){
		$("#login-modal").fadeIn();
		$("#join-modal").fadeOut();
	});
	
	// 모달에서 회원가입 눌렀을때 실행
	$("#join").on("click",function(){
		$("#join-modal").fadeIn();
		$("#login-modal").fadeOut();
	});
	
	// 모달에서 닫기 눌렀을때 실행
	$(".modal-close").on("click",function(){
		$("#login-modal").fadeOut();
		$("#join-modal").fadeOut();
	});
});
	