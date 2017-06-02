package com.goldskyer.scorecloud.support;

import org.hibernate.cfg.DefaultNamingStrategy;
import org.springframework.stereotype.Service;

@Service("scoreNamingStrategy")
public class ScoreNamingStrategy extends DefaultNamingStrategy
{

	//这是为了实现单例  
	public static final ScoreNamingStrategy INSTANCE = new ScoreNamingStrategy();

	//重新定义名称映射关系  
	public String classToTableName(String className) {   
		return super.classToTableName(className);
  
    }

}