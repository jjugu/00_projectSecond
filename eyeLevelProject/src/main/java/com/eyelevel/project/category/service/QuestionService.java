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

import com.eyelevel.project.category.dto.AnswerAndTeacherProfileDTO;
import com.eyelevel.project.category.dto.AnswerDTO;
import com.eyelevel.project.category.dto.BtnDTO;
import com.eyelevel.project.category.dto.QuestionAndStudentProfileDTO;
import com.eyelevel.project.category.dto.QuestionDTO;
import com.eyelevel.project.category.dto.StudentImpl;
import com.eyelevel.project.category.dto.TeacherImpl;
import com.eyelevel.project.category.entity.Answer;
import com.eyelevel.project.category.entity.AnswerAndTeacherProfile;
import com.eyelevel.project.category.entity.Question;
import com.eyelevel.project.category.entity.QuestionAndAnswer;
import com.eyelevel.project.category.entity.QuestionAndStudentProfile;
import com.eyelevel.project.category.repository.AnswerAndTeacherProfileRepository;
import com.eyelevel.project.category.repository.AnswerRepository;
import com.eyelevel.project.category.repository.QuestionAndAnswerRepository;
import com.eyelevel.project.category.repository.QuestionAndStudentProfileRepository;
import com.eyelevel.project.category.repository.QuestionRepository;
import com.eyelevel.project.common.paging.SelectCriteria;

@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	private final QuestionAndAnswerRepository questionAndAnswerRepository;
	private final QuestionAndStudentProfileRepository questionAndStudentProfileRepository;
	private final AnswerRepository answerRepository;
	private final AnswerAndTeacherProfileRepository answerAndTeacherProfileRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public QuestionService(QuestionRepository questionRepository,
			QuestionAndAnswerRepository questionAndAnswerRepository,
			QuestionAndStudentProfileRepository questionAndStudentProfileRepository, AnswerRepository answerRepository,
			AnswerAndTeacherProfileRepository answerAndTeacherProfileRepository, ModelMapper modelMapper) {
		this.questionRepository = questionRepository;
		this.questionAndAnswerRepository = questionAndAnswerRepository;
		this.questionAndStudentProfileRepository = questionAndStudentProfileRepository;
		this.answerRepository = answerRepository;
		this.answerAndTeacherProfileRepository = answerAndTeacherProfileRepository;
		this.modelMapper = modelMapper;
	}
	
	/* ????????? ?????? ????????? */
	public List<AnswerAndTeacherProfileDTO> findAnswerTeacherInfo(Long questionNo) {

		List<AnswerAndTeacherProfile> answerList = answerAndTeacherProfileRepository.findByQuestionNoLike(questionNo);
		
		return answerList.stream().map(profile -> modelMapper.map(profile, AnswerAndTeacherProfileDTO.class)).collect(Collectors.toList());
	}

	// [?????? ????????????] ????????? ?????? ??????
	public QuestionAndStudentProfileDTO findQuestionList(Long questionNo) {
		
		QuestionAndStudentProfile question = questionAndStudentProfileRepository.findById(questionNo).get();
		
		return modelMapper.map(question, QuestionAndStudentProfileDTO.class);
	}
	
	/* [??????] */
	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {
			/* ?????? ?????? */
			if("questionName".equals(searchCondition)) {
				count = questionAndAnswerRepository.countByquestionNameContaining(searchValue);
			}
			/* ?????? ?????? */
			if("questionContent".equals(searchCondition)) {
				count = questionAndAnswerRepository.countByquestionContentContaining(searchValue);
			}
		} else {
			count = (int)questionAndAnswerRepository.count();
		}

		return count;
	}
	
	/* [????????? ?????? ??? ??????] */
	public List<QuestionAndStudentProfileDTO> searchQuestionList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* ????????? ????????? ????????? ?????? ?????? ?????? */
		Pageable paging = PageRequest.of(index, count, Sort.by("questionNo"));

		List<QuestionAndStudentProfile> questionList = new ArrayList<QuestionAndStudentProfile>();
		if(searchValue != null) {

			/* ?????? ?????? */
			if("questionName".equals(selectCriteria.getSearchCondition())) {
				questionList = questionAndStudentProfileRepository.findByquestionNameContaining(selectCriteria.getSearchValue(), paging);
			}
			/* ?????? ?????? */
			if("questionContent".equals(selectCriteria.getSearchCondition())) {
				questionList = questionAndStudentProfileRepository.findByquestionContentContaining(selectCriteria.getSearchValue(), paging);
			}
		} else {
			questionList = questionAndStudentProfileRepository.findAll(paging).toList();
		}

		return questionList.stream().map(question -> modelMapper.map(question, QuestionAndStudentProfileDTO.class)).collect(Collectors.toList());
	}
	
	/* [?????? : ??????] ?????? / ????????? */
	@Transactional
	public void registNewQuestion(QuestionDTO newQuestion, StudentImpl student) {
		
		newQuestion.setStudentNo(student.getStudentNo());
		newQuestion.setQuestionCom("N");	// ???????????? N ?????? ?????? 
		questionRepository.save(modelMapper.map(newQuestion, Question.class));
	}

	/* [?????? : ?????????, ?????????] ?????? / ?????? */
	@Transactional
	public void registAnswer(AnswerDTO newAnswer, Long questionNo, TeacherImpl teacher) {
		
		newAnswer.setQuestionNo(questionNo);
		newAnswer.setTeacherNo(teacher.getTeacherNo());
		answerRepository.save(modelMapper.map(newAnswer, Answer.class));
	}

	/* [?????? : ?????????, ?????????] ?????? / ????????? */
	@Transactional
	public void deleteQuestion(Long questionNo) {
		/* ?????? ????????? ????????? ?????? ?????? */
		answerRepository.deleteByQuestionNo(questionNo);
		questionRepository.deleteById(questionNo);
	}

	/* [?????? : ?????????, ?????????] ???????????? ?????? / ????????? */
	@Transactional
	public void modifyQuestion(Long questionNo) {
		QuestionAndAnswer question = questionAndAnswerRepository.findById(questionNo).get();
		question.setQuestionCom("Y");
	}
	
	/* [?????? : ????????????] ?????? / ?????? */
	@Transactional
	public void modifyAnswer(AnswerDTO answer, Long answerNo) {
		Answer modifyAnswer = answerRepository.findById(answerNo).get();
		modifyAnswer.setAnswerContent(answer.getAnswerContent());
	}

	/* [?????? : ?????????, ??????] ?????? / ?????? */
	@Transactional
	public void deleteAnswer(Long answerNo) {
		answerRepository.deleteById(answerNo);
	}
}