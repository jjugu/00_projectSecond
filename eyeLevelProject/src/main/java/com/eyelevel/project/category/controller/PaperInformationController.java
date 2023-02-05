package com.eyelevel.project.category.controller;

import java.awt.print.Pageable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eyelevel.project.category.dto.PaperInformationDTO;
import com.eyelevel.project.category.service.PaperInformationService;
import com.eyelevel.project.common.paging.Pagenation;
import com.eyelevel.project.common.paging.SelectCriteria;




@Controller
@RequestMapping("/paper")
public class PaperInformationController {
	
	private final PaperInformationService paperService;
	
	@Autowired
	public PaperInformationController(PaperInformationService paperService) {
		this.paperService = paperService;
	}	

	
	// 학생 한명 조회
	@GetMapping("/{paperNo}")
	public ModelAndView findpaperByNo(ModelAndView mv, @PathVariable int paperNo) {

		PaperInformationDTO paper = paperService.findPaperByNo(paperNo);
		
		mv.addObject("paper", paper);
		mv.setViewName("paper/one");
		
		return mv;
	}
	
	// 학생 전체 조회
	@GetMapping("/list")
	public ModelAndView findPaperList(ModelAndView mv) {

		List<PaperInformationDTO> paperList = paperService.findPaperList();
		
		mv.addObject("paperList", paperList);
		mv.setViewName("paper/list");
		
		return mv;
	}

//	// 학생 검색 기능
	@GetMapping("/search")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = paperService.selectTotalCount(searchCondition, searchValue);

		int limit = 20;

		int buttonAmount = 10;

		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			if(searchValue instanceof String) {selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		}
		}else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<PaperInformationDTO> paperList = paperService.searchPaperList(selectCriteria);

		for(PaperInformationDTO paper : paperList) {
			System.out.println(paper);
		}

		mv.addObject("paperList", paperList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("paper/search");

		return mv;
	}
	
	@GetMapping("/modify")
	public void modifyPage() {}
		
	}