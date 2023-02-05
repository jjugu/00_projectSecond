package com.eyelevel.project.category.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eyelevel.project.category.dto.FilesDTO;
import com.eyelevel.project.category.dto.PwdDTO;
import com.eyelevel.project.category.dto.TeacherAreaAndTeachingAreaDTO;
import com.eyelevel.project.category.dto.TeacherImpl;
import com.eyelevel.project.category.dto.TeacherProfileAndTeacherAreaDTO;
import com.eyelevel.project.category.entity.Files;
import com.eyelevel.project.category.entity.TeacherAreaAndTeachingArea;
import com.eyelevel.project.category.entity.TeacherProfileAndTeacherArea;
import com.eyelevel.project.category.repository.FilesRepository;
import com.eyelevel.project.category.repository.TeacherAreaAndTeacherProfileRepository;
import com.eyelevel.project.category.repository.TeacherAreaAndTeachingAreaRepository;
import com.eyelevel.project.category.repository.TeacherProfileAndTeacherAreaRepository;
import com.eyelevel.project.category.repository.TeacherProfileRepository;
import com.eyelevel.project.common.paging.SelectCriteria;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class TeacherProfileService {

	private final TeacherProfileAndTeacherAreaRepository teacherProfileAndTeacherAreaRepository;
	private final TeacherAreaAndTeacherProfileRepository teacherAreaAndTeacherProfileRepository;
	private final FilesRepository filesRepository;
	private final TeacherProfileRepository teacherProfileRepository;
	private final TeacherAreaAndTeachingAreaRepository teacherAreaAndTeachingAreaRepository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder bCryptPwdEncoder;
	
	@Autowired
	public TeacherProfileService(TeacherProfileAndTeacherAreaRepository teacherProfileAndTeacherAreaRepository,
			TeacherAreaAndTeacherProfileRepository teacherAreaAndTeacherProfileRepository,
			FilesRepository filesRepository, TeacherProfileRepository teacherProfileRepository,
			TeacherAreaAndTeachingAreaRepository teacherAreaAndTeachingAreaRepository, ModelMapper modelMapper,
			BCryptPasswordEncoder bCryptPwdEncoder) {
		this.teacherProfileAndTeacherAreaRepository = teacherProfileAndTeacherAreaRepository;
		this.teacherAreaAndTeacherProfileRepository = teacherAreaAndTeacherProfileRepository;
		this.filesRepository = filesRepository;
		this.teacherProfileRepository = teacherProfileRepository;
		this.teacherAreaAndTeachingAreaRepository = teacherAreaAndTeachingAreaRepository;
		this.modelMapper = modelMapper;
		this.bCryptPwdEncoder = bCryptPwdEncoder;
	}
	
	/* 페이징 처리 */
	public int selectTotalCount(String searchCondition, String searchValue) {
		
		int count = 0;
		if(searchValue != null) {
			if("teacherNo".equals(searchCondition)) {
				count = teacherProfileAndTeacherAreaRepository.countByTeacherNoContaining(searchValue);
			}
			if("teacherName".equals(searchCondition)) {
				count = teacherProfileAndTeacherAreaRepository.countByTeacherNameContaining(searchValue);
			}
		} else {
			count = (int)teacherProfileAndTeacherAreaRepository.count();
		}

		return count;
	}

	/* 페이징 처리 (목록 조회) */
	public List<TeacherProfileAndTeacherAreaDTO> searchTeacherProfileAndTeacherArea(SelectCriteria selectCriteria) {
		
		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		Pageable paging = PageRequest.of(index, count, Sort.by("teacherNo"));

		List<TeacherProfileAndTeacherArea> profileList = new ArrayList<TeacherProfileAndTeacherArea>();
		if(searchValue != null) {

			/* [교번 검색] */
			if("teacherNo".equals(selectCriteria.getSearchCondition())) {
				profileList = teacherProfileAndTeacherAreaRepository.findByTeacherNoContaining(selectCriteria.getSearchValue(), paging);
			}
			/* [이름 검색] */
			if("teacherName".equals(selectCriteria.getSearchCondition())) {
				profileList = teacherProfileAndTeacherAreaRepository.findByTeacherNameContaining(selectCriteria.getSearchValue(), paging);
			}
		} else {
			profileList = teacherProfileAndTeacherAreaRepository.findAll(paging).toList();
		}

		return profileList.stream().map(profile -> modelMapper.map(profile, TeacherProfileAndTeacherAreaDTO.class)).collect(Collectors.toList());
	}
	
	/* [접속자 : 관리자, 교사] 교사 개인정보 상세 페이지 */
	public TeacherProfileAndTeacherAreaDTO findTeacherProfileAndTeacherAreaByTeacherNo(String teacherNo) {
		
		TeacherProfileAndTeacherArea detailProfile = teacherProfileAndTeacherAreaRepository.findById(teacherNo).get();
		
		return modelMapper.map(detailProfile, TeacherProfileAndTeacherAreaDTO.class);
	}

	/* [접속자 : 관리자, 교사] 교사 개인정보 상세 페이지 (지역정보) */
	public List<TeacherAreaAndTeachingAreaDTO> findTeacherAreaListByAreaNo(String teacherNo) {
		
		List<TeacherAreaAndTeachingArea> teacherAreaList = teacherAreaAndTeachingAreaRepository.findByTeacherNoContaining(teacherNo);
		System.out.println("teacherAreaList : " + teacherAreaList);
		
		return teacherAreaList.stream().map(area -> modelMapper.map(area, TeacherAreaAndTeachingAreaDTO.class)).collect(Collectors.toList());
	}
	
	/* [접속 : 관리자, 교사] 수정 / 교사 개인정보 및 교사 교습 지역 */
	@Transactional
	public void modifyProfileAndArea(TeacherProfileAndTeacherAreaDTO newProfile, MultipartFile mfile, FilesDTO newFile) {
		
		/* 파일 수정 및 등록 */
		// 경로 설정 및 생성 [★프로젝트 발표 시 아래 경로 수정!!!!!!!!!!!!!!!!!!!!!!!!!이미지폴더 리프레시 안해주면 로딩이안됨]
//		String filepath = "C:\\00_personalPJ\\eyeLevelProject\\src\\main\\resources\\static\\image\\";
		String filepath = "C:\\00_eyelevel\\eyeLevelProject\\src\\main\\resources\\static\\image\\";
		String thumbnailPath = "\\image\\";
		File directory = new File(filepath);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		/* 개인정보 수정과 새로 업로드한 파일이 있는 경우 (기존 파일이 있는 경우 에러 발생 -> 방지하기 위해 조건식 추가) */
		/* files 테이블과 조인하여 화면으로 넘겨주어도 무방하지만 교사정보 + 교사 지역정보 + 교사 사진 정보 3개를 조인하여야함 */
		// 파일 저장 명 변경
		String originFileName = mfile.getOriginalFilename();
		System.out.println("★★★★★★★ originFileName : " + originFileName);
		if(!originFileName.isEmpty()) {
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			String saveName = UUID.randomUUID().toString().replace("-", "") + extension;
			// 파일 저장
			try {
				mfile.transferTo(new File(filepath + saveName));
				Thumbnails.of(filepath + saveName).size(300, 400).toFile(filepath + "thumbnail_" + saveName);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				new File(filepath + "/" + saveName).delete();
			}
			
			/* 교사 1명당 하나의 파일만 저장하기 위한 조건식 */
			if(filesRepository.findByTeacherNo(newProfile.getTeacherNo()) == null) {
				/* 기존 파일 테이블에 교사 번호가 없는 경우 (신규 등록) */
				newFile.setTeacherNo(newProfile.getTeacherNo());
				newFile.setFileType(extension);
				newFile.setFileOriginName(originFileName);
				newFile.setFileSaveName(saveName);
				newFile.setFilePath(filepath);
				newFile.setThumbnailPath(thumbnailPath + "thumbnail_" + saveName);
				filesRepository.save(modelMapper.map(newFile, Files.class));
				
				TeacherProfileAndTeacherArea profile = teacherProfileAndTeacherAreaRepository.findById(newProfile.getTeacherNo()).get();
				
				profile.setTeacherPicture(newFile.getThumbnailPath());
				profile.setTeacherAddress(newProfile.getTeacherAddress());
				profile.setTeacherPhone(newProfile.getTeacherPhone());
				profile.setTeacherEmail(newProfile.getTeacherEmail());
				profile.setTeacherLevel(newProfile.getTeacherLevel());
				profile.setTeacherPr(newProfile.getTeacherPr());
			} else {
				/* 기존 파일 테이블에 교사 번호가 있는 경우 (수정) */
				Files file = filesRepository.findByTeacherNo(newProfile.getTeacherNo());
				file.setFileType(extension);
				file.setFileOriginName(originFileName);
				file.setFileSaveName(saveName);
				file.setFilePath(filepath);
				file.setThumbnailPath(thumbnailPath + "thumbnail_" + saveName);
				
				TeacherProfileAndTeacherArea profile = teacherProfileAndTeacherAreaRepository.findById(newProfile.getTeacherNo()).get();
				
				profile.setTeacherPicture(file.getThumbnailPath());
				profile.setTeacherAddress(newProfile.getTeacherAddress());
				profile.setTeacherPhone(newProfile.getTeacherPhone());
				profile.setTeacherEmail(newProfile.getTeacherEmail());
				profile.setTeacherLevel(newProfile.getTeacherLevel());
				profile.setTeacherPr(newProfile.getTeacherPr());
			}
			
		/* 개인정보만 수정할 뿐 새로 업로드한 파일이 없는 경우 */
		} else {
			TeacherProfileAndTeacherArea profile = teacherProfileAndTeacherAreaRepository.findById(newProfile.getTeacherNo()).get();
			
			// 새로 파일을 업로드하지 않은 경우 썸네일 주소를 바꾸지 않음
			profile.setTeacherAddress(newProfile.getTeacherAddress());
			profile.setTeacherPhone(newProfile.getTeacherPhone());
			profile.setTeacherEmail(newProfile.getTeacherEmail());
			profile.setTeacherLevel(newProfile.getTeacherLevel());
			profile.setTeacherPr(newProfile.getTeacherPr());
			// ★ 주소 수정 기능 추가
		}
	}

	/* [접속 : 관리자] 수정 / 교사 재직 정보 처리 */
	@Transactional
	public void modifyProfile(TeacherProfileAndTeacherAreaDTO newProfile) {
		
		TeacherProfileAndTeacherArea profile = teacherProfileAndTeacherAreaRepository.findById(newProfile.getTeacherNo()).get();
		profile.setTeacherEnt('Y');
	}
	
	/* [접속 : 관리자] 삭제 / 교사 개인정보 및 교사 교습 지역 */
	@Transactional
	public void deleteProfileAndArea(TeacherProfileAndTeacherAreaDTO newProfile) {
		
		teacherProfileAndTeacherAreaRepository.deleteById(newProfile.getTeacherNo());
	}

	/* [접속 : 관리자, 교사] 수정 / 교사 비밀번호 */
	@Transactional
	public int modifyPwd(TeacherImpl teacher, PwdDTO pwd) {
		
		TeacherProfileAndTeacherArea profile = teacherProfileAndTeacherAreaRepository.findById(teacher.getTeacherNo()).get();
		String teacherPw = profile.getTeacherPw();
		String originPw = pwd.getOriginPw();
		String newPw = pwd.getNewPw();
		String checkPw = bCryptPwdEncoder.encode(pwd.getCheckPw());
		
		if(bCryptPwdEncoder.matches(originPw, teacherPw) && bCryptPwdEncoder.matches(newPw, checkPw)) {
			/* 기존 비밀번호가 일치하고, 신규 비밀번호와 확인 비밀번호도 일치하는 경우 */
			profile.setTeacherPw(checkPw);
			return 1;
			
		} else if(bCryptPwdEncoder.matches(originPw, teacherPw) && !bCryptPwdEncoder.matches(newPw, checkPw)) {
			/* 기존 비밀번호는 일치하지만, 신규 비밀번호와 확인 비밀번호가 일치하지 않는 경우 */
			return 2;
			
		} else {
			/* 기존 비밀번호가 일치하지 않는 경우 */
			return 3;
		}
	}
	
	/* 선생 등록 */
	public void registNewTeacher(TeacherProfileAndTeacherAreaDTO newTeacher) {
		teacherProfileAndTeacherAreaRepository.save(modelMapper.map(newTeacher, TeacherProfileAndTeacherArea.class));
		
	}
}