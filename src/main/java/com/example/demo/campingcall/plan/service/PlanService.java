package com.example.demo.campingcall.plan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.campingcall.plan.domain.Participant;
import com.example.demo.campingcall.plan.domain.Room;
import com.example.demo.campingcall.plan.repository.PlanParticipantRepository;
import com.example.demo.campingcall.plan.repository.PlanRoomRepository;

@Service
public class PlanService {

	private PlanRoomRepository planRoomRepository;
	
	private PlanParticipantRepository planParticipantRepository;
	
	public PlanService(PlanRoomRepository planRoomRepository
					,PlanParticipantRepository planParticipantRepository) {
		this.planRoomRepository = planRoomRepository;
		this.planParticipantRepository = planParticipantRepository;
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
		
		Participant particitant = Participant.builder()
											.roomId(result.getId())
											.userId(userId)
											.build();
		
		Participant part = planParticipantRepository.save(particitant);
		
		return result.getId();
	}
	//방 만들기 끝
	
	// 사용자가 소속되어 있는 방 목록 가져오기
	public List<Room> getRoomList(int userId) {
		
		List<Participant> participantList = planParticipantRepository.findByUserId(userId);
		List<Room> roomList = new ArrayList<>();
		
		if(participantList == null || participantList.size() <= 0) {
			return null;
		}else {
			for(int i = 0; i < participantList.size(); i++) {
				Optional<Room> optionalRoom = planRoomRepository.findById(participantList.get(i).getRoomId());
				Room room = optionalRoom.orElse(null);
				
				if(room == null) {
					return null;
				}
				
				roomList.add(room);
			}
		}
		
		
		
		return roomList;
	}
	//
}
