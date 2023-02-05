package com.eyelevel.project.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/loginform")
	public void LoginForm() {
		System.out.println("통합 로그인 페이지 입니다.");
	}
}
