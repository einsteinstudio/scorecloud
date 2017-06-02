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
					<label class="col-md-2 control-label">部门</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.department}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">物品名称</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.name}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">物品编号</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.productCode}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">损坏原因</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.breakReason}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">物品管理员</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.manager}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">备注</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.note}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">申请日期</label>
					<div class="col-md-10">
						<p class="form-control-static"><fmt:formatDate value="${flow.createDate}"  pattern="yyyy-MM-dd" /></p>
					</div>
				</div>

			</div>

		</div>
		</div>
		</div>
	</form>
		<!-- 图片集合 -->
		<div class="row"><div class="col-md-12">
		<div class="panel panel-default ">
			<div class="panel-heading" data-original-title="">
				<h2>
					<i class="fa fa-picture-o red"></i><strong>相关图片</strong>
				</h2>
				<div class="panel-actions">
						<a href="gallery.html#" class="btn-close"><i class="fa fa-times"></i></a>
				</div>
			</div>
			<div class="panel-body">

				<div class="row">
					<div style="margin-bottom: 10px" class="col-md-4">
						<img class="img-thumbnail" src="http://${cdnDomain}/cdn/template/proton/assets/img/gallery/photo1.jpg"
							alt="Sample Image">
					</div>
					<div style="margin-bottom: 30px" class="col-md-4">
						<img class="img-thumbnail" src="http://${cdnDomain}/cdn/template/proton/assets/img/gallery/photo2.jpg"
							alt="Sample Image">
					</div>
				</div>
			</div>
		</div>
		</div>
		</div>
				<jsp:include  page="his_data.jsp"/> 
				<jsp:include  page="btn_group.jsp"/> 
		
	
</div>
<script type="text/javascript"
	src="//${cdnDomain}/cdn/template/proton/assets/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/manager/js/workflow/audit.js"></script>
<!-- 引用核心层插件 -->
<!-- 引用控制层插件 -->

<script>
$(document).ready(function() {
	 flowId="${flow.id}";
});
</script>



