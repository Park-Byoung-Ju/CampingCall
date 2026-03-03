package com.example.demo.campingcall.mypage.domin;

import com.example.demo.campingcall.camp.domain.Camp;
import com.example.demo.campingcall.camp.domain.CampBooking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking {

	CampBooking campBooking;
	
	Camp camp;
}
