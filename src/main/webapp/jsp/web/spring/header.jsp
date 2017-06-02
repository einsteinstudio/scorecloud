<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0">
  <tbody><tr>
    <td>
  
  <div style="background: url('//${cdnDomain}/cdn/spring/index_files/topPic3_meitu_1_meitu_3.jpg') no-repeat center; width: 1000px;
  height: 240px;">
  <div style="float:right">
<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0" height="210" width="350">
<param name="movie" value="/manager/assets/xc.swf">
<param name="quality" value="high">
<param name="Loop" value="-1">
<param name="wmode" value="transparent">
<embed autostart="true" loop="true" src="/manager/assets/xc.swf" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="350" height="210" wmode="transparent">

</object>
</div>
  </div> 
  </td>
  </tr>
</tbody></table>
<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0">
  <tbody>
  <tr>
    <td class="menu">|
    <a class="Channel" href="/web/spring/index.htm">${lang.index[n18]}</a>&nbsp;|&nbsp;
    <c:forEach var="menu" items="${cachedMenu.children}" >
    	<c:if test="${menu.hide==false &&(!menu.needLogin || (menu.needLogin && sessionScope.accountId!=null) )}">
    		      <a class="Channel" href="/web/spring/blog/list.htm?blogType=${menu.name}" target="_blank" title="">${menu.name}</a>|
    	</c:if>
  	</c:forEach>
    </td>
  </tr>
  <tr>
    <td class="banner_bottom"></td>
  </tr>
</tbody>
</table>