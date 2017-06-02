<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="refresh" content="300">
<meta http-equiv="x-ua-compatible" content="ie=7">
<title>${cms.title.content}&gt;&gt; 首页</title>
	<link href="//${cdnDomain}/cdn/spring/index_files/swiper.min.css"
	rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript"
	src="//${cdnDomain}/cdn/spring/index_files/prototype.js"></script>
<script language="javascript" type="text/javascript"
	src="//${cdnDomain}/cdn/spring/index_files/scriptaculous.js"></script>
<script type="text/javascript"
	src="//${cdnDomain}/cdn/spring/index_files/util.js"></script>
<script type="text/javascript"
	src="//${cdnDomain}/cdn/spring/index_files/effects.js"></script>
<script type="text/javascript"
	src="//${cdnDomain}/cdn/spring/index_files/dragdrop.js"></script>
<script type="text/javascript"
	src="//${cdnDomain}/cdn/spring/index_files/controls.js"></script>
<script language="javascript" type="text/javascript"
	src="//${cdnDomain}/cdn/spring/index_files/checklogin.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="//${cdnDomain}/cdn/spring/index_files/menu.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="//${cdnDomain}/cdn/spring/index_files/jquery.min.js"></script>
<script type="text/JavaScript" src="//${cdnDomain}/cdn/spring/index_files/swiper.jquery.min.js"></script>
<script language="JavaScript" type="text/JavaScript">
<!--
/*第一种形式 第二种形式 更换显示样式*/
function setTab(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  menu.className=i==cursel?"hover":"";
  con.style.display=i==cursel?"block":"none";
 }
}
//-->
</script>
<link href="//${cdnDomain}/cdn/spring/index_files/bhsx.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	.swiper-container {
		width: 275px;
		height: 188px;
	}

	.swiper-slide {
		background-position: center;
		background-size: cover;
		background-repeat: no-repeat;
		background-color: #000;
	}

	.item-title {
		position: absolute;
		margin: 20px 0 0 35px;
		font-size: 16px;
		color: #fff;
		font-weight: bolder;
	}

	.notice1_linetext, .notice2_linetext, .notice3_linetext {
		display: inline-block;
	    overflow: hidden;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	    width: 150px;
	}

	.bannerLeft2_linetext, .bannerLeft3_linetext, .bannerLeft4_linetext, .bannerLeft5_linetext {
		display: inline-block;
	    overflow: hidden;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	    width: 152px;
	}
	.block_linetext {
		display: inline-block;
	    overflow: hidden;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	    width: 294px;
	}

	.bannerTop_linetext {
		display: inline-block;
	    overflow: hidden;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	    width: 194px;
	}
	</style>
