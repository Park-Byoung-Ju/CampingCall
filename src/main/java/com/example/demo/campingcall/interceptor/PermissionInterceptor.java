package com.example.demo.campingcall.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request
							, HttpServletResponse response
							, Object handler) throws IOException {
		
		// 로그인이 안된 상태에서 로그인 이외에 페이지 접근 금지
		// 로그인 페이지로 이동
		HttpSession session = request.getSession();
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if(userId == null) {
			response.sendRedirect("/main");
			
			return false;
		}
		
		
		return true;
	}
}
