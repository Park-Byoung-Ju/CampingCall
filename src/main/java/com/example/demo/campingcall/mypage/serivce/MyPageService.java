package com.example.demo.campingcall.mypage.serivce;

import java.util.List;
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
	
	public List<CampBooking> getMainBookingList(int userId){
		List<CampBooking> result = myPageRepository.findTop3ByUserIdOrderByDateDesc(userId);
		
		return result;
	}
	
	public List<CampBooking> getBookingList(int userId, int start, int end){
		List<CampBooking> bookingList = myPageRepository.boardList(start, end, userId);
		
		return bookingList;
	}
	
	public int getCountUserId(int userId) {
		return myPageRepository.countByUserIdOrderByDateDesc(userId);
	}

}