</head>
<body>


	<jsp:include page="header.jsp" />
	<table border="0" cellspacing="0" cellpadding="0" width="1000"
		bgcolor="#ffffff" align="center">
		<tbody>

			<tr>
				<td valign="top" width="250" align="left">
					<table border="0" cellspacing="0" cellpadding="0" width="250">
						<tbody>
							<tr>
								<td class="userbga"><a href="javascript:void();"><font
										color="#ffffff">最新公告</font></a></td>
							</tr>
							<tr>
								<td class="usercenter" align="middle">
									<table border="0" cellspacing="0" cellpadding="0" width="200"
										align="center">
										<tbody>
											<tr>
												<td>
													<div id="Tab3">
														<div class="Menugg">
															<ul>
																<li id="one1" class="hover"
																	onmouseover="setTab(&#39;one&#39;,1,3)">${notice1}</li>
																<li id="one2" onmouseover="setTab(&#39;one&#39;,2,3)">${notice2}</li>
																<li id="one3" onmouseover="setTab(&#39;one&#39;,3,3)">${notice3}</li>
															</ul>
														</div>
														<div class="ggbox">
															<div id="con_one_1">
																<table width="100%" cellpadding="0" cellspacing="0">
																	<tbody>
																	    <c:forEach var="blog" items="${notice1s}" >
																	    <tr>
																			<td width="10" valign="top" class="linetext"><img
																				src="//${cdnDomain}/cdn/spring/index_files/Article_common4.gif"
																				alt="普通公告"></td>
																			<td class="linetext"><a class="notice1_linetext"
																				href="/web/spring/blog/view.htm?id=${blog.id}"
																				title="${blog.title}" target="_blank">${blog.title}</a></td>
																			<td align="right" class="linetext" width="40"><fmt:formatDate value="${blog.updateDate}" pattern="MM-dd"/></td>
																		</tr>
																		</c:forEach>
																		<tr></tr>
																	</tbody>
																</table>
																<br>
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%">
																		<tbody>
																			<tr>
																				<td height="22" valign="center" align="right"><a target="_blank"
																					href="/web/spring/blog/list.htm?blogType=${notice1}">更多..</a></td>
																			</tr>
																		</tbody>
																	</table>
															</div>
															<div style="DISPLAY: none" id="con_one_2">
																<table width="100%" cellpadding="0" cellspacing="0">
																	<tbody>
																		<c:forEach var="blog" items="${notice2s}" >
																	    <tr>
																			<td width="10" valign="top" class="linetext"><img
																				src="//${cdnDomain}/cdn/spring/index_files/Article_common4.gif"
																				alt="普通公告"></td>
																			<td class="linetext"><a class="notice2_linetext"
																				href="/web/spring/blog/view.htm?id=${blog.id}"
																				title="${blog.title}" target="_blank">${blog.title}</a></td>
																			<td align="right" class="linetext" width="40"><fmt:formatDate value="${blog.updateDate}" pattern="MM-dd"/></td>
																		</tr>
																		</c:forEach>
																		<tr></tr>
																	</tbody>
																</table>
																<br>
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%">
																		<tbody>
																			<tr>
																				<td height="22" valign="center" align="right"><a target="_blank"
																					href="/web/spring/blog/list.htm?blogType=${notice2}">更多..</a></td>
																			</tr>
																		</tbody>
																	</table>
															</div>
															<div style="DISPLAY: none" id="con_one_3">
																<table width="100%" cellpadding="0" cellspacing="0">
																	<tbody>
																		<c:forEach var="blog" items="${notice3s}" >
																	    <tr>
																			<td width="10" valign="top" class="linetext"><img
																				src="//${cdnDomain}/cdn/spring/index_files/Article_common4.gif"
																				alt="普通公告"></td>
																			<td class="linetext"><a class="notice3_linetext"
																				href="/web/spring/blog/view.htm?id=${blog.id}"
																				title="${blog.title}" target="_blank">${blog.title}</a></td>
																			<td align="right" class="linetext" width="40"><fmt:formatDate value="${blog.updateDate}" pattern="MM-dd"/></td>
																		</tr>
																		</c:forEach>
																		<tr></tr>
																	</tbody>
																</table>
																<br>
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%">
																		<tbody>
																			<tr>
																				<td height="22" valign="center" align="right"><a target="_blank"
																					href="/web/spring/blog/list.htm?blogType=${notice3}">更多..</a></td>
																			</tr>
																		</tbody>
																	</table>
															</div>
														</div>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td class="userbottom"></td>
							</tr>
						</tbody>
					</table>
					<table border="0" cellspacing="0" cellpadding="0" width="250">
						<tbody>
							<tr>
								<td class="tesebg"></td>
							</tr>
							<tr>
								<td class="tesecenter" valign="top" align="left">
									<table border="0" cellspacing="0" cellpadding="0" width="162"
										align="center">
										<tbody>
											<tr>
												<td>
													<table width="100%" cellpadding="0" cellspacing="0">
														<tbody>
															
															<c:forEach var="m" items="${innerLink.children}" >
																<tr>
																<td width="10" valign="top" class="linetext"><img
																	src="//${cdnDomain}/cdn/spring/index_files/icon.gif" style="margin-top:8px;"></td>
																<td class="linetext"><a class="bannerLeft3_linetext"
																	href="${m.link}"
																	title="${m.name}" target="_blank" style="padding-left:5px;">${m.name}</a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</td>
											</tr>

										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td class="tesebottom"></td>
							</tr>
							<tr>
								<td class="tesecenter" valign="top" align="left">
									<table border="0" cellspacing="0" cellpadding="0" width="162"
										align="center">
										<tbody>
											<tr>
												<td align="middle">
													<table width="100%" cellpadding="0" cellspacing="5"
														border="0" align="center">
														<tbody>
															<tr valign="top">
																<td align="center"><a class=""
																	href="/web/spring/blog/view.htm?id=${bannerLeft2s[0].id}"
																	title="${bannerLeft2s[0].title}" target="_blank"><img
																		class="pic1"
																		src="${bannerLeft2s[0].coverImage}"
																		width="130" height="90" border="0"></a></td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="0" cellspacing="0">
														<tbody>
															<c:forEach var="blog" items="${bannerLeft2s}">
																<tr>
																<td width="10" valign="top" class="linetext"><img
																	src="//${cdnDomain}/cdn/spring/index_files/Article_ontop4.gif"
																	alt="固顶文章"></td>
																<td class="linetext"><a class="bannerLeft2_linetext"
																	href="/web/spring/blog/view.htm?id=${blog.id}"
																	title="${blog.title}" target="_blank">${blog.title}</a></td>
															</tr>
															</c:forEach>
															<tr></tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
									<table border="0" cellspacing="0" cellpadding="0" width="162"
										align="center">
										<tbody>
											<tr>
												<td height="22" valign="center" align="right"><a target="_blank"
													href="/web/spring/blog/list.htm?blogType=${bannerLeft2}">更多..</a></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td class="ywbg"></td>
							</tr>
							<tr>
								<td class="tesecenter" valign="top" align="left">
									<table border="0" cellspacing="0" cellpadding="0" width="162"
										align="center">
										<tbody>
											<tr>
												<td>
													<table width="100%" cellpadding="0" cellspacing="0">
														<tbody>
															<c:forEach var="blog" items="${bannerLeft3s}">
																<tr>
																<td width="10" valign="top" class="linetext"><img
																	src="//${cdnDomain}/cdn/spring/index_files/Article_ontop4.gif"
																	alt="固顶文章"></td>
																<td class="linetext"><a class="bannerLeft3_linetext"
																	href="/web/spring/blog/view.htm?id=${blog.id}"
																	title="${blog.title}" target="_blank">${blog.title}</a></td>
															</tr>
															</c:forEach>
															<tr></tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td align="right"><a target="_blank"
													href="/web/spring/blog/list.htm?blogType=${bannerLeft3}">更多...</a></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td class="whbg"></td>
							</tr>
							<tr>
								<td class="tesecenter" valign="top" align="left">
									<table border="0" cellspacing="0" cellpadding="0" width="162"
										align="center">
										<tbody>
											<tr>
												<td>
													<table width="100%" cellpadding="0" cellspacing="0">
														<tbody>
															<c:forEach var="blog" items="${bannerLeft4s}">
																<tr>
																<td width="10" valign="top" class="linetext"><img
																	src="//${cdnDomain}/cdn/spring/index_files/Article_ontop4.gif"
																	alt="固顶文章"></td>
																<td class="linetext"><a class="bannerLeft4_linetext"
																	href="/web/spring/blog/view.htm?id=${blog.id}"
																	title="${blog.title}" target="_blank">${blog.title}</a></td>
															</tr>
															</c:forEach>
															<tr></tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td align="right"><a target="_blank"
													href="/web/spring/blog/list.htm?blogType=${bannerLeft4}">更多...</a></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td class="zybg"></td>
							</tr>
							<tr>
								<td class="zybottom" valign="top" align="left">
									<table border="0" cellspacing="0" cellpadding="0" width="162"
										align="center">
										<tbody>
											<tr>
												<td>
													<table width="100%" cellpadding="0" cellspacing="0">
														<tbody>
															<c:forEach var="blog" items="${bannerLeft5s}">
																<tr>
																<td width="10" valign="top" class="linetext"><img
																	src="//${cdnDomain}/cdn/spring/index_files/Article_ontop4.gif"
																	alt="固顶文章"></td>
																<td class="linetext"><a class="bannerLeft5_linetext"
																	href="/web/spring/blog/view.htm?id=${blog.id}"
																	title="${blog.title}" target="_blank">${blog.title}</a></td>
															</tr>
															</c:forEach>
															<tr></tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td align="right"><a target="_blank"
													href="/web/spring/blog/list.htm?blogType=${bannerLeft5}">更多...</a></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
					<table border="0" cellspacing="0" cellpadding="0" width="250">
						<tbody>
							<tr>
								<td height="16"></td>
							</tr>
						</tbody>
					</table>
				</td>
				<td valign="top" width="750" align="left">
					<jsp:include page="crumbs.jsp" />
					<!-- <table border="0" cellspacing="0" cellpadding="0" width="750"
						align="center">
						<tbody>
							<tr>
								<td height="49" width="47"><img
									src="//${cdnDomain}/cdn/spring/index_files/dh1.gif"
									width="47" height="49"></td>
								<td
									background="//${cdnDomain}/cdn/spring/index_files/dh2.gif"
									width="528">您现在的位置：&nbsp;<a class="LinkPath"
									href="http://www.bhsx.cn/">下村小学</a>&nbsp;&gt;&gt;&nbsp;首页
								</td>
								<td height="49"
									background="//${cdnDomain}/cdn/spring/index_files/dh3.gif"
									width="25"><img
									src="//${cdnDomain}/cdn/spring/index_files/taiyang.gif"
									width="25" height="23"></td>
								<td
									background="//${cdnDomain}/cdn/spring/index_files/dh3.gif"
									width="150"><script type="text/javascript">
