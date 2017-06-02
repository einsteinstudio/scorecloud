<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="main">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">组织架构</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2>
								<i class="fa fa-indent red"></i><strong>部门详情</strong>
							</h2>
						</div>
						<div class="panel-body">
							<form id="addForm" action="" method="post">
								<input type="hidden" name="id" value="">
								<input id="parentId" type="hidden" name="parentId" value="root">
								<div class="form-group">
									<label for="nf-email">部门名称</label> <input type="input"
										name="name" class="form-control" placeholder="部门名称" value="">
								</div>
								<div class="form-group">
									<label for="nf-email">排序号</label> <input type="input"
										name="weight" class="form-control" placeholder="排序号">
								</div>
								<div class="form-group">
									<label for="nf-email">部门电话</label> <input type="input" name="phone"
										class="form-control" placeholder="部门电话">
								</div>
								<div class="form-group">
									<label for="nf-email">部门职责</label> <input type="input"
										name="duty" class="form-control" placeholder="部门职责">
								</div>
						</div>
						<div class="panel-footer">
							<button type="submit" class="btn btn-sm btn-info">
								<i class="fa fa-dot-circle-o"></i> 保存
							</button>
							<button id="addBtn" type="button" class="btn btn-sm btn-info">
								<i class="fa fa-dot-circle-o"></i> 新建下级部门
							</button>
							<button type="button" class="btn btn-sm btn-info">
								<i class="fa fa-dot-circle-o"></i> 删除
							</button>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<link rel="stylesheet"
	href="/manager/ztree/css/zTreeStyle/zTreeStyle.mine.css"
	type="text/css">
<script src="/manager/assets/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/manager/js/dept/department.js"></script>
<script type="text/javascript">
	zData = ${treeNode};
</script>