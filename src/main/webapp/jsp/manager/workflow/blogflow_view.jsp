<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/manager/";
%>
<div class="main" style="padding-button:100px">
	<form id="addForm" action="" method="post"
		class="form form-horizontal ">
		<div class="row">
		<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-indent red"></i><strong>基本信息</strong>
				</h2>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<label class="col-md-2 control-label">标题</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.title}<span style="width:20px"></span><a target="blank" href="/manager/blog/toModify.htm?id=${flow.id}">查看详情</a></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">类型</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.type}</p>
					</div>
				</div>
				<%-- <div class="form-group">
					<label class="col-md-2 control-label">子类型</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.subType}</p>
					</div>
				</div> --%>
				<div class="form-group">
					<label class="col-md-2 control-label">摘要</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.subject}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">作者</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.author}</p>
					</div>
				</div>
				<%-- <div class="form-group">
					<label class="col-md-2 control-label">媒体类型</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.mediaType.desc}</p>
					</div>
				</div> --%>
				<div class="form-group">
					<label class="col-md-2 control-label">来源</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.source}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">审核状态</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.auditStatus}</p>
					</div>
				</div>
			</div>

		</div>
		</div>
		</div>
		<h3 style="color:red">如果内容过长导致审核框无法弹出，请收起内容，再审核</h3>
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-indent red"></i><strong>内容详情</strong>
					</h2>
					<div class="panel-actions">
						<a href="index.html#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
						<a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
						<a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
					</div>
				</div>
				<div class="panel-body" style="display: none;">
					${flow.content }
				</div>
			</div>
		</div>
	</form>
		
		<jsp:include  page="his_data.jsp"/> 
		<jsp:include  page="btn_group.jsp"/> 
		



</div>
<script type="text/javascript"
	src="//${cdnDomain}/cdn/template/proton/assets/js/jquery-2.1.1.min.js"></script>

<!-- 引用核心层插件 -->
<!-- 引用控制层插件 -->
<script type="text/javascript"
	src="/manager/js/workflow/purchaseflow_toApply.js"></script>
<script type="text/javascript" src="/manager/js/workflow/audit.js"></script>	
<script>
	$(document).ready(function() {
		flowId="${flow.id}";
	});
</script>