var d=new Date()//为日期命名
document.write(""+d.getFullYear()+"年")//返回年
document.write((d.getMonth()+1)+"月")
document.write(d.getDate()+"日")//返回日
var weekday=new Array(7)//建立一个星期的数组
weekday[0]="星期日"
weekday[1]="星期一"
weekday[2]="星期二"
weekday[3]="星期三"
weekday[4]="星期四"
weekday[5]="星期五"
weekday[6]="星期六"
document.write("  " + weekday[d.getDay()])//输出星期
                                                </script></td>
							</tr>
						</tbody>
					</table> -->
					<table border="0" cellspacing="0" cellpadding="0" width="750">
						<tbody>
							<tr>
								<td valign="top" width="528" align="left">
									<table border="0" cellspacing="0" cellpadding="0" width="525">
										<tbody>
											<tr>
												<td height="30"><a href="javascript:void();"><img
														border="0"
														src="//${cdnDomain}/cdn/spring/index_files/newsbg.gif"
														width="525" height="30"></a></td>
											</tr>
											<tr>
												<td>
													<table border="0" cellspacing="0" cellpadding="0"
														width="525">
														<tbody>
															<tr>
																<jsp:include page="flipper.jsp" />
																<td valign="top" width="244" align="left">
																	<table width="100%" cellpadding="0" cellspacing="0">
																		<tbody>
																			<c:forEach var="blog" items="${bannerTops}" >
																				<tr>
																				<td width="10" valign="top" class="linetext"><img
																					src="//${cdnDomain}/cdn/spring/index_files/Article_common4(1).gif"
																					alt="普通文章"></td>
																				<td class="linetext"><a class="bannerTop_linetext"
																					href="/web/spring/blog/view.htm?id=${blog.id}"
																					title="${blog.title}"
																					target="_blank">
																					<span <c:if test="${blog.readed==false}">style="color:red"</c:if>>${blog.title}<span>
																					</a></td>
																				<td align="right" class="linetext" width="40"><font
																					color="red"><fmt:formatDate value="${blog.updateDate}" pattern="MM-dd"/>
																					</font></td>
																				</tr>
																			</c:forEach>
																			<tr></tr>
																		</tbody>
																	</table>
																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td valign="top" width="222" align="left">
									<table border="0" cellspacing="0" cellpadding="0" width="211"
										align="center">
										<tbody>
											<tr>
												<td class="gg" height="37" colspan="3">用户登陆</td>
											</tr>
											<tr>
												<td height="19" colspan="3"><img
													src="//${cdnDomain}/cdn/spring/index_files/dl01.gif"
													width="211" height="19"></td>
											</tr>
											<tr>
												<td height="153"
													background="//${cdnDomain}/cdn/spring/index_files/dlleft.gif"
													width="10"></td>
												<td bgcolor="#fff8de" valign="bottom" width="191"
													align="middle"><iframe id="UserLogin" height="150"
														<c:if test="${ empty sessionScope.accountId}"> src="/web/spring/index/loginFrame.htm?from=http://${frontDomain}"</c:if>
														<c:if test="${not empty sessionScope.accountId}"> src="/web/spring/index/loginSuccess.htm"</c:if>
														frameborder="0" width="180" scrolling="no"></iframe></td>
												<td
													background="//${cdnDomain}/cdn/spring/index_files/dlright.gif"
													width="10"></td>
											</tr>
											<tr>
												<td height="23" colspan="3"><img
													src="//${cdnDomain}/cdn/spring/index_files/dl4.gif"
													width="211" height="23"></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
					<jsp:include page="searchBar.jsp" />

					<table border="0" cellspacing="0" cellpadding="0" width="750">
						<tbody>
							<tr>
								<td valign="top" colspan="2" align="left">
									<jsp:include page="index/banner.jsp" />
								</td>
							</tr>
							
							<tr>
								<td height="120" width="12" align="right"></td>
								<td width="738" align="middle"><object
										data="/manager/assets/bannertwo.swf"
										width="732" height="100" type="application/x-shockwave-flash">
										<param name="movie" value="/Template/bhsx201104/bannertwo.swf">
										<param name="loop" value="-1">
											<param name="quality" value="high">
												<param name="flashvars" value="autoPlay=true">
											
												<param name="bgcolor" value="#FFFFFF">
													<embed src="/Template/bhsx201104/bannertwo.swf"
														quality="high" bgcolor="#FFFFFF" width="732" height="100"
														name="myMovieName" align="" autoPlay="true"  autostart="true" loop="true"
														type="application/x-shockwave-flash"  flashvars="autoPlay=true"
														pluginspage="http://www.macromedia.com/go/getflashplayer">
									</object></td>
							</tr>
							<tr>
								<td colspan="2">
									<jsp:include page="index/ground.jsp" />
								</td>
							</tr>
							<tr>
								<td height="5" valign="top" colspan="2" align="left">
								</td>
							</tr>
							<jsp:include page="index/bannerPic.jsp"/>
							<tr>
								<td height="16" valign="top" colspan="2" align="left"></td>
							</tr>
							<tr>
								<td colspan="2">
									<jsp:include page="index/ground2.jsp" />
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	<jsp:include page="footer.jsp"/>
	<script
		src="//${cdnDomain}/cdn/spring/index_files/CA111761353603847750003.js"
		type="text/javascript"></script>
        <script>
        var mySwiper = new Swiper('.swiper-container', {
        direction: 'horizontal',
        loop: true,
        autoplay: 3000,
        pagination: '.swiper-pagination',
        paginationHide: false,
        parallax: true
        });
        </script>
<a href="http://webscan.360.cn/index/checkwebsite/url/xcxx.szgm.edu.cn" name="2560998304a2afae4990b5df64ab832e" >360网站安全检测平台</a></body>
</html>