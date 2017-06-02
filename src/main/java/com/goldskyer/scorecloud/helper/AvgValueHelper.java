package com.goldskyer.scorecloud.helper;

public class AvgValueHelper
{
	public static Float calculateAvgValue(Float avgScore, Float hegeRate, Float youliangRate)
	{
		return avgScore * 0.4f + hegeRate * 0.3f + youliangRate * 0.3f;
	}
}
