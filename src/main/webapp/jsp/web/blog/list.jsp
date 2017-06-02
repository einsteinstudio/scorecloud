<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <meta name="description" content="">
        <link rel="icon" href="../img/favicon.ico">
        <title>
            深圳市下村小学
        </title>
        <link href="//${cdnDomain}/cdn/pcweb/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="//${cdnDomain}/cdn/pcweb/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            	<jsp:include  page="../header.jsp"/> 	
            
            <ol class="breadcrumb">
                <li style="list-style: none">
                    <span>您现在的位置:</span>
                </li>
                <li>
                    <a href="#">深圳市下村小学</a>
                </li>
                <li>
                    <a href="#">公明下村在线</a><span class="pull-right">2016年5月19日 星期四</span>
                </li>
                <li class="active">校园新闻
                </li>
            </ol>
            <div class="row">
                <div class="col-xs-3">
                    	<jsp:include  page="../sidebar.jsp"/> 	
                </div>
                <div class="col-xs-9">
                    <div class="panel-body list-group my-padding-fix-0">
                        <c:forEach var="blog" items="${blogs}" >
                        	<a href="#" class="list-group-item" title="${blog.title}"><span class="my-news-item-title-1">${blog.title}</span> <span class="pull-right"><fmt:formatDate value="${blog.updateDate}" pattern="yyyy-MM-dd"/></span></a> 
					    </c:forEach>
                        <%@ include file="pagination.jsp"%>
                    </div>
                </div>
            </div>
            	<jsp:include  page="../footer.jsp"/> 	         
        </div><script src="//${cdnDomain}/cdn/pcweb/bower_components/jquery/dist/jquery.min.js" type="text/javascript">
</script><script src="//${cdnDomain}/cdn/pcweb/bower_components/bootstrap/dist/js/bootstrap.min.js" type="text/javascript">
</script><script type="text/javascript" src="//${cdnDomain}/cdn/pcweb/dist/js/common.js?014700efcdcbb0c49f07">
</script><script type="text/javascript" src="//${cdnDomain}/cdn/pcweb/dist/js/view.js?014700efcdcbb0c49f07">
</script>
    </body>
</html>