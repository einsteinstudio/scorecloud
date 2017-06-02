<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>	
<head>
<title>后台管控中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="/manager/assets/css/style.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
		<script src="/manager/assets/js/jquery-2.1.1.min.js"></script>	

<!--//webfonts-->
<script src="/plugins/jquery/jquery.validate.js"></script>
<script src="/plugins/jquery/jquery.form.js"></script>
<script src="/manager/js/login.js"></script>
<script type="text/javascript" src="//${cdnDomain}/cdn/library/crypto-js/components/core.js"></script>
<script type="text/javascript" src="//${cdnDomain}/cdn/library/crypto-js/components/md5-min.js"></script>
</head>
<body>
<script>$(document).ready(function(c) {
	$('.close').on('click', function(c){
		$('.login-form').fadeOut('slow', function(c){
	  		$('.login-form').remove();
		});
	});	  
});
</script>
 <!--SIGN UP-->
 <h1>智慧校园-后台管控中心</h1>
<div class="login-form">
	<div class="close"> </div>
		<div class="head-info">
			<label class="lbl-1"> </label>
			<label class="lbl-2"> </label>
			<label class="lbl-3"> </label>
		</div>
			<div class="clear"> </div>
	<div class="avtar">
		<img src="/manager/assets/images/avtar.png" />
	</div>
			<form id="login-form" action="/manager/doLogin.json">
					<input autocomplete="off" name="j_username" type="text" class="text" value="用户名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '用户名';}" >
						<div class="key">
					<input autocomplete="off" style="margin-bottom:20px" name="j_password" type="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}">
					
					<div style="margin-top:-60px">
					<input name="j_code" type="text" style="width:60px;height:28px" /><a href="javascript:refreshimg()" title="看不清楚，换个图片">
					<img id="checkcode" src="/code" style="border: 1px solid #ffffff"></a>
					</div>
					<div class="error-hold" style="color:red;height:40px"></div>
					</div>
						<div class="signin">
							<input type="submit" value="登录" >
						</div>
			</form>

</div>
<div class="copy-rights">
					<p>Copyright &copy; 2015.Company name All rights reserved. <a href="http://www.goldskyer.com/" target="_blank" title="智慧校园">智慧校园</a> - Collect from <a href="http://www.goldskyer.com/" title="网页模板" target="_blank">智慧校园</a></p>
</div>

</body>
</html>