<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/manager/";
%>
<div class="s-dialog">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h2>
				<i class="fa fa-indent red"></i><strong><c:if test="${eventType!='COMMENT'}">审批意见</c:if><c:if test="${eventType=='COMMENT'}">发表评论</c:if></strong>
			</h2>
		</div>
		<div class="panel-body">
			<form id="addForm" action="" method="post">				
				<input type="hidden" name="taskId" value="${task.id}">
				<input type="hidden" name="eventType" value="${eventType}">
				<div class="form-group">
					<c:if test="${eventType!='COMMENT'}">
					<label for="nf-email">理由</label> <textarea type="input" 
						name="note" class="form-control" placeholder="理由"
						value=""></textarea></c:if>
					<c:if test="${eventType=='COMMENT'}">
					<label for="nf-email">评论内容</label> <textarea type="input" 
						name="note" class="form-control" placeholder="评论"
						value=""></textarea></c:if>
				</div>
				<div class="form-group">
					<label">图片或附件</label>
					<div id="uploadImage" class="demo uploadFrame"></div>	
					<input type="hidden" name="picUrl" value="">	
				</div>
		</div>
		 <div class="panel-footer">
			<button type="submit" class="btn btn-sm btn-info">
				<i class="fa fa-dot-circle-o"></i> 提交
			</button>
			<button
					id="closeWin" type="button" class="btn btn-sm btn-info">返回</button>
		</div> 
		</form>
	</div>
</div>
<script src="<%=basePath%>assets/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/manager/js/workflow/audit.js"></script>




