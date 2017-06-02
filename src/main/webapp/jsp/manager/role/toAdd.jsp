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
	<div class="panel panel-default">
		<div class="panel-heading">
			<h2>
				<i class="fa fa-indent red"></i><strong> 角色详情</strong>
			</h2>
		</div>
		<div class="panel-body">
			<form id="addForm" action="" method="post">
				<input type="hidden" name="id" value="">
				<div class="form-group">
					<label for="nf-email">角色编号</label> <input type="input"
						name="roleNo" class="form-control" placeholder="角色编号"
						value="${pMenu.name}">
				</div>
				<div class="form-group">
					<label for="nf-email">角色名称</label> <input type="input"
						name="roleName" class="form-control" placeholder="角色名称">
				</div>

				<div class="form-group">
					<label for="nf-password">使用状态</label> <label
						class="switch switch-success"> <input name="useStatus"
						type="checkbox" class="switch-input" checked=""> <span
						class="switch-label" data-on="On" data-off="Off"></span> <span
						class="switch-handle"></span>
					</label>

				</div>

				<div class="form-group">
					<label class="control-label">权限配置</label>
					<c:forEach var="map" items="${groupedRights}" varStatus="stat">
						<div class="">
							<div>${map.key}</div>
							<c:forEach var="r" items="${map.value}" varStatus="stat">
								<label class="checkbox-inline" for="inline-checkbox1"> <input
									type="checkbox" id="${r.id}" name="right_${r.id}"
									value="on"> ${r.rightName}
							</c:forEach>
						</div>
					</c:forEach>
				</div>
		</div>
		<div class="panel-footer">
			<button type="submit" class="btn btn-sm btn-info">
				<i class="fa fa-dot-circle-o"></i> 提交
			</button>
			<!-- <button id="reset" type="reset" class="btn btn-sm btn-danger">
				<i class="fa fa-ban"></i> 重置
			</button> -->
			<a target="_self" href="list.htm"><button
					id="reset" type="button" class="btn btn-sm btn-info">返回</button></a>
		</div>
		</form>
	</div>
</div>
<script src="<%=basePath%>assets/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/manager/js/role/toAdd.js"></script>




