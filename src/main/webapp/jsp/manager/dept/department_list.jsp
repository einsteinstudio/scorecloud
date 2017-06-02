<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="main">
	<div class="row">		
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2><i class="fa fa-table red"></i><span class="break"></span><strong>部门管理</strong></h2>
							<div class="panel-actions">
								<c:if test="${not empty rightMap.DEPT_ADD}"><a class="btn-add" href="toAdd.htm?pId=${pId}"><i class="fa fa-plus"></i></a></c:if>
								<a class="btn-setting" href="table.html#"><i class="fa fa-rotate-right"></i></a>
								<a class="btn-minimize" href="table.html#"><i class="fa fa-chevron-up"></i></a>
								<a class="btn-close" href="table.html#"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="panel-body">
							<table id="paper-table" class="table table-striped table-bordered bootstrap-datatable datatable">
							  <thead>
								  <tr>
									  <th>部门名称</th>
                                      <th>部门电话</th>
									  <th>权重</th>
									  <th>操作</th>
								  </tr>
							  </thead>   							  
						  </table>  
					</div> 
				</div><!--/col-->
			</div>
		</div>
</div>
<script src="//${cdnDomain}/cdn/template/proton/assets/js/jquery-2.1.1.min.js"></script>
<script>
	showDelete="${not empty rightMap.DEPT_DELETE}";
	showEdit="${not empty rightMap.DEPT_EDIT}";
	$(document).ready(function() {
	    table= $('#paper-table').dataTable( {
	        "processing": false,
	        "serverSide": true,
	        rowId:"[0]",
	        "ajax": "list_data.json?pId=${pId}",
	        "language": {
                "url": "http://cdn.datatables.net/plug-ins/1.10.11/i18n/Chinese.json"
            },
            "columnDefs": [
	            {
	                "render": function ( data, type, row ) {
	                	
	                	var html='';
	                    var viewHtml=  '<a href="view.htm?id='+data+'" class="btn btn-success">'+
											'<i class="fa fa-search-plus "></i></a>';
						var editHtml='<a href="toModify.htm?&id='+data+'" class="btn btn-info">'+
											'<i class="fa fa-edit "></i></a>';
											
						var deleteHtml='<a href="#" class="btn btn-danger delete" id="'+data+'">'+
											'<i class="fa fa-trash-o "></i></a>';
						if(showEdit=='true')
							html+=editHtml;
						if(showDelete=='true')
							html+=deleteHtml;
						return html;
	                },
	                "targets": 3
	            },
	            {
	            	"render": function ( data, type, row ) {
	            		return '<a target="_self" href="list.htm?pId='+row[3]+'" >'+data+'</a>';
	            	},
	            	"targets": 0
	            }
        	]
	    });
	    
	    $(".delete").live("click", function() {
	    	var id = $(this).attr("id");
	    	if (confirm("确定删除当前记录吗？")) {
	    		window.location.href = "delete.htm?id=" + id+"&pId=${pId}";
	    	}
	    	return false;
	    });
	    
	});
</script>