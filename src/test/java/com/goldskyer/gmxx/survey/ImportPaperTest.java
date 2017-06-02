package com.goldskyer.gmxx.survey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.gmxx.service.BaseTest;
import com.goldskyer.gmxx.survey.entities.Answer;
import com.goldskyer.gmxx.survey.entities.Paper;
import com.goldskyer.gmxx.survey.entities.PartPaper;
import com.goldskyer.gmxx.survey.entities.Question;
import com.goldskyer.gmxx.survey.service.PaperService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ImportPaperTest extends BaseTest{
	@Autowired
	private PaperService paperService;
	@Autowired
	private BaseDao baseDao;
	
	@Test
	public void testImport() throws FileNotFoundException, IOException
	{
		String domain="心理测试";
		String questionType="心理测试";
		String author = "金天凡";
		List<String> lines = IOUtils.readLines(new FileInputStream(new File("/Users/jintianfan/Documents/MyWorkspace/gmxx/src/test/resources/paper004.txt")));
		
		Paper paper = new Paper();
		paper.setTheme("normal");
		paper.setTitle("人格测试");
		PartPaper partPaper = new PartPaper();
		partPaper.setPaper(paper);
		paper.getPartPapers().add(partPaper);
		for(String line:lines)
		{
			line  = StringUtils.trim(line);
			//log.info("读取行："+line);
			if(line.matches("[0-9]*[、.．].*"))
			{
				Question question = new Question();
				partPaper.getQuestions().add(question);
				//log.info("新一题目");
				//question.setId(UUID.randomUUID().toString().replace("-", ""));
				String[] items = line.split("[、.]");
				question.setQuestionName(StringUtils.trim(items[1]));
				question.setDomain(domain);
				question.setType(questionType);
				question.setAuthor(author);
				question.setWeight(Double.valueOf(""+(partPaper.getQuestions().size())));
				question.setPartPaper(partPaper);
				log.info("序号："+items[0]);
				log.info("题目内容："+items[1]);
			}
			else
			{
				if(StringUtils.isBlank(line)){
					log.info("该行为空");
					continue;
				}
				String[] lineSubs = StringUtils.trim(line).split(" ");
				log.info("cur line:"+line);
				for(String sub:lineSubs)
				{
					String subBlocks = StringUtils.trim(sub);
					if(StringUtils.isBlank(subBlocks))
					{
						continue;
					}
					if(subBlocks.contains(":"))
					{
						String [] ss = subBlocks.split(":");
						Answer answer = new Answer();
						answer.setQuestion(partPaper.getQuestions().get(partPaper.getQuestions().size()-1));
						answer.setAnswerContent(StringUtils.trim(ss[0]));
						answer.setScore(Double.valueOf(StringUtils.trim(ss[1])));
						answer.setCurOption(""+(partPaper.getQuestions().get(partPaper.getQuestions().size()-1).getAnswers().size()+1));
						partPaper.getQuestions().get(partPaper.getQuestions().size()-1).getAnswers().add(answer);
						log.info("题目答案："+subBlocks);
					}
					else
					{
						Answer answer = new Answer();
						answer.setQuestion(partPaper.getQuestions().get(partPaper.getQuestions().size()-1));
						answer.setAnswerContent(StringUtils.trim(subBlocks));
						answer.setCurOption(""+(partPaper.getQuestions().get(partPaper.getQuestions().size()-1).getAnswers().size()+1));
						answer.setScore(Double.valueOf(answer.getCurOption())-1.0);
						partPaper.getQuestions().get(partPaper.getQuestions().size()-1).getAnswers().add(answer);
						log.info("题目答案："+subBlocks);
					}
				}				
			}
		}
		paperService.addPaper(paper);
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.setExcludes( new String[]{"question","paper"});
		//System.out.println(JSONObject.fromObject(paper,jsonConfig));
	}
	
	
	@Test
	public void testQueryPaper()
	{
		Paper paper = paperService.queryPaperById("8a53b782540d766e01540d767bcf0000");
		for(PartPaper pp:paper.getPartPapers())
		{
			System.out.println("xx"+pp.getWeight());
		}
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.setExcludes( new String[]{"question","paper","partPaper"});
		System.out.println(JSONObject.fromObject(paper,jsonConfig));	
	}
	
	@Test
	public void deletePaper()
	{
		baseDao.delete(Paper.class, "8a72f0c253eeb7560153eeb762da0000");
	}
}
