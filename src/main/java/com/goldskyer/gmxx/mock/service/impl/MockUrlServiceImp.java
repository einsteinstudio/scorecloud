package com.goldskyer.gmxx.mock.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.gmxx.common.dtos.CmdResultDto;
import com.goldskyer.gmxx.mock.entities.MockUrl;
import com.goldskyer.gmxx.mock.service.MockUrlService;

@Service("mockUrlService")
public class MockUrlServiceImp   implements MockUrlService{
	@Autowired
	private BaseDao baseDao;
	@Override
	public String addMockUrl(MockUrl mockUrl) {
		return baseDao.add(mockUrl);
	}

	@Override
	public void deleteMockUrl(String id) {
		baseDao.delete(MockUrl.class,id);
	}

	@Override
	public void modify(MockUrl mockUrl) {
		baseDao.modify(mockUrl);
	}

	@Override
	public List<MockUrl> queryMockUrlsByGroup(String group) {
		return baseDao.query("select m from MockUrl where m.group=? order by createDate desc",group);
	}

	@Override
	public List<MockUrl> queryAllMockUrls() {
		return baseDao.query("select m from MockUrl m order by createDate desc");
	}

	@Override
	public MockUrl queryMockUrlById(String id) {
		return baseDao.query(MockUrl.class, id);
	}

	@Override
	public MockUrl queryMockUrlByUrl(String url) {
		return (MockUrl) baseDao.queryUnique("select t from MockUrl t where t.url=?",url);
	}

	@Override
	public CmdResultDto executeShell(String shellCmd) {
		CmdResultDto cmdResultDto = new CmdResultDto();
        Process process = null;  
        try {  
            process = Runtime.getRuntime().exec(shellCmd);  
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));  
            String line = "";  
            while ((line = input.readLine()) != null) {  
            	cmdResultDto.getProcessList().add(line);  
            }  
            input.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
   
        return cmdResultDto;
	}

}

