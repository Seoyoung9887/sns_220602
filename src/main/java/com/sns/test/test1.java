package com.sns.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test1 {
	
	@ResponseBody
	@RequestMapping("/test/1")
	public String test() {
		return"Hello world";
	}
	@ResponseBody
	@RequestMapping("/test/2")
	public Map<String,Object> test2(){
		Map<String,Object> map = new HashMap<>();
		map.put("qweq", 1234);
		return map;
	}
	@RequestMapping("/test/3")
	public String test3() {
		return"test/test";
	}
	

}
