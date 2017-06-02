package com.goldskyer.scorecloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.common.exceptions.BusinessException;
import com.goldskyer.common.utils.DateUtil;
import com.goldskyer.common.utils.StringUtil;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.scorecloud.dto.ClassDto;
import com.goldskyer.scorecloud.dto.ScoreDto;
import com.goldskyer.scorecloud.dto.ScoreQueryDto;
import com.goldskyer.scorecloud.dto.ScoreResultDto;
import com.goldskyer.scorecloud.dto.StudentScoreDto;
import com.goldskyer.scorecloud.entities.Score;
import com.goldskyer.scorecloud.entities.Subject;
import com.goldskyer.scorecloud.service.ClassService;
import com.goldskyer.scorecloud.service.ExamService;
import com.goldskyer.scorecloud.service.GradeService;
import com.goldskyer.scorecloud.service.ScoreService;
import com.goldskyer.scorecloud.service.StudentService;
import com.goldskyer.scorecloud.service.SubjectService;

@Service
@SuppressWarnings("unchecked")

public class ScoreServiceImpl implements ScoreService
{
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private ClassService classService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private ExamService examService;
	@Autowired
	private StudentService studentService;


	@Autowired
	private SubjectService subjectService;




	@Transactional
	public void addOrUpdateScoreDto(String schId, ScoreDto scoreDto)
	{
		List<ScoreDto> scoreDtos = new ArrayList<>();
		scoreDtos.add(scoreDto);
		batchAddScoreDtos(schId, scoreDtos);
	}

	/**
	 * 批量成绩插入
	 * @param scoreDtos
	 */
	@Transactional
	public void batchAddScoreDtos(String schId, List<ScoreDto> scoreDtos)
	{
		List<Score> scores=new ArrayList<>();
		for(ScoreDto scoreDto:scoreDtos)
		{
			Score score = new Score();
			score.setId(scoreDto.getId());
			score.setExamId(scoreDto.getExamId());
			score.setSubjectId(scoreDto.getSubjectId());
			score.setScore(scoreDto.getScore());
			score.setStudentId(scoreDto.getStudentId());
			scores.add(score);
		}
		for (Score score : scores)
		{
			addOrUpdateScore(schId,score);
		}
	}

	@Transactional
	public String addOrUpdateScore(String schId, Score score)
	{
		Score existScore = queryUniqueScore(schId, score.getExamId(), score.getSubjectId(), score.getStudentId());
		if (existScore != null)
		{
			existScore.setScore(score.getScore());
			modifyScore(schId, existScore);
			return existScore.getId();
		}
		else
		{
			return addScore(schId, score);
		}
	}

	public void modifyScore(String schId, Score score)
	{

		Map map = new HashMap<>();
		map.put("id", score.getId());
		map.put("examId", score.getExamId());
		map.put("subjectId", score.getSubjectId());
		map.put("studentId", score.getStudentId());
		map.put("score", score.getScore());
		if (score.getScore() != null)
		{
			baseDao.executeSql(
					"update " + schId
							+ "_score set examId=:examId,subjectId=:subjectId,studentId=:studentId,score=:score where id=:id",
					map);
		}
		else
		{
			baseDao.executeSql(
					"update " + schId
							+ "_score set examId=:examId,subjectId=:subjectId,studentId=:studentId,score=null where id=:id",
					map);
		}

	}

	/**
	 * 存粹添加分数，如果分数为null，则不添加
	 * @param schId
	 * @param score
	 * @return 返回null，则表示添加失败
	 */
	public String addScore(String schId, Score score)
	{
		if (score.getScore() == null)
		{
			return null;
		}
		Map map = new HashMap<>();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		map.put("id", id);
		map.put("examId", score.getExamId());
		map.put("subjectId", score.getSubjectId());
		map.put("studentId", score.getStudentId());
		map.put("score", score.getScore());
		baseDao.executeSql("insert into " + schId
				+ "_score (id,examId,subjectId,studentId,score) values (:id,:examId,:subjectId,:studentId,:score)",
				map);
		return id;
	}

	public Score queryUniqueScore(String schId, String examId, String subjectId, String studentId)
	{
		Map map = new HashMap<>();
		map.put("examId", examId);
		map.put("subjectId", subjectId);
		map.put("studentId", studentId);
		Object object = baseDao.querySqlUnique(
				"select id,examId,subjectId,studentId,score from " + schId
						+ "_score where examId=:examId and subjectId=:subjectId and studentId=:studentId",
				map);
		if (object == null)
			return null;
		return buildScoreFromObject(object);
	}

