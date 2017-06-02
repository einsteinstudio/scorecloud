<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 对外通知 -->
     <div class="panel panel-success">
      <div class="panel-heading">
       <h3 class="panel-title">对外通知<a class="pull-right my-news-more" href="/web/blog/list.htm?blogType=对外通知">更多</a></h3>
      </div>
      <div class="panel-body list-group my-padding-fix-0">
      	<c:forEach var="item" items="${notices}" varStatus="stat">
      		 <a href="/web/blog/view.htm?id=${item.id}" class="list-group-item" title="${item.title}"><span class="my-news-item-title">${item.title}</span> <span><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd"/></span> <c:if test="${stat.index<3}"> <span class="label label-warning twinkling">New</span> </c:if></a>      		
      	</c:forEach>
      </div>
     </div>
     <!--安全管理 -->
     <div class="panel panel-success">
      <div class="panel-heading">
       <h3 class="panel-title">安全管理<a class="pull-right my-news-more" href="/web/blog/list.htm?blogType=安全管理">更多</a></h3>
      </div>
      <div class="panel-body list-group my-padding-fix-0">
      	<c:forEach var="item" items="${anquans}"  varStatus="stat" >
      		 <a href="/web/blog/view.htm?id=${item.id}" class="list-group-item" title="${item.title}"><span class="my-news-item-title">${item.title}</span> <span><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd"/></span> <c:if test="${stat.index<3}"> <span class="label label-warning twinkling">New</span> </c:if> </a>      		
      	</c:forEach>
      </div>
     </div>
     <!--教师随笔 -->
     <div class="panel panel-success">
      <div class="panel-heading">
       <h3 class="panel-title">教师随笔<a class="pull-right my-news-more" href="/web/blog/list.htm?blogType=教师随笔">更多</a></h3>
      </div>
      <div class="panel-body list-group my-padding-fix-0">
      	<c:forEach var="item" items="${suibis}"  varStatus="stat">
      		 <a href="/web/blog/view.htm?id=${item.id}" class="list-group-item" title="${item.title}"><span class="my-news-item-title">${item.title}</span> <span><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd"/></span> <c:if test="${stat.index<3}"> <span class="label label-warning twinkling">New</span> </c:if> </a>      		
      	</c:forEach>
      </div>
     </div>
     <!--党群路线 -->
     <div class="panel panel-success">
      <div class="panel-heading">
       <h3 class="panel-title">党群路线<a target="_blank" class="pull-right my-news-more" href="/web/blog/list.htm?blogType=党群路线">更多</a></h3>
      </div>
      <div class="panel-body list-group my-padding-fix-0">
      	<c:forEach var="item" items="${dangquns}"  varStatus="stat">
      		 <a href="/web/blog/view.htm?id=${item.id}" class="list-group-item" title="${item.title}"><span class="my-news-item-title">${item.title}</span> <span><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd"/></span> <c:if test="${stat.index<3}"> <span class="label label-warning twinkling">New</span> </c:if> </a>      		
      	</c:forEach>
      </div>
     </div>
     <!-- end block -->