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
							<h2><i class="fa fa-table red"></i><span class="break"></span><strong>我的消息</strong></h2>
							<div class="panel-actions">
								<a class="btn-minimize" href="table.html#"><i class="fa fa-chevron-up"></i></a>
								<a class="btn-close" href="table.html#"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="panel-body">
							<table id="paper-table" class="table table-striped table-bordered bootstrap-datatable datatable">
							  <thead>
								  <tr>
									  <th>发送者</th>
                                      <th>消息标题</th>
									  <th>类型</th>
									  <th>状态</th>
									  <th>发送时间</th>
									  <th>操作</th>
								  </tr>
							  </thead>   							  
						  </table>  
					</div> 
				</div><!--/col-->
			</div>
		</div>
		
		<!-- <div class="row">		
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2><i class="fa fa-table red"></i><span class="break"></span><strong>我的发送</strong></h2>
							<div class="panel-actions">
								 <a class="btn-add" href="toAdd.htm"><i class="fa fa-plus"></i></a> 
								<a class="btn-minimize" href="table.html#"><i class="fa fa-chevron-up"></i></a>
								<a class="btn-close" href="table.html#"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="panel-body">
							<table id="send-list" class="table table-striped table-bordered bootstrap-datatable datatable">
							  <thead>
								  <tr>
									  <th>接收者</th>
                                      <th>消息标题</th>
                                      <th>类型</th>
									  <th>状态</th>
									  <th>发送时间</th>
									  <th>操作</th>
								  </tr>
							  </thead>   							  
						  </table>  
					</div> 
				</div>/col
			</div>
		</div> -->

	<div class="modal fade in" id="myModal" aria-hidden="false"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">消息详情</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="mId" >
					<p id="textInfo"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="read" type="button" class="btn btn-primary" data-dismiss="modal">已读</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</div>
<script src="<%=basePath %>assets/js/jquery-2.1.1.min.js"></script>
<script>
	showDelete="${not empty rightMap.CMS_DELETE}";
	showEdit="${not empty rightMap.CMS_EDIT}";
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
	                	
	                	
						return '<a><i id="'+row[5]+'" data="'+row[6]+'"class="viewDetail fa fa-search-plus ">查看</i></a>';
	                },
	                "targets": 5
	            },
	            {
	                "render": function ( data, type, row ) {
	                	
						return '<span class="lable-status label label-success">'+data+'</span>';
	                },
	                "targets": 3
	            },
        	]
	    });
	   
	   var table2= $('#send-list').dataTable( {
	        "processing": false,
	        "serverSide": true,
	        rowId:"[0]",
	        "ajax": "list_send_data.json",
	        "language": {
               "url": "http://cdn.datatables.net/plug-ins/1.10.11/i18n/Chinese.json"
           },
           "columnDefs": [
	            {
	                "render": function ( data, type, row ) {
	                	
	                	
						return '<a><i id="'+row[5]+'" data="'+row[6]+'"class="viewDetail fa fa-search-plus ">查看</i></a>';
	                },
	                "targets": 5
	            },
	            {
	                "render": function ( data, type, row ) {
	                	
						return '<span class="lable-status label label-success">'+data+'</span>';
	                },
	                "targets": 3
	            },
       	]
	    });
	   
	    
	    $(".delete").live("click", function() {
	    	var id = $(this).attr("id");
	    	if (confirm("确定删除当前记录吗？")) {
	    		window.location.href = "delete.htm?id=" + id;
	    	}
	    	return false;
	    });
	    //打开消息详情对话框
	    $('.viewDetail').live('click',function(){
	    	$('#textInfo').html($(this).attr('data'));
	    	$('#mId').val($(this).attr('id'));
		    $('#myModal').modal('show');
	    });
	    
	    $('#read').live('click',function(){
	    	$.ajax({
	    		url:"/manager/message/read.json?messageId="+$('#mId').val(),
	    		success:function(d){
	    			if(d.result=="success"){
		    			$('#'+$('#mId').val()).parents('tr').find('.lable-status').html('已读');

	    			}else{
	    				showInfo("warning",d.msg)
	    			}
	    		}
	    	});
	    });
	    
	});
</script>