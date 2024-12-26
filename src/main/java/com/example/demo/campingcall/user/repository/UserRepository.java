package com.example.demo.campingcall.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.campingcall.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByLoginIdAndPassword(@Param("userId") String userId
									, @Param("password") String password);
	
	public User findByLoginId(@Param("loginId") String loginId);
}
