package com.eyelevel.project.category.controller;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eyelevel.project.category.dto.BusinessLogDTO;
import com.eyelevel.project.category.dto.StudentProfileAndStudentFamilyDTO;
import com.eyelevel.project.category.entity.StudentFamilyAndStudentProfile;
import com.eyelevel.project.category.service.BusinessLogService;
import com.eyelevel.project.common.paging.Pagenation;
import com.eyelevel.project.common.paging.SelectCriteria;






@Controller
@RequestMapping("/business")
public class BusinessLogController {
	
	private final BusinessLogService businessService;
	
	@Autowired
	public BusinessLogController(BusinessLogService businessService) {
		this.businessService = businessService;
	}	

	
	// 학생 한명 조회
	@GetMapping("/{businessDate}")
	public ModelAndView findbusinessByDate(ModelAndView mv, @PathVariable Date businessDate) {

		BusinessLogDTO business = businessService.findBusinessByDate(businessDate);
		
		mv.addObject("business", business);
		mv.setViewName("/one");
		
		return mv;
	}
	
	// 학생 전체 조회
	@GetMapping("/list")
	public ModelAndView findbusinessList(ModelAndView mv) {

		List<BusinessLogDTO> businessList = businessService.findBusinessList();
		
		mv.addObject("businessList", businessList);
		mv.setViewName("business/list");
		
		return mv;
	}

	// 학생 검색 기능
	@GetMapping("/search")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = businessService.selectTotalCount(searchCondition, searchValue);

		int limit = 10;

		int buttonAmount = 5;

		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<BusinessLogDTO> businessList = businessService.searchBusinessList(selectCriteria);

		for(BusinessLogDTO business : businessList) {
			System.out.println(business);
		}

		mv.addObject("businessList", businessList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("business/search");

		return mv;
	}
	
	@GetMapping("/regist")
	public void registPage() {}
	
	
}