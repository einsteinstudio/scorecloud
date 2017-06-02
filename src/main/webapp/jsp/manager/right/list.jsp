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
							<h2><i class="fa fa-table red"></i><span class="break"></span><strong>用户管理</strong></h2>
							<div class="panel-actions">
								<a class="btn-add" href="<%=basePath%>right/toAdd.htm"><input style="width:60px;" type="button" value="添加"/></a>
								<a class="btn-setting" href="table.html#"><i class="fa fa-rotate-right"></i></a>
								<a class="btn-minimize" href="table.html#"><i class="fa fa-chevron-up"></i></a>
								<a class="btn-close" href="table.html#"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="panel-body">
							<table id="paper-table" class="table table-striped table-bordered bootstrap-datatable datatable">
							  <thead>
								  <tr>
									  <th>权限编号</th>
                                      <th>权限名称</th>
									  <th>状态</th>
									  <th>创建时间</th>
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
	$(document).ready(function() {
	    $('#paper-table').dataTable( {
	        "processing": false,
	        "serverSide": true,
	        rowId:"[0]",
	        "ajax": "<%=basePath%>right/list_data.json",
	        "language": {
                "url": "http://cdn.datatables.net/plug-ins/1.10.11/i18n/Chinese.json"
            },
            "columnDefs": [
	            {
	                "render": function ( data, type, row ) {
	                    return  '<a href="<%=basePath%>right/view.htm?id='+data+'" class="btn btn-success">'+
											'<i class="fa fa-search-plus "></i>'+                                           
										'</a>'+
										'<a href="<%=basePath%>right/toModify.htm?id='+data+'" class="btn btn-info">'+
											'<i class="fa fa-edit "></i>    '+                                        
										'</a>'+
										'<a href="#" class="btn btn-danger delete" id="'+data+'">'+
											'<i class="fa fa-trash-o "></i> '+

										'</a>';
	                },
	                "targets": 4
	            },
	            {
	                "render": function ( data, type, row ) {
	                    return  new Date(data).format("yyyy-MM-dd hh:mm:ss");
	                },
	                "targets": 3
	            },
	            {
	                "render": function ( data, type, row ) {
	                	 return  (data != '-1') ? '正常':'停用';
	                },
	                "targets": 2
	            },
        	]
	    });
	    
	    $(".delete").live("click", function() {
	    	var id = $(this).attr("id");
	    	var blogType = '<%=type%>';
	    	if (confirm("确定删除当前记录吗？")) {
	    		window.location.href = "<%=basePath%>right/delete.htm?id=" + id;
	    	}
	    	return false;
	    });
	});
</script>