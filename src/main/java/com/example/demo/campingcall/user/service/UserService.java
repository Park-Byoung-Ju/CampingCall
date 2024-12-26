package com.example.demo.campingcall.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.campingcall.common.SaltEncoder;
import com.example.demo.campingcall.user.domain.User;
import com.example.demo.campingcall.user.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// 회원가입
	public boolean join(String loginId
					, String password
					, String name
					, String email) {
		
		String passwordEncodingData = SaltEncoder.saltEncoding(password);
		
		User user = User.builder()
						.loginId(loginId)
						.password(passwordEncodingData)
						.name(name)
						.email(email)
						.build();
		
		try {
			userRepository.save(user);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	// 회원가입 끝
	
	// 로그인
	public User login(String loginId, String password) {
		String passwordEncodingData = SaltEncoder.saltEncoding(password);
		
		User user = userRepository.findByLoginIdAndPassword(loginId, passwordEncodingData);
		
		return user;
	}
	// 로그인 끝
	
	// 아이디 중복체크
	public User idCheck(String loginId) {
		
		return userRepository.findByLoginId(loginId);
	}
	// 아이디 중복체크 끝
}
