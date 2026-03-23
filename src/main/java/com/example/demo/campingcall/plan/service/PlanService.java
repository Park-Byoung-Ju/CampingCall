package com.example.demo.campingcall.plan.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.campingcall.plan.domain.Room;
import com.example.demo.campingcall.plan.repository.PlanRoomRepository;

@Service
public class PlanService {

	private PlanRoomRepository planRoomRepository;
	
	public PlanService(PlanRoomRepository planRoomRepository) {
		this.planRoomRepository = planRoomRepository;
	}
	
	//방 만들기
	public Integer CreateRoom(Integer userId,String title, LocalDate startDate, LocalDate endDate) {
		Room room = Room.builder()
						.master(userId)
						.title(title)
						.startDate(startDate)
						.endDate(endDate)
						.build();
		Room result = planRoomRepository.save(room);
		
		return result.getId();
	}
	//방 만들기 끝
}
