package com.goldskyer.gmxx.survey.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldskyer.common.utils.DateUtil;
import com.goldskyer.common.utils.StringUtil;
import com.goldskyer.core.controllers.CoreBaseController;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.survey.entities.AccountAnswer;
import com.goldskyer.gmxx.survey.entities.SurveyRecord;
import com.goldskyer.gmxx.survey.service.PaperService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("xasdas")
@RequestMapping("/xx/survey")
public class SurveyController extends CoreBaseController
{
	@Autowired
	private PaperService paperService;
	
	@Autowired
	private BaseDao baseDao;
	/**
	 * 提交答案，并且进行评分
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/submit.json",produces="application/json;charset=UTF-8")
    @ResponseBody
	public JsonData submit(HttpServletRequest request, @RequestParam String result, String fp, String selfInfo)
	{
		JsonData jsonData = JsonData.success();
		JSONObject resultJson = JSONObject.fromObject(result);
		JSONArray dataArray = resultJson.getJSONArray("data");
		String paperId = resultJson.getString("paperId");
		Map<String, String> selfMap = StringUtil.splitStringToMap(selfInfo, ",", "=");
		SurveyRecord surveyRecord = new SurveyRecord();
		surveyRecord.setFp(fp + StringUtils.trimToEmpty(selfMap.get("random")));
		surveyRecord.setCompany(selfMap.get("company"));
		surveyRecord.setDepartment(selfMap.get("department"));
		surveyRecord.setGender(selfMap.get("gender"));
		surveyRecord.setPaperId(paperId);
		surveyRecord.setCreateDate(new Date());
		surveyRecord.setName(StringUtils.trimToEmpty(selfMap.get("name")));
		surveyRecord.setSource(StringUtils.trimToEmpty(selfMap.get("source")));
		surveyRecord.setMobile(StringUtils.trimToEmpty(selfMap.get("mobile")));
		if (StringUtils.isNotBlank(selfMap.get("age")))
		{
			surveyRecord.setAge(Integer.valueOf(selfMap.get("age")));
		}
		String testId = DateUtil.date2String(new Date(), "yyyy-MM-dd_HH_mm_ss");
		List<AccountAnswer> accountAnswers = new ArrayList<>();
		for(int i=0;i<dataArray.size();i++)
		{
			JSONObject jsonObject = dataArray.getJSONObject(i);
			AccountAnswer accountAnswer = new AccountAnswer();
			accountAnswer.setAccountId(surveyRecord.getFp());//匿名用户答题使用浏览器指纹
			accountAnswer.setPaperId(jsonObject.getString("paperId"));
			accountAnswer.setQuestionId(jsonObject.getString("questionId"));
			accountAnswer.setCreateDate(new Date());
			accountAnswer.setAnswerId(jsonObject.getString("answerId"));
			accountAnswer.setTestId(testId);
			accountAnswers.add(accountAnswer);
		}
//		if(!paperId.equals("paper004"))
//		{
//			baseDao.add(accountAnswers);
//		}
		baseDao.add(accountAnswers);
		if(paperId.equals("paper001"))
		{
			jsonData.setMsg(getResultMsgByPressTest(accountAnswers, surveyRecord));
		}
		else if(paperId.equals("paper002"))
		{
			jsonData.setMsg(getResultMsgByGloomyTest(accountAnswers, surveyRecord));
		}
		else if(paperId.equals("paper003"))
		{
			jsonData.setMsg(getResultMsgByWorryTest(accountAnswers, surveyRecord));
		}
		else if(paperId.equals("paper004"))
		{
			List<String> aList = getResultMsgBySCLTest(accountAnswers, surveyRecord);
			jsonData.data.put("reports",aList);
			StringBuffer sb = new StringBuffer();
			for(String a:aList)
			{
				sb.append("<div>"+a+"</div>");
			}
			jsonData.setMsg(sb.toString());
		}
		surveyRecord.setResult(jsonData.getMsg());
		baseDao.add(surveyRecord);
		return jsonData;
	}
	
	/**
	 * 获取压力测试的结果
	 * @param accountAnswers
	 * @return
	 */
	private String getResultMsgByPressTest(List<AccountAnswer> accountAnswers, SurveyRecord surveyRecord)
	{
		Double score=caculateScore(accountAnswers);
		log.info("当前用户得分：" + score);
		surveyRecord.setScore(score);
		if(score<50)
		{
			return "<p>严重压力型</p><p>压力零界点，可能出现适应障碍的问题，应高度重视，积极寻求心理咨询）</p>";
		}
		else if(score<=70)
		{
			return "<p>感受压力型</p><p>目前正处于一定压力下，需要一定专业疏导</p>";
		}
		else if(score<=115)
		{
			return "<p>轻微紧张型</p><p>需多加留意，注意调适休息</p>";
		}
		else 
		{
			return "<p>适应良好型</p><p>可以很好的调节情绪，应对压力</p>";
		}
	}
	
