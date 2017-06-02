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
							<h2><i class="fa fa-table red"></i><span class="break"></span><strong>${title}</strong></h2>
							<div class="panel-actions">
								<c:if test="${not empty rightMap.INI_ADD}"><a class="btn-add" href="toAdd.htm"><i class="fa fa-plus"></i></a></c:if>
								<a class="btn-setting" href="table.html#"><i class="fa fa-rotate-right"></i></a>
								<a class="btn-minimize" href="table.html#"><i class="fa fa-chevron-up"></i></a>
								<a class="btn-close" href="table.html#"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="panel-body">
							<table id="paper-table" class="table table-striped table-bordered bootstrap-datatable datatable">
							  <thead>
								  <tr>
									  <th>配置项</th>
                                      <th>中文名</th>
									  <th>描述</th>
									  <th>值</th>
									  <th>即时更新</th>
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
	showDelete="${not empty rightMap.INI_DELETE}";
	showEdit="${not empty rightMap.INI_EDIT}";
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
	                    var viewHtml=  '<a href="view.htm?id='+data+'" class="btn btn-success">'+
											'<i class="fa fa-search-plus "></i></a>';
						var editHtml='<a href="toModify.htm?id='+data+'" class="btn btn-info">'+
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
	                	
	                	if(data)
	                		{
	                			return "是";
	                		}
	                	else{
	                		return "否";
	                	}
	                },
	                "targets": 4
	            }
	            
	            
        	]
	    });
	    
	    $(".delete").live("click", function() {
	    	var id = $(this).attr("id");
	    	var blogType = '<%=type%>';
	    	if (confirm("确定删除当前记录吗？")) {
	    		window.location.href = "delete.htm?type=<%=type%>&id=" + id;
	    	}
	    	return false;
	    });
	    
	});
</script>