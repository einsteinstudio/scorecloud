$(function() {
	$('#closeWin').live('click',function(){
		top.$.jBox.close("auditDialog");
	});
	$('.agree').bind("click",function(){
		$.jBox("iframe:/manager/workflow/audit/view.htm?id="+flowId+"&eventType=APPROVE", {
		    title: "我的审核",
			id:"auditDialog",
		    width: 800,
		    height: 450,
		    buttons: {  }
		});
	});
	$('.reject').bind("click",function(){
		$.jBox("iframe:/manager/workflow/audit/view.htm?id="+flowId+"&eventType=REJECT", {
			id:"auditDialog",
		    title: "我的审核",
		    width: 800,
		    height: 450,
		    buttons: {  }
		});
	});
	$('.comment').bind("click",function(){
		$.jBox("iframe:/manager/workflow/audit/view.htm?id="+flowId+"&eventType=COMMENT", {
			id:"auditDialog",
		    title: "我的评论",
		    width: 800,
		    height: 450,
		    buttons: {  }
		});
	});
	
	$('.revoke').bind("click",function(){
		$.jBox("iframe:/manager/workflow/audit/view.htm?id="+flowId+"&eventType=REVOKE", {
			id:"auditDialog",
		    title: "我的回收",
		    width: 800,
		    height: 450,
		    buttons: {  }
		});
	});
	
	$("#uploadImage").zyUpload({
		width : "90%", // 宽度
		height : "400px", // 宽度
		itemWidth : "120px", // 文件项的宽度
		itemHeight : "100px", // 文件项的高度
		url : "/manager/upload/addImage.htm", // 上传文件的路径
		multiple : true, // 是否可以多个文件上传
		dragDrop : true, // 是否可以拖动上传文件
		del : true, // 是否可以删除文件
		finishDel : false, // 是否在上传文件完成后删除预览
	});
	
	$('#addForm').validate({
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
				url : "/manager/workflow/audit/submit.json",
				success : function(data) {
					if (data.result == "success") {
						top.location.reload(); 
					} else {
						alert(data.msg);
					}
					top.$.jBox.close("auditDialog");
				}
			});
		},
	});
});