	private String getResultMsgByWorryTest(List<AccountAnswer> accountAnswers, SurveyRecord surveyRecord)
	{
		Double score=caculateScore(accountAnswers);
		log.info("当前用户得分：" + score);
		surveyRecord.setScore(score);
		if (score < 50)
		{
			return "<p>没有焦虑</p><p>你在过去的七天，焦虑水平正常，可以说你基本上是淡定地过好每一天。</p>";
		}
		else if (score <= 59)
		{
			return "<p>轻度焦虑</p><p>你在过去的七天，很少时候显出急躁紧张、心悸或者胃疼。人群中大约有百分之33.3%的人与你同在。</p>";
		}
		else if (score <= 69)
		{
			return "<p>中度焦虑</p><p>在过去的七天，可能有一半的时间里你都感到处处都有些不对而烦躁，觉得头疼胃紧，气短，有时还难以入睡。也许还会莫名感到肌肉酸痛。人群中大约有16.6%的人与你同在。你也许最近冲刺得太厉害，让你的身心都有些疲惫了。</p>";
		}
		else 
		{
			return "<p>重度焦虑</p><p>在过去的七天，压力像块巨石压得你透不过气，你可能被各种焦躁的情绪环绕，让你感到胸闷气短，浑身酸痛，即使不运动也浑身冒汗。你可能有些拉肚子，而且难以入睡。有一种想咆哮但哮不出来的惆怅。首先你需要想想近期是不是有什么事情严重影响了你的情绪，如果你已经确定没有，且这样的情况已经至少持续了一个月，那么你可能需要寻求专业帮助来缓解你的焦虑。</p>";
		}
	}
	
	private String getResultMsgByGloomyTest(List<AccountAnswer> accountAnswers, SurveyRecord surveyRecord)
	{
		Double score = caculateScore(accountAnswers);
		log.info("当前用户得分：" + score);
		surveyRecord.setScore(score);
		if (score < 53)
		{
			return "<p>没有抑郁</p><p>你现在的心理状况非常的好，请继续保持你的良好心态，想提醒你的是如果累了就休息休息。</p>";
		}
		else if (score <= 62)
		{
			return "<p>轻度抑郁</p><p>给自己一个休整期和冷却期，让情绪有自然的出口。当然，这不意味着要自我封闭，而是多与人交往、接近大自然，用享受长假的心态来度过情绪的低谷。</p>";
		}
		else if (score <= 72)
		{
			return "<p>中度抑郁</p><p>情绪特别低落、思维迟缓、动作或行为减少。应借助自身调节与专业心理治疗来进行治疗。</p>";
		}
		else
		{
			return "<p>重度抑郁</p><p>建议你尽快去接受专业帮助。因为当你需要援助而没有及时地寻求援助时，你可能被你的问题击毁。</p>";
		}
	}
	
