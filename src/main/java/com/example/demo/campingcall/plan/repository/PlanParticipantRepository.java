package com.example.demo.campingcall.plan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.campingcall.plan.domain.Participant;

@Repository
public interface PlanParticipantRepository extends JpaRepository<Participant, Integer>{

	public List<Participant> findByUserId(@Param("userId") int userId);
}
