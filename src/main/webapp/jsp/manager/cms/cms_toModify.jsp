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
				<i class="fa fa-indent red"></i><strong> CMS详情</strong>
			</h2>
		</div>
		<div class="panel-body">
			<form id="addForm" action="" method="post">
				<input type="hidden" name="gId" value="${cs.gId}">
				<div class="form-group">
					<label for="nf-email">CMS名</label> <input type="input"
						name="id" class="form-control" placeholder="CMS名"
						value="${cs.id}">
				</div>
				<div class="form-group">
					<label for="nf-email">描述</label> <input type="input"
						name="description" class="form-control" placeholder="描述" value="${cs.description}">
				</div>
				<div class="form-group">
					<label for="nf-email">语言</label> <%-- <input type="input"
						name="n18" class="form-control" placeholder="语言" value="${cs.n18 }"> --%>
						<select name="n18" class="form-control">
							<option value="cn" <c:if test="${cs.n18=='cn'}"> selected="selected"</c:if> >中文</option>
							<option value="en" <c:if test="${cs.n18=='en'}"> selected="selected"</c:if>>英文</option>
						</select>
				</div>
				<div class="form-group">
					<label for="nf-email">配置内容</label> <input type="input"
						name="content" class="form-control" placeholder="配置内容" value="${cs.content}">
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
<script type="text/javascript" src="/manager/js/cms/cms_toModify.js"></script>




