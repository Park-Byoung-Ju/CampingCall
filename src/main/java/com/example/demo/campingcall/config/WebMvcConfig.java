package com.example.demo.campingcall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.campingcall.common.FileManager;
import com.example.demo.campingcall.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		FileManager fileManger = new FileManager();
		
		registry.addResourceHandler("/images/**") // 이미지와 url path를 연결하는 과정
		.addResourceLocations("file:///" + fileManger.FILE_UPLOAD_PATH + "/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new PermissionInterceptor())
											.addPathPatterns("/plan/**");
	}

}
