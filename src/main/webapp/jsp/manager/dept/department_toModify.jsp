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
				<i class="fa fa-indent red"></i><strong> 部门详情</strong>
			</h2>
		</div>
		<div class="panel-body">
			<form id="addForm" action="" method="post">
				<input name="id" value="${dept.id}" type="hidden">
				<div class="form-group">
					<label for="nf-email">上级部门</label> 
					<select id="select" name="parentId" class="form-control" size="1">
						<option value="root" <c:if test="${dept.parentId=='root'}">selected="selected"</c:if> >无</option>
				    	 <c:forEach var="vo" items="${selectVos}">  
				    	 	<option value="${vo.value}" <c:if test="${vo.value==dept.parentId}">selected="selected"</c:if> >${vo.name}</option>
				    	 </c:forEach>                   
				     </select>
				</div>
				<div class="form-group">
					<label for="nf-email">部门名称</label> <input type="input" name="name"
						class="form-control" placeholder="部门名称" value="${dept.name}">
				</div>
				<div class="form-group">
					<label for="nf-email">排序号</label> <input type="input" name="weight"
						class="form-control" placeholder="排序号" value="${dept.weight}">
				</div>
				<div class="form-group">
					<label for="nf-email">部门电话</label> <input type="input" name="phone"
						class="form-control" placeholder="部门电话" value="${dept.phone}">
				</div>
				<div class="form-group">
					<label for="nf-email">部门职责</label> <input type="input" name="duty"
						class="form-control" placeholder="部门职责" value="${dept.duty}">
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
<script>

$(function() {
	/**
	 * Menu表单校验
	 */
	$('#addForm').validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : "部门名称不能为空"
			}
		},
		showErrors : function(errorMap, errorList) {
			var element = this.currentElements;
			if (element.next().hasClass('popover')) {
				element.popover('destroy');
			}
			this.defaultShowErrors();
		},
		submitHandler : function(form) { // 表单提交句柄,为一回调函数，带一个参数：form
			$(form).ajaxSubmit({
				type : "post",
				url : "modify.json",
				success : function(data) {
					if (data.result == "success") {
						window.location.href = "list.htm?pId=${dept.parentId}";
					} else {
						alert(data.msg);
					}
				}
			});
		},
	});
});

</script>



