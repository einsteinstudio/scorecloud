package com.goldskyer.scorecloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.common.util.BeanUtil;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.scorecloud.dto.OptionDto;
import com.goldskyer.scorecloud.dto.OptionValueDto;
import com.goldskyer.scorecloud.dto.ScoreCloudEnv;
import com.goldskyer.scorecloud.entities.Option;
import com.goldskyer.scorecloud.entities.OptionValue;
import com.goldskyer.scorecloud.service.OptionService;
import com.goldskyer.scorecloud.service.OptionValueService;

@Service
public class OptionServiceImpl implements OptionService
{
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private OptionValueService optionValueService;

	public OptionDto queryOptionDtoByInnerCode(String schId, String innerCode)
	{
		Option option = (Option) baseDao.queryUnique("from Option where innerCode=? and schId=?", innerCode, schId);
		OptionDto optionDto = new OptionDto();

		BeanUtil.copyProperties(optionDto, option);
		List<OptionValue> optionValues = baseDao.query("from OptionValue where optionId=? order by weight",
				option.getId());
		for (OptionValue optionValue : optionValues)
		{
			OptionValueDto dto = new OptionValueDto();
			BeanUtil.copyProperties(dto, optionValue);
			optionDto.getOptionValueDtos().add(dto);
		}
		return optionDto;
	}

	public OptionDto queryOptionDtoById(String id)
	{
		Option option = baseDao.query(Option.class, id);
		OptionDto optionDto = new OptionDto();

		BeanUtil.copyProperties(optionDto, option);
		List<OptionValue> optionValues = baseDao.query("from OptionValue where optionId=? order by weight", id);
		for (OptionValue optionValue : optionValues)
		{
			OptionValueDto dto = new OptionValueDto();
			BeanUtil.copyProperties(dto, optionValue);
			optionDto.getOptionValueDtos().add(dto);
		}

		return optionDto;
	}

	public List<OptionDto> queryAllOptionDtos(String schId)
	{
		List<OptionDto> optionDtos = new ArrayList<>();
		List<OptionValue> optionValues = baseDao.query(
				"from OptionValue where optionId in (select id from Option where  schId=?) order by optionId,weight",
				schId);
		Map<String, List<OptionValueDto>> map = createOptionValueMap(optionValues);
		List<Option> options = baseDao.query("from Option where schId=? order by weight", schId);
		for (Option option : options)
		{
			OptionDto optionDto = new OptionDto();
			optionDto.setId(option.getId());
			optionDto.setInnerCode(option.getInnerCode());
			optionDto.setOptionName(option.getOptionName());
			optionDto.setShowTip(option.isShowTip());
			optionDto.setTip(option.getTip());
			if (map.containsKey(optionDto.getId()))
			{
				optionDto.setOptionValueDtos(map.get(optionDto.getId()));
			}
			else
			{
				optionDto.setOptionValueDtos(new ArrayList<OptionValueDto>());
			}
			optionDtos.add(optionDto);
		}
		return optionDtos;
	}

	private Map<String, List<OptionValueDto>> createOptionValueMap(List<OptionValue> optionValues)
	{
		Map<String, List<OptionValueDto>> map = new HashMap<>();
		for (OptionValue v : optionValues)
		{
			OptionValueDto optionValueDto = new OptionValueDto();
			optionValueDto.setId(v.getId());
			optionValueDto.setChecked(v.isChecked());
			optionValueDto.setOptionId(v.getOptionId());
			optionValueDto.setWeight(v.getWeight());
			optionValueDto.setValue(v.getValue());
			if (map.get(v.getOptionId()) == null)
			{
				map.put(v.getOptionId(), new ArrayList<OptionValueDto>());
			}
			map.get(v.getOptionId()).add(optionValueDto);
		}
		return map;
	}

	@Transactional
	public void addOptionDto(OptionDto optionDto)
	{
		Option option = new Option();
		option.setInnerCode(optionDto.getInnerCode());
		option.setOptionName(optionDto.getOptionName());
		option.setShowTip(optionDto.isShowTip());
		option.setWeight(optionDto.getWeight());
		option.setTip(optionDto.getTip());
		option.setSchId(ScoreCloudEnv.get().getSchId());
		String optionId=baseDao.add(option);
		//添加选项配置
		if (optionDto.getOptionValueDtos() != null)
		{
			for (OptionValueDto v : optionDto.getOptionValueDtos())
			{
				OptionValue optionValue=new OptionValue();
				optionValue.setOptionId(optionId);
				optionValue.setChecked(v.isChecked());
				optionValue.setValue(v.getValue());
				optionValue.setWeight(v.getWeight());
				baseDao.add(optionValue);
			}
		}
	}


	@Transactional
	public void modifyOptionDto(OptionDto optionDto)
	{
		optionDto.setSchId(ScoreCloudEnv.get().getSchId());
		Option oldOption = new Option();
		oldOption.setId(optionDto.getId());
		oldOption.setInnerCode(optionDto.getInnerCode());
		oldOption.setOptionName(optionDto.getOptionName());
		oldOption.setShowTip(optionDto.isShowTip());
		oldOption.setWeight(optionDto.getWeight());
		oldOption.setTip(optionDto.getTip());
		oldOption.setSchId(optionDto.getSchId());
		baseDao.modify(oldOption);
	}

	@Transactional
	public void deleteOption(String id)
	{
		baseDao.delete(Option.class, id);
		baseDao.execute("delete from OptionValue where optionId=?", id);
	}


}
