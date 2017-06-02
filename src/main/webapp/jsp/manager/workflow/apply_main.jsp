<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/manager/";
    String type = request.getParameter("type");
%>
<div class="main">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">
				<i class="fa fa-life-bouy"></i>流程申请
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="/manager/index.htm">主页</a></li>
				<li><i class="fa fa-life-bouy"></i>流程申请</li>
			</ol>
		</div>
	</div>

	<div class="row">

		<div class="col-md-6">
			<a href="/manager/workflow/purchaseflow/toApply.htm">
			<div class="smallstat red-bg">
				<i class="fa fa-shopping-cart white-bg"></i> <span class="value black">采购流程</span> <span class="title">校园需要采购教学设备
				文具、实验器材、请点此申请</span>
			</div>
			</a>
			<!--/.smallstat-->
		</div>
		<!--/.col-->

		<div class="col-md-6">
			<a href="/manager/workflow/usecarflow/toApply.htm">
			<div class="smallstat magenta-bg">
				<i class="fa fa-car white-bg"></i> <span class="value black">车辆管理</span> <span class="title">申请出差、接送访客、集体学习等请点此申请</span>
			</div>
			</a>
			<!--/.smallstat-->
		</div>
		<!--/.col-->

		<div class="col-md-6">
			<a href="/manager/workflow/repaireflow/toApply.htm">
			<div class="smallstat blue-bg">
				<i class="fa fa-laptop white-bg"></i> <span class="value black">故障报修</span> <span class="title">教学设备器材损坏、办公桌椅、教师电气设备损坏等点此申请</span>
			</div>
			</a>
			<!--/.smallstat-->
		</div>
		<!--/.col-->

		<!-- <div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
			<div class="smallstat green-bg">
				<i class="fa fa-moon-o white-bg"></i> <span class="value black">$1
					859,00</span> <span class="title">Profit</span>
			</div>
			/.smallstat
		</div> -->
		<!--/.col-->

	</div>
</div>
<script src="<%=basePath %>assets/js/jquery-2.1.1.min.js"></script>
<script>
	$(document).ready(function() {
		
	});
</script>