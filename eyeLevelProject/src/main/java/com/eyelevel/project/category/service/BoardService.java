package com.eyelevel.project.category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eyelevel.project.category.dto.BoardAndReplyAndTeacherProfileDTO;
import com.eyelevel.project.category.dto.CategoryAndBoardInfoDTO;
import com.eyelevel.project.category.dto.CategoryDTO;
import com.eyelevel.project.category.dto.TeacherImpl;
import com.eyelevel.project.category.entity.BoardAndReplyAndTeacherProfile;
import com.eyelevel.project.category.entity.Category;
import com.eyelevel.project.category.entity.CategoryAndBoardInfo;
import com.eyelevel.project.category.repository.BoardAndReplyAndTeacherProfileRepository;
import com.eyelevel.project.category.repository.CategoryAndBoardInfoRepository;
import com.eyelevel.project.category.repository.CategoryRepository;
import com.eyelevel.project.category.repository.ReplyAndTeacherProfileRepository;
import com.eyelevel.project.category.repository.TeacherProfileRepository;
import com.eyelevel.project.common.paging.SelectCriteria;

@Service
public class BoardService {
	
	private final CategoryAndBoardInfoRepository categoryAndBoardInfoRepository;
	private final BoardAndReplyAndTeacherProfileRepository boardAndReplyAndTeacherProfileRepository;
	private final ReplyAndTeacherProfileRepository replyAndTeacherProfileRepository;
	private final TeacherProfileRepository teacherProfileRepository;
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public BoardService(CategoryAndBoardInfoRepository categoryAndBoardInfoRepository,
			BoardAndReplyAndTeacherProfileRepository boardAndReplyAndTeacherProfileRepository,
			ReplyAndTeacherProfileRepository replyAndTeacherProfileRepository,
			TeacherProfileRepository teacherProfileRepository, CategoryRepository categoryRepository,
			ModelMapper modelMapper) {
		this.categoryAndBoardInfoRepository = categoryAndBoardInfoRepository;
		this.boardAndReplyAndTeacherProfileRepository = boardAndReplyAndTeacherProfileRepository;
		this.replyAndTeacherProfileRepository = replyAndTeacherProfileRepository;
		this.teacherProfileRepository = teacherProfileRepository;
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}
	
	/* [페이징 처리] 게시글 갯수 */
	public int selectTotalCount(String searchCondition, String searchValue, String categoryCode) {
		int count = 0;
		if(searchValue != null) {
			/* 게시글 제목 검색 */
			if("boardName".equals(searchCondition)) {
				count = boardAndReplyAndTeacherProfileRepository.countByBoardNameAndCategoryCodeContaining(searchValue, categoryCode);
			}
			/* 게시글 내용 검색 */
			if("boardContent".equals(searchCondition)) {
				count = boardAndReplyAndTeacherProfileRepository.countByBoardContentAndCategoryCodeContaining(searchValue, categoryCode);
			}
		} else {
			count = (int)boardAndReplyAndTeacherProfileRepository.countByCategoryCodeContaining(categoryCode);
		}
		return count;
	}


	/* [페이징 처리] 조회 목록 */
	public List<BoardAndReplyAndTeacherProfileDTO> searchBoardList(SelectCriteria selectCriteria, String categoryCode) {
		
		int index = selectCriteria.getPageNo() - 1;								// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("boardNo"));		// Pageable은 org.springframework.data.domain패키지로 import

		List<BoardAndReplyAndTeacherProfile> boardList = new ArrayList<BoardAndReplyAndTeacherProfile>();
		if(searchValue != null) {
			/* 게시글 제목 검색 */
			if("boardName".equals(selectCriteria.getSearchCondition())) {
				boardList = boardAndReplyAndTeacherProfileRepository.findByBoardNameAndCategoryCodeContaining(selectCriteria.getSearchValue(), paging, categoryCode);
			}
			/* 게시글 내용 검색 */
			if("menuPrice".equals(selectCriteria.getSearchCondition())) {
				boardList = boardAndReplyAndTeacherProfileRepository.findByBoardContentAndCategoryCodeContaining(selectCriteria.getSearchValue(), paging, categoryCode);
			}
		} else {
			boardList = boardAndReplyAndTeacherProfileRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return boardList.stream().map(menu -> modelMapper.map(menu, BoardAndReplyAndTeacherProfileDTO.class)).collect(Collectors.toList());
	}

	/* [페이징 처리] 조회 목록 화면단 처리를 위한 카테고리 정보 */
	public CategoryDTO searchCategoryInfo(String categoryCode) {
		Category categoryInfo = categoryRepository.findById(categoryCode).get();
		return modelMapper.map(categoryInfo, CategoryDTO.class);
	}
	
	/* [접속 : 관리자, 선생님] 교류 게시판 모든 카테고리 게시글 목록 => 3분할 화면에 사용 */
	public List<CategoryAndBoardInfoDTO> findCategoryAndBoardList() {
		
		List<CategoryAndBoardInfo> categoryAndBoardList = categoryAndBoardInfoRepository.findAll();
		
		// 확인용 출력 구문
		System.out.println("▶ [서비스] categoryAndBoardList : " + categoryAndBoardList);
		
		return categoryAndBoardList.stream().map(board -> modelMapper.map(board, CategoryAndBoardInfoDTO.class)).collect(Collectors.toList());
	}

	/* [접속 : 관리자, 선생님] 교류 게시판 카테고리별 게시글 상세페이지 */
	public BoardAndReplyAndTeacherProfileDTO findBoardByBoardNo(String categoryCode, Long boardNo) {
		
		BoardAndReplyAndTeacherProfile board = boardAndReplyAndTeacherProfileRepository.findBoardAndReplyAndTeacherProfileByCategoryCodeAndBoardNoLike(categoryCode, boardNo);
		
		System.out.println("▶ [서비스] board : " + board);
		
		return modelMapper.map(board, BoardAndReplyAndTeacherProfileDTO.class);
	}

	/* [추가] 선생교류 글쓰기 게시판 */
	@Transactional
	public void registNewBoard(BoardAndReplyAndTeacherProfileDTO newCategoryregist, TeacherImpl teacher) {
		newCategoryregist.setTeacherNo(teacher.getTeacherNo());
		boardAndReplyAndTeacherProfileRepository.save(modelMapper.map(newCategoryregist, BoardAndReplyAndTeacherProfile.class));
	}
}