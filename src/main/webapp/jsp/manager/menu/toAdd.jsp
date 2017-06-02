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
				<i class="fa fa-indent red"></i><strong>栏目详情</strong>
			</h2>
		</div>
		<div class="panel-body">
			<form id="menuForm" action="" method="post">
				<input type="hidden" name="id" value="">
				<div class="form-group">
					<label for="nf-email">上级栏目</label> <input type="hidden"
						name="parentId" value="${pMenu.id}"> <input type="input"
						name="parentName" class="form-control" readonly="true"
						value="${pMenu.name}">
				</div>
				<div class="form-group">
					<label for="nf-email">栏目名称</label> <input type="input" name="name"
						class="form-control" placeholder="栏目名称">
				</div>
				<div class="form-group">
					<label for="nf-password">栏目类型</label>
					<div>
						<label class="radio-inline" for="inline-radio1"> <input
							type="radio" id="inline-radio1" name="type" checked="checked"
							value="MULTI"> 多篇
						</label> <label class="radio-inline" for="inline-radio1"> <input
							type="radio" id="inline-radio1" name="type" value="SINGLE">
							单篇
						</label> <label class="radio-inline" for="inline-radio1"> <input
							type="radio" id="inline-radio1" name="type" value="LINK">
							链接
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="nf-password">链接</label> <input type="input" name="link"
						class="form-control" placeholder="链接">
				</div>
				<div class="form-group">
					<label for="nf-password">资源类型</label>
					<div>
						<label class="radio-inline" for="inline-radio2"> <input
							type="radio" id="mediaType" name="mediaType" checked="checked"
							value="RICHTEXT"> 富文本
						</label> <label class="radio-inline" for="inline-radio2"> <input
							type="radio" id="mediaType" name="mediaType" value="VIDEO">
							视频
						</label> <label class="radio-inline" for="inline-radio2"> <input
							type="radio" id="mediaType" name="mediaType" value="LIVE">
							直播
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="nf-password">权重</label> <input type="input"
						name="weight" class="form-control" placeholder="权重" value="10">
				</div>
				<div class="form-group">
					<label for="nf-password">使用状态</label> <label
						class="switch switch-success"> <input name="show"
						type="checkbox" class="switch-input" checked=""> <span
						class="switch-label" data-on="On" data-off="Off"></span> <span
						class="switch-handle"></span>
					</label>
				</div>
				<div class="form-group">
					<label for="nf-password">登陆可见</label> <label
						class="switch switch-success"> <input name="needLogin"
						type="checkbox" class="switch-input" > <span
						class="switch-label" data-on="On" data-off="Off"></span> <span
						class="switch-handle"></span>
					</label>
				</div>
				<!--   加了checked=""就会显示选中 -->
				<div class="form-group">
					<label for="nf-password">允许评论</label> <label
						class="switch switch-success"> <input name="canComment"
						type="checkbox" class="switch-input" checked=""> <span   
						class="switch-label" data-on="On" data-off="Off"></span> <span
						class="switch-handle"></span>
					</label>
				</div>
		</div>
		<div class="panel-footer">
			<button type="submit" class="btn btn-sm btn-info">
				<i class="fa fa-dot-circle-o"></i> 提交
			</button>
			<button id="reset" type="reset" class="btn btn-sm btn-danger">
				<i class="fa fa-ban"></i> 重置
			</button>
			<a target="_self" href="/manager/menu/list.htm?pId=${pMenu.id}"><button
					id="reset" type="button" class="btn btn-sm btn-info">返回</button></a>
		</div>
		</form>
	</div>

</div>
<script src="<%=basePath%>assets/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/manager/js/menu/toAdd.js"></script>

