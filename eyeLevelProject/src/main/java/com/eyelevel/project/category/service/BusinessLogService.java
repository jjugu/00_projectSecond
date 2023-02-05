package com.eyelevel.project.category.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eyelevel.project.category.dto.BusinessLogDTO;
import com.eyelevel.project.category.dto.StudentProfileAndStudentFamilyDTO;
import com.eyelevel.project.category.entity.BusinessLog;
import com.eyelevel.project.category.entity.StudentProfileAndStudentFamily;
import com.eyelevel.project.category.repository.BusinessLogRepository;
import com.eyelevel.project.common.paging.SelectCriteria;

@Service
public class BusinessLogService {

	private final BusinessLogRepository BusinessRepository;
	private final ModelMapper modelMapper;			// modelMapper 빈을 선언
	
	@Autowired
	public BusinessLogService(BusinessLogRepository BusinessRepository, ModelMapper modelMapper) {
		this.BusinessRepository = BusinessRepository;
		this.modelMapper = modelMapper;
	}

	// 내용 하나 조회
	public BusinessLogDTO findBusinessByDate(Date BusinessDate) {

		BusinessLog Business = BusinessRepository.findById(BusinessDate).get();
		
		return modelMapper.map(Business, BusinessLogDTO.class);
	}

	// 학생 전체 조회
	public List<BusinessLogDTO> findBusinessList() {

		List<BusinessLog> BusinessList = BusinessRepository.findAll(Sort.by("BusinessDate"));
		
		return BusinessList.stream().map(Business -> modelMapper.map(Business, BusinessLogDTO.class)).collect(Collectors.toList());
	}
	
//	// 학생 검색 결과
	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {
			if("BusinessDate".equals(searchCondition)) {
				count = BusinessRepository.countBybusinessDateContaining(searchValue);
			}
		} else {
			count = (int)BusinessRepository.count();
		}

		return count;
	}
	//등록
	@Transactional
	public void registNewBusiness(BusinessLogDTO newBusiness) {
		BusinessRepository.save(modelMapper.map(newBusiness, BusinessLog.class));
	}
//	
//	// 학생 검색 기능
	public List<BusinessLogDTO> searchBusinessList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("BusinessDate"));	// Pageable은 org.springframework.data.domain패키지로 import

		List<BusinessLog> BusinessList = new ArrayList<BusinessLog>();
		if(searchValue != null) {
			
			if("BusinessDate".equals(selectCriteria.getSearchCondition())) {
				BusinessList = BusinessRepository.findBybusinessDateContaining(selectCriteria.getSearchValue(), paging);
			}
			
		} else {
			BusinessList = BusinessRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return BusinessList.stream().map(Business -> modelMapper.map(Business, BusinessLogDTO.class)).collect(Collectors.toList());
	}
//	@Transactional
//	public void modifyBusiness(BusinessDTO Business) {
//		
//		Business foundBusiness = BusinessRepository.findById(Business.getBusinessDate()).get();
//		foundBusiness.setBusinessDate(Business.getBusinessDate());	
//	}

	
		
		
	

	

}