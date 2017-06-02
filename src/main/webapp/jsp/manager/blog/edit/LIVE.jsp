<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/manager/";
%>

<style type="text/css">
/* Basic Grey */
.basic-grey {
margin-left:auto;
margin-right:auto;
max-width: 100%;
background: #F7F7F7;
padding: 25px 15px 25px 10px;
font: 12px Georgia, "Times New Roman", Times, serif;
color: #888;
text-shadow: 1px 1px 1px #FFF;
border:1px solid #E4E4E4;
}
.basic-grey h1 {
font-size: 25px;
padding: 0px 0px 10px 40px;
display: block;
border-bottom:1px solid #E4E4E4;
margin: -10px -15px 30px -10px;;
color: #888;
}
.basic-grey h1>span {
display: block;
font-size: 11px;
}
.basic-grey label {
display: block;
margin: 0px;
}
.basic-grey label>span {
float: left;
width: 20%;
text-align: right;
padding-right: 10px;
margin-top: 10px;
color: #888;
}
.basic-grey input[type="text"], .basic-grey input[type="email"], .basic-grey textarea, .basic-grey select {
border: 1px solid #DADADA;
color: #888;
height: 30px;
margin-bottom: 16px;
margin-right: 6px;
margin-top: 2px;
outline: 0 none;
padding: 3px 3px 3px 5px;
width: 70%;
font-size: 12px;
line-height:15px;
box-shadow: inset 0px 1px 4px #ECECEC;
-moz-box-shadow: inset 0px 1px 4px #ECECEC;
-webkit-box-shadow: inset 0px 1px 4px #ECECEC;
}
.basic-grey textarea{
padding: 5px 3px 3px 5px;
}
.basic-grey select {
background: #FFF url('down-arrow.png') no-repeat right;
background: #FFF url('down-arrow.png') no-repeat right);
appearance:none;
-webkit-appearance:none;
-moz-appearance: none;
text-indent: 0.01px;
text-overflow: '';
width: 70%;
height: 35px;
line-height: 25px;
}
.basic-grey textarea{
height:100px;
}
.basic-grey .button {
background: #E27575;
border: none;
padding: 10px 25px 10px 25px;
color: #FFF;
box-shadow: 1px 1px 5px #B6B6B6;
border-radius: 3px;
text-shadow: 1px 1px 1px #9E3F3F;
cursor: pointer;
}
.basic-grey .button:hover {
background: #CF7A7A
}
</style>
    	<div class="main">
    		<form action="<%=basePath%>blog/modify.htm" method="post" class="basic-grey">
    			<input type="hidden" name="id" value='${blog.id}'/>
    			<input type="hidden" name="type" value='${blog.type}'/>
    			
				<label for="title">标题：</label>
				<input type="text" name="title" id="title" value='${blog.title}' style="width:80%"/>
				<br/>
				<br/>
				<label for="subject">摘要信息：</label>
				<textarea rows="3" style="width:80%" name="subject">${blog.subject}</textarea>
				<br/>
				<br/>
				
				<label for="coverImage">缩略图：</label>
				<!-- 加载编辑器的容器 -->
				<script id="coverImage" name="coverImage" type="text/plain">
					<img src="${blog.coverImage}"/>
				</script> 
				<br/>
				<br/>
				
	    		<label for="content">直播IP：</label>
				<input type="text" name="liveIp" id="content" value="${liveVideo.ip}" style="width:80%"/>
				<br/>

				<label for="author">作者：</label>
				<input type="text" style="width:80%" name="author" value="${blog.author}"/>
				<br/>
				
				<label for="author">权重：</label>
				<input type="text" style="width:80%" name="weight" value="${blog.weight}" />
				<br/>		
				<input type="radio" name="open" value="true"  <c:if test="${isOpen}">checked="checked"</c:if>/> 开启转换
				<input type="radio" name="open" value="false" <c:if test="${!isOpen}">checked="checked"</c:if> /> 关闭转换
				<br>	
				<br>	
    			<input type="submit" class="button" value="提交"/>	
    		</form>
    	</div>