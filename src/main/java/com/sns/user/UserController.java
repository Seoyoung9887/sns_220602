package com.sns.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 화면
@RequestMapping("/user")
@Controller
public class UserController {
		
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "user/sign_up");
		return"template/layout";
	}
	@PostMapping("/sign_up_for_submit")
	public String sifnUpFotSubmit(
			@RequestParam("loginId")String loginId,
			@RequestParam("password")String password,
			@RequestParam("name")String name,
			@RequestParam("email")String email) {
		//DB insert
		
		return "redirect:/user/sign_in_view";
	}
	@RequestMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/sign_in");
		return "template/layout";
	}
	
	@RequestMapping("/sign_out")
	public String signOut(HttpSession session) {
	
		//로그아웃- 세션에 있는 키-값들을 모두 지운다
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		//리다이렉트
		//로그인 화면으로 
		return "redirect:/timeline/timeline_view";
	}
}
