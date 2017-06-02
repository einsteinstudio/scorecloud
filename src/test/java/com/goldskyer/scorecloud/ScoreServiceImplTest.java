package com.goldskyer.scorecloud;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.gmxx.service.BaseTest;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.dto.ScoreQueryDto;
import com.goldskyer.scorecloud.dto.ScoreResultDto;
import com.goldskyer.scorecloud.entities.Score;
import com.goldskyer.scorecloud.service.ScoreService;

public class ScoreServiceImplTest extends BaseTest
{
	@Autowired
	private BaseDao baseDao;

	@Autowired
	private ScoreService scoreService;

	@Test
	public void midfyScore()
	{
		Score score = new Score();
		score.setId("f22acbf3f07743e385527e97e820aa1f");
		score.setExamId("123213");
		score.setStudentId("123");
		score.setScore(12.99f);
		score.setSubjectId("21321");
		scoreService.modifyScore("xcxx", score);
	}

	@Test
	public void testAddScore()
	{
		Score score = new Score();
		score.setExamId("123213");
		score.setStudentId("123");
		score.setScore(12.1f);
		score.setSubjectId("21321");
		scoreService.addScore("xcxx", score);
	}

	@Test
	public void testQuery()
	{
		Score score = scoreService.queryUniqueScore("xcxx", "402880ea591830320159184274830006",
				"402880ea591830320159184296420007",
				"402880ea591250630159125308b40001");
		System.out.println(score);
	}

	@Rollback(false)
	public void testAdd()
	{
		Score score = new Score();
		score.setExamId("1");
		score.setSubjectId("1");
		score.setStudentId("1");
		score.setScore(99f);
		baseDao.add(score);
	}

	@Rollback(false)
	@Transactional
	@Test
	public void batchAdd()
	{
		for (int i = 0; i < 3; i++)
		{
			{
			Score score = new Score();
			score.setExamId("1");
			score.setSubjectId("1");
				score.setStudentId(i + "_");
			score.setScore(99f);
			baseDao.add(score);
			}
			{
				Score score = new Score();
				score.setExamId("1");
				score.setSubjectId("2");
				score.setStudentId(i + "_");
				score.setScore(98f);
			baseDao.add(score);
			}

		}
	}

	@Test
	public void testQueryScores()
	{
		List<String> choosedSubjectIds = new ArrayList<>();
		choosedSubjectIds.add("1");
		//choosedSubjectIds.add("2");
		ScoreQueryDto scoreQueryDto = new ScoreQueryDto();
		scoreQueryDto.setExamId("1");
		scoreQueryDto.setClassId("001");
		scoreQueryDto.setChoosedSubjectIds(choosedSubjectIds);
		scoreQueryDto.setSchId(ScoreCloudEnv.get().getSchId());
		ScoreResultDto resultDto = scoreService.queryScoresByScoreQueryDto(scoreQueryDto);
		System.out.println(resultDto.toString());
	}
}
