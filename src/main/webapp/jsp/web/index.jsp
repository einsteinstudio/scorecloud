<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="zh-CN">
 <head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <meta name="description" content="" />
  <link rel="icon" href="//${cdnDomain}/cdn/pcweb/img/favicon.ico" />
  <title>深圳市下村小学</title>
  <link href="//${cdnDomain}/cdn/pcweb/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link href="//${cdnDomain}/cdn/pcweb/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
  <link href="//${cdnDomain}/cdn/pcweb/bower_components/Swiper/dist/css/swiper.min.css" rel="stylesheet" />
 </head>
 <body>
  <div class="container">
	<jsp:include  page="header.jsp"/> 	
   <ol class="breadcrumb">
    <i class="fa fa-hand-o-right"></i> 
    <span>您现在的位置:</span>
    <li><a href="#">深圳市下村小学</a></li>
    <li><a href="#">公明下村在线</a></li>
    <li class="active">首页</li>
    <span class="pull-right">2016年5月19日 星期四</span>
   </ol>
   <div class="row">
    <div class="col-xs-3">
	<jsp:include  page="sidebar.jsp"/> 	
     
    </div>
    <div class="col-xs-9">
     <div class="row">
      <div class="col-xs-8">
       <div class="panel panel-primary">
        <div class="panel-heading">
         <h3 class="panel-title">校园新闻<a class="pull-right my-news-more" href="#">更多</a></h3>
        </div>
        <div class="panel-body row my-panel-fix-1">
         <div id="carousel-example-generic" class="carousel slide col-xs-6 my-padding-fix-0 my-carousel-fix" data-ride="carousel">
          <ol class="carousel-indicators">
           <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
           <li data-target="#carousel-example-generic" data-slide-to="1"></li>
           <li data-target="#carousel-example-generic" data-slide-to="2"></li>
          </ol>
          <div class="carousel-inner" role="listbox">
          
          	<c:forEach var="item" items="${xinwens}"  varStatus="stat" >
          		<div class='item  <c:if test="${stat.index==0}">active</c:if>'>
		            <img src="${item.thumbImage}" alt="{item.title}" />
		            <div class="carousel-caption">
		             
		            </div>
		           </div>
          	</c:forEach>

          </div>
         </div>
         <div class="list-group col-xs-6 my-list-group-fix-1">
         	<c:forEach var="item" items="${xinwens}"  varStatus="stat" >
         	       <a href="/web/blog/view.htm?id=${item.id}" class="list-group-item list-group-item-success" title="${item.title}"><span class="my-news-item-title">${item.title}</span> <span><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd"/></span> <c:if test="${stat.index<3}"><span class="label label-warning twinkling">New</span></c:if> </a>
	      	</c:forEach>
         </div>
        </div>
       </div>
      </div>
      <div class="col-xs-4">
       <div class="input-group my-search">
        <input type="text" class="form-control" placeholder="关键字搜索..." /> 
        <span class="input-group-btn"><button class="btn btn-default" type="button"><i class="fa fa-search"></i></button></span>
       </div>
       <div class="panel panel-info">
        <div class="panel-heading">
         <h3 class="panel-title">用户登录</h3>
        </div>
        <div class="panel-body my-login">
         <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-user ft-17"></i></span> 
          <input type="text" class="form-control" placeholder="用户名" />
         </div>
         <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-key"></i></span> 
          <input type="password" class="form-control" placeholder="密码" />
         </div>
         <button type="button" class="btn btn-primary">登录</button> 
         <a href="#">忘记密码?</a>
        </div>
       </div>
      </div>
     </div>
     <div class="row">
      <div class="col-xs-6">
       <div class="panel panel-danger">
        <div class="panel-heading">
         <h3 class="panel-title">百校帮扶<a class="pull-right my-news-more" href="/web/blog/list.htm?blogType=百校帮扶">更多</a></h3>
        </div>
        <div class="panel-body list-group my-padding-fix-0">
	        <c:forEach var="item" items="${baixiaos}"  varStatus="stat" >
	                 <a href="/web/blog/view.htm?id=${item.id}" class="list-group-item" title="${item.title}"><span class="my-news-item-title">${item.title}</span> <span><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd"/></span> <c:if test="${stat.index<3}"> <span class="label label-warning twinkling">New</span> </c:if> </a>  	        
	      	</c:forEach>
        </div>
       </div>
      </div>
      <div class="col-xs-6">
       <div class="panel panel-danger">
        <div class="panel-heading">
         <h3 class="panel-title">服务窗口<a class="pull-right my-news-more" href="#">更多</a></h3>
        </div>
        <div class="panel-body list-group my-padding-fix-0">
        	<c:forEach var="item" items="${fuwus}" varStatus="stat">
	                 <a href="/web/blog/view.htm?id=${item.id}" class="list-group-item" title="${item.title}"><span class="my-news-item-title">${item.title}</span> <span><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd"/></span> <c:if test="${stat.index<3}"> <span class="label label-warning twinkling">New</span> </c:if> </a>  	        
	      	</c:forEach>
        </div>
       </div>
      </div>
     </div>
     <div class="row my-bg-1"></div>
     <div class="row">
      <div class="col-xs-6">
       <div class="panel panel-warning">
        <div class="panel-heading">
         <h3 class="panel-title">家长必读<a class="pull-right my-news-more" href="/web/blog/list.htm?blogType=家长必读">更多</a></h3>
        </div>
        <div class="panel-body list-group my-padding-fix-0">
         	<c:forEach var="item" items="${jiazhangs}" varStatus="stat">
	                 <a href="/web/blog/view.htm?id=${item.id}" class="list-group-item" title="${item.title}"><span class="my-news-item-title">${item.title}</span> <span><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd"/></span> <c:if test="${stat.index<3}"> <span class="label label-warning twinkling">New</span> </c:if> </a>  	        
	      	</c:forEach>
         </div>
       </div>
      </div>
      <div class="col-xs-6">
       <div class="panel panel-warning">
        <div class="panel-heading">
         <h3 class="panel-title">校务公开<a class="pull-right my-news-more" href="/web/blog/list.htm?blogType=校务公开">更多</a></h3>
        </div>
        <div class="panel-body list-group my-padding-fix-0">
         	<c:forEach var="item" items="${xiaowus}" varStatus="stat">
	                 <a href="/web/blog/view.htm?id=${item.id}" class="list-group-item" title="${item.title}"><span class="my-news-item-title">${item.title}</span> <span><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd"/></span> <c:if test="${stat.index<3}"> <span class="label label-warning twinkling">New</span> </c:if> </a>  	        
	      	</c:forEach>
         </div>
       </div>
      </div>
     </div>
     <div class="row my-tab">
      <ul class="nav nav-tabs" role="tablist">
       <li role="presentation" class="active"><a href="#scenery" aria-controls="scenery" role="tab" data-toggle="tab">校园风景</a></li>
      </ul>
      <div class="tab-content">
       <div role="tabpanel" class="tab-pane active" id="scenery">
        <div class="swiper-container">
         <div class="swiper-wrapper">
          
      		<c:forEach var="item" items="${fengjings}" varStatus="stat">
	      		<div class="swiper-slide">
	           <img src="${item.coverImage}" class="my-pic" />
	          </div>
	      	</c:forEach>
         </div>
        </div>
       </div>
      </div>
     </div>
    </div>
   </div>
	<jsp:include  page="footer.jsp"/> 	
  </div>
  <script src="//${cdnDomain}/cdn/pcweb/bower_components/jquery/dist/jquery.min.js"></script>
  <script src="//${cdnDomain}/cdn/pcweb/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="//${cdnDomain}/cdn/pcweb/dist/common.js?014700efcdcbb0c49f07"></script>
  <script type="text/javascript" src="//${cdnDomain}/cdn/pcweb/dist/index.js?014700efcdcbb0c49f07"></script>
  <a href="http://webscan.360.cn/index/checkwebsite/url/xcxx.szgm.edu.cn"><img border="0" src="http://img.webscan.360.cn/status/pai/hash/7ec353e4f1069d43d40c19cf7fbe055c"/></a>
 </body>
</html>