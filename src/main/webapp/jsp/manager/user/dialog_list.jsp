<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="s-dialog">
	<div class="panel panel-default">
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-default">
				  	<div class="panel-heading">选择部门</div>
				  	<div class="panel-body">
						<div class="zTreeDemoBackground left">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
				  	</div>
				</div>
			</div>
			<div class="col-md-8">
				<div class="panel panel-default">
				  	<div class="panel-heading">选择人员</div>
				  	<div class="panel-body">
						<table id="paper-table"
							class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th width="20px"></th>
									<th>账号</th>
									<th>姓名</th>
								</tr>
							</thead>
						</table>
				  	</div>
				</div>
				
			</div>
		</div>
		<div class="row">
			<div class='col-md-12'>
				<div class="panel panel-default">
				  	<div class="panel-body accountIds">
				  	</div>
				</div>
			</div>
		</div>
		<div class="panel-footer">
				<button id="mysave" type="button" class="btn btn-sm btn-info">
					<i class="fa fa-dot-circle-o"></i> 提交
				</button>
		</div>
			

	</div>
	
</div>
<script src="//${cdnDomain}/cdn/template/proton/assets/js/jquery-2.1.1.min.js"></script>
<script src="//${cdnDomain}/cdn/template/proton/assets/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="//${cdnDomain}/cdn/template/proton/assets/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/manager/js/user/user_dialog_list.js"></script>
<script type="text/javascript">
	zData = ${treeNode};
	 
</script>