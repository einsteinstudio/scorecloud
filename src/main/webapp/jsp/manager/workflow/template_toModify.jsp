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
	<form class="form" id="addForm" action="" method="post">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-indent red"></i><strong> 模板详情</strong>
				</h2>
			</div>
			<div class="panel-body">
				<input type="hidden" name="id" value="${template.id}">
				<div class="form-group">
					<label for="nf-email">模板名</label> <input type="input" name="name"
						class="form-control" placeholder="模板名" value="${template.name}">
				</div>
				<div class="form-group">
					<label for="nf-email">适用类型</label>
					<select name="objectType" class="form-control">
						<option value="内容审核" <c:if test="${template.objectType=='内容审核'}"> selected="selected"</c:if> >内容审核</option>
						<option value="采购流程" <c:if test="${template.objectType=='采购流程'}"> selected="selected"</c:if> >采购流程</option>
						<option value="报修流程" <c:if test="${template.objectType=='报修流程'}"> selected="selected"</c:if> >报修流程</option>
						<option value="用车流程" <c:if test="${template.objectType=='用车流程'}"> selected="selected"</c:if> >用车流程</option>
					</select>
				</div>
				<div class="form-group">
					<label for="nf-email">适用子类型</label> <input type="input"
						name="subType" class="form-control" placeholder="适用子类型"
						value="${template.subType}">
				</div>
			</div>
		</div>

		<c:forEach var="node" items="${nodes}">

			<div class="detail-wrap ">
				<div class="panel panel-default purchase-detail col-md-6">
					<div class="panel-heading">
						<h2>
							<i class="fa fa-indent red"></i><strong>节点详情</strong>
						</h2>
						<div class="panel-actions">
							<a class="btn-close-d btn-close-detail"><i
								class="fa fa-times"></i></a>
						</div>
					</div>
					<div class="panel-body ">
						<div class="form-group">
							<input class="nodeId" type="hidden" name="nodeId[]" value="${node.id}">
							<label class="col-md-3">状态</label>
							<div class="col-md-9">
								<input type="input" name="status[]" class="form-control"
									placeholder="名称" value="${node.status}">
							</div>
						</div>
						<%-- <div class="form-group">
							<label class="col-md-3">指定账号</label>
							<div class="col-md-9">
								<input type="input" name=accountId[] class="choose_account form-control"
									placeholder="指定账号" value="${node.accountId}">
							</div>
						</div> --%>
						<div class="form-group">
							<label class="col-md-3">配置角色</label>
							<div class="col-md-9">
								<select name="role[]" class="form-control" size="1">
		                            <c:forEach var="r" items="${roles}" >
		                            	<option value="${r.id}" <c:if test="${r.id==node.role}"> selected="selected"</c:if>>${r.roleName}</option>
		                            </c:forEach>
		                        </select>
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
			<button type="button" id="add-detail" class="btn btn-sm btn-info">
				增加节点
			</button>
			<!-- <button id="reset" type="reset" class="btn btn-sm btn-danger">
				<i class="fa fa-ban"></i> 重置
			</button> -->
			<a target="_self" href="list.htm"><button id="reset"
					type="button" class="btn btn-sm btn-info">返回</button></a>
		</div>
	</form>

<div class="detail-wrap-temp"> 
	<div class="detail-wrap" style="display:none">
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
				<div class="form-group">
					<input class="nodeId" type="hidden" name="nodeId[]"
					> <label class="col-md-3">状态</label>
					<div class="col-md-9">
						<input type="input" name="status[]" class="form-control"
							placeholder="名称" value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3">指定账号</label>
					<div class="col-md-9">
						<input type="input" name=accountId[]
							class="choose_account form-control" placeholder="指定账号"
							value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3">配置角色</label>
					<div class="col-md-9">
						<select name="role[]" class="form-control" size="1">
							<c:forEach var="r" items="${roles}">
								<option value="${r.id}">${r.roleName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</div>
<script src="<%=basePath%>assets/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="/manager/js/workflow/template_toModify.js"></script>




