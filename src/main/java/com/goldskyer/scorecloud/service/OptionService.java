package com.goldskyer.scorecloud.service;

import java.util.List;

import com.goldskyer.scorecloud.dto.OptionDto;

public interface OptionService
{
	public List<OptionDto> queryAllOptionDtos(String schId);

	public void addOptionDto(OptionDto optionDto);

	public void modifyOptionDto(OptionDto optionDto);

	public void deleteOption(String id);

	public OptionDto queryOptionDtoById(String id);

	public OptionDto queryOptionDtoByInnerCode(String schId, String innerCode);
}