	private List<String> getResultMsgBySCLTest(List<AccountAnswer> accountAnswers, SurveyRecord surveyRecord)
	{
		List<String> qutihua=new ArrayList<>();
		List<String> qiangpo=new ArrayList<>();
		List<String> renji=new ArrayList<>();
		List<String> yiyu=new ArrayList<>();
		List<String> jiaolv=new ArrayList<>();
		List<String> didui=new ArrayList<>();
		List<String> kongbu=new ArrayList<>();
		List<String> pianzhi=new ArrayList<>();
		List<String> jingshen=new ArrayList<>();
		
		for(int i=0;i<accountAnswers.size();i++)
		{
			AccountAnswer accountAnswer=accountAnswers.get(i);
			if (",SCL1,SCL4,SCL12,SCL27,SCL40,SCL42,SCL48,SCL49,SCL52,SCL53,SCL56,SCL58,"
					.contains(",SCL" + (i + 1) + ","))
			{
				qutihua.add(accountAnswer.getAnswerId());
			}
			if (",SCL3,SCL9,SCL10,SCL28,SCL38,SCL45,SCL46,SCL51,SCL55,SCL65,".contains(",SCL" + (i + 1) + ","))
			{
				qiangpo.add(accountAnswer.getAnswerId());
			}
	
			if (",SCL6,SCL21,SCL34,SCL36,SCL37,SCL41,SCL61,SCL69,SCL73,".contains(",SCL" + (i + 1) + ","))
			{
				renji.add(accountAnswer.getAnswerId());
			}
			if (",SCL5,SCL14,SCL15,SCL20,SCL22,SCL26,SCL29,SCL30,SCL31,SCL32,SCL54,SCL71,SCL79,"
					.contains(",SCL" + (i + 1) + ","))
			{
				yiyu.add(accountAnswer.getAnswerId());
			}
			
			if (",SCL2,SCL17,SCL23,SCL33,SCL39,SCL57,SCL72,SCL78,SCL80,SCL86,".contains(",SCL" + (i + 1) + ","))
			{
				jiaolv.add(accountAnswer.getAnswerId());
			}
			if (",SCL11,SCL24,SCL63,SCL67,SCL74,SCL81,".contains(",SCL" + (i + 1) + ","))
			{
				didui.add(accountAnswer.getAnswerId());
			}
			if (",SCL13,SCL25,SCL47,SCL50,SCL70,SCL75,SCL82,".contains(",SCL" + (i + 1) + ","))
			{
				kongbu.add(accountAnswer.getAnswerId());
			}
			if (",SCL8,SCL18,SCL43,SCL68,SCL76,SCL83,".contains(",SCL" + (i + 1) + ","))
			{
				pianzhi.add(accountAnswer.getAnswerId());
			}
			
			
			if (",SCL7,SCL16,SCL35,SCL62,SCL77,SCL84,SCL85,SCL87,SCL88,SCL90,".contains(",SCL" + (i + 1) + ","))
			{
				jingshen.add(accountAnswer.getAnswerId());
			}
		}
		
		Double qutihuaScore=paperService.caculateScore(qutihua)/12.0;
		Double qiangpoScore=paperService.caculateScore(qiangpo)/10.0;
		Double renjiScore=paperService.caculateScore(renji)/9.0;
		
		Double yiyuScore=paperService.caculateScore(yiyu)/13.0;
		Double jiaolvScore=paperService.caculateScore(jiaolv)/10.0;
		Double diduiScore=paperService.caculateScore(didui)/6.0;
		
		Double kongbuScore=paperService.caculateScore(kongbu)/7.0;
		Double pianzhiScore=paperService.caculateScore(pianzhi)/6.0;
		Double jingshenScore=paperService.caculateScore(jingshen)/10.0;
		List<String> reports = new ArrayList<>();
 		if(qutihuaScore>3.0)
		{
 			reports.add("躯体化症状：在身体上有较明显的不适感，并常伴有头痛、肌肉酸痛等症状。");
		}
 		if(qiangpoScore>3.0)
		{
 			reports.add("强迫症状：较明显。");
		}
 		if(renjiScore>3.0)
		{
 			reports.add("人际关系敏感：表明个体人际关系较为敏感，人际交往中自卑感较强，并伴有行为症状（如坐立不安，退缩等）。");
		}
 		
 		if(yiyuScore>3.0)
		{
 			reports.add("抑郁症状：抑郁程度较强，生活缺乏足够的兴趣，缺乏运动活力。");
		}
 		if(jiaolvScore>3.0)
		{
 			reports.add("焦虑症状：较易焦虑，易表现出烦躁、不安静和神经过敏。");
		}
 		if(diduiScore>3.0)
		{
 			reports.add("敌对情绪：易表现出敌对的思想、情感和行为。");
		}
 		
 		if(kongbuScore>3.0)
		{
 			reports.add("恐怖症状：较为明显，常表现出社交、广场和人群恐惧。");
		}
 		if(pianzhiScore>3.0)
		{
 			reports.add("偏执症状：偏执症状明显，较易猜疑和敌对。");
		}
 		if(jingshenScore>3.0)
		{
 			reports.add("精神病性：表现出一定的精神病性。");
		}
		if (reports.size() == 0)
		{
			reports.add("无明显症状。");
		}
		surveyRecord.setResult(reports.toString());
 		return reports;
	}
	
	private Double caculateScore(List<AccountAnswer> accountAnswers)
	{
		double score=0.0;
		List<String> aIds=new ArrayList<>();
		for(AccountAnswer accountAnswer:accountAnswers)
		{
			aIds.add(accountAnswer.getAnswerId());
		}
		 score = paperService.caculateScore(aIds);
		return score;
	}
}