	private Score buildScoreFromObject(Object o)
	{
		Object[] oo = (Object[]) o;
		Score score = new Score();
		score.setId(oo[0].toString());
		score.setExamId(oo[1].toString());
		score.setSubjectId(oo[2].toString());
		score.setStudentId(oo[3].toString());
		if (oo[4] != null)
			score.setScore(Float.valueOf(oo[4].toString()));
		return score;
	}





	/**
	 *  考试成绩查询(按班级查询)
	 *  聚合计算放在内存中进行.
	 *  group by 需要SET  @@GLOBAL.sql_mode = ''	
	 *  group_concat 在mysql5.1版本会返回Blob，需要使用二进制方式读取，真垃圾
	 * @param scoreQueryDto
	 * @return  ss:11;bb:22;
	 */
	public ScoreResultDto queryScoresByScoreQueryDto(ScoreQueryDto scoreQueryDto)
	{
		//设置默认的科目。
		if (scoreQueryDto.getChoosedSubjectIds() == null || scoreQueryDto.getChoosedSubjectIds().isEmpty())
		{
			scoreQueryDto.setChoosedSubjectIds(subjectService.querySubjectIdsByExamId(scoreQueryDto.getExamId()));
		}
		ScoreResultDto scoreResultDto = new ScoreResultDto();
		scoreResultDto.setExamId(scoreQueryDto.getExamId());
		scoreResultDto.setExamDto(examService.queryExamDtoById(scoreResultDto.getExamId()));
		//if(StringUtils.isNotBlank(scoreQueryDto.get))
		// 
		StringBuffer queryHql = new StringBuffer(
				"select stu.id,stu.studentNo,stu.studentName,group_concat( concat(subjectId,'=',score) SEPARATOR ';') ,stu.classId"
						+ " from  (select * from student where 1=1 ");

		if (StringUtils.isNotBlank(scoreQueryDto.getClassId()))
		{
			queryHql.append("  and classId='" + scoreQueryDto.getClassId() + "'");
		}
		//判断年级
		if (StringUtils.isNotBlank(scoreQueryDto.getGradeId()))
		{
			queryHql.append(
					"  and classId in (select id from class where gradeId='" + scoreQueryDto.getGradeId() + "')");
		}
		if (StringUtils.isNotBlank(scoreQueryDto.getStudentNo()))
		{
			queryHql.append(" and studentNo like '%" + scoreQueryDto.getStudentNo() + "%'");
		}
		if (StringUtils.isNotBlank(scoreQueryDto.getStudentName()))
		{
			queryHql.append(" and studentName like '%" + scoreQueryDto.getStudentName() + "%'");
		}
		if (StringUtils.isNotBlank(scoreQueryDto.getNameno()))
		{
			queryHql.append(" and (studentName like '%" + scoreQueryDto.getNameno()
					+ "%' or studentNo like '%" + scoreQueryDto.getNameno() + "%'  )");
		}
		//) s  on s.studentId=stu.id
		queryHql.append(") stu left join (select * from " + scoreQueryDto.getSchId() + "_score where 1=1  ");
		//过滤exam列
		if (StringUtils.isBlank(scoreQueryDto.getExamId()))
		{
			throw new BusinessException("请指定一个考试，否则无法查询");
		}
		if (StringUtils.isNotBlank(scoreQueryDto.getExamId()))
		{
			queryHql.append(" and (examId='" + scoreQueryDto.getExamId() + "' or examId is null) ");
		}
		if (scoreQueryDto.getChoosedSubjectIds() != null && scoreQueryDto.getChoosedSubjectIds().size() > 0)
		{
			queryHql.append(" and (subjectId in :subjectIds or subjectId is null)");
		}
		queryHql.append(") s  on s.studentId=stu.id");
		//查询各列的主题
		scoreResultDto
				.setSubjects(subjectService.querySubjectsByChoosedSujectIds(scoreQueryDto.getChoosedSubjectIds()));
		queryHql.append(" group by stu.studentNo order by stu.studentNo ");
		//queryHql.append(" order by stu.studentNo,s.subjectId");
		Map map =new HashMap<>();
		map.put("subjectIds", scoreQueryDto.getChoosedSubjectIds());
		List<Object[]> list = baseDao.querySql(queryHql.toString(), map, 0, 10000000);
		//获取班级排名和年级排名
		Map<String, Integer> classRankMap = new HashMap<>();
		Map<String, Integer> gradeRankMap = new HashMap<>();
		Map<String, Map<String, Integer>> subjectsClassRankMap = new HashMap<>();
		Map<String, Map<String, Integer>> subjectsGradeRankMap = new HashMap<>();
		if (list != null && list.size() == 1)//如果查询结果只有一个学生，那么显示这个学生的考试明细
		{
			scoreQueryDto.setClassId(list.get(0)[4].toString());
		}
		if (StringUtils.isNotBlank(scoreQueryDto.getClassId()))//指定了班级,优先使用班级
		{
			ClassDto classDto = classService.queryClassDtoById(scoreQueryDto.getClassId());
			scoreResultDto.setClassDto(classDto);
			scoreResultDto.setGradeDto(gradeService.queryGradeDto(classDto.getGradeId()));
			classRankMap = caculateTotalClassRankMap(scoreQueryDto.getSchId(), scoreQueryDto.getExamId(),
					scoreQueryDto.getClassId());
			gradeRankMap = caculateGradeRankMap(scoreQueryDto.getSchId(), scoreQueryDto.getExamId(),
					classDto.getGradeId());
			subjectsClassRankMap = caculateSubjectsClassRankMap(scoreQueryDto.getSchId(), scoreResultDto.getSubjects(),
					classDto.getGradeId(),
					classDto.getId());

			subjectsGradeRankMap = caculateSubjectsGradeRankMap(scoreQueryDto.getSchId(), scoreResultDto.getSubjects(),
					classDto.getGradeId());
		}
		else if (StringUtils.isNotBlank(scoreQueryDto.getGradeId()))//指定年级查询
		{
			scoreResultDto.setGradeDto(gradeService.queryGradeDto(scoreQueryDto.getGradeId()));
			classRankMap = caculateClassRankMapByGradeId(scoreQueryDto.getSchId(), scoreQueryDto.getExamId(),
					scoreQueryDto.getGradeId());
			gradeRankMap = caculateGradeRankMap(scoreQueryDto.getSchId(), scoreQueryDto.getExamId(),
					scoreQueryDto.getGradeId());
			subjectsClassRankMap = caculateSubjectsClassRankMapByGradeId(scoreQueryDto.getSchId(),
					scoreResultDto.getSubjects(),
					scoreQueryDto.getGradeId());
			subjectsGradeRankMap = caculateSubjectsGradeRankMap(scoreQueryDto.getSchId(), scoreResultDto.getSubjects(),
					scoreQueryDto.getGradeId());
		}
		for (Object[] oo : list)
		{
			StudentScoreDto studentScoreDto=new StudentScoreDto();
			studentScoreDto.setStudentId(oo[0].toString());
			studentScoreDto.setStudentNo(oo[1].toString());
			studentScoreDto.setStudentName(oo[2].toString());
			if (oo[3] != null)
			{
				String text = oo[3].toString();
				if (oo[3] instanceof byte[])
				{
					text = new String((byte[]) oo[3]);
				}
				studentScoreDto.setSubjectScores(
						convertToScoreListByChoosedSubjectIds(text,
						scoreQueryDto.getChoosedSubjectIds()));
			}
			else
			{
				for (String id : scoreQueryDto.getChoosedSubjectIds())
				{
					studentScoreDto.getSubjectScores().add(null);
				}
			}
			caculateScoreData(studentScoreDto);
			studentScoreDto.setClassRank(classRankMap.get(studentScoreDto.getStudentId()));
			studentScoreDto.setGradeRank(gradeRankMap.get(studentScoreDto.getStudentId()));
			//科目班级排名
			if(subjectsClassRankMap!=null &&!subjectsClassRankMap.isEmpty())
			{
				for (Subject subject : scoreResultDto.getSubjects())
				{
					studentScoreDto.getSubjectClassRanks()
							.add(subjectsClassRankMap.get(subject.getId()).get(studentScoreDto.getStudentId()));
				}
			}
			//科目年级排名
			if (subjectsGradeRankMap != null && !subjectsGradeRankMap.isEmpty())
			{
				for (Subject subject : scoreResultDto.getSubjects())
				{
					studentScoreDto.getSubjectGradeRanks()
							.add(subjectsGradeRankMap.get(subject.getId()).get(studentScoreDto.getStudentId()));
				}
			}
			scoreResultDto.getStudentScoreDtos().add(studentScoreDto); //添加数据

		}
		setscoreResultTitle(scoreResultDto);
		return scoreResultDto;
	}
	
