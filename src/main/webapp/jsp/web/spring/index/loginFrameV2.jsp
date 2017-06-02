<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%
	String fromUrl = request.getParameter("from");
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
a{text-decoration:none;}
a:link{color:#464646;}
a:visited{color:#464646;}
a:hover{color:#ff0000;}
a:active{color:#464646;}
-->
</style>


<!--完毕-->
<script type="text/javascript" src="//${cdnDomain}/cdn/spring/index_files/jquery.min.js"></script>
<script src="/plugins/jquery/jquery.validate.js"></script>
<script src="/plugins/jquery/jquery.form.js"></script>
<script type="text/javascript" src="//${cdnDomain}/cdn/library/crypto-js/components/core.js"></script>
<script type="text/javascript" src="//${cdnDomain}/cdn/library/crypto-js/components/md5-min.js"></script>
<script type="text/javascript" src="/plugins/jquery/jquery.cookie.js"></script>
</head>
<body bgcolor="#fff8de" leftmargin="0" topmargin="0">
<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
    <div id="my-form" action="/manager/like.json?from=_t" method="post" name="good"  target="_top">
    <tbody><tr>
        <td height="25" align="right">${lang.username[n18]}：</td><td height="25"><input name="dog" type="text" id="dog" size="16" maxlength="20" style="width:110px;"></td>
    </tr>
    <tr>
        <td height="25" align="right">${lang.password[n18]}：</td><td height="25">
        	<input type="text" id="cat1"  size="16" maxlength="20" style="width:110px;" value="" onfocus="javascript:clearPass();"  onkeyup="this.value=this.value.replace(/./g,'*');" onkeypress="javascript:hiddenPass(event)"/> 
          	<input id="cat" type="hidden" name="cat"  class="inputs_dl" value="" /> 
        </td>
    </tr>
    <tr>
        <td height="25" align="right">${lang.authCode[n18]}：</td><td height="25"><input name="j_code" type="text" id="CheckCode" size="6" maxlength="6"></td>
    </tr>
    <tr>
        <td height="25" align="right"></td><td height="25"><a href="javascript:refreshimg()" title="${lang.nextAuthCode[n18]}"><img id="checkcode" src="/code" style="border: 1px solid #ffffff"></a></td>
    </tr>
    <tr>
        <td height="25" colspan="2" align="center">
            <input name="like" type="submit" id="like" value=" ${lang.login[n18]} ">
        <br><br>
     </div>
<a href="javascript:alert('${lang.notOpen[n18]}');" target="_blank">${lang.regist[n18]}</a>&nbsp;&nbsp;<a href="javascript:alert('${lang.notOpen[n18]}');" target="_blank">${lang.forget[n18]}?</a></td>
</tr></tbody></table>
<script type="text/javascript">
function refreshimg(){
	document.all.checkcode.src='/code?'+Math.random();
}
//加密框
function hiddenPass(event){ 
	//http://stackoverflow.com/questions/17059385/referenceerror-event-is-not-defined-in-mozila-firefox
    event = event || window.event //For IE

     var pass = document.getElementById("cat1"); 
 var j_pass = document.getElementById("cat"); 
   if(event.keyCode==13){ 
   check(); 
   } 
    var keycode=event.keyCode;                 
     var keychar=String.fromCharCode(keycode); 
   j_pass.value=j_pass.value+keychar; 
   j_pass.value=j_pass.value.substring(0,pass.length);	
} 
 
function clearPass(){ 
    $("#cat1").val(""); 
    $("#cat").val(""); 
} 
   
   $(document).ready(function(){
	   $('#like').click(function(){
			 var catcat= CryptoJS.MD5(CryptoJS.MD5($('input[name="cat"]').val())+"")+"";
		   $.ajax({
			   url:"/manager/like.json",
			   data:{
				   "dog":$('input[name="dog"]').val(),
				   "cat":catcat,
				   "j_code":$('input[name="j_code"]').val()
			   },
			   success:function(str)
			   {
				   var data = eval('(' + str + ')');
				   if(data.result=='success')
				   {
					   window.parent.location.href=data.data.successUrl;
	    			}else{
	    				$('.error-hold').html(data.msg);
	    				refreshimg();
	    				alert(data.msg);
	    				
	    			} 
			   }
		   });	
	   });
	});
</script>


</body></html>