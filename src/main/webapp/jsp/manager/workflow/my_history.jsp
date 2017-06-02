<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/manager/";
    String type = request.getParameter("type");
%>
<div class="main">
	<div class="row">		
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2><i class="fa fa-table red"></i><span class="break"></span><strong>我的审核</strong></h2>
							<div class="panel-actions">
								<c:if test="${not empty rightMap.WORKFLOW_ADD}"><a class="btn-add" href="/manager/workflow/purchaseflow_toApply.htm"><i class="fa fa-plus"></i></a></c:if>
								<a class="btn-setting" href="table.html#"><i class="fa fa-rotate-right"></i></a>
								<a class="btn-minimize" href="table.html#"><i class="fa fa-chevron-up"></i></a>
								<a class="btn-close" href="table.html#"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="panel-body">
							<table id="paper-table" class="table table-striped table-bordered bootstrap-datatable datatable">
							  <thead>
								  <tr>
								  	  <th>任务名</th>
									  <th>申请人</th>
                                      <th>流程类型</th>
									  <td>我的操作</td>
									  <th>流程状态</th>
									  <th>操作时间</th>
									   <th>操作</th>
								  </tr>
							  </thead>   							  
						  </table>  
					</div> 
				</div><!--/col-->
			</div>
		</div>
</div>
<script src="<%=basePath %>assets/js/jquery-2.1.1.min.js"></script>
<script>
	showView="${not empty rightMap.WORKFLOW_VIEW}";

	showDelete="${not empty rightMap.WORKFLOW_DELETE}";
	showEdit="${not empty rightMap.WORKFLOW_EDIT}";
	$(document).ready(function() {
	   var table= $('#paper-table').dataTable( {
	        "processing": false,
	        "serverSide": true,
	        rowId:"[0]",
	        "ajax": "list_data.json",
	        "language": {
                "url": "http://cdn.datatables.net/plug-ins/1.10.11/i18n/Chinese.json"
            },
            "columnDefs": [
	            {
	                "render": function ( data, type, row ) {
	                	
	                	var html='';
	                    var viewHtml=  '<a href="/manager/workflow/mytask/view.htm?id='+data+'" class="small-btn btn-success">'+
											'<i class="fa fa-search-plus ">查看</i></a>';
						if(showView=='true')
							html+=viewHtml;
						return html;
	                },
	                "targets": 6
	            },
	            {
	                "render": function ( data, type, row ) {
	                	
						return '<span class="label label-success">'+data+'</span>';
	                },
	                "targets": 4
	            },
	            {
	                "render": function ( data, type, row ) {
	                	if(data=='REVOKE'){
	                		return '回收流程';
	                	}else if (data=='COMMENT'){
	                		return '发表评论';
	                	}else if (data=='APPROVE'){
	                		return '审核通过';
	                	}else if (data=='CREATE'){
	                		return '创建流程';
	                	}else if (data==='REJECT'){
	                		return '审核拒绝';
	                	}else{
	                		return '未知操作';
	                	}
	                },
	                "targets": 3
	            }
        	]
	    });
	    
	    $(".delete").live("click", function() {
	    	var id = $(this).attr("id");
	    	if (confirm("确定删除当前记录吗？")) {
	    		window.location.href = "delete.htm?id=" + id;
	    	}
	    	return false;
	    });
	    
	});
</script>