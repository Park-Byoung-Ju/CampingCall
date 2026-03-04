package com.example.demo.campingcall.mypage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.campingcall.camp.domain.CampBooking;

@Repository
public interface MyPageRepository extends JpaRepository<CampBooking, Integer> {
	
	public List<CampBooking> findTop3ByUserIdOrderByDateDesc(@Param("userId") int userId);
	

	public int countByUserIdOrderByDateDesc(@Param("userId") int userId);
	
	
	@Query(value="SELECT * FROM `camping` where `userId` = :userId ORDER BY createdAt DESC LIMIT :start, :end", nativeQuery=true)
	public List<CampBooking> boardList(@Param("start") int start
								,@Param("end") int end
								,@Param("userId") int userId);

			
}
