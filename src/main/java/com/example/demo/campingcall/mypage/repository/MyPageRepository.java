package com.example.demo.campingcall.mypage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.campingcall.camp.domain.CampBooking;

@Repository
public interface MyPageRepository extends JpaRepository<CampBooking, Integer> {
	
}
