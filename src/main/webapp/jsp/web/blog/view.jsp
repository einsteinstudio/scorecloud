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
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                ${blog.type}
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div class="blog-post">
                                <h2 class="blog-post-title">
                                    ${blog.title}
                                </h2>
                                <p class="blog-post-meta">
                                    发表于 <fmt:formatDate value="${blog.updateDate}" pattern="yyyy-MM-dd"/>&nbsp;作者 ${blog.author}
                                </p>
                                ${blog.content}
                            </div>
                            
                        </div>
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