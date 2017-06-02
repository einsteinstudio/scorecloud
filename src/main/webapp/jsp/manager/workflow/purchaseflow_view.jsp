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
				<input type="hidden" name="token" value="${token}"> <input
					type="hidden" name="id" value="">
				<div class="form-group">
					<label class="col-md-2 control-label">所在部门</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.department}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">采购类型</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.type}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">交付日期</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.deliverDate}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">支付方式</label>
					<div class="col-md-10">
						<p class="form-control-static">${flow.payWay}</p>
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
		<div class="row">
		<c:forEach var="detail" items="${flow.details}">
			<div class="purchase-detail-wrap col-md-6">
				<div class="panel panel-default purchase-detail ">
					<div class="panel-heading">
						<h2>
							<i class="fa fa-indent red"></i><strong>采购明细</strong>
						</h2>
						<div class="panel-actions">
							<a class="btn-close-d btn-close-detail"><i
								class="fa fa-times"></i></a>
						</div>
					</div>
					<div class="panel-body ">
						<input type="hidden" name="id" value="">
						<div class="form-group">
							<label class="col-md-3 control-label">名称</label>
							<div class="col-md-9">
								<p class="form-control-static">${detail.name}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">规格</label>
							<div class="col-md-9">
								<p class="form-control-static">${detail.norm}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">采购数量</label>
							<div class="col-md-9">
								<p class="form-control-static">${detail.buyCount}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">单位价格</label>
							<div class="col-md-9">
								<p class="form-control-static">${detail.price}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
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



