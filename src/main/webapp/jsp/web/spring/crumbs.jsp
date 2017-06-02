<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<table cellspacing="0" cellpadding="0" align="center" border="0">
	<tbody>
		<tr>
			<td width="47" height="49"><img height="49"
				src="//${cdnDomain}/cdn/spring/index_files/dh1.gif" width="47"></td>
			<td width="528" align="left" valign="middle"
				background="//${cdnDomain}/cdn/spring/index_files/dh2.gif">
				${lang.currentLocation[n18]}：&nbsp;<a class="LinkPath" href="/web/spring/index.htm">${cms.title.content}</a>
				<c:forEach var="crumb" items="${crumbs}">
										&nbsp;&gt;&gt;&nbsp;<a class="LinkPath"
						href="/web/spring/blog/list.htm?blogType=${crumb}">${crumb}</a>
				</c:forEach>
			</td>
			<td width="25"
				background="//${cdnDomain}/cdn/spring/index_files/dh3.gif"
				height="49"><img height="23"
				src="//${cdnDomain}/cdn/spring/index_files/taiyang.gif" width="25"></td>
			<td width="150"
				background="//${cdnDomain}/cdn/spring/index_files/dh3.gif"><script
					type="text/javascript">
					var d = new Date()//为日期命名
					<c:if test="${n18=='cn'}">
					document.write("" + d.getFullYear() + "年"+(d.getMonth() + 1)+"月"+d.getDate())//返回年
					</c:if>
					<c:if test="${n18=='en'}">
					document.write("" + d.getFullYear() + "-"+(d.getMonth() + 1)+"-"+d.getDate())//返回年
					</c:if>
					var weekday = new Array(7)//建立一个星期的数组
					weekday[0] = "${lang.week7[n18]}"
					weekday[1] = "${lang.week1[n18]}"
					weekday[2] = "${lang.week2[n18]}"
					weekday[3] = "${lang.week3[n18]}"
					weekday[4] = "${lang.week4[n18]}"
					weekday[5] = "${lang.week5[n18]}"
					weekday[6] = "${lang.week6[n18]}"
					document.write("  " + weekday[d.getDay()])//输出星期
				</script></td>
			<td width="50"><c:if test="${n18=='cn'}"><a id="enBtn" href="/web/spring/index.htm" targe="self">English</a></c:if><c:if test="${n18=='en'}"><a id="cnBtn" href="/web/spring/index.htm" targe="self">Chinese</a></c:if></td>
		</tr>
	</tbody>
</table>
<script type="text/javascript" src="/plugins/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/plugins/jquery/jquery.cookie.js"></script>
<script type="text/javascript">
	if($.cookie('n18') == null)
		$.cookie('n18','cn',{path:"/"});	
	
	$("#enBtn").bind("click",function(){
		$.cookie('n18','en',{path:"/"});	
	})
	$("#cnBtn").bind("click",function(){
		$.cookie('n18','cn',{path:"/"});	
	})
</script>