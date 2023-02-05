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
	
	/* 게시글 댓글 리스트 */
	public List<AnswerAndTeacherProfileDTO> findAnswerTeacherInfo(Long questionNo) {

		List<AnswerAndTeacherProfile> answerList = answerAndTeacherProfileRepository.findByQuestionNoLike(questionNo);
		
		return answerList.stream().map(profile -> modelMapper.map(profile, AnswerAndTeacherProfileDTO.class)).collect(Collectors.toList());
	}

	// [질문 학생정보] 게시판 내용 정보
	public QuestionAndStudentProfileDTO findQuestionList(Long questionNo) {
		
		QuestionAndStudentProfile question = questionAndStudentProfileRepository.findById(questionNo).get();
		
		return modelMapper.map(question, QuestionAndStudentProfileDTO.class);
	}
	
	/* [검색] */
	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {
			/* 제목 검색 */
			if("questionName".equals(searchCondition)) {
				count = questionAndAnswerRepository.countByquestionNameContaining(searchValue);
			}
			/* 내용 검색 */
			if("questionContent".equals(searchCondition)) {
				count = questionAndAnswerRepository.countByquestionContentContaining(searchValue);
			}
		} else {
			count = (int)questionAndAnswerRepository.count();
		}

		return count;
	}
	
	/* [페이징 처리 및 검색] */
	public List<QuestionAndStudentProfileDTO> searchQuestionList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("questionNo"));

		List<QuestionAndStudentProfile> questionList = new ArrayList<QuestionAndStudentProfile>();
		if(searchValue != null) {

			/* 제목 검색 */
			if("questionName".equals(selectCriteria.getSearchCondition())) {
				questionList = questionAndStudentProfileRepository.findByquestionNameContaining(selectCriteria.getSearchValue(), paging);
			}
			/* 내용 검색 */
			if("questionContent".equals(selectCriteria.getSearchCondition())) {
				questionList = questionAndStudentProfileRepository.findByquestionContentContaining(selectCriteria.getSearchValue(), paging);
			}
		} else {
			questionList = questionAndStudentProfileRepository.findAll(paging).toList();
		}

		return questionList.stream().map(question -> modelMapper.map(question, QuestionAndStudentProfileDTO.class)).collect(Collectors.toList());
	}
	
	/* [접속 : 학생] 저장 / 게시글 */
	@Transactional
	public void registNewQuestion(QuestionDTO newQuestion, StudentImpl student) {
		
		newQuestion.setStudentNo(student.getStudentNo());
		newQuestion.setQuestionCom("N");	// 답변여부 N 으로 고정 
		questionRepository.save(modelMapper.map(newQuestion, Question.class));
	}

	/* [접속 : 관리자, 선생님] 등록 / 댓글 */
	@Transactional
	public void registAnswer(AnswerDTO newAnswer, Long questionNo, TeacherImpl teacher) {
		
		newAnswer.setQuestionNo(questionNo);
		newAnswer.setTeacherNo(teacher.getTeacherNo());
		answerRepository.save(modelMapper.map(newAnswer, Answer.class));
	}

	/* [접속 : 관리자, 선생님] 삭제 / 게시글 */
	@Transactional
	public void deleteQuestion(Long questionNo) {
		/* 자식 속성의 댓글을 먼저 지움 */
		answerRepository.deleteByQuestionNo(questionNo);
		questionRepository.deleteById(questionNo);
	}

	/* [접속 : 관리자, 선생님] 답변여부 수정 / 게시글 */
	@Transactional
	public void modifyQuestion(Long questionNo) {
		QuestionAndAnswer question = questionAndAnswerRepository.findById(questionNo).get();
		question.setQuestionCom("Y");
	}
	
	/* [접속 : 교사본인] 수정 / 댓글 */
	@Transactional
	public void modifyAnswer(AnswerDTO answer, Long answerNo) {
		Answer modifyAnswer = answerRepository.findById(answerNo).get();
		modifyAnswer.setAnswerContent(answer.getAnswerContent());
	}

	/* [접속 : 관리자, 교사] 삭제 / 댓글 */
	@Transactional
	public void deleteAnswer(Long answerNo) {
		answerRepository.deleteById(answerNo);
	}
}