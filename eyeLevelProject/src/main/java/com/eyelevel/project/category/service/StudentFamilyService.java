package com.eyelevel.project.category.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eyelevel.project.category.dto.StudentFamilyAndStudentProfileDTO;
import com.eyelevel.project.category.dto.StudentProfileAndStudentFamilyDTO;
import com.eyelevel.project.category.entity.StudentFamily;
import com.eyelevel.project.category.entity.StudentFamilyAndStudentProfile;
import com.eyelevel.project.category.repository.StudentFamilyAndStudentProfileRepository;

@Service
public class StudentFamilyService {
	private final StudentFamilyAndStudentProfileRepository familyAndStudentRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public StudentFamilyService(StudentFamilyAndStudentProfileRepository familyAndStudentRepository, ModelMapper modelMapper) {
		this.familyAndStudentRepository = familyAndStudentRepository;
		this.modelMapper = modelMapper;
	}

	// 가족 개별
	public StudentFamilyAndStudentProfileDTO findFamilyByNo(Long familyNo) {
		StudentFamilyAndStudentProfile family = familyAndStudentRepository.findById(familyNo).get();
		return modelMapper.map(family, StudentFamilyAndStudentProfileDTO.class);
	}

	// 가족 전체	
	public List<StudentFamilyAndStudentProfileDTO> findFamilyList() {
		List<StudentFamilyAndStudentProfile> familyList = familyAndStudentRepository.findAll(Sort.by("familyNo"));
		return familyList.stream().map(family -> modelMapper.map(family, StudentFamilyAndStudentProfileDTO.class)).collect(Collectors.toList());
	}
	
	// 가족 등록
	@Transactional
	public void registNewFamily(StudentFamilyAndStudentProfileDTO newFamily) {
		familyAndStudentRepository.save(modelMapper.map(newFamily, StudentFamilyAndStudentProfile.class));
	}
	
	// 가족 수정
	@Transactional
	public void modifyFamily(StudentFamilyAndStudentProfileDTO family) {

		StudentFamilyAndStudentProfile foundFamily = familyAndStudentRepository.findById(family.getFamilyNo()).get();
		foundFamily.setFamilyPhone(family.getFamilyPhone());
	}	
}