	/**
	 *设置导出考试的成绩报表名称 
	 * @param scoreResultDto
	 */
	private void setscoreResultTitle(ScoreResultDto scoreResultDto)
	{
		String title = scoreResultDto.getExamDto().getExamName() + "_" + scoreResultDto.getGradeDto().getGradeName();
		if (scoreResultDto.getClassDto() != null)
		{
			title += "_" + scoreResultDto.getClassDto().getClassName();
		}
		title += "_考试成绩明细_" + DateUtil.date2String(new Date(), "yyyy年MM月dd日hh时mm分");
		scoreResultDto.setTitle(title);
	}

	private List<Float> convertToScoreListByChoosedSubjectIds(String groupScores, List<String> choosedSubjects)
	{
		Map<String, String> map = StringUtil.splitStringToMap(groupScores, ";", "=");
		List<Float> scores = new ArrayList<>();
		for (String subjectId : choosedSubjects)
		{
			if (StringUtils.isNotBlank(map.get(subjectId)))
			{
				scores.add(Float.valueOf(map.get(subjectId)));
			}
			else
			{
				scores.add(null);
			}
		}
		return scores;
	}

	/**
	 * 计算学生的各科统计数据
	 * @param studentScoreDto
	 */
	private void caculateScoreData(StudentScoreDto studentScoreDto)
	{
		if (studentScoreDto.getSubjectScores() == null)
			return;
		Float sum = 0f;
		int count = 0;
		for (Float f : studentScoreDto.getSubjectScores())
		{
			if (f != null)
			{
				sum += f;
				count++;
			}
		}
		studentScoreDto.setTotalScore(sum);
		if (count > 0)
		{
			studentScoreDto.setAvgScore(sum / count);
		}
		else
		{
			studentScoreDto.setAvgScore(0f);
		}

	}

	/**
	 * 计算学生的班级排名.没有成绩的按0分计算(没有分数不参与排名，这种做法不好，会导致没有分数的排在前面)
	 * @param examId
	 * @param classId
	 * @return
	 */
	private Map<String, Integer> caculateTotalClassRankMap(String schId,String examId, String classId)
	{
		Map<String, Integer> rankMap = new HashMap<>();
		StringBuffer queryHql = new StringBuffer(
				"select s.studentId,sum(s.score) from " + schId
						+ "_score s,student stu where s.studentId=stu.id and s.examId=:examId and stu.classId=:classId group by s.studentId order by sum(s.score) desc,stu.studentNo ");
		Map map = new HashMap<>();
		map.put("examId", examId);
		map.put("classId", classId);
		List<Object[]> list = baseDao.querySql(queryHql.toString(), map, 0, 10000000);
		return buildRankMapByQueryResult(list);
	}

	/**
	 * 按年级查询总分班级排名
	 * @param examId
	 * @param gradeId
	 * @return
	 */
	private Map<String, Integer> caculateClassRankMapByGradeId(String schId, String examId, String gradeId)
	{
		Map<String, Integer> rankMap = new HashMap<>();
		List<String> cIds = classService.queryClassIdsByGradeId(gradeId);
		for (String cId : cIds)
		{
			rankMap.putAll(caculateTotalClassRankMap(schId, examId, cId));
		}
		return rankMap;
	}

	/**
	 * 计算学生的年级排名(没有分数不参与排名)
	 * @param examId
	 * @param classId
	 * @return
	 */
	private Map<String, Integer> caculateGradeRankMap(String schId, String examId, String gradeId)
	{
		List<String> classIds = classService.queryClassIdsByGradeId(gradeId);
		Map<String, Integer> rankMap = new HashMap<>();
		StringBuffer queryHql = new StringBuffer(
				"select s.studentId,sum(s.score) from " + schId
						+ "_score s,student stu where s.studentId=stu.id and   s.examId=:examId and stu.classId in :classIds group by s.studentId order by sum(s.score) desc,stu.studentNo ");
		Map map = new HashMap<>();
		map.put("examId", examId);
		map.put("classIds", classIds);
		List<Object[]> list = baseDao.querySql(queryHql.toString(), map, 0, 10000000);
		return buildRankMapByQueryResult(list);
	}

