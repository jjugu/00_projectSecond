package com.eyelevel.project.category.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eyelevel.project.category.dto.CalendarDTO;
import com.eyelevel.project.category.dto.TeacherImpl;
import com.eyelevel.project.category.service.CalendarService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
	
	private final CalendarService calendarService;
	
	@Autowired
	public CalendarController(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	// 이벤트 조회
	@GetMapping("/schedule")
	public ModelAndView scheduleList(ModelAndView mv) {
		
		List<CalendarDTO> scheduleList = calendarService.scheduleList();
		mv.addObject("scheduleList", scheduleList);
		mv.setViewName("calendar/schedule");
//		System.out.println("controller scheduleList: "+scheduleList);
		
		return mv;
	}
	
	// 이벤트 등록
	@PostMapping(value="/scheduleAdd", produces="application/json; charset=UTF-8")
	@ResponseBody
	public void addEvent(@RequestBody List<Map<String, Object>> param, CalendarDTO newSchedule, RedirectAttributes rttr, @AuthenticationPrincipal TeacherImpl teacher) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);

		String title = "";
		String start = "";
		String end = "";
		LocalDateTime startDate = null;
		LocalDateTime endDate = null;
		for (int i = 0; i < param.size(); i++) {

			title = (String) param.get(i).get("title");
			start = (String) param.get(i).get("start");
			end = (String) param.get(i).get("end");

			startDate = LocalDateTime.parse(start, dateTimeFormatter);
			endDate = LocalDateTime.parse(end, dateTimeFormatter);

//			System.out.println(title);
//			System.out.println(startDate);
//			System.out.println(endDate);
		}
		System.out.println(title);
		System.out.println(startDate);
		System.out.println(endDate);
//		List<CalendarDTO> scheduleList = new ArrayList<>();
		newSchedule.setTitle(title);
		newSchedule.setStart(startDate);
		newSchedule.setEnd(endDate);
		calendarService.registAddschedule(newSchedule, teacher);// 저장이 일어남
		System.out.println(newSchedule);
	}
	
	// 이벤트 삭제
	@DeleteMapping(value="/scheduleAdd", produces="application/json; charset=UTF-8")
    @ResponseBody
    public void deleteEvent(@RequestBody List<Map<String, Object>> param, RedirectAttributes rttr, @AuthenticationPrincipal TeacherImpl teacher){
		String title = "";
		/*
		 * String start = ""; String end = ""; LocalDateTime startDate = null;
		 * LocalDateTime endDate = null; DateTimeFormatter dateTimeFormatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
		 */
        for (int i = 0; i < param.size(); i++) {
            title = (String) param.get(i).get("title");
        }
        System.out.println("삭제기능 : " + title);
        calendarService.deleteBytitle(title);
    }
	
	// 이벤트 수정
    @PatchMapping(value="/scheduleAdd", produces="application/json; charset=UTF-8")
    @ResponseBody
    public void modifyEvent(@RequestBody List<Map<String, Object>> param, CalendarDTO modifySchedule, RedirectAttributes rttr, @AuthenticationPrincipal TeacherImpl teacher){
		String title = "";
		String start = "";
		String end = "";
		LocalDateTime startDate = null;
		LocalDateTime endDate = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
 
        for (int i = 0; i < param.size(); i++) {
 
			title = (String) param.get(i).get("title");
			start = (String) param.get(i).get("start");
			end = (String) param.get(i).get("end");
 
            startDate = LocalDateTime.parse(start, dateTimeFormatter);
            endDate = LocalDateTime.parse(end, dateTimeFormatter);
 
			System.out.println("수정에서 :" +title);
			System.out.println(startDate);
			System.out.println(endDate);
        }
        modifySchedule.setTitle(title);
        modifySchedule.setStart(startDate);
        modifySchedule.setEnd(endDate);
		calendarService.modifySchedule(modifySchedule, title, teacher);// 저장이 일어남
		System.out.println(modifySchedule);
	}

}
