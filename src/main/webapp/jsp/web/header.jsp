<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <header class="header"></header>
 <div class="masthead">
  <nav>
   <ul class="nav nav-justified">
    <li class="active"><a href="#">首页</a></li>
    <c:forEach var="menu" items="${cachedMenu.children}" >
         <li><a href="/web/blog/list.htm?type=${mm.name}">${menu.name}</a></li>
  </c:forEach>
   </ul>
  </nav>
 </div>