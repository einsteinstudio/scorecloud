package com.goldskyer.scorecloud.freemarker.component.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.scorecloud.dto.ExamDto;
import com.goldskyer.scorecloud.dto.GradeDto;
import com.goldskyer.scorecloud.dto.OptionDto;
import com.goldskyer.scorecloud.dto.OptionValueDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.entities.Grade;
import com.goldskyer.scorecloud.freemarker.component.SelectComponent;
import com.goldskyer.scorecloud.freemarker.component.vo.OptGroupVo;
import com.goldskyer.scorecloud.freemarker.component.vo.OptGroupVoWrapper;
import com.goldskyer.scorecloud.service.ClassService;
import com.goldskyer.scorecloud.service.ExamService;
import com.goldskyer.scorecloud.service.GradeService;
import com.goldskyer.scorecloud.service.OptionService;
import com.goldskyer.scorecloud.service.OptionValueService;

/**
 * 下拉列表控件
 * @author jintianfan
 *
 */
@Service
public class SelectComponentImpl implements SelectComponent
{
	@Autowired
	private GradeService gradeService;
	@Autowired
	private OptionService optionService;
	@Autowired
	private OptionValueService optionValueService;
	@Autowired
	private ClassService classService;
	@Autowired
	private ExamService examService;
	/**
	 * 班级下拉菜单
	 * @param name
	 */
	public OptGroupVoWrapper buildClassGroupSelect(String name)
	{
		OptGroupVoWrapper groupSelect = new OptGroupVoWrapper();
		groupSelect.setName(name);
		return groupSelect;
	}

	/**
	 * 获取用户年级选择的下拉列表
	 * @return
	 */
	public OptionDto buildGradeIdSelect()
	{
		List<Grade> grades = gradeService.queryActivaeGrades(ScoreCloudEnv.get().getSchId());
		List<OptionValueDto> optionValueDtos = new ArrayList<>();
		for (int i = 0; i < grades.size(); i++)
		{
			OptionValueDto vo = new OptionValueDto();
			if (i == 0)
				vo.setChecked(true);
			vo.setDescrp(grades.get(i).getGradeName());
			vo.setValue(grades.get(i).getId());
			optionValueDtos.add(vo);
		}
		OptionDto optionDto = new OptionDto();
		optionDto.setShowTip(false);
		optionDto.setOptionValueDtos(optionValueDtos);
		return optionDto;
	}

	public OptGroupVoWrapper buildCLassIdSelect()
	{
		OptGroupVoWrapper wrapper = new OptGroupVoWrapper();
		wrapper.setName("classId");
		List<GradeDto> gradeDtos = gradeService.queryAllGradeDtoWithClassDtos(ScoreCloudEnv.get().getSchId());
		for (int i = 0; i < gradeDtos.size(); i++)
		{
			OptGroupVo optGroupVo = new OptGroupVo();
			optGroupVo.setGroupName(gradeDtos.get(i).getGradeName());
			for (int k = 0; k < gradeDtos.get(i).getClassDtos().size(); k++)
			{
				OptionValueDto vo = new OptionValueDto();
				vo.setDescrp(gradeDtos.get(i).getClassDtos().get(k).getClassName());
				vo.setValue(gradeDtos.get(i).getClassDtos().get(k).getId());
				optGroupVo.getOptionValueDtos().add(vo);
			}
			wrapper.getOptGroupVos().add(optGroupVo);
		}
		return wrapper;
	}

	/**
	 * DTo
	 */
	public OptGroupVoWrapper buildExamdSelect()
	{
		OptGroupVoWrapper wrapper = new OptGroupVoWrapper();
		wrapper.setName("examId");
		Map<String, List<ExamDto>> examDtoMap = examService.queryGroupedExamDtosByYear();
		for(String year: examDtoMap.keySet())
		{
			OptGroupVo optGroupVo = new OptGroupVo();
			optGroupVo.setGroupName(year);
			for (ExamDto examDto:examDtoMap.get(year))
			{
				OptionValueDto vo = new OptionValueDto();
				vo.setDescrp(examDto.getExamName());
				vo.setValue(examDto.getId());
				optGroupVo.getOptionValueDtos().add(vo);
				if (StringUtils.isBlank(wrapper.getFirstId()))
				{
					wrapper.setFirstId(vo.getValue());
				}
			}
			wrapper.getOptGroupVos().add(optGroupVo);
		}
		return wrapper;
	}

}
