package com.goldskyer.scorecloud.service;

import com.goldskyer.scorecloud.dto.OptionValueDto;

public interface OptionValueService
{
	public void addOrModifyOptionValueDto(OptionValueDto optionValueDto);
	public void deleteOptionValue(String id);

	public void addOptionValueDto(OptionValueDto optionValueDto);

	public void modifyOptionValueDto(OptionValueDto optionValueDto);
}
