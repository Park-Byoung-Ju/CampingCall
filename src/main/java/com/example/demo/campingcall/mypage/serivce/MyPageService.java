package com.example.demo.campingcall.mypage.serivce;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.campingcall.camp.domain.CampBooking;
import com.example.demo.campingcall.mypage.repository.MyPageRepository;

@Service
public class MyPageService {
	private MyPageRepository myPageRepository;
	
	public MyPageService(MyPageRepository myPageRepository) {
		this.myPageRepository = myPageRepository;
	}
	
	public CampBooking getBooking(int id) {
		Optional<CampBooking> getCampBooking = myPageRepository.findById(id);
		CampBooking result = getCampBooking.orElse(null);
		
		return result;
	}

}
