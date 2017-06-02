<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

   <div class="row my-site">
    <div class="panel panel-info">
     <div class="panel-heading">
      <h3 class="panel-title">网址导航</h3>
     </div>
     <div class="panel-body">
      <div class="col-xs-3">
       <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">国家级教育网站<span class="caret"></span></button>
        <ul class="dropdown-menu">
         <li><a href="#">教育部基教教材中心</a></li>
         <li><a href="#">中华人民共和国教育部</a></li>
         <li><a href="#">中国心理导向网</a></li>
         <li><a href="#">国家基础教育资源网</a></li>
         <li><a href="#">中国基础教育网</a></li>
        </ul>
       </div>
      </div>
      <div class="col-xs-3">
       <div class="btn-group">
        <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">国家级教育网站<span class="caret"></span></button>
        <ul class="dropdown-menu">
         <li><a href="#">教育部基教教材中心</a></li>
         <li><a href="#">中华人民共和国教育部</a></li>
         <li><a href="#">中国心理导向网</a></li>
         <li><a href="#">国家基础教育资源网</a></li>
         <li><a href="#">中国基础教育网</a></li>
        </ul>
       </div>
      </div>
      <div class="col-xs-3">
       <div class="btn-group">
        <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">国家级教育网站<span class="caret"></span></button>
        <ul class="dropdown-menu">
         <li><a href="#">教育部基教教材中心</a></li>
         <li><a href="#">中华人民共和国教育部</a></li>
         <li><a href="#">中国心理导向网</a></li>
         <li><a href="#">国家基础教育资源网</a></li>
         <li><a href="#">中国基础教育网</a></li>
        </ul>
       </div>
      </div>
      <div class="col-xs-3">
       <div class="btn-group">
        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">国家级教育网站<span class="caret"></span></button>
        <ul class="dropdown-menu">
         <li><a href="#">教育部基教教材中心</a></li>
         <li><a href="#">中华人民共和国教育部</a></li>
         <li><a href="#">中国心理导向网</a></li>
         <li><a href="#">国家基础教育资源网</a></li>
         <li><a href="#">中国基础教育网</a></li>
        </ul>
       </div>
      </div>
     </div>
    </div>
   </div>
   <footer class="footer">
    <p>Copyright&copy;2016 深圳科瑞奇科技有限公司</p>
   </footer>