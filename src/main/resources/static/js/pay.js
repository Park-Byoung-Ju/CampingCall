/**
 * 결제 시스템
 */

$(document).ready(function(){
	// 결제
	$(".bookingBtn").on("click",function(){
		let userId = $("#user").data("id");
		let userName = $("#user").data("name");
		let id = $("#campTitle").data("id");
		
		if(buttonCount == 0){
			alert("날짜를 선택하고 확인버튼을 눌러주세요");
			return false;
		}
		
		if(userId.length == 0){
			alert("로그인 이후 사용할수있는 서비스입니다");
			return false;
		}
		
		let campTitle = $("#campTitle").data("value");
		let campPrice = $(this).data("price");
		let campValue = $(this).data("value");
		
		let price = $(this).data("price");
		let count = $(this).data("id");
		
		let title = campTitle + " " + campValue;
		let date = $("#date").val();
		
		if(date.length == 0){
			alert("날짜를 입력해주세요");
			return false;
		}
		
		let today = new Date();
		let hours = today.getHours(); // 시
		let minutes = today.getMinutes();  // 분
		let seconds = today.getSeconds();  // 초
		let milliseconds = today.getMilliseconds();
		
		let merchant_uid = hours + "" + minutes + "" + seconds + "" + milliseconds + "" + userId;
		
		$.ajax({
			type:"get"
			,url:"/camp/pay"
			,data:{					
				"contentId" : id				
				,"userId" : userId				
				,"bookingNumber" : count				
				,"price" : price				
				,"date" : date
			}
			,success:function(response){
				if(response){
					alert("성공");
					location.reload();
					
				}else{
					alert("db저장 실패");
				}
			}
			,error:function(){
				alert("서버 문제");
			}
		});
		
						/*
		var IMP = window.IMP;
		IMP.init("imp12658013");
		
		IMP.request_pay({
		    pg : 'kakaopay.TC0ONETIME',
		    pay_method : 'card',
		    merchant_uid: merchant_uid, // 상점에서 관리하는 주문 번호
		    name : title,
		    amount : campPrice,
		    buyer_name : userName
		}, function(rsp) {
		    if ( rsp.success ) {
				//location.href="/camp/pay?contentId=" + id + "&userId=" + userId + "&bookingNumber=" + count + "&price=" + price + "&date=" + date;
								
				alert("결제성공");
				//location.href="/main";
				
				//location.href="/camp/pay?contentId=" + id + "&userId=" + userId + "&bookingNumber=" + count + "&price=" + price + "&date=" + date;
				
				
				
		    	
		    } else {
		        var msg = '결제에 실패하였습니다.';
		        msg += rsp.error_msg;
		        
		        alert(msg);
		    }
		});
		*/
		
	});
	//결제 끝
});