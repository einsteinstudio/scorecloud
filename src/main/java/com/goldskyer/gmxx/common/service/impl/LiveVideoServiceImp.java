package com.goldskyer.gmxx.common.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.goldskyer.core.service.impl.CoreServiceImp;
import com.goldskyer.gmxx.common.entities.LiveVideo;
import com.goldskyer.gmxx.common.service.LiveVideoService;
import com.goldskyer.gmxx.common.utils.ShellUtil;

@Service
public class LiveVideoServiceImp extends CoreServiceImp implements LiveVideoService
{
	public LiveVideo queryLiveVideoByBlogId(String blogId)
	{
		return (LiveVideo) baseDao.queryUnique("select c from LiveVideo c where c.blogId=?", blogId);
	}

	@Override
	public List<LiveVideo> queryLiveVideos()
	{
		return baseDao.query("select c from LiveVideo c where c.status='ON'");
	}

	public void addLiveVideo(LiveVideo liveVideo)
	{
		liveVideo.setCreateDate(new Date());
		liveVideo.setUpdateDate(new Date());
		baseDao.add(liveVideo);
	}

	public void updateLiveVideo(LiveVideo liveVideo)
	{
		liveVideo.setUpdateDate(new Date());
		baseDao.modify(liveVideo);
	}

	public boolean startLiveVideo(final LiveVideo liveVideo)
	{
		new Thread(new Runnable() {

			@Override
			public void run()
			{
				try
				{
					ShellUtil.runShell("/data/script/start_live.sh " + liveVideo.getIp());
				}
				catch (Exception e)
				{
					log.fatal(e.getMessage(), e);
				}
			}
		}).start();
		return true;
	}

	public boolean stopLiveVideo(final LiveVideo liveVideo)
	{
		new Thread(new Runnable() {

			@Override
			public void run()
			{
				try
				{
					ShellUtil.runShell("/data/script/stop_live.sh " + liveVideo.getIp());
				}
				catch (Exception e)
				{
					log.fatal(e.getMessage(), e);
				}
			}
		}).start();
		return true;
	}

	public boolean checkAuthCode(String id, String input)
	{
		LiveVideo liveVideo = queryLiveVideoByBlogId(id);
		if (liveVideo != null && StringUtils.equals(liveVideo.getAuthCode(), input))
		{
			return true;
		}
		return false;
	}

}