	private Map<String, Integer> caculateSubjectGradeRankMap(String schId, Subject subject, String gradeId)
	{
		List<String> classIds = classService.queryClassIdsByGradeId(gradeId);
		Map<String, Integer> rankMap = new HashMap<>();
		StringBuffer queryHql = new StringBuffer(
				"select s.studentId,s.score from " + schId
						+ "_score s,student stu where s.studentId=stu.id and   s.examId=:examId and s.subjectId=:subjectId and stu.classId in :classIds group by s.studentId order by s.score desc,stu.studentNo ");
		Map map = new HashMap<>();
		map.put("examId", subject.getExamId());
		map.put("subjectId", subject.getId());
		map.put("classIds", classIds);
		List<Object[]> list = baseDao.querySql(queryHql.toString(), map, 0, 10000000);
		return buildRankMapByQueryResult(list);
	}

	/**
	 * 查询指定班级学生各个科目的排名
	 * @param subjects
	 * @return
	 */
	public Map<String, Map<String, Integer>> caculateSubjectsClassRankMap(String schId, List<Subject> subjects,
			String gradeId,
			String classId)
	{
		Map<String, Map<String, Integer>> rankMap=new HashMap<>();
		for(Subject subject:subjects)
		{
			rankMap.put(subject.getId(), caculateSubjectClassRankMap(schId, subject, gradeId, classId));
		}
		return rankMap;
	}

	/**
	 * 查询所有年级的学生的各个科目班级排名
	 * @param subjects
	 * @param gradeId
	 * @return
	 */
	public Map<String, Map<String, Integer>> caculateSubjectsClassRankMapByGradeId(String schId, List<Subject> subjects,
			String gradeId)
	{
		List<String> cIds = classService.queryClassIdsByGradeId(gradeId);
		Map<String, Map<String, Integer>> rankMap = new HashMap<>();
		for (Subject subject : subjects)
		{
			rankMap.put(subject.getId(), new HashMap());
		}
		for (Subject subject : subjects)
		{
			for (String cId : cIds)
			{
				rankMap.get(subject.getId()).putAll(caculateSubjectClassRankMap(schId, subject, gradeId, cId));
			}
		}
		return rankMap;
	}

	/**
	 * 查询整个年级各个科目的班级排名
	 * @param subjects
	 * @param gradeId
	 * @return
	 */
	public Map<String, Map<String, Integer>> caculateSubjectsClassRankMap(String schId, List<Subject> subjects,
			String gradeId)
	{
		List<String> cIds = classService.queryClassIdsByGradeId(gradeId);
		Map<String, Map<String, Integer>> rankMap = new HashMap<>();
		for (Subject subject : subjects)
		{
			for (String cId : cIds)
			{
				rankMap.put(subject.getId(), caculateSubjectClassRankMap(schId, subject, gradeId, cId));
			}
		}
		return rankMap;
	}

	/**
	 * 查询指定班级学生各个科目的排名
	 * @param subjects
	 * @return
	 */
	public Map<String, Map<String, Integer>> caculateSubjectsGradeRankMap(String schId, List<Subject> subjects,
			String gradeId)
	{
		Map<String, Map<String, Integer>> rankMap = new HashMap<>();
		for (Subject subject : subjects)
		{
			rankMap.put(subject.getId(), caculateSubjectGradeRankMap(schId, subject, gradeId));
		}
		return rankMap;
	}

	/**
	 * 获取本年级的班级排名数据
	 * @param subject
	 * @param gradeId
	 * @return
	 */
	private Map<String, Integer> caculateSubjectClassRankMap(String schId, Subject subject, String gradeId,
			String classId)
	{
		StringBuffer queryHql = new StringBuffer(
				"select s.studentId,s.score from " + schId
						+ "_score s,student stu where s.studentId=stu.id and   s.examId=:examId and s.subjectId=:subjectId and stu.classId =:classId group by s.studentId order by s.score desc,stu.studentNo ");
		Map map = new HashMap<>();
		map.put("examId", subject.getExamId());
		map.put("subjectId", subject.getId());
		map.put("classId", classId);
		List<Object[]> list = baseDao.querySql(queryHql.toString(), map, 0, 10000000);
		return buildRankMapByQueryResult(list);
	}

	private Map<String, Integer> buildRankMapByQueryResult(List<Object[]> list)
	{
		Map<String, Integer> rankMap = new HashMap<>();
		Double preScore = -1d;
		int preRank = 1;
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i)[1] == null)//没有登记分数
			{
				list.get(i)[1] = 0d;
			}
			if (Math.abs(((Double) list.get(i)[1]) - preScore) > 0.001)//不相等
			{
				preRank = i + 1;
			}
			rankMap.put(list.get(i)[0].toString(), preRank);

			preScore = (Double) list.get(i)[1];
		}
		return rankMap;
	}



}
