package com.goldskyer.gmxx.common.dtos;

import java.util.ArrayList;
import java.util.List;

public class CmdResultDto {
	private List<String> processList=new ArrayList<String>();
	public List<String> getProcessList() {
		return processList;
	}

	public void setProcessList(List<String> processList) {
		this.processList = processList;
	}
	
	
}
