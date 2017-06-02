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

<meta http-equiv="x-ua-compatible" content="ie=7">
<title>${cms.title.content}&gt;&gt; </title>
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
										color="#ffffff">${lang.latestAnnouncement[n18]}</font></a></td>
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
																	onmouseover="javascript:void();">${notice1}</li>
																<%-- <li id="one2" onmouseover="setTab(&#39;one&#39;,2,3)">${notice2}</li>
																<li id="one3" onmouseover="setTab(&#39;one&#39;,3,3)">${notice3}</li> --%>
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
																				alt="${lang.userLogin[n18]}"></td>
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
																				<td height="22" valign="center" align="right"><a
																					href="/web/spring/blog/list.htm?blogType=${notice1}">${lang.more[n18]}..</a></td>
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
																					alt="${lang.userLogin[n18]}"></td>
																				<td class="linetext"><a class="bannerTop_linetext"
																					href="/web/spring/blog/view.htm?id=${blog.id}"
																					title="${blog.title}"
																					target="_blank">${blog.title}</a></td>
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
												<td class="gg" height="37" colspan="3">${lang.userLogin[n18]}</td>
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
													align="middle"><iframe id="UserLogin" height="145"
														<c:if test="${ empty sessionScope.accountId}"> src="/web/spring/index/loginFrame.htm?fromUrl=${frontDomain}"</c:if>
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
								<td height="120" width="12" align="right"></td>
								<td width="738" align="middle"><object
										data="/manager/assets/bannertwo.swf"
										width="732" height="100" type="application/x-shockwave-flash">
										<param name="movie" value="/Template/bhsx201104/bannertwo.swf">
											<param name="quality" value="high">
												<param name="bgcolor" value="#FFFFFF">
													<embed src="/Template/bhsx201104/bannertwo.swf"
														quality="high" bgcolor="#FFFFFF" width="732" height="100"
														name="myMovieName" align=""
														type="application/x-shockwave-flash"
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
<a href="http://webscan.360.cn/index/checkwebsite/url/xcxx.szgm.edu.cn"><img border="0" src="http://img.webscan.360.cn/status/pai/hash/7ec353e4f1069d43d40c19cf7fbe055c"/></a>
</body>
</html>