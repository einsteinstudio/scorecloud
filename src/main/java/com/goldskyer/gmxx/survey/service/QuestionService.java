package com.goldskyer.gmxx.survey.service;

import com.goldskyer.gmxx.survey.entities.Question;

public interface QuestionService {
	public void addQuestion(Question question);
	
	public void modifyQuestion(Question question);
	
	public Question queryQuestionById(String questionId);
	
}
