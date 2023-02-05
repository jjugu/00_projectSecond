package com.eyelevel.project.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eyelevel.project.category.dto.AnswerAndTeacherProfileDTO;
import com.eyelevel.project.category.dto.AnswerDTO;
import com.eyelevel.project.category.dto.BtnDTO;
import com.eyelevel.project.category.dto.QuestionAndStudentProfileDTO;
import com.eyelevel.project.category.dto.QuestionDTO;
import com.eyelevel.project.category.dto.StudentImpl;
import com.eyelevel.project.category.dto.TeacherImpl;
import com.eyelevel.project.category.service.QuestionService;
import com.eyelevel.project.common.paging.Pagenation;
import com.eyelevel.project.common.paging.SelectCriteria;

@Controller
@RequestMapping("/question")
public class QuestionController {
	
	private final QuestionService questionService;
	
	@Autowired
	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}

	/* [접속 : 관리자, 교사, 학생] 질문게시판 검색 및 페이징 */
	@GetMapping("/searchList")
	public ModelAndView searchcategoryQuestion(HttpServletRequest request, ModelAndView mv) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		/*  ↑ currentPage에 디폴트로 1을 주면 
		   main 에서 searchList 로 넘어올 때 항상 1페이지로 되어있다.*/
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		/* ↓ 검색던지는 parameter 이름*/
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		/* ↓ 검색 했을때 결과 */

		int totalCount = questionService.selectTotalCount(searchCondition, searchValue);

		int limit = 10;
		/* ↑ 글을 가져올 때 처음 글을 정하고 
		   몇개를 가져올 것인가를 정한다 10개로 지정함 ! */
		
		/* ↓  버튼갯수 */
		int buttonAmount = 5;

		SelectCriteria selectCriteria = null;	// SelectCriteria 자료형을 가진 객체 생성 및 초기화
		if(searchValue != null && !"".equals(searchValue)) {	// searchValue가 null 값 과 공백이 아닐경우
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<QuestionAndStudentProfileDTO> questionList = questionService.searchQuestionList(selectCriteria);

		mv.addObject("questionList", questionList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("question/searchList");

		return mv;
	}
	
	/* [접속 : 관리자, 교사, 학생] 질문게시판 상세페이지 */
	@GetMapping("/detail/{questionNo}")					/* ↓ @PathVariable을 사용해 questionNo로 경로위치를 변경한다 */
	public ModelAndView findQuestionByNo(ModelAndView mv, @PathVariable Long questionNo, BtnDTO btn) {
						/*↑ find+찾고자 하는 클래스+By+무엇으로 찾을지(type) */
		QuestionAndStudentProfileDTO question = questionService.findQuestionList(questionNo);
		List<AnswerAndTeacherProfileDTO> answerList = questionService.findAnswerTeacherInfo(questionNo);
		
		mv.addObject("btn", btn);
		mv.addObject("question", question);
		mv.addObject("answerList", answerList);
		mv.setViewName("question/detail");
		
		return mv;
	}
	
	/* [접속 : 학생] 질문게시판 글쓰기 */
	@GetMapping("/regist")	// 화면 이동을 위하여 GetMapping 사용
	public void registQuestion() {}
	
	/* [접속 : 학생] 질문게시판 글쓰기 */
	@PostMapping("/regist")	// 저장!
	public ModelAndView registQuestion(ModelAndView mv, QuestionDTO newQuestion, RedirectAttributes rttr, @AuthenticationPrincipal StudentImpl student) {

		questionService.registNewQuestion(newQuestion, student);

		rttr.addFlashAttribute("successMessage", "게시글 등록에 성공하셨습니다");
		mv.setViewName("redirect:/question/searchList");
		
		return mv;
	}
	
	/* [접속 : 관리자, 교사] 게시글 삭제 및 답변여부 / 게시글 */
	@PostMapping("/contentModifyAndDelete/{questionNo}")
	public String modifyAndDeleteContent(RedirectAttributes rttr, @PathVariable Long questionNo, @ModelAttribute BtnDTO btn) {

		if(btn.getBtn().equals("답변미완")) {
			/* 답변완료 처리하는 경우 */
			questionService.modifyQuestion(questionNo);
			rttr.addFlashAttribute("completeSuccessMessage", "답변완료 처리가 되었습니다.");
			
			return "redirect:/question/detail/" + questionNo;
		} else {
			/* 게시글을 삭제하는 경우 */
			questionService.deleteQuestion(questionNo);
			rttr.addFlashAttribute("deleteSuccessMessage", "삭제가 완료되었습니다.");
			
			return "redirect:/question/searchList";
		}
	}
	
	/* [접속 : 선생님] 등록 / 댓글 */
	@PostMapping("/answerRegist/{questionNo}")																/*  @AuthenticationPrincipal 선언하여 TeacherImpl에서 정보를 받아온다*/
	public String registAnswer(RedirectAttributes rttr, AnswerDTO newAnswer, @PathVariable Long questionNo, @AuthenticationPrincipal TeacherImpl teacher) {
		
		questionService.registAnswer(newAnswer, questionNo, teacher);
		rttr.addFlashAttribute("successMessage", "댓글 등록에 성공하였습니다.");
		
		return "redirect:/question/detail/" + questionNo;
	}
	
	/* [접속 : 관리자, 교사] 수정 및 삭제(삭제는 교사 본인만 가능) / 댓글 */
	@PostMapping("/answerDelete/{questionNo}/{answerNo}")
	public String deleteAnswer(RedirectAttributes rttr, @ModelAttribute AnswerDTO answer, @PathVariable Long questionNo, @PathVariable Long answerNo, BtnDTO btn) {

		if(btn.getBtn().equals("저장")) {
			/* 댓글 수정 */
			questionService.modifyAnswer(answer, answerNo);
			rttr.addFlashAttribute("modifySuccessMessage", "댓글 수정이 완료되었습니다.");
			
			return "redirect:/question/detail/" + questionNo;
		} else {
			/* 댓글 삭제 */
			questionService.deleteAnswer(answerNo);
			rttr.addFlashAttribute("deleteSuccessMessage", "댓글 삭제가 완료되었습니다.");
			
			return "redirect:/question/detail/" + questionNo;
		}
	}
}