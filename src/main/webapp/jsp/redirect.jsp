<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String forwardUrl="/index.htm";
	/* if(request.getServerName().contains("xcxx.goldskyer.com") || request.getServerName().contains("xcxx.szgm.edu.cn"))
	{
		forwardUrl="/web/spring/index.htm";
	} */
	forwardUrl="/web/spring/index.htm";
%>

<jsp:forward page="<%=forwardUrl %>" />