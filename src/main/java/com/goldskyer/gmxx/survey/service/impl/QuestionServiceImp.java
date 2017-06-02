package com.goldskyer.gmxx.survey.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.goldskyer.core.service.impl.CoreServiceImp;
import com.goldskyer.gmxx.survey.entities.Question;
import com.goldskyer.gmxx.survey.service.QuestionService;

@Service
public class QuestionServiceImp extends CoreServiceImp implements QuestionService{

	@Override
	public void addQuestion(Question question) {
		question.setCreateDate(new Date());
		question.setUpdateDate(new Date());
		baseDao.add(question);
		
	}

	@Override
	public void modifyQuestion(Question question) {
		baseDao.modify(question);
	}
	
	public Question queryQuestionById(String questionId)
	{
		return baseDao.query(Question.class,questionId);
	}

}
