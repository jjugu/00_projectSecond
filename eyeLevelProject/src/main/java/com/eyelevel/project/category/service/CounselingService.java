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

import com.eyelevel.project.category.dto.CounselingCategoryDTO;
import com.eyelevel.project.category.dto.CounselingDTO;
import com.eyelevel.project.category.entity.Counseling;
import com.eyelevel.project.category.entity.CounselingCategory;
import com.eyelevel.project.category.repository.CounselingCategoryRepository;
import com.eyelevel.project.category.repository.CounselingRepository;
import com.eyelevel.project.common.paging.SelectCriteria;


@Service
public class CounselingService {
	private final CounselingRepository counselingRepository;
	private final CounselingCategoryRepository counselingCategoryRepository;
	private final ModelMapper modelMapper;			// modelMapper 빈을 선언
	
	@Autowired
	public CounselingService(CounselingRepository counselingRepository, CounselingCategoryRepository counselingCategoryRepository, ModelMapper modelMapper) {
		this.counselingCategoryRepository = counselingCategoryRepository;
		this.counselingRepository = counselingRepository;
		this.modelMapper = modelMapper;
	}

	// 상담 개별
	public CounselingDTO findCounselingByNo(int counselingNo) {
		Counseling counseling = counselingRepository.findById(counselingNo).get();
		return modelMapper.map(counseling, CounselingDTO.class);
	}

	// 상담 전체	
	public List<CounselingDTO> findCounselingList() {
		List<Counseling> counselingList = counselingRepository.findAll(Sort.by("counselingNo"));
		return counselingList.stream().map(counseling -> modelMapper.map(counseling, CounselingDTO.class)).collect(Collectors.toList());
	}
	
	// 상담 검색
//	@Transactional
//	public int selectTotalCount(String searchCondition, String searchValue) {
//		int count = 0;
//		if(searchValue != null) {
//			if("counselingNo".equals(searchCondition)) {
//				count = counselingRepository.countByCounselingNoContaining(searchValue);
//			}
//			
//		} else {
//			count = (int)counselingRepository.count();
//		}
//
//		return count;
//	}
	
	@Transactional
	public int selectTotalCount(String searchCondition, String searchValue) {
	  int count = 0;
	  if(searchValue != null) {
	    if("categoryName".equals(searchCondition)) {
	      count = counselingRepository.countByCategoryNoCategoryNameContaining(searchValue);
	    }
	  } else {
	    count = (int)counselingRepository.count();
	  }
	  return count;
	}
	
	@Transactional
	public List<CounselingDTO> searchCounselingList(SelectCriteria selectCriteria) {
	  int index = selectCriteria.getPageNo() - 1;
	  int count = selectCriteria.getLimit();
	  String searchValue = selectCriteria.getSearchValue();

	  Pageable paging = PageRequest.of(index, count, Sort.by("counselingNo").descending());

	  List<Counseling> counselingList = new ArrayList<Counseling>();
	  if(searchValue != null) {
	    if("categoryName".equals(selectCriteria.getSearchCondition())) {
	      counselingList = counselingRepository.findByCategoryNoCategoryNameContaining(selectCriteria.getSearchValue(), paging);
	    }
	  } else {
	    counselingList = counselingRepository.findAll(paging).toList();
	  }
	  return counselingList.stream().map(counseling -> modelMapper.map(counseling, CounselingDTO.class)).collect(Collectors.toList());
	}
	
	// 상담 등록 ajax
	public List<CounselingCategoryDTO> findAllCategory() {
		List<CounselingCategory> categoryList = counselingCategoryRepository.findAllCategory();
		return categoryList.stream().map(category -> modelMapper.map(category, CounselingCategoryDTO.class)).collect(Collectors.toList());
	}
		
	// 상담 등록
	@Transactional
	public void registNewCounseling(CounselingDTO newCounseling) {
		counselingRepository.save(modelMapper.map(newCounseling, Counseling.class));
	}
	
	// 상담 수정
	@Transactional
	public void modifyCounseling(CounselingDTO counseling) {
		Counseling foundCounseling = counselingRepository.findById(counseling.getCounselingNo()).get();
		foundCounseling.setCounselingSt(counseling.getCounselingSt());
		foundCounseling.setCounselingCom(counseling.getCounselingCom());
	}
	
}
