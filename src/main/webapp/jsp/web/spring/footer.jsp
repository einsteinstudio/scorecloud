<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:if test="${n18=='cn'}">
	<table width="1000" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tbody>
			<tr>
				<td width="109" height="34"><img
					src="//${cdnDomain}/cdn/spring/index_files/web_l.gif" width="109"
					height="34"></td>
				<td width="867"
					background="//${cdnDomain}/cdn/spring/index_files/web_c.gif"><table
						width="867" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<c:forEach var="child" items="${friendLink.children}">
									<td width="175" align="center"><select id="friendsitelist"
										onchange="if(this.options[this.selectedIndex].value!=&#39;&#39;){window.open(this.options[this.selectedIndex].value);}"><option
												selected="">－－${child.name}－－</option>
											<c:forEach var="m" items="${child.children}">
												<option value="${m.link}">${m.name}</option>
											</c:forEach>
								</c:forEach>
								<td width="342"></td>
							</tr>
						</tbody>
					</table></td>
				<td width="24"><img
					src="//${cdnDomain}/cdn/spring/index_files/web_r.gif" width="24"
					height="34"></td>
			</tr>
		</tbody>
	</table></c:if>
	<table width="1000" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tbody>
			<tr>
				<td height="207" align="center" valign="middle"
					background="//${cdnDomain}/cdn/spring/index_files/bottom_bg.gif"><table
						width="960" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td height="26" align="center">| <a class="Bottom"
									onclick="this.style.behavior='url(#default#homepage)'; this.setHomePage('http://${frontDomain}');">${lang.setHomePage[n18]}</a> | <a
									class="Bottom"
									href="javascript:window.external.addFavorite('http://${frontDomain}','深圳下村学校')">${lang.addToFavorite[n18]}</a>
									| <a class="Bottom" href="mailto:caesarzhj@qq.com">${lang.contactManager[n18]}</a>| <a class="Bottom"
									href="/manager/index.htm"
									target="_blank">${lang.backendLogin[n18]}</a>&nbsp;|&nbsp;
								</td>
							</tr>
							<tr>
								<td height="3" bgcolor="#6FA811"></td>
							</tr>
							<tr>
								<td height="80" align="center" valign="middle">${cms.contact01.content}
								</td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>