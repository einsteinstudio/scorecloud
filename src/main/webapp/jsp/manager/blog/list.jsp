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
		<a name="firstLine"></a>  
		<div class="col-md-3">
			<p>中文资源</p>
			<div class="dd" id="nestable" style="width:100%">
				<ol class="dd-list">
					<c:forEach var="menu" items="${roledMenu.children}">
						<c:if test="${menu.checked}">
							<li class="dd-item item" data-id="${menu.id}">
								<div class="dd-handle <c:if test='${menu.name==type}'>dd-clicked</c:if> "  >
									${menu.name}
								</div>
								<ol class="dd-list">
									<c:forEach var="mm" items="${menu.children}" >
										<c:if test="${mm.checked}">
											<li class="dd-item " data-id="${mm.id}"><div class="dd-handle  <c:if test='${mm.name==type}'>dd-clicked</c:if> ">${mm.name}</div></li>
										</c:if>
									</c:forEach>
								</ol>
							</li>
						</c:if>
					</c:forEach>
				</ol>
			</div>
			<p>英文资源</p>
			<div class="dd" id="nestable" style="width:100%">
				<ol class="dd-list">
					<c:forEach var="menu" items="${roledEnMenu.children}">
						<c:if test="${menu.checked}">
							<li class="dd-item item" data-id="${menu.id}">
								<div class="dd-handle <c:if test='${menu.name==type}'>dd-clicked</c:if> "  >
									${menu.name}
								</div>
								<ol class="dd-list">
									<c:forEach var="mm" items="${menu.children}" >
										<li class="dd-item " data-id="${mm.id}"><div class="dd-handle  <c:if test='${mm.name==type}'>dd-clicked</c:if> ">${mm.name}</div></li>
									</c:forEach>
								</ol>
							</li>
						</c:if>
					</c:forEach>
				</ol>
			</div>
			
		</div>	
				<div class="col-md-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2><i class="fa fa-table red"></i><span class="break"></span><strong>${title}</strong></h2>
							<div class="panel-actions">
								<c:if test="${not empty rightMap.BLOG_ADD}"><a class="btn-add" ><i class="fa fa-plus"></i></c:if>
								<a class="btn-setting" href="table.html#"><i class="fa fa-rotate-right"></i></a>
								<a class="btn-minimize" href="table.html#"><i class="fa fa-chevron-up"></i></a>
								<a class="btn-close" href="table.html#"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="panel-body">
							<table id="paper-table" class="table table-striped table-bordered bootstrap-datatable datatable">
							  <thead>
								  <tr>
									  <th>标题</th>
                                      <th>作者</th>
									  <th>状态</th>
									  <th>最近更新</th>
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
	curType="${type}";
	console.log("default type:"+curType);
	if(curType=="")
		curType=$('.dd-handle:first').html().trim();

	console.log(curType);
	showDelete="${not empty rightMap.BLOG_DELETE}";
	showEdit="${not empty rightMap.BLOG_EDIT}";
	$(document).ready(function() {
		/**
		*点击树形菜单事件
		*/
		$(".dd-handle").live("click",function(){
			$(".dd-handle").removeClass("dd-clicked");
			$(this).addClass("dd-clicked");
			curType=$(this).html().trim();
			console.log(curType);
			oTable.api().ajax.reload();
			location.href = "#firstLine";
		});
		
		oTable=$('#paper-table').dataTable( {
	        "processing": false,
	        "serverSide": true,
	        rowId:"[0]",
	        "ajax": {
	        	"url":"<%=basePath%>blog/list_data.json",
	        	"data":{
	        		"type":function(){return curType}
	        	}
	        },
	        "language": {
                "url": "http://cdn.datatables.net/plug-ins/1.10.11/i18n/Chinese.json"
            },
            "columnDefs": [
	            {
	                "render": function ( data, type, row ) {
	                	
	                	var html='';
	                    var viewHtml=  '<a href="<%=basePath%>blog/view.htm?type=<%=type%>&id='+data+'" class="btn btn-success">'+
											'<i class="fa fa-search-plus "></i></a>';
						var editHtml='<a href="<%=basePath%>blog/toModify.htm?type=<%=type%>&id='+data+'" class="btn btn-info">'+
											'<i class="fa fa-edit "></i></a>';
											
						var deleteHtml='<a href="#" class="btn btn-danger delete" id="'+data+'">'+
											'<i class="fa fa-trash-o "></i></a>';
						if(showEdit=='true')
							html+=editHtml;
						if(showDelete=='true')
							html+=deleteHtml;
						return html;
	                },
	                "targets": 4
	            },
	            {
	                "render": function ( data, type, row ) {
	                    return  '<span class="label label-success">'+data+'</span>';
	                },
	                "targets": 2
	            },
	            {
	                "render": function ( data, type, row ) {
	                    return  new Date(data).format("yyyy-MM-dd hh:mm:ss");
	                },
	                "targets": 3
	            },
        	]
	    });
	    
	    $(".delete").live("click", function() {
	    	var id = $(this).attr("id");
	    	var blogType = '<%=type%>';
	    	if (confirm("确定删除当前记录吗？")) {
	    		window.location.href = "<%=basePath%>blog/delete_record.htm?type=<%=type%>&id=" + id;
	    	}
	    	return false;
	    });
	    
	    $('.btn-add').live("click",function(){
	    	window.location.href = "/manager/blog/toAdd.htm?type="+curType;
	    });
	});
</script>