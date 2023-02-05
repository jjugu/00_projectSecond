package com.eyelevel.project.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eyelevel.project.category.dto.StudentImpl;
import com.eyelevel.project.category.dto.StudentProfileAndStudentFamilyDTO;
import com.eyelevel.project.category.entity.StudentFamilyAndStudentProfile;
import com.eyelevel.project.category.service.StudentFamilyService;
import com.eyelevel.project.category.service.StudentProfileService;
import com.eyelevel.project.common.paging.Pagenation;
import com.eyelevel.project.common.paging.SelectCriteria;

@Controller
@RequestMapping("/student")
public class StudentProfileController {
	
	private final StudentProfileService studentService;
	private final StudentFamilyService familyService;
	
   private final BCryptPasswordEncoder bCryptPwd;

	@Autowired
	   public StudentProfileController(StudentProfileService studentService, StudentFamilyService familyService, BCryptPasswordEncoder bCryptPwd)

		{
		this.studentService = studentService;
		this.familyService = familyService;
		this.bCryptPwd = bCryptPwd;
		}	
	
	// 학생 개별
	@GetMapping("/{studentNo}")
	public ModelAndView findStudentByNo(ModelAndView mv, @PathVariable Long studentNo) {
	
		StudentProfileAndStudentFamilyDTO student = studentService.findStudentByNo(studentNo);
		List<StudentFamilyAndStudentProfile> familyList = student.getFamilyList();
		
		mv.addObject("student", student);
		mv.addObject("familyList", familyList);
		mv.setViewName("student/one");
		
		return mv;
	}
	
	// 학생 화면 개별
	@GetMapping("/personal")
	public ModelAndView getStudentByNo(ModelAndView mv, @AuthenticationPrincipal StudentImpl studentImpl) {
		
		StudentProfileAndStudentFamilyDTO student2 = studentService.getStudentByNo(studentImpl.getStudentNo());
		List<StudentFamilyAndStudentProfile> familyList = student2.getFamilyList();
		
		mv.addObject("student", student2);
		mv.addObject("familyList", familyList);
		mv.setViewName("student/personal");
		return mv;
	}

	// 학생 등록
	@GetMapping("/regist")
	public void registPage() {}
	@PostMapping("/regist")
	public ModelAndView registPage(ModelAndView mv, StudentProfileAndStudentFamilyDTO newStudent, RedirectAttributes rttr) {
		
		 String encryptPwd = bCryptPwd.encode(newStudent.getStudentPw());
	      newStudent.setStudentPw(encryptPwd);
		
		studentService.registNewStudent(newStudent);
		List<StudentFamilyAndStudentProfile> familyList = newStudent.getFamilyList();
		
		for (StudentFamilyAndStudentProfile family : familyList) {
			System.out.println(family);
			}
		
		mv.addObject("familyList", familyList);
		rttr.addFlashAttribute("registSuccessMessage", "학생 등록 완료");
		
		mv.setViewName("redirect:/student/search");
		
		return mv;
	}
	
	// 학생 [관리자, 선생] 수정
	@GetMapping("/modify")
	public void modifyPage() {}
	@PostMapping("/modify")
	public String modifyPage(RedirectAttributes rttr, @ModelAttribute StudentProfileAndStudentFamilyDTO student) {
		
		studentService.modifyStudent(student);
		
		rttr.addFlashAttribute("modifySuccessMessage", "학생 수정 성공");

		return "redirect:/student/" + student.getStudentNo();
	}	
	
	// 학생 [학생] 수정
	@GetMapping("/modifypersonal")
	public void modifyPersonalPage() {}
	@PostMapping("/modifypersonal")
	public String modifyPersonalPage(RedirectAttributes rttr, @ModelAttribute StudentProfileAndStudentFamilyDTO student) {
		
		String encryptPwd = bCryptPwd.encode(student.getStudentPw());
	      student.setStudentPw(encryptPwd);
		
	      studentService.modifyPersonalStudent(student);
		
		rttr.addFlashAttribute("modifySuccessMessage", "학생 수정 성공");

		return "redirect:/student/personal";
	}	
	
	
	// [관리자, 선생] 학생 검색
	@GetMapping("/search")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = studentService.selectTotalCount(searchCondition, searchValue);

		int limit = 20;

		int buttonAmount = 10;

		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			  if (searchValue instanceof String) {
			    selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
			  }
			} else {
			  selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
			}
		System.out.println(selectCriteria);
		

		List<StudentProfileAndStudentFamilyDTO> studentList = studentService.searchStudentList(selectCriteria);

		for(StudentProfileAndStudentFamilyDTO student : studentList) {
			System.out.println(student);
		}

		mv.addObject("studentList", studentList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("student/search");

		return mv;
	}
}