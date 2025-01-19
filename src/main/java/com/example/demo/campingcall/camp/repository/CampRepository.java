package com.example.demo.campingcall.camp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.campingcall.camp.domain.CampBooking;

@Repository
public interface CampRepository extends JpaRepository<CampBooking,Integer>{

	public List<CampBooking> findByContentIdAndDateOrderByBookingNumber(@Param("contentId") int contentId
																		,@Param("date") LocalDate date);
}
