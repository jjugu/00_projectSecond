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

import com.eyelevel.project.category.dto.PaperInformationDTO;
import com.eyelevel.project.category.entity.PaperInformation;
import com.eyelevel.project.category.repository.PaperInformationRepository;
import com.eyelevel.project.common.paging.SelectCriteria;

@Service
public class PaperInformationService {

	private final PaperInformationRepository paperRepository;
	private final ModelMapper modelMapper;			// modelMapper 빈을 선언
	
	@Autowired
	public PaperInformationService(PaperInformationRepository PaperRepository, ModelMapper modelMapper) {
		this.paperRepository = PaperRepository;
		this.modelMapper = modelMapper;
	}

	// 내용 하나 조회
	public PaperInformationDTO findPaperByNo(int paperNo) {

		PaperInformation paper = paperRepository.findById(paperNo).get();
		
		return modelMapper.map(paper, PaperInformationDTO.class);
	}

	// 학생 전체 조회
	public List<PaperInformationDTO> findPaperList() {

		List<PaperInformation> paperList = paperRepository.findAll(Sort.by("paperNo"));
		
		return paperList.stream().map(paper -> modelMapper.map(paper, PaperInformationDTO.class)).collect(Collectors.toList());
	}
	
//	//  검색 결과
	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {
			if("paperName".equals(searchCondition)) {
				count = paperRepository.countByPaperNameContaining(searchValue);
			}
		} else {
			count = (int)paperRepository.count();
		}

		return count;
	}
//	
//	// 내용 검색 기능
	public List<PaperInformationDTO> searchPaperList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("PaperNo"));	// Pageable은 org.springframework.data.domain패키지로 import

		List<PaperInformation> PaperList = new ArrayList<PaperInformation>();
		if(searchValue != null) {
			
			if("paperName".equals(selectCriteria.getSearchCondition())) {
				PaperList = paperRepository.findByPaperNameContaining(selectCriteria.getSearchValue(), paging);
			}
			
		} else {
			PaperList = paperRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return PaperList.stream().map(Paper -> modelMapper.map(Paper, PaperInformationDTO.class)).collect(Collectors.toList());
	}
	@Transactional
	public void modifyPaper(PaperInformationDTO paper) {
		
		PaperInformation foundPaper = paperRepository.findById(paper.getPaperNo()).get();
		foundPaper.setPaperName(paper.getPaperName());	
	}
		
		
	

	

}