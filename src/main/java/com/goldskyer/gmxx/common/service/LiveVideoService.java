package com.goldskyer.gmxx.common.service;

import java.util.List;

import com.goldskyer.gmxx.common.entities.LiveVideo;

public interface LiveVideoService
{
	public LiveVideo queryLiveVideoByBlogId(String blogId);

	public void addLiveVideo(LiveVideo liveVideo);

	public void updateLiveVideo(LiveVideo liveVideo);

	public List<LiveVideo> queryLiveVideos();

	public boolean stopLiveVideo(final LiveVideo liveVideo);

	public boolean startLiveVideo(final LiveVideo liveVideo);

	public boolean checkAuthCode(String id, String input);

}
