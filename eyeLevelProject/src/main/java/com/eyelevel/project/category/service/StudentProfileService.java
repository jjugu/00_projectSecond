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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eyelevel.project.category.dto.AdminImpl;
import com.eyelevel.project.category.dto.StudentImpl;
import com.eyelevel.project.category.dto.StudentProfileAndStudentFamilyDTO;
import com.eyelevel.project.category.dto.TeacherImpl;
import com.eyelevel.project.category.entity.Admin;
import com.eyelevel.project.category.entity.StudentProfile;
import com.eyelevel.project.category.entity.StudentProfileAndStudentFamily;
import com.eyelevel.project.category.entity.TeacherProfile;
import com.eyelevel.project.category.repository.AdminRepository;
import com.eyelevel.project.category.repository.StudentFamilyAndStudentProfileRepository;
import com.eyelevel.project.category.repository.StudentFamilyRepository;
import com.eyelevel.project.category.repository.StudentProfileAndStudentFamilyRepository;
import com.eyelevel.project.category.repository.StudentProfileRepository;
import com.eyelevel.project.category.repository.TeacherProfileRepository;
import com.eyelevel.project.common.paging.SelectCriteria;


@Service
public class StudentProfileService implements UserDetailsService{

	private final StudentProfileRepository studentRepository;
	private final TeacherProfileRepository teacherRepository;
	private final AdminRepository adminRepository;
	
	private final StudentProfileAndStudentFamilyRepository studentAndFamilyRepository;
	private final StudentFamilyAndStudentProfileRepository familyAndStudentRepository;
	private final StudentFamilyRepository familyRepository;
	
	private final ModelMapper modelMapper;
	
	@Autowired
	public StudentProfileService(
			
			AdminRepository adminRepository,
			TeacherProfileRepository teacherRepository,
			StudentProfileRepository studentRepository,
			StudentFamilyRepository familyRepository,
			
			StudentProfileAndStudentFamilyRepository studentAndFamilyRepository,
			StudentFamilyAndStudentProfileRepository familyAndStudentRepository, 

			ModelMapper modelMapper) {
		
		this.adminRepository = adminRepository;
		this.teacherRepository = teacherRepository;
		this.studentRepository = studentRepository;
		this.familyRepository = familyRepository;
		
		this.studentAndFamilyRepository = studentAndFamilyRepository;
		this.familyAndStudentRepository = familyAndStudentRepository;
		
		this.modelMapper = modelMapper;
	}

	// 로그인 구간
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		User user = null;
		
		System.out.println("사용자가 입력한 아이디가 넘어옴: " + memberId);
		
		StudentProfile student = null;
		TeacherProfile teacher = null;
		Admin admin = null;
		
		student = studentRepository.findByStudentId(memberId); 
		if(student != null) {
			authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
			
			StudentImpl user1 = new StudentImpl(student.getStudentId(), student.getStudentPw(), authorities);
			user1.setDetails(student);			
			return user1;
		}
		
		teacher = teacherRepository.findByTeacherId(memberId);
		if(teacher != null) {
			authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
			authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
			
			TeacherImpl user2 = new TeacherImpl(teacher.getTeacherId(), teacher.getTeacherPw(), authorities);
			
			user2.setDetails(teacher);			
			return user2;
		}
		
		admin = adminRepository.findByAdminId(memberId);
		if(admin != null) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
			
			AdminImpl user3 = new AdminImpl(admin.getAdminId(), admin.getAdminPw(), authorities);
			user3.setDetails(admin);			
			return user3;
		}
		return user;
	}
	
	// 학생 개별
	@Transactional
	public StudentProfileAndStudentFamilyDTO findStudentByNo(Long studentNo) {
		StudentProfileAndStudentFamily student = studentAndFamilyRepository.findById(studentNo).get();
		return modelMapper.map(student, StudentProfileAndStudentFamilyDTO.class);
	}
	// 학생 화면 개별
	@Transactional
	public StudentProfileAndStudentFamilyDTO getStudentByNo(Long studentNo) {
		StudentProfileAndStudentFamily student2 = studentAndFamilyRepository.findById(studentNo).get();
		return modelMapper.map(student2, StudentProfileAndStudentFamilyDTO.class);	
	}	

	// 학생 등록
	@Transactional
	public void registNewStudent(StudentProfileAndStudentFamilyDTO newStudent) {
		studentAndFamilyRepository.save(modelMapper.map(newStudent, StudentProfileAndStudentFamily.class));
	}

	// 학생 수정
	@Transactional
	public void modifyStudent(StudentProfileAndStudentFamilyDTO student) {
		StudentProfileAndStudentFamily foundStudent = studentAndFamilyRepository.findById(student.getStudentNo()).get();
		foundStudent.setStudentMemo(student.getStudentMemo());
		foundStudent.setStudentStatus(student.getStudentStatus());	
	}
	
	@Transactional
	public void modifyPersonalStudent(StudentProfileAndStudentFamilyDTO student) {
		StudentProfileAndStudentFamily foundStudent = studentAndFamilyRepository.findById(student.getStudentNo()).get();
		foundStudent.setStudentPw(student.getStudentPw());
		foundStudent.setStudentAddress(student.getStudentAddress());
		foundStudent.setStudentPhone(student.getStudentPhone());	
	}


	
	
	// 학생 검색
	@Transactional
	public int selectTotalCount(String searchCondition, String searchValue) {
		int count = 0;
		if(searchValue != null) {

			
			if("studentName".equals(searchCondition)) {
				count = studentAndFamilyRepository.countByStudentNameContaining(searchValue);
			}
			if("studentId".equals(searchCondition)) {
				count = studentAndFamilyRepository.countByStudentIdContaining(searchValue);
			}
			if("studentAddress".equals(searchCondition)) {
				count = studentAndFamilyRepository.countByStudentAddressContaining(searchValue);
			}
			
		} else {
			count = (int)studentAndFamilyRepository.count();
		}

		return count;
	}
	@Transactional
	public List<StudentProfileAndStudentFamilyDTO> searchStudentList(SelectCriteria selectCriteria) {
			int index = selectCriteria.getPageNo() - 1;
			int count = selectCriteria.getLimit();
			String searchValue = selectCriteria.getSearchValue();

			Pageable paging = PageRequest.of(index, count, Sort.by("studentNo"));

			List<StudentProfileAndStudentFamily> studentList = new ArrayList<StudentProfileAndStudentFamily>();
			if(searchValue != null) {
				

				
				if("studentName".equals(selectCriteria.getSearchCondition())) {
					studentList = studentAndFamilyRepository.findByStudentNameContaining(selectCriteria.getSearchValue(), paging);
				}
				
				if("studentId".equals(selectCriteria.getSearchCondition())) {
					studentList = studentAndFamilyRepository.findByStudentIdContaining(selectCriteria.getSearchValue(), paging);
				}
				
				if("studentAddress".equals(selectCriteria.getSearchCondition())) {
					studentList = studentAndFamilyRepository.findByStudentAddressContaining(selectCriteria.getSearchValue(), paging);
				}
				
			} else {
				studentList = studentAndFamilyRepository.findAll(paging).toList();
			}
			return studentList.stream().map(student -> modelMapper.map(student, StudentProfileAndStudentFamilyDTO.class)).collect(Collectors.toList());
			}
}