package com.goldskyer.scorecloud.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.scorecloud.dto.OptionValueDto;
import com.goldskyer.scorecloud.entities.OptionValue;
import com.goldskyer.scorecloud.service.OptionValueService;

@Service
public class OptionValueServiceImpl implements OptionValueService
{
	@Autowired
	private BaseDao baseDao;

	public void deleteOptionValue(String id)
	{
		baseDao.delete(OptionValue.class, id);
	}

	@Transactional
	public void addOrModifyOptionValueDto(OptionValueDto optionValueDto)
	{
		if (StringUtils.isNotBlank(optionValueDto.getId()))
		{
			modifyOptionValueDto(optionValueDto);
		}
		else
		{
			addOptionValueDto(optionValueDto);
		}
	}

	public void addOptionValueDto(OptionValueDto optionValueDto)
	{
		OptionValue optionValue = new OptionValue();
		optionValue.setId(optionValueDto.getId());
		optionValue.setOptionId(optionValueDto.getOptionId());
		optionValue.setValue(optionValueDto.getValue());
		optionValue.setWeight(optionValueDto.getWeight());
		optionValue.setChecked(optionValueDto.isChecked());
		optionValue.setDescrp(optionValueDto.getDescrp());
		String id = baseDao.add(optionValue);
		optionValueDto.setId(id);
	}

	public void modifyOptionValueDto(OptionValueDto optionValueDto)
	{
		OptionValue oldOptionValue = baseDao.query(OptionValue.class, optionValueDto.getId());
		oldOptionValue.setId(optionValueDto.getId());
		oldOptionValue.setOptionId(optionValueDto.getOptionId());
		oldOptionValue.setValue(optionValueDto.getValue());
		oldOptionValue.setWeight(optionValueDto.getWeight());
		oldOptionValue.setChecked(optionValueDto.isChecked());
		oldOptionValue.setDescrp(optionValueDto.getDescrp());
		baseDao.modify(oldOptionValue);
	}

}
