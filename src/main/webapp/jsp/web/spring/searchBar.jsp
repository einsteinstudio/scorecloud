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

<table style="display:none" cellspacing="0" cellpadding="0" width="750" align="center"
	border="0">
	<form id="search" name="search"
		action="/web/spring/blog/list.htm" method="post"></form>
	<tbody>
		<tr>
			<td width="156" height="49"><img height="49"
				src="//${cdnDomain}/cdn/spring/index_files/sousuo1.gif"
				width="156"></td>
			<td align="middle" width="515"
				background="//${cdnDomain}/cdn/spring/index_files/sousuo2.gif"><input
				type="radio" checked="" value="Article" name="ModuleName">
				文章 <input type="radio" value="Soft" name="ModuleName"> 下载 <input
				type="radio" value="Photo" name="ModuleName"> 图片 <input
				id="Keyword" maxlength="50" value="关键字" name="Keyword"> <input
				id="Submit" type="submit" value="・搜索・" name="Submit"> <input
				id="Field" type="hidden" value="Title" name="Field"></td>
			<td align="middle" width="79"><img height="49"
				src="//${cdnDomain}/cdn/spring/index_files/sousuo3.gif"
				width="79"></td>
		</tr>

	</tbody>
</table>