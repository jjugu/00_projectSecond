package com.eyelevel.project.category.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eyelevel.project.category.dto.AnswerDTO;
import com.eyelevel.project.category.dto.CalendarDTO;
import com.eyelevel.project.category.dto.QuestionAndAnswerDTO;
import com.eyelevel.project.category.dto.TeacherImpl;
import com.eyelevel.project.category.entity.Answer;
import com.eyelevel.project.category.entity.Calendar;
import com.eyelevel.project.category.entity.Question;
import com.eyelevel.project.category.repository.CalendarRepository;

@Service
public class CalendarService {

	private final CalendarRepository calendarRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CalendarService(CalendarRepository calendarRepository, ModelMapper modelMapper) {
		this.calendarRepository = calendarRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 달력 일정 조회 */
	public List<CalendarDTO> scheduleList() {
		List<Calendar> scheduleList = calendarRepository.findAll(Sort.by("id"));
		
//		System.out.println("service scheduleList: " + scheduleList);
		
		return scheduleList.stream().map(calendar -> modelMapper.map(calendar, CalendarDTO.class)).collect(Collectors.toList());
	}
	
	/* 달력 일정 저장 */
	@Transactional
	public void registAddschedule(CalendarDTO newSchedule, TeacherImpl teacher) {
		newSchedule.setTeacherName(teacher.getTeacherName());
		calendarRepository.save(modelMapper.map(newSchedule, Calendar.class));
	}

	/* 달력 일정 수정 */
	@Transactional
	public void modifySchedule (CalendarDTO modifySchedule, String title, TeacherImpl teacher) {
		Calendar modifyCalendar = calendarRepository.findBytitle(title);
		modifyCalendar.setStart(modifySchedule.getStart());
		modifyCalendar.setEnd(modifySchedule.getEnd());
		modifyCalendar.setTeacherName(teacher.getTeacherName());
	}

	/* 달력 일정 삭제 */
	@Transactional
	public void deleteBytitle(String title) {
//		calendarRepository.deleteBytitle(title);
		Calendar deleteCalendar = calendarRepository.findBytitle(title);
		
		calendarRepository.delete(deleteCalendar);
		
	}
	
	
}
