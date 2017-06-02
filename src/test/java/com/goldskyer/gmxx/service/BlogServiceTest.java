package com.goldskyer.gmxx.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.NotTransactional;

import com.goldskyer.core.dto.EnvParameter;
import com.goldskyer.core.entities.Blog;
import com.goldskyer.core.service.BlogService;

public class BlogServiceTest extends BaseTest
{
	@Autowired
	@Qualifier("blogService")
	private BlogService blogService;

	@Test
	@NotTransactional
	public void testAddBlog()
	{
		EnvParameter.put(new EnvParameter());
		EnvParameter.get().setDomain("gmxx.goldskyer.com");
		Blog blog = new Blog();
		blog.setAuthor("管理员");
		blog.setCoverImage("//storage.goldskyer.com/gmxx/1.jpg");
		blog.setContent("//storage.goldskyer.com/gmxx/hls05/gmxx_orign.m3u8");
		blog.setDomain("gmxx.goldskyer.com");
		blog.setSource("光明小学");
		blog.setSubject("光明小学创办于一九六三年。三十多年来一直是企业办学，一九九九年十月，光明农场实行了政企分开，光明小学归属宝安人民政府管理，从此纳入了政府办学的体制。");
		blog.setTitle("光明小学宣传视频");
		blog.setType("校宣传片");
		blogService.addBlog(blog);
	}
	
