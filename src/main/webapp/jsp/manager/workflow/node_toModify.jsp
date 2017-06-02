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
		<c:forEach var="node" items="${nodes}">
		<div class="detail-wrap ">
			<div class="panel panel-default purchase-detail col-md-6">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-indent red"></i><strong>节点详情</strong>
					</h2>
					<div class="panel-actions">
						<a class="btn-close-d btn-close-detail"><i class="fa fa-times"></i></a>
					</div>
				</div>
				<div class="panel-body ">
					<input type="hidden" name="id" value="">


					<div class="form-group">
						<label class="col-md-3">状态</label>
						<div class="col-md-9">
							<input type="input" name="status[]" class="form-control"
								placeholder="名称" value="${node.status}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3">指定账号</label>
						<div class="col-md-9">
							<input type="input" name=accountId[] class="form-control"
								placeholder="指定账号" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3">权限</label>
						<div class="col-md-9">
							<input type="input" name="role[]" class="form-control"
								placeholder="权限" value="">
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>

		<div class="panel-footer">
			<button type="submit" class="btn btn-sm btn-info">
				<i class="fa fa-dot-circle-o"></i> 提交
			</button>
			<button id="add-detail" type="button" class="btn btn-sm btn-info">
				<i class="fa fa-dot-circle-o"></i> 添加节点
			</button>
			<a target="_self" href="list.htm"><button id="reset"
					type="button" class="btn btn-sm btn-info">返回</button></a>
		</div>
	</form>

</div>
<script type="text/javascript"
	src="//${cdnDomain}/cdn/template/proton/assets/js/jquery-2.1.1.min.js"></script>


<script>
	$(document).ready(
			function() {

				$("#add-detail")
						.bind(
								"click",
								function() {
									$(".panel-footer").before(
											'<div class="detail-wrap ">'
													+ $(".form").find(
															".detail-wrap")
															.first().html()
													+ "</div>");
								});
				//点击隐藏标签
				$(".btn-close-detail").live(
						"click",
						function() {
							if ($(this).parents('#addForm').find(
									'.purchase-detail').length <= 1) {
								showInfo("warning", "请至少保留一个节点")
							} else {
								$(this).parents('.detail-wrap').remove();
							}
						});
			});
</script>