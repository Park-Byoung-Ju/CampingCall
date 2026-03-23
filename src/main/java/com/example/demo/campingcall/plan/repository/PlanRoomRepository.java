package com.example.demo.campingcall.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.campingcall.camp.domain.CampBooking;
import com.example.demo.campingcall.plan.domain.Room;

@Repository
public interface PlanRoomRepository extends JpaRepository<Room, Integer>{

}
