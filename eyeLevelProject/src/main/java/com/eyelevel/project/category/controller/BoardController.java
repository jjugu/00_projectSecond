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

import com.eyelevel.project.category.dto.BoardAndReplyAndTeacherProfileDTO;
import com.eyelevel.project.category.dto.BtnDTO;
import com.eyelevel.project.category.dto.CategoryAndBoardInfoDTO;
import com.eyelevel.project.category.dto.CategoryDTO;
import com.eyelevel.project.category.dto.ReplyAndTeacherProfileDTO;
import com.eyelevel.project.category.dto.TeacherImpl;
import com.eyelevel.project.category.service.BoardService;
import com.eyelevel.project.common.paging.Pagenation;
import com.eyelevel.project.common.paging.SelectCriteria;


@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	/* [접속자 : 관리자, 선생님] 카테고리별 게시글 목록 */
	@GetMapping("/searchList/{categoryCode}")
	public ModelAndView findBoardListByCategoryNo(HttpServletRequest request, ModelAndView mv, @PathVariable String categoryCode) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = boardService.selectTotalCount(searchCondition, searchValue, categoryCode);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 10;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스 반환 */
		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}

		List<BoardAndReplyAndTeacherProfileDTO> boardList = boardService.searchBoardList(selectCriteria, categoryCode);
		CategoryDTO categoryInfo = boardService.searchCategoryInfo(categoryCode);
		
		// 확인용 출력 구문
		System.out.println("▶ [컨트롤러] boardList : " + boardList);
		System.out.println("▶ [컨트롤러] categoryInfo : " + categoryInfo);
		
		mv.addObject("categoryInfo", categoryInfo);
		mv.addObject("boardList", boardList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("board/searchList");

		return mv;
	}
	
	/* [접속 : 관리자, 선생님] 교류 게시판 글 목록 => 3분할 화면에 사용 */
	@GetMapping("/category")
	public ModelAndView findCategoryAndBoardList(ModelAndView mv) {
		
		List<CategoryAndBoardInfoDTO> categoryAndBoardList = boardService.findCategoryAndBoardList();

		// 확인용 출력 구문
		System.out.println("▶ [컨트롤러] categoryAndBoardList : " + categoryAndBoardList);
		
		mv.addObject("categoryAndBoardList", categoryAndBoardList);
		mv.setViewName("board/category");
		
		return mv;
	}
	
	/* [접속 : 관리자, 선생님] 게시판 상세페이지 */
	@GetMapping("/searchList/{categoryCode}/detail/{boardNo}")
	public ModelAndView findBoardByBoardNo(ModelAndView mv, @PathVariable String categoryCode, @PathVariable Long boardNo) {
		
		BoardAndReplyAndTeacherProfileDTO board = boardService.findBoardByBoardNo(categoryCode, boardNo);
		
		System.out.println("▶ [컨트롤러] board : " + board);
		
		mv.addObject("board", board);
		mv.addObject("btn", new BtnDTO());
		mv.setViewName("/board/detail");
		
		return mv;
	}

	/* [추가] 게시판 글쓰기 선생교류 */
	@GetMapping("/categoryregist")
	public void categoryregistPage() {}
	
	@PostMapping("/categoryregist")
	public ModelAndView registMenu(ModelAndView mv, BoardAndReplyAndTeacherProfileDTO newCategoryregist, RedirectAttributes rttr, @AuthenticationPrincipal TeacherImpl teacher) {

		boardService.registNewBoard(newCategoryregist, teacher);

		rttr.addFlashAttribute("registSuccessMessage", "게시글 등록에 성공하셨습니다");
		mv.setViewName("redirect:/board/category");
		
		System.out.println(mv);
		return mv;
	}
	
	/* [접속 : 관리자, 선생님] 수정 & 삭제 / 게시글 */
	@PostMapping("/contentModifyAndDelete")
	public String modifyAndDeleteContent(RedirectAttributes rttr, @ModelAttribute BoardAndReplyAndTeacherProfileDTO newBoard, @ModelAttribute BtnDTO btn) {

		String categoryCode = newBoard.getCategoryCode();
		Long boardNo = newBoard.getBoardNo();

		/* 메소드 만들면됨!! */
		if(btn.getBtn() == "수정") {
			boardService.modifyContent(newBoard);
			rttr.addFlashAttribute("successMessage", "게시글 수정이 완료되었습니다.");
			
			System.out.println("▶ [컨트롤러] newBoard 수정 또는 삭제 : " + newBoard);
			return "redirect:/board/searchList/" + categoryCode + "/detail/" + boardNo;
		} else if(btn.getBtn() == "삭제") {
			boardService.deleteContent(newBoard);
			rttr.addFlashAttribute("successMessage", "게시글 삭제가 완료되었습니다.");
			
			System.out.println("▶ [컨트롤러] newBoard 수정 또는 삭제 : " + newBoard);
			return "redirect:/board/searchList/" + categoryCode;
		}
	}
	
	/* [접속 : 관리자, 선생님] 등록 / 댓글 */
	@PostMapping("/replyRegist")
	public String registReply(RedirectAttributes rttr, @ModelAttribute BoardAndReplyAndTeacherProfileDTO newReply) {

		String categoryCode = newReply.getCategoryCode();
		Long boardNo = newReply.getBoardNo();
		
		boardService.registReply(newReply);
		
		rttr.addFlashAttribute("successMessage", "댓글 등록에 성공하였습니다.");
		
		System.out.println("▶ [컨트롤러] newReply 수정 : " + newReply);

		return "redirect:/board/searchList/" + categoryCode + "/detail/" + boardNo;
	}
	
	/* [접속 : 관리자, 선생님] 삭제 / 댓글 */
	@PostMapping("/replyDelete")
	public String deleteReply(RedirectAttributes rttr, @ModelAttribute ReplyAndTeacherProfileDTO reply) {
		
		String categoryCode = newReply.getCategoryCode();
		Long boardNo = newReply.getBoardNo();
		
		boardService.modifyReply(reply);
		
		rttr.addFlashAttribute("successMessage", "댓글 삭제에 성공하였습니다.");
		
		System.out.println("▶ [컨트롤러] reply 삭제 : " + reply);
		
		return "redirect:/board/searchList/" + categoryCode + "/detail/" + boardNo;
	}
}