package com.example.demo.campingcall.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.campingcall.user.domain.User;
import com.example.demo.campingcall.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestContoller {

	private UserService userService;
	
	public UserRestContoller(UserService userService) {
		this.userService = userService;
	}
	
	// 회원가입
	@PostMapping("/join")
	public boolean join(@RequestParam("loginId") String loginId
						, @RequestParam("password") String password
						, @RequestParam("name") String name
						, @RequestParam("email") String email) {
		
		return userService.join(loginId, password, name, email);
	}
	
	// 로그인
	@PostMapping("/login")
	public boolean login(@RequestParam("loginId") String loginId
						, @RequestParam("password") String password
						, HttpServletRequest request) {
		
		User user = userService.login(loginId, password);
		
		if(user == null) {
			return false;
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			
			return true;
		}

	}
	// 로그인 끝
	
	// 아이디 중복체크
	@GetMapping("/idCheck")
	public boolean idCheck(@RequestParam("loginId") String loginId) {
		
		if(userService.idCheck(loginId) == null) {
			return false;
		}else {
			return true;
		}
	}
	// 아이디 중복체크 끝
	
	// 로그아웃
	@GetMapping("/logout")
	public boolean logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session == null) {
			return false;
		}else {	
			session.removeAttribute("userId");
			session.removeAttribute("userName");
			
			return true;
		}
	}
	// 로그아웃 끝
}
