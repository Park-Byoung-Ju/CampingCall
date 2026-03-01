package com.example.demo.campingcall.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.campingcall.user.domain.User;

public interface MyPageRepository extends JpaRepository<User, Integer> {

}
