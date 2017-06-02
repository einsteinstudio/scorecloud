package com.goldskyer.gmxx.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.gmxx.survey.entities.Answer;
import com.goldskyer.gmxx.survey.entities.Paper;
import com.goldskyer.gmxx.survey.entities.Question;
import com.goldskyer.gmxx.survey.service.PaperService;

import junit.framework.Assert;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class PaperServcieTest extends BaseTest {
	
	@Autowired
	private PaperService paperService;
	
	@Test
	public void queryPaperId()
	{
		Paper paper = paperService.queryPaperById("001");
		Assert.assertNotNull(paper);
		System.out.println(paper.getTitle());
		System.out.println(paper.getPartPapers().size());
		for(Question question:paper.getPartPapers().iterator().next().getQuestions())
		{
//			System.out.println(question.getQuestionName());
//			for(Answer answer:question.getAnswers())
//			{
//				System.out.println(answer.getCurOption()+"."+answer.getAnswerContent());
//			}
			JsonConfig jsonConfig = new JsonConfig();  
			jsonConfig.setExcludes( new String[]{"question"});  
			System.out.println(JSONObject.fromObject(question,jsonConfig));
			System.out.println("-----------------end question");
		}
	}
	
	@Test
	public void testQueryPaperByIdSortByWeight()
	{
		
		Paper paper = paperService.queryPaperByIdSortByWeight("001");
		Assert.assertNotNull(paper);
		System.out.println(paper.getTitle());
		System.out.println(paper.getPartPapers().size());
		for(Question question:paper.getPartPapers().iterator().next().getQuestions())
		{
			System.out.println(question.getQuestionName());
			for(Answer answer:question.getAnswers())
			{
				System.out.println(answer.getCurOption()+"."+answer.getAnswerContent());
			}
			System.out.println("-----------------end question");
		}
	}
}
