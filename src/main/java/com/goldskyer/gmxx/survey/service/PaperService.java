package com.goldskyer.gmxx.survey.service;

import java.util.List;

import com.goldskyer.gmxx.survey.entities.Paper;

public interface PaperService {
	
	public Paper queryPaperById(String  paperId);
	
	public void addPaper(Paper paper) ;
	public void deletePaper(String papaerId);
	
	public void modifyPaper(Paper paper);
	public  Paper queryPaperByIdSortByWeight(String paperId);
	
	/**
	 * 查询答案得分
	 * @param aIds
	 * @return
	 */
	public Double caculateScore(List<String> aIds);
}
