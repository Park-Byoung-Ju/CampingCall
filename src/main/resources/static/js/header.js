/**
 * header의 js
 */

// 로그아웃
	$("#logout").on("click", function(){
		$.ajax({
			type:"get"
			,url:"/user/logout"
			,success:function(response){
				if(response){
					alert("로그아웃 되었습니다");
					location.reload();
				}else{
					alert("로그인 상태가 아닙니다")
				}
			}
			,error:function(){
				alert("로그아웃 서버 문제로 실패");
			}
		});
	});
// 로그아웃 끝

// location
// 캠핑장

// 캠핑장 끝

// 관광지
// 관광지 끝

// 계획
// 계획 끝

// 게시판
$("#boardLocation").on("click",function(){
	location.href="/board/list-view"
});
// 게시판 끝

// locaion 끝