package com.goldskyer.gmxx.mock.service;

import java.util.List;

import com.goldskyer.gmxx.common.dtos.CmdResultDto;
import com.goldskyer.gmxx.mock.entities.MockUrl;


public interface MockUrlService {
	public MockUrl queryMockUrlByUrl(String url);
	
	public String addMockUrl(MockUrl mockUrl);
	
	public void deleteMockUrl(String id);
	
	public void modify(MockUrl mockUrl);
	
	public MockUrl queryMockUrlById(String id);
	
	public List<MockUrl> queryMockUrlsByGroup(String group);
	
	public List<MockUrl> queryAllMockUrls();
	
	public CmdResultDto executeShell(String path);
}
