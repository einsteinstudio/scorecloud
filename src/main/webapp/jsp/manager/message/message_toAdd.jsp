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
				<i class="fa fa-indent red"></i><strong>发送消息</strong>
			</h2>
		</div>
		<div class="panel-body">
			<form id="addForm" action="" method="post">
				<input type="hidden" name="gId" value="">
				<div class="form-group">
					<label for="nf-email">标题</label> <input type="input"
						name="title" class="form-control" placeholder="标题"
						value="">
				</div>
				<div class="form-group">
					<label for="nf-email">消息内容</label> <input type="input"
						name="text" class="form-control" placeholder="消息内容">
				</div>
				<div class="form-group">
					<label for="nf-email">接收者</label> <input class="choose_account form-control choosed-names" id="receiver" type="input"
						name="receiverNames" class="form-control" placeholder="接收者">
					<input type="hidden" class="choosed-ids" name="receivers" value="">
				</div>
		</div>
		<div class="panel-footer">
			<button type="submit" class="btn btn-sm btn-info">
				<i class="fa fa-dot-circle-o"></i> 发送
			</button>
			<a target="_self" href="list.htm"><button
					id="reset" type="button" class="btn btn-sm btn-info">返回</button></a>
		</div>
		</form>
	</div>
</div>
<script src="<%=basePath%>assets/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/manager/js/message/message_toAdd.js"></script>




