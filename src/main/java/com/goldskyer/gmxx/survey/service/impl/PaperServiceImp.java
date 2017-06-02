package com.goldskyer.gmxx.survey.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.core.service.impl.CoreServiceImp;
import com.goldskyer.gmxx.survey.entities.Paper;
import com.goldskyer.gmxx.survey.service.PaperService;
@Service
public class PaperServiceImp  extends CoreServiceImp implements PaperService{

	@Override
	@Transactional
	public void addPaper(Paper paper) {
		baseDao.add(paper);
	}

	@Override
	public void deletePaper(String paperId) {
		baseDao.delete(Paper.class,paperId);
	}

	@Override
	public void modifyPaper(Paper paper) {
		baseDao.modify(paper);
	}

	@Override
	@Transactional
	public Paper queryPaperById(String paperId) {
		return queryPaperByIdSortByWeight(paperId);
	}
	
	public  Paper queryPaperByIdSortByWeight(String paperId)
	{
		Paper paper=baseDao.query(Paper.class,paperId);
		return paper;
	}
	
	public Double caculateScore(List<String> aIds)
	{
		Map map = new HashMap();
		map.put("ids", aIds);
		return (Double) baseDao.queryUnique("select sum(score) from Answer where id in :ids",map);
	}
}
