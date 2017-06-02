package com.goldskyer.gmxx.common.listeners;

import java.util.Map;

import com.goldskyer.core.SpringContextHolder;
import com.goldskyer.core.listeners.CoreStartUpListener;
import com.goldskyer.gmxx.common.helpers.LangHelper;

public class StartUpListener extends CoreStartUpListener{

	@Override
	public void initWeb() {
		//启动加载
		System.out.println("start....");
		Map langMap = LangHelper.buildLangMap();
		SpringContextHolder.sc.setAttribute("lang", langMap);
	}

}
