<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${cms.title.content}</title>


    <style type="text/css">
    
    </style>
</head>

<body>
    <div style="margin:4px">
        <h1>联系我们</h1>
        <div>地址：${cms.schoolAddress.content}</div>
        <div>邮编：${cms.mailCode.content}</div>
        <div>电话：${cms.schoolMobile.content}</div>
        <div>网址：${cms.schoolWebsite.content}</div>
        <a  href="http://api.map.baidu.com/marker?location=22.762282,113.953329&amp;title=广东省&amp;content=深圳市光明小学&amp;output=html"><img style="max-width: 100%;" src="http://api.map.baidu.com/staticimage?center=113.953329%2C22.762282&amp;zoom=19&amp;width=720&amp;height=400&amp;markers=114.091528%2C22.637969&amp;markerStyles=l%2CA" alt="" /></a>
    </div>

</body>

</html>
