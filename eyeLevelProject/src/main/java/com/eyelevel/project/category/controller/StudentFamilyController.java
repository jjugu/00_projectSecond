package com.eyelevel.project.category.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eyelevel.project.category.dto.StudentFamilyAndStudentProfileDTO;
import com.eyelevel.project.category.dto.StudentFamilyDTO;
import com.eyelevel.project.category.dto.StudentProfileAndStudentFamilyDTO;
import com.eyelevel.project.category.entity.StudentFamilyAndStudentProfile;
import com.eyelevel.project.category.service.StudentFamilyService;
import com.eyelevel.project.category.service.StudentProfileService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/family")
public class StudentFamilyController {
	private final StudentFamilyService familyService;
	private final StudentProfileService studentService;
	
	@Autowired
	public StudentFamilyController(StudentFamilyService familyService, StudentProfileService studentService) {
		this.familyService = familyService;
		this.studentService = studentService;
	}	
	
	// 가족 개별
	@GetMapping("/{familyNo}")
	public ModelAndView findFamilyByNo(ModelAndView mv, @PathVariable Long familyNo) {

		StudentFamilyAndStudentProfileDTO family = familyService.findFamilyByNo(familyNo);
		
		mv.addObject("family", family);
		mv.setViewName("family/one");
		
		return mv;
	}
	
	// 가족 전체
	@GetMapping("/list")
	public ModelAndView findFamilyList(ModelAndView mv) {

		List<StudentFamilyAndStudentProfileDTO> familyList = familyService.findFamilyList();
		
		mv.addObject("familyList", familyList);
		mv.setViewName("family/list");
		
		return mv;
	}
	
	@Transactional
	public void save(StudentFamilyAndStudentProfile studentFamily) {
	}

	// 가족 등록
	@GetMapping("/regist")
	public void registPage() {}
	@PostMapping("/regist")
	public ModelAndView registPage(ModelAndView mv, StudentFamilyAndStudentProfileDTO newFamily, RedirectAttributes rttr) {
		
		familyService.registNewFamily(newFamily);
		
		rttr.addFlashAttribute("registSuccessMessage", "가족 등록 완료");
		mv.setViewName("redirect:/student/search");
		
		return mv;
	}
	
	// 가족 수정
	@GetMapping("/modify")
	public void modifyPage() {}
	@PostMapping("/modify")
	public String modifyPage(RedirectAttributes rttr, @ModelAttribute StudentFamilyAndStudentProfileDTO family) {
		
		familyService.modifyFamily(family);
		
		rttr.addFlashAttribute("modifySuccessMessage", "가족 수정 완료");

		return "redirect:/student/personal";
	}
	
}