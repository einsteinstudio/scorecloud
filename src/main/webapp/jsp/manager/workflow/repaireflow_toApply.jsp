<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/manager/";
%>
<div class="main">
	<form id="addForm" action="" method="post" class="form">

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
					<label>部门</label> <input type="input" name="department"
						class="form-control" placeholder="部门" value="">
				</div>
				<div class="form-group">
					<label>物品名称</label> <input type="input" name="name"
						class="form-control" placeholder="物品名称" value="">
				</div>
				<div class="form-group">
					<label>物品编码</label> <input type="input" name="productCode"
						class="form-control" placeholder="物品编码" value="">
				</div>
				<div class="form-group">
					<label>损坏原因</label>
					<textarea name="breakReason" class="form-control"
						placeholder="损坏原因" value=""></textarea>
				</div>
				<div class="form-group">
					<label>物品管理员</label> <input type="input" name="manager"
						class="form-control" placeholder="物品管理员" value="">
				</div>

				<div class="form-group">
					<label>备注</label>
					<textarea name="note" class="form-control" placeholder="备注"
						value=""></textarea>
				</div>
				<div class="form-group">
					<label">相关图片</label>
					<div id="uploadImage" class="demo uploadFrame"></div>
					<input type="hidden" name="picUrl" value="">
				</div>
			</div>

		</div>


		<div class="panel-footer">
			<button type="submit" class="btn btn-sm btn-info">
				<i class="fa fa-dot-circle-o"></i> 提交
			</button>
			<a target="_self" href="list.htm"><button id="reset"
					type="button" class="btn btn-sm btn-info">返回</button></a>
		</div>
	</form>

</div>
<script type="text/javascript"
	src="//${cdnDomain}/cdn/template/proton/assets/js/jquery-2.1.1.min.js"></script>

<!-- 引用核心层插件 -->
<!-- 引用控制层插件 -->
<script type="text/javascript"
	src="/manager/js/workflow/repaireflow_toApply.js"></script>
<script>
	$(document).ready(function() {
		$("#uploadImage").zyUpload({
			width : "90%", // 宽度
			height : "400px", // 宽度
			itemWidth : "120px", // 文件项的宽度
			itemHeight : "100px", // 文件项的高度
			url : "/manager/upload/addImage.htm", // 上传文件的路径
			multiple : true, // 是否可以多个文件上传
			dragDrop : true, // 是否可以拖动上传文件
			del : true, // 是否可以删除文件
			finishDel : false, // 是否在上传文件完成后删除预览
			/* 外部获得的回调接口 */
			onSelect : function(files, allFiles) { // 选择文件的回调方法
				console.info("当前选择了以下文件：");
				console.info(files);
				console.info("之前没上传的文件：");
				console.info(allFiles);
			},
			onDelete : function(file, surplusFiles) { // 删除一个文件的回调方法
				console.info("当前删除了此文件：");
				console.info(file);
				console.info("当前剩余的文件：");
				console.info(surplusFiles);
			},
			onSuccess : function(file, response) { // 文件上传成功的回调方法
				console.info("此文件上传成功：");
				console.info(file);
			},
			onFailure : function(file) { // 文件上传失败的回调方法
				console.info("此文件上传失败：");
				console.info(file);
			},
			onComplete : function(responseInfo) { // 上传完成的回调方法
				console.info("文件上传完成");
				console.info(responseInfo);
			}
		});
		laydate({
			elem : '#deliverDate',
			format : 'YYYY-MM-DD hh:mm',
			defSkin : "danlan",
			istime: true,

		});
	});
</script>