	@Test
	public void testUpdateBlog() {
		
/*		String[] ids = new String[] {
"1603031006032567609",
"1509011819303570363",
"1511181546479454634",
"1512161448134022315",
"1511181452404610626",
"1511181131546047791",
"1511181135076774799",
"001",
"1511181532023327349",
"1511181514447071459",
"1511181626506658677",
"1511181128025830518",
"1511181019235685972",
"1511181545060653325",
"1511181528265747744",
"1511181541483320472",
"1511181136595557399",
"1511181623551732444",
"1511181609218219980",
"1511181617269342300",
"1511181107083673075",
"1511181619433415852",
"402880e853f671760153f67197cc0000",
"402880e853f671760153f6719a030001",
"402880e853f671760153f6719c7e0002",
"402880e853f671760153f671a17a0004",
"402880e853f671760153f671a3f80005",
"402880e853f671760153f671a66a0006",
"402880e853f671760153f671a8b60007",
"402880e853f671760153f671ab180008",
"402880e853f671760153f671ad550009",
"402880e853f671760153f671af8d000a",
"402880e853f671760153f671b19d000b",
"402880e853f671760153f671b43e000c",
"402880e853f671760153f671b6a8000d",
"402880e853f671760153f671b8fe000e",
"402880e853f671760153f671bb6e000f",
"8a53b78254129309015412931b820000",
"8a53b78254129309015412931df20001",
"8a53b7825412930901541293201d0002",
"8a53b7825412930901541293240f0004",
"8a53b782541293090154129326090005",
"8a53b782541293090154129328060006",
"8a53b782541293090154129329d40007",
"8a53b78254129309015412932bc30008",
"8a53b78254129309015412932fa7000a",
"8a53b782541293090154129333bb000c",
"8a7298165412cad6015412cae8760000",
"8a7298165412cad6015412caecfe0002",
"shufake",
"jindiansongdu",
"hulusi",
"wangluoyingyu",
"guojilijie",
"xintibiaoyan",
"00000000543999d601543a0b4602003f",
"00000000543999d601543a104dea0040",
"00000000543999d601543a136af10041",
"00000000543999d601543a15e7670042",
"00000000543999d601543a1817a40043",
"00000000543c95b401543d99d6d50f25",
"00000000543c95b401543da103020f26",
"00000000543c95b401543dbde8340f4b",
"00000000543c95b401543dc16ed90f4c",
"00000000543c95b401543dc5fbb20f4e"            
		};
				
				
		for (String id : ids) {
			Blog blog = blogService.queryBlog(id);
			
			if (null != blog.getCoverImage()) {
				String newimg = blog.getCoverImage().replace("http://gmxx.goldskyer.com/manager/upload/image", "//storage.goldskyer.com/gmxx/upload/images");
			
				blog.setCoverImage(newimg);
				
				blogService.modifyBlogContent(blog);
				
				System.out.println("-------------->success," + id);
			}
			
			
			
		}*/
		
		Blog blog = blogService.queryBlog("8a53b78254129309015412931df20001");
		//String s ="<p>		</p><p><br/></p><p><img src=\"/manager/upload/image/20160423/1461396922569038133.jpg\" title=\"1461396922569038133.jpg\" alt=\"QQ图片20160423151944.jpg\"/></p><p style=\"text-align: center;\"><video class=\"edui-upload-video  vjs-default-skin   video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"/manager/upload/video/20160422/1461336844856019258.mp4\" data-setup=\"{}\"><source src=\"/manager/upload/video/20160422/1461336844856019258.mp4\" type=\"video/mp4\"/><source src=\"/manager/upload/video/20160422/1461336844856019258.mp4\" type=\"application/x-mpegURL\"/></video></p><p><br/></p><p><span style=\"text-indent: 38px;\">&nbsp;&nbsp;&nbsp;</span></p><p><span style=\"text-indent: 38px;\">&nbsp;光明小学象棋社团成立于2014年5月，学校聘请了有丰富专业技术经验的原黑龙江省棋院象棋教练王老师，来我校进行象棋社团的普及、推广、指导。在一年多的时间里，社团的小同学们努力、认真、向上、勤奋，也在广东省棋类特色学校四棋联、以及光明新区的比赛中取得了七个第一名，四个第二名，五个第三名的优异成绩。同时，通过象棋专业训练，孩子们做事比以前稳重了，思维品质有所提升，待人接物、文明礼仪等方面也有了进步。</span></p><p><br/></p><p><br/></p><p><br/></p><p></p>";
		//String s ="<video id=\"really-cool-video\" class=\"video-js vjs-default-skin\" controls preload=\"auto\" width=\"420\" height=\"280\" webkit-playsinline><source src=\"//storage.goldskyer.com/gmxx/upload/classes/yuwen02.mp4\" type=\"video/mp4\"><source src=\"//storage.goldskyer.com/gmxx/upload/classes/yuwen02.mp4\" type=\"application/x-mpegURL\"><p class=\"vjs-no-js\">您的设备暂时无法播放此视频！</p></video>";
		//String s = "<video controls preload=\"auto\" webkit-playsinline><source src=\"http://gmxx.goldskyer.com/manager/upload/video/20160422/1461336844856019258.mp4\" type=\"video/mp4\"></video>";
		String s = "<p>		</p><p><br/></p><p><img src=\"//storage.goldskyer.com/gmxx/upload/images/20160423/1461396922569038133.jpg\" title=\"1461396922569038133.jpg\" alt=\"QQ图片20160423151944.jpg\"/></p><p style=\"text-align: center;\"><video class=\"edui-upload-video  vjs-default-skin   video-js\" controls=\"\" preload=\"auto\" width=\"420\" height=\"280\" src=\"//storage.goldskyer.com/gmxx/upload/videos/1461336844856019258.mp4\" data-setup=\"{}\"><source src=\"//storage.goldskyer.com/gmxx/upload/videos/1461336844856019258.mp4\" type=\"video/mp4\"/></video></p><p><br/></p><p><span style=\"text-indent: 38px;\">&nbsp;&nbsp;&nbsp;</span></p><p><span style=\"text-indent: 38px;\">&nbsp;光明小学象棋社团成立于2014年5月，学校聘请了有丰富专业技术经验的原黑龙江省棋院象棋教练王老师，来我校进行象棋社团的普及、推广、指导。在一年多的时间里，社团的小同学们努力、认真、向上、勤奋，也在广东省棋类特色学校四棋联、以及光明新区的比赛中取得了七个第一名，四个第二名，五个第三名的优异成绩。同时，通过象棋专业训练，孩子们做事比以前稳重了，思维品质有所提升，待人接物、文明礼仪等方面也有了进步。</span></p><p><br/></p><p><br/></p><p><br/></p><p></p>";
		blog.setContent(s);
		blogService.modifyBlogContent(blog);
		System.out.println("-------------->success");
	}
	
}
