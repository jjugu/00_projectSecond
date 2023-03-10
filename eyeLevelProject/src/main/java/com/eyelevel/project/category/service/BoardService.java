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
	
	/* [????????? ??????] ????????? ?????? */
	public int selectTotalCount(String searchCondition, String searchValue, String categoryCode) {
		int count = 0;
		if(searchValue != null) {
			/* ????????? ?????? ?????? */
			if("boardName".equals(searchCondition)) {
				count = boardAndReplyAndTeacherProfileRepository.countByBoardNameAndCategoryCodeContaining(searchValue, categoryCode);
			}
			/* ????????? ?????? ?????? */
			if("boardContent".equals(searchCondition)) {
				count = boardAndReplyAndTeacherProfileRepository.countByBoardContentAndCategoryCodeContaining(searchValue, categoryCode);
			}
		} else {
			count = (int)boardAndReplyAndTeacherProfileRepository.countByCategoryCodeContaining(categoryCode);
		}
		return count;
	}


	/* [????????? ??????] ?????? ?????? */
	public List<BoardAndReplyAndTeacherProfileDTO> searchBoardList(SelectCriteria selectCriteria, String categoryCode) {
		
		int index = selectCriteria.getPageNo() - 1;								// Pageble????????? ????????? ???????????? 0?????? ??????(1???????????? 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* ????????? ????????? ????????? ?????? ?????? ?????? */
		Pageable paging = PageRequest.of(index, count, Sort.by("boardNo"));		// Pageable??? org.springframework.data.domain???????????? import

		List<BoardAndReplyAndTeacherProfile> boardList = new ArrayList<BoardAndReplyAndTeacherProfile>();
		if(searchValue != null) {
			/* ????????? ?????? ?????? */
			if("boardName".equals(selectCriteria.getSearchCondition())) {
				boardList = boardAndReplyAndTeacherProfileRepository.findByBoardNameAndCategoryCodeContaining(selectCriteria.getSearchValue(), paging, categoryCode);
			}
			/* ????????? ?????? ?????? */
			if("menuPrice".equals(selectCriteria.getSearchCondition())) {
				boardList = boardAndReplyAndTeacherProfileRepository.findByBoardContentAndCategoryCodeContaining(selectCriteria.getSearchValue(), paging, categoryCode);
			}
		} else {
			boardList = boardAndReplyAndTeacherProfileRepository.findAll(paging).toList();
		}

		/* ????????? Stream API??? ModelMapper??? ???????????? entity??? DTO??? ?????? ??? List<MenuDTO>??? ?????? */
		return boardList.stream().map(menu -> modelMapper.map(menu, BoardAndReplyAndTeacherProfileDTO.class)).collect(Collectors.toList());
	}

	/* [????????? ??????] ?????? ?????? ????????? ????????? ?????? ???????????? ?????? */
	public CategoryDTO searchCategoryInfo(String categoryCode) {
		Category categoryInfo = categoryRepository.findById(categoryCode).get();
		return modelMapper.map(categoryInfo, CategoryDTO.class);
	}
	
	/* [?????? : ?????????, ?????????] ?????? ????????? ?????? ???????????? ????????? ?????? => 3?????? ????????? ?????? */
	public List<CategoryAndBoardInfoDTO> findCategoryAndBoardList() {
		
		List<CategoryAndBoardInfo> categoryAndBoardList = categoryAndBoardInfoRepository.findAll();
		
		// ????????? ?????? ??????
		System.out.println("??? [?????????] categoryAndBoardList : " + categoryAndBoardList);
		
		return categoryAndBoardList.stream().map(board -> modelMapper.map(board, CategoryAndBoardInfoDTO.class)).collect(Collectors.toList());
	}

	/* [?????? : ?????????, ?????????] ?????? ????????? ??????????????? ????????? ??????????????? */
	public BoardAndReplyAndTeacherProfileDTO findBoardByBoardNo(String categoryCode, Long boardNo) {
		
		BoardAndReplyAndTeacherProfile board = boardAndReplyAndTeacherProfileRepository.findBoardAndReplyAndTeacherProfileByCategoryCodeAndBoardNoLike(categoryCode, boardNo);
		
		System.out.println("??? [?????????] board : " + board);
		
		return modelMapper.map(board, BoardAndReplyAndTeacherProfileDTO.class);
	}

	/* [??????] ???????????? ????????? ????????? */
	@Transactional
	public void registNewBoard(BoardAndReplyAndTeacherProfileDTO newCategoryregist, TeacherImpl teacher) {
		newCategoryregist.setTeacherNo(teacher.getTeacherNo());
		boardAndReplyAndTeacherProfileRepository.save(modelMapper.map(newCategoryregist, BoardAndReplyAndTeacherProfile.class));
	}
}