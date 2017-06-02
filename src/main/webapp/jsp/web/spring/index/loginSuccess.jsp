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
<html><head><meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>会员登录</title>

<style type="text/css">
<!--*{padding:0px;border:0px;margin:0px}
input{background:#ffffff;border:#dfdfdf 1px solid;color:#464646;height:18px;border-color:#c8c8c8 #c8c8c8 #c8c8c8 #c8c8c8;font-size:12px;}
.input1{border:#dfdfdf 0px solid;width:47px;height:19px;}
td{font-family:宋体;font-size:12px;line-height:130%;color:#464646;}
a:link{color:#464646;}
a:visited{color:#464646;}
a:hover{color:#ff0000;}
a:active{color:#464646;}
-->
</style>
<!--增加使userlogin.asp不能脱离框架结构的判断 >
<script language="javascript">
if(self==top){self.location.href="index.asp";}
</script>
<完毕-->
<!--完毕-->
	<script language="JavaScript" type="text/JavaScript"
	src="//${cdnDomain}/cdn/spring/index_files/jquery.min.js"></script>
<script src="/plugins/jquery/jquery.validate.js"></script>
<script src="/plugins/jquery/jquery.form.js"></script>
<script type="text/javascript" src="//${cdnDomain}/cdn/library/crypto-js/components/core.js"></script>
<script type="text/javascript" src="//${cdnDomain}/cdn/library/crypto-js/components/md5-min.js"></script>
</head>
<body bgcolor="#fff8de" leftmargin="0" topmargin="0">
<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tbody>
    	<tr><td style="text-align:center">欢迎您登陆:${account.nickname}</td><tr>
    	<tr><td style="text-align:center">你有<font color="#FF0000">${unReadCnt}</font>条未读消息,<a href="/manager/message/list.htm" target="_blank"><font color="#FF0000">点击阅读</font></a></td><tr>
    	<tr><td style="text-align:center;padding-top:20px"><a id="logout" href="/manager/index.htm" target="_blank">点此登陆后台</a></td><tr>
    	<tr><td style="text-align:center;padding-top:20px"><a id="logout" href="/web/spring/index.htm?action=logout" target="self">退出</a></td><tr>
    </tbody></table>
<script language="javascript">

   
   $(document).ready(function(){
	  /*  $("#logout").bind("click",function(){
	   		 $.ajax({
		   	url: '/logout.htm',
		   	type: 'POST',
		   	dataType: 'json',
		   	data: {param1: 'value1'},
		   	success:function(){
		   		window.parent.location.href="/";
		   	}
		   }); 

	   })*/
	 
	   
		
	});
</script>


</body></html>