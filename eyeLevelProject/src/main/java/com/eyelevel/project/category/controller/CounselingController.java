package com.eyelevel.project.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.eyelevel.project.category.dto.CounselingDTO;
import com.eyelevel.project.category.service.CounselingService;
import com.eyelevel.project.common.paging.Pagenation;
import com.eyelevel.project.common.paging.SelectCriteria;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/counseling")
public class CounselingController {
	private final CounselingService counselingService;

	@Autowired
	public CounselingController(CounselingService counselingService) {
		this.counselingService = counselingService;
	}	
	
	// 상담 개별
	@GetMapping("/{counselingNo}")
	public ModelAndView findCounselingByNo(ModelAndView mv, @PathVariable int counselingNo) {

		CounselingDTO counseling = counselingService.findCounselingByNo(counselingNo);
		
		mv.addObject("counseling", counseling);
		mv.setViewName("counseling/one");
		
		return mv;
	}
	


	// 상담 검색
	@GetMapping("/search")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = counselingService.selectTotalCount(searchCondition, searchValue);

		int limit = 10;

		int buttonAmount = 5;

		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<CounselingDTO> counselingList = counselingService.searchCounselingList(selectCriteria);

		for(CounselingDTO counseling : counselingList) {
			System.out.println(counseling);
		}

		mv.addObject("counselingList", counselingList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("counseling/search");

		return mv;
	}

	// 상담 등록
	@GetMapping("/regist")
	public void registPage() {}	
	@GetMapping(value="/category", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String findCategoryList(){

		Gson gson = new GsonBuilder()
			      .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
			      .setPrettyPrinting()
			      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
			      .serializeNulls()
			      .disableHtmlEscaping()
			      .create();
	
		return gson.toJson(counselingService.findAllCategory());
	}	
	@PostMapping("/regist")
	public ModelAndView registPage(ModelAndView mv, CounselingDTO newCounseling, RedirectAttributes rttr) {
		
		counselingService.registNewCounseling(newCounseling);
		
		rttr.addFlashAttribute("registSuccessMessage", "상담 등록 완료");
		mv.setViewName("redirect:/");
		
		return mv;
	}
	
	// 상담 수정
	@GetMapping("/modify")
	public void modifyPage() {}
	@PostMapping("/modify")
	public String modifyPage(RedirectAttributes rttr, @ModelAttribute CounselingDTO counseling) {
		
		counselingService.modifyCounseling(counseling);
		
		rttr.addFlashAttribute("modifySuccessMessage", "상담 수정 완료");

		return "redirect:/counseling/" + counseling.getCounselingNo();
	}
	
}