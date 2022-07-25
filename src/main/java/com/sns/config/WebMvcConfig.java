package com.sns.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.common.FileManagerService;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

	// 웹의 이미지주소와 실제 파일 경로를 매핑해주는 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // 이미지의 웹주소 http://localhost/images/marobiana_164567873651/sun.png
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // 실제 파일이 있는 곳
	}
}