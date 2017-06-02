<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/manager/";
%>
<div class="main">
	<div class="row">		
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2><i class="fa fa-table red"></i><span class="break"></span><strong>${title}</strong></h2>
							<div class="panel-actions">
								<c:if test="${not empty rightMap.LANMU_ADD}"><a  target="_self" href="/manager/menu/toAdd.htm?pId=${pId}"><i class="fa fa-plus"></i></a></c:if>
								<a class="btn-setting" href="table.html#"><i class="fa fa-rotate-right"></i></a>
								<a class="btn-minimize" href="table.html#"><i class="fa fa-chevron-up"></i></a>
								<a class="btn-close" href="table.html#"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="panel-body">
							<table id="paper-table" class="table table-striped table-bordered bootstrap-datatable datatable">
							  <thead>
								  <tr>
									  <th>栏目名称</th>
                                      <th>栏目类型</th>
									  <th>资源类型</th>
									  <th>权重</th>
									  <th>状态</th>
									  <th>操作</th>
								  </tr>
							  </thead>   							  
						  </table>  
					</div> 
				</div><!--/col-->
			</div>
		</div>
		
		<div class="row">	
			<c:if test="${not empty ppId}">
							<a target="_self" href="/manager/menu/list.htm?pId=${ppId}"><button id="reset" type="button" class="btn btn-sm btn-info">返回</button></a>
			</c:if>
		</div>
</div>
<script src="<%=basePath %>assets/js/jquery-2.1.1.min.js"></script>
 <script>
 showDelete="${not empty rightMap.LANMU_DELETE}";
 showEdit="${not empty rightMap.LANMU_EDIT}";
	$(document).ready(function() {
	    $('#paper-table').dataTable( {
	        "processing": false,
	        "serverSide": true,
	        "lengthMenu": [[-1], ["All"]],
	        rowId:"[0]",
	        "ajax": "/manager/menu/list.json?pId=${pId}",
	        "language": {
                "url": "http://cdn.datatables.net/plug-ins/1.10.11/i18n/Chinese.json"
            },
            "columnDefs": [
	            {
	                "render": function ( data, type, row ) {
	                	var html='';
	                	var editHtml='<a href="/manager/menu/toModify.htm?id='+data+'" class="btn btn-info">'+
						'<i class="fa fa-edit "></i></a>';
						var deleteHtml='<a href="#" class="btn btn-danger delete" id="'+data+'">'+
						'<i class="fa fa-trash-o "></i></a>';
						if(showEdit=='true')
							html+=editHtml;
						if(showDelete=='true')
							html+=deleteHtml;
						return html;
	                },
	                "targets": 5
	            },
	            {
	                "render": function ( data, type, row ) {
	                    return  '<a target="_self" href="/manager/menu/list.htm?pId='+row[5]+'">'+data+'</a>';
	                },
	                "targets": 0
	            },
	            {
	                "render": function ( data, type, row ) {
	                    return data?"隐藏":"显示";
	                },
	                "targets": 4
	            }
        	] 
	    });
	    
	    $(".delete").live("click", function() {
	    	var id = $(this).attr("id");
	    	$.ajax({
	    		url:"/manager/menu/delete.json?id="+id,
	    		success:function(data){
	    			//showInfo('info','删除成功');
	    			 window.location.href="/manager/menu/list.htm?pId=${pId}";
	    		}
	    	});
	    });
	});
</script>