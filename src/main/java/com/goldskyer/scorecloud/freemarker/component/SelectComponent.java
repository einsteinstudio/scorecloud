package com.goldskyer.scorecloud.freemarker.component;

import com.goldskyer.scorecloud.dto.OptionDto;
import com.goldskyer.scorecloud.freemarker.component.vo.OptGroupVoWrapper;

public interface SelectComponent
{
	/**
	 * 获取用户年级选择的下拉列表
	 * @return
	 */
	public OptionDto buildGradeIdSelect();

	public OptGroupVoWrapper buildCLassIdSelect();

	public OptGroupVoWrapper buildExamdSelect();
}
