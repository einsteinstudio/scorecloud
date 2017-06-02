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
.basic-grey input[type="text"], .basic-grey input[type="email"], .basic-grey input[type="password"], .basic-grey textarea, .basic-grey select {
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
    		<form action="<%=basePath%>teacher/modify.htm" method="post" class="basic-grey">
				<input type="hidden" name="id" value='${teacher.id}'/>
				
				<label for="title">教师姓名：</label>
				<input type="text" name="name" id="name" value='${teacher.name}' style="width:80%" disabled="disabled"/>
				<br/>
				
				<label for="title">性别：</label>
				<input type="text" name="gender" id="gender" value='${teacher.gender}' style="width:80%" disabled="disabled"/>
				<br/>
				
				<label for="title">家庭地址：</label>
				<input type="text" name="address" id="address" value='${teacher.address}' style="width:80%" disabled="disabled"/>
				<br/>
				
				<label for="title">QQ号码：</label>
				<input type="text" name="qq" id="qq" value='${teacher.qq}' style="width:80%" disabled="disabled"/>
				<br/>
				
				<label for="title">电子邮箱：</label>
				<input type="text" name="email" id="email" value='${teacher.email}' style="width:80%" disabled="disabled"/>
				<br/>
				
				<label for="title">手机号码：</label>
				<input type="text" name="mobile" id="mobile" value='${teacher.mobile}' style="width:80%" disabled="disabled"/>
				<br/>
				
				<label for="title">办公电话：</label>
				<input type="text" name="officetel" id="officetel" value='${teacher.officetel}' style="width:80%" disabled="disabled"/>
				<br/>

				<label for="title">身份证号：</label>
				<input type="text" name="idcardno" id="idcardno" value='${teacher.idcardno}' style="width:80%" disabled="disabled"/>
				<br/>
				
				<label for="title">工号：</label>
				<input type="text" name="jobnum" id="jobnum" value='${teacher.jobnum}' style="width:80%" disabled="disabled"/>
				<br/>
				
				<label for="title">职级：</label>
				<input type="text" name="joblevel" id="joblevel" value='${teacher.joblevel}' style="width:80%" disabled="disabled"/>
				<br/>
				
				<label for="title">学科：</label>
				<input type="text" name="discipline" id="discipline" value='${teacher.discipline}' style="width:80%" disabled="disabled"/>
				<br/>

    		</form>
    	</div>
    	<script type="text/javascript">
	    </script>	
