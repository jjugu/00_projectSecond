package com.eyelevel.project.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eyelevel.project.category.dto.BtnDTO;
import com.eyelevel.project.category.dto.FilesDTO;
import com.eyelevel.project.category.dto.PwdDTO;
import com.eyelevel.project.category.dto.TeacherAreaAndTeachingAreaDTO;
import com.eyelevel.project.category.dto.TeacherImpl;
import com.eyelevel.project.category.dto.TeacherProfileAndTeacherAreaDTO;
import com.eyelevel.project.category.service.TeacherProfileService;
import com.eyelevel.project.common.paging.Pagenation;
import com.eyelevel.project.common.paging.SelectCriteria;

@Controller
@RequestMapping("/profile")
public class TeacherProfileController {

	private final TeacherProfileService teacherProfileService;
	private final BCryptPasswordEncoder bCryptPwd;
	
	
	@Autowired
	public TeacherProfileController(TeacherProfileService teacherProfileService, BCryptPasswordEncoder bCryptPwd) {
		this.teacherProfileService = teacherProfileService;
		this.bCryptPwd = bCryptPwd;
	}

	/* [접속자 : 관리자] 선생 정보 관리 목록 */
	@GetMapping("/searchList")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = teacherProfileService.selectTotalCount(searchCondition, searchValue);

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
		
		List<TeacherProfileAndTeacherAreaDTO> profileList = teacherProfileService.searchTeacherProfileAndTeacherArea(selectCriteria);

		mv.addObject("profileList", profileList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("profile/searchList");

		return mv;
	}

	/* [접속자 : 관리자, 교사] 교사 개인정보 상세 페이지 */
	@GetMapping(value = {"/detail", "/detail/{teacherNo}"})
	public ModelAndView findTeacherProfileAndTeacherAreaByTeacherNo(ModelAndView mv, @PathVariable(required = false) String teacherNo, 
																	@AuthenticationPrincipal TeacherImpl teacher, BtnDTO btn) {

		if(teacherNo == null) {	teacherNo = teacher.getTeacherNo();	}
		TeacherProfileAndTeacherAreaDTO detailProfile = teacherProfileService.findTeacherProfileAndTeacherAreaByTeacherNo(teacherNo);
		List<TeacherAreaAndTeachingAreaDTO> teacherAreaList = teacherProfileService.findTeacherAreaListByAreaNo(teacherNo);
		
		mv.addObject("profile", detailProfile);
		mv.addObject("teacherAreaList", teacherAreaList);
		mv.addObject("btn", btn);
		mv.setViewName("/profile/detail");
		
		return mv;
	}
	
	/* [접속 : 관리자, 교사] 수정 및 삭제 / 교사 개인정보 (사진 업로드 포함) */
	@PostMapping("/modifyAndDelete")
	public String modifyProfile(RedirectAttributes rttr, @ModelAttribute TeacherProfileAndTeacherAreaDTO newProfile, 
								FilesDTO newFile, @RequestParam(value="files", required = false) MultipartFile mfile, @ModelAttribute BtnDTO btn) throws InterruptedException {

		if(btn.getBtn().equals("수정")) {
			/* 수정하는 경우 */
			teacherProfileService.modifyProfileAndArea(newProfile, mfile, newFile);
			rttr.addFlashAttribute("successMessage", "수정이 완료되었습니다.");
			
			/* 썸네일 업로드 속도를 위한 딜레이 추가 */
			Thread thread = new Thread();
			thread.sleep(2500);
			
			return "redirect:/profile/detail/" + newProfile.getTeacherNo();
		} else if(btn.getBtn().equals("퇴사처리")) {
			/* 퇴사처리하는 경우 */
			teacherProfileService.modifyProfile(newProfile);
			rttr.addFlashAttribute("successMessage", "퇴사처리가 완료되었습니다.");
			return "redirect:/profile/detail/" + newProfile.getTeacherNo();
		} else {
			/* 삭제하는 경우 */
			teacherProfileService.deleteProfileAndArea(newProfile);
			rttr.addFlashAttribute("successMessage", "계정 삭제가 완료되었습니다.");
			return "redirect:/profile/searchList";
		}
	}
	
	/* [접속 : 교사] 수정 / 비밀번호 */
	@GetMapping("/modifyPwd")
	public void modifyPwd() {}
	
	/* [접속 : 교사] 수정 / 비밀번호 */
	@PostMapping("/modifyPwd")
	public String modifyPwd(RedirectAttributes rttr, @AuthenticationPrincipal TeacherImpl teacher, PwdDTO pwd) {

		int result = teacherProfileService.modifyPwd(teacher, pwd);
		
		if(result == 1) {
			/* 기존 비밀번호가 일치하고, 신규 비밀번호와 확인 비밀번호도 일치하는 경우 */
			rttr.addFlashAttribute("successMessage", "비밀번호 수정이 완료되었습니다.\\n다른 변경 사항이 있는 경우, 작성 후 수정 버튼을 눌러주세요.");
			return "redirect:/profile/detail";
		} else if(result == 2) {
			/* 기존 비밀번호는 일치하지만, 신규 비밀번호와 확인 비밀번호가 일치하지 않는 경우 */
			rttr.addFlashAttribute("failedMessage", "신규 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return "redirect:/profile/modifyPwd";
		} else {
			/* 기존 비밀번호가 일치하지 않는 경우 */
			rttr.addFlashAttribute("failedMessage", "기존 비밀번호를 정확하게 입력해주세요.");
			return "redirect:/profile/modifyPwd";
		}
	}
	
	/*교사 등록*/
	@GetMapping("/regist")
	public void registPage() {}
	@PostMapping("/regist")
	public ModelAndView registPage(ModelAndView mv, TeacherProfileAndTeacherAreaDTO newTeacher, RedirectAttributes rttr) {
		
		 String encryptPwd = bCryptPwd.encode(newTeacher.getTeacherPw());
		 newTeacher.setTeacherPw(encryptPwd);
		
		 teacherProfileService.registNewTeacher(newTeacher);
		
		 rttr.addFlashAttribute("registSuccessMessage", "교사 등록 완료");
		
		 mv.setViewName("redirect:/profile/searchList");
		
		 return mv;
	}
}