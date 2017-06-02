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
<table border="0" cellspacing="0" cellpadding="0" width="750"
	align="left">
	<tbody>
		<tr>
			<td>
				<table border="0" cellspacing="0" cellpadding="0" width="750"
					align="center">
					<tbody>
						<tr>
							<td height="50" width="375"><div class="item-title">${banner01}</div><a
								href="javascript:void();"><img border="0"
									src="//${cdnDomain}/cdn/spring/index_files/dyzc.gif"
									width="375" height="50"></a></td>
							<td height="50" width="375"><div class="item-title">${banner02}</div><a href="javascript:void();"><img border="0"
									src="//${cdnDomain}/cdn/spring/index_files/jxtd.gif"
									width="375" height="50"></a></td>
						</tr>
						<tr>
							<td height="25" valign="top"
								background="//${cdnDomain}/cdn/spring/index_files/hd_top.gif"
								align="left"></td>
							<td valign="top"
								background="//${cdnDomain}/cdn/spring/index_files/hd_top.gif"
								width="375" align="left"></td>
						</tr>
						<tr>
							<td valign="top"
								background="//${cdnDomain}/cdn/spring/index_files/hd_cen.gif"
								align="left">
								<div id="Tab4">
									<div class="Menufour">
										<ul>
											<c:forEach var="m" items="${banner01Menu.children}"
												varStatus="stat">
												<c:if test="${stat.index<4}">
													<c:if test="${stat.index==0}">
														<li id="four${stat.index+1}" class="hover"
														onmouseover="setTab(&#39;four&#39;,${stat.index+1},4)">${m.name}</li>
													</c:if>
													<c:if test="${stat.index>0}">
														<li id="four${stat.index+1}"
														onmouseover="setTab(&#39;four&#39;,${stat.index+1},4)">${m.name}</li>
													</c:if>		
												</c:if>
											</c:forEach>
										</ul>
									</div>
									<div class="fourbox">
										<c:forEach var="list" items="${banner01List}" varStatus="stat">
											<div id="con_four_${stat.index+1}"
												<c:if test="${stat.index==0}"> style="display: block;" </c:if>
												<c:if test="${stat.index>0}"> style="display: none;" </c:if>>
												<table width="100%" cellpadding="0" cellspacing="0">
													<tbody>
														<c:forEach var="blog" items="${list}">
															<tr>
																<td width="10" valign="top" class="linetextx"><img
																	src="//${cdnDomain}/cdn/spring/index_files/Article_common4(3).gif"
																	alt="普通文章"></td>
																<td class="linetext block_linetext"><a class=""
																	href="/web/spring/blog/view.htm?id=${blog.id}"
																	title="${blog.title}" target="_blank">${blog.title}</a></td>
																<td align="right" class="linetextx" width="40"><fmt:formatDate
																		value="${blog.updateDate}" pattern="MM-dd" /></td>
															</tr>
														</c:forEach>
														<tr></tr>
													</tbody>
												</table>
												<br>
												<table border="0" cellspacing="0" cellpadding="0"
													width="94%" align="center">
													<tbody>
														<tr>
															<td height="22" valign="center" align="right"><a target="_blank"
																href="/web/spring/blog/list.htm?blogType=${banner01Menu.children[stat.index].name}">更多..</a></td>
														</tr>
													</tbody>
												</table>
											</div>
										</c:forEach>
									</div>
								</div>
							</td>
							<td valign="top"
								background="//${cdnDomain}/cdn/spring/index_files/hd_cen.gif"
								width="375" align="left">
								<div id="Tab5">
									<div class="Menufive">
										<ul>
											<c:forEach var="m" items="${banner02Menu.children}"
												varStatus="stat">
												<c:if test="${stat.index<4}">
													<c:if test="${stat.index==0}">
														<li id="five${stat.index+1}" class="hover"
														onmouseover="setTab(&#39;five&#39;,${stat.index+1},4)">${m.name}</li>
													</c:if>
													<c:if test="${stat.index>0}">
														<li id="five${stat.index+1}"
														onmouseover="setTab(&#39;five&#39;,${stat.index+1},4)">${m.name}</li>
													</c:if>			
												</c:if>
											</c:forEach>
										</ul>
									</div>
									<div class="fivebox">
										<c:forEach var="list" items="${banner02List}" varStatus="stat">
											<div id="con_five_${stat.index+1}"
												<c:if test="${stat.index==0}"> style="display: block;" </c:if>
												<c:if test="${stat.index>0}"> style="display: none;" </c:if>>
												<table width="100%" cellpadding="0" cellspacing="0">
													<tbody>
														<c:forEach var="blog" items="${list}">
															<tr>
																<td width="10" valign="top" class="linetextx"><img
																	src="//${cdnDomain}/cdn/spring/index_files/Article_common4(3).gif"
																	alt="普通文章"></td>
																<td class="linetext block_linetext"><a class=""
																	href="/web/spring/blog/view.htm?id=${blog.id}"
																	title="${blog.title}" target="_blank">${blog.title}</a></td>
																<td align="right" class="linetextx" width="40"><fmt:formatDate
																		value="${blog.updateDate}" pattern="MM-dd" /></td>
															</tr>
														</c:forEach>
														<tr></tr>
													</tbody>
												</table>
												<br>
												<table border="0" cellspacing="0" cellpadding="0"
													width="94%" align="center">
													<tbody>
														<tr>
															<td height="22" valign="center" align="right"><a target="_blank"
																href="/web/spring/blog/list.htm?blogType=${banner02Menu.children[stat.index].name}">更多..</a></td>
														</tr>
													</tbody>
												</table>
											</div>
										</c:forEach>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td height="25" valign="top"
								background="//${cdnDomain}/cdn/spring/index_files/hd_bot.gif"
								align="left"></td>
							<td height="25" valign="top"
								background="//${cdnDomain}/cdn/spring/index_files/hd_bot.gif"
								align="left"></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>