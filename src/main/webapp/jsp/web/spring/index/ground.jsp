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
<table border="0" cellspacing="0" cellpadding="0" width="750">
	<tbody>
		<tr>
			<td valign="top" align="middle">
				<table class="ground" border="0" cellspacing="0" cellpadding="0" width="375">
					<tbody>
						<tr>
							<td height="51"><div class="item-title">${ground01}</div><a href="javascript:void();"><img
									border="0"
									src="//${cdnDomain}/cdn/spring/index_files/kypt.gif"
									width="375" height="51"></a></td>
						</tr>
						<tr>
							<td height="115" valign="center" align="middle">
								<table border="0" cellspacing="0" cellpadding="0" width="370">
									<tbody>
										<tr>
											<td height="69" valign="center" width="92" align="middle"><img
												<c:if test="${fn:length(ground01s)>0 &&not empty ground01s[0].thumbImage  }">src="${ground01s[0].thumbImage}"</c:if>
												<c:if test="${fn:length(ground01s)==0 || empty ground01s[0].thumbImage}">src="//${cdnDomain}/cdn/spring/index_files/nopic.gif"</c:if>
												
												width="85" height="auto" border="0"></td>
											<td width="11"></td>
											<td valign="top" width="267"><c:if
													test="${fn:length(ground01s)>0}">
													<table border="0" cellspacing="0" cellpadding="0"
														width="98%">
														<tbody>
															<tr>
																<td height="22" align="left"><a target="_blank"
																	href="/web/spring/blog/view.htm?id=${ground01s[0].id}"><font
																		style="FONT-WEIGHT: bold" color="#009933">${ground01s[0].title}</font></a></td>
															</tr>
															<tr>
																<td height="34" align="left">${ground01s[0].subject}</td>
															</tr>
															<tr>
																<td height="22" align="right"><a title="" target="_blank"
																	href="/web/spring/blog/view.htm?id=${ground01s[0].id}"
																	target="_top">详细...</a></td>
															</tr>
														</tbody>
													</table>
												</c:if></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td><img
								src="//${cdnDomain}/cdn/spring/index_files/lm_top.gif"
								width="375" height="17"></td>
						</tr>
						<tr>
							<td valign="top"
								background="//${cdnDomain}/cdn/spring/index_files/lm_cen.gif"
								align="middle">
								<table border="0" cellspacing="0" cellpadding="0" width="355">
									<tbody>
										<tr>
											<td valign="top" align="left">
												<table width="100%" cellpadding="0" cellspacing="0">
													<tbody>
														<c:forEach var="blog" items="${ground01s}">
															<tr>
																<td width="10" valign="top" class="linetextx"><img
																	src="//${cdnDomain}/cdn/spring/index_files/Article_ontop5.gif"
																	alt="固顶文章"></td>
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
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td valign="top"
								background="//${cdnDomain}/cdn/spring/index_files/lm_cen.gif"
								align="middle">
								<table border="0" cellspacing="0" cellpadding="0" width="94%" align="center">
									<tbody>
										<tr>
											<td height="22" valign="center" align="right"><a target="_blank" href="/web/spring/blog/list.htm?blogType=${ground01}">更多..</a></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td><img
								src="//${cdnDomain}/cdn/spring/index_files/lm_bottom.gif"
								width="375" height="17"></td>
						</tr>
					</tbody>
				</table>
			</td>
			<td valign="top" align="middle">
				<table class="ground" border="0" cellspacing="0" cellpadding="0" width="375">
					<tbody>
						<tr>
							<td height="51"><div class="item-title">${ground02}</div><a href="javascript:void();"><img
									border="0"
									src="//${cdnDomain}/cdn/spring/index_files/jgzj.gif"
									width="375" height="51"></a></td>
						</tr>
						<tr>
							<td height="115" valign="center" align="middle">
								<table border="0" cellspacing="0" cellpadding="0" width="370">
									<tbody>
										<tr>
											<td height="69" valign="center" width="92" align="middle"><img
												<c:if test="${fn:length(ground02s)>0 &&not empty ground02s[0].thumbImage  }">src="${ground02s[0].thumbImage}"</c:if>
												<c:if test="${fn:length(ground02s)==0 || empty ground02s[0].thumbImage}">src="//${cdnDomain}/cdn/spring/index_files/nopic.gif"</c:if>
												
												width="85" height="auto" border="0"></td>
											<td width="11"></td>
											<td valign="top" width="267"><c:if
													test="${fn:length(ground02s)>0}">
													<table border="0" cellspacing="0" cellpadding="0"
														width="98%">
														<tbody>
															<tr>
																<td height="22" align="left"><a target="_blank"
																	href="/web/spring/blog/view.htm?id=${ground02s[0].id}"><font
																		style="FONT-WEIGHT: bold" color="#009933">${ground02s[0].title}</font></a></td>
															</tr>
															<tr>
																<td height="34" align="left">${ground02s[0].subject}</td>
															</tr>
															<tr>
																<td height="22" align="right"><a title="" target="_blank"
																	href="/web/spring/blog/view.htm?id=${ground02s[0].id}"
																	target="_top">详细...</a></td>
															</tr>
														</tbody>
													</table>
												</c:if></td>
										</tr>									
									</tbody>
								</table>
							</td>
							
						</tr>
						<tr>
							<td><img
								src="//${cdnDomain}/cdn/spring/index_files/rm_top.gif"
								width="375" height="17"></td>
						</tr>
						<tr>
							<td valign="top"
								background="//${cdnDomain}/cdn/spring/index_files/rm_cen.gif"
								align="middle">
								<table border="0" cellspacing="0" cellpadding="0" width="355">
									<tbody>
										<tr>
											<td valign="top" align="left">
												<table width="100%" cellpadding="0" cellspacing="0">
													<tbody>
														<c:forEach var="blog" items="${ground02s}">
															<tr>
																<td width="10" valign="top" class="linetextx"><img
																	src="//${cdnDomain}/cdn/spring/index_files/Article_common4(4).gif"
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
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td valign="top"
								background="//${cdnDomain}/cdn/spring/index_files/rm_cen.gif"
								align="middle">
								<table border="0" cellspacing="0" cellpadding="0" width="94%" align="center">
									<tbody>
										<tr>
											<td height="22" valign="center" align="right"><a target="_blank" href="/web/spring/blog/list.htm?blogType=${ground02}">更多..</a></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td><img
								src="//${cdnDomain}/cdn/spring/index_files/rm_bottom.gif"
								width="375" height="17"></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>