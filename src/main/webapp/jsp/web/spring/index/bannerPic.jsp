<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<tr>
	<td height="51" valign="top"
		background="//${cdnDomain}/cdn/spring/index_files/bjwz.gif"
		colspan="2" align="left"></td>
</tr>
<tr>
	<td height="8" valign="top" colspan="2" align="left"></td>
</tr>
<tr>
	<td valign="top" colspan="2" align="left">
		<div id="Tab2">
			<div class="Menubox">
				<ul>
					<li id="two1" class="hover" onmouseover="setTab(&#39;two&#39;,1,3)"><a
						href="javascript:void();"><font
							color="#ffffff">${bannerPic01}</font></a></li>
					<li id="two2" onmouseover="setTab(&#39;two&#39;,2,3)"><a
						href="javascript:void();"><font
							color="#ffffff">${bannerPic02}</font></a></li>
					<li id="two3" onmouseover="setTab(&#39;two&#39;,3,3)"><a
						href="javascript:void();"><font
							color="#ffffff">${bannerPic03}</font></a></li>
				</ul>
			</div>
			<div class="Contentbox">
				<div style="margin-left: 12px; padding-top: 10px;" id="con_two_1">
					<!--滚动代码开始-->
					<div style="WIDTH: 718px; HEIGHT: 160px; OVERFLOW: hidden"
						id="demo">
						<table border="0" cellpadding="0" align="left" cellspace="0">
							<tbody>
								<tr>
									<td id="demo11" valign="top">
										<table width="100%" cellpadding="0" cellspacing="5" border="0"
											align="center">
											<tbody>
												<tr valign="top">
													<c:forEach var="blog" items="${bannerPic01s}">
														<td align="center"><a class=""
															href="/web/spring/blog/view.htm?id=${blog.id}"
															title="${blog.title}" target="_blank"><img
																class="pic1" src="${blog.coverImage}" width="191"
																height="139" border="0"></a><br>
														<a class=""
															href="javascript:void();"
															title="${blog.title}" target="_blank">${blog.title}</a></td>
													</c:forEach>
												</tr>
											</tbody>
										</table>
									</td>
									<td id="demo12" valign="top"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<script>
  var speed=15
  demo12.innerHTML=demo11.innerHTML
  function Marquee11(){
  if(demo12.offsetWidth-demo.scrollLeft<=0)
  demo.scrollLeft-=demo11.offsetWidth
  else{
  demo.scrollLeft++
  }
  }
  var MyMar1=setInterval(Marquee11,speed)
  demo.onmouseover=function() {clearInterval(MyMar1)}
  demo.onmouseout=function() {MyMar1=setInterval(Marquee11,speed)}
</script>
					<!--滚动代码结束-->
				</div>
				<div style="DISPLAY: none; margin-left: 12px; padding-top: 10px;" id="con_two_2">
					<!--滚动代码开始-->
					<div style="WIDTH: 718px; HEIGHT: 160px; OVERFLOW: hidden"
						id="jiaoshi">
						<table border="0" cellpadding="0" align="left" cellspace="0">
							<tbody>
								<tr>
									<td id="jiaoshi11" valign="top">
										<table width="100%" cellpadding="0" cellspacing="5" border="0"
											align="center">
											<tbody>
												<tr valign="top">
													<c:forEach var="blog" items="${bannerPic02s}">
														<td align="center"><a class=""
															href="/web/spring/blog/view.htm?id=${blog.id}"
															title="${blog.title}" target="_blank"><img
																class="pic1" src="${blog.coverImage}" width="191"
																height="139" border="0"></a><br>
														<a class=""
															href="javascript:void();"
															title="${blog.title}" target="_blank">${blog.title}</a></td>
													</c:forEach>

												</tr>
											</tbody>
										</table>
									</td>
									<td id="jiaoshi12" valign="top"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<script>
  var speed=15
  jiaoshi12.innerHTML=jiaoshi11.innerHTML
  function Marquee11(){
  if(jiaoshi12.offsetWidth-jiaoshi.scrollLeft<=0)
  jiaoshi.scrollLeft-=jiaoshi11.offsetWidth
  else{
  jiaoshi.scrollLeft++
  }
  }
  var MyMar1=setInterval(Marquee11,speed)
  jiaoshi.onmouseover=function() {clearInterval(MyMar1)}
  jiaoshi.onmouseout=function() {MyMar1=setInterval(Marquee11,speed)}
</script>
					<!--滚动代码结束-->
				</div>
				<div style="DISPLAY: none; margin-left: 12px; padding-top: 10px;" id="con_two_3">
					<!--滚动代码开始-->
					<div style="WIDTH: 718px; HEIGHT: 160px; OVERFLOW: hidden"
						id="feng">
						<table border="0" cellpadding="0" align="left" cellspace="0">
							<tbody>
								<tr>
									<td id="feng11" valign="top">
										<table width="100%" cellpadding="0" cellspacing="5" border="0"
											align="center">
											<tbody>
												<tr valign="top">
													<c:forEach var="blog" items="${bannerPic03s}">
														<td align="center"><a class=""
															href="/web/spring/blog/view.htm?id=${blog.id}"
															title="${blog.title}" target="_blank"><img
																class="pic1" src="${blog.coverImage}" width="191"
																height="139" border="0"></a><br>
														<a class=""
															href="javascript:void();"
															title="${blog.title}" target="_blank">${blog.title}</a></td>
													</c:forEach>

												</tr>
											</tbody>
										</table>
									</td>
									<td id="feng12" valign="top"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<script>
  var speed=15
  feng12.innerHTML=feng11.innerHTML
  function Marquee11(){
  if(feng12.offsetWidth-feng.scrollLeft<=0)
  feng.scrollLeft-=feng11.offsetWidth
  else{
  feng.scrollLeft++
  }
  }
  var MyMar1=setInterval(Marquee11,speed)
  feng.onmouseover=function() {clearInterval(MyMar1)}
  feng.onmouseout=function() {MyMar1=setInterval(Marquee11,speed)}
</script>
					<!--滚动代码结束-->
				</div>
			</div>
		</div>
	</td>
</tr>