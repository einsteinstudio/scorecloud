package com.goldskyer.scorecloud.vo;

import java.util.ArrayList;
import java.util.List;

import com.goldskyer.scorecloud.dto.TargetScoreItem;

public class TargetScoreVo
{
	private List<TargetScoreItem> items = new ArrayList<>();

	public List<TargetScoreItem> getItems()
	{
		return items;
	}

	public void setItems(List<TargetScoreItem> items)
	{
		this.items = items;
	}

}
