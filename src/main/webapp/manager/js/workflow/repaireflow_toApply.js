$(function() {
	
	
	
	
	/**
	 * Menu表单校验
	 */
	$('#addForm').validate({
		rules : {
			department : {
				required : true
			},
			name : {
				required : true
			},
			breakReason : {
				required : true
			},
			manager : {
				required : true
			}
		},
		messages : {
			department : {
				required : "部门不能为空"
			},
			name : {
				required : "物品名称不能为空"
			},
			breakReason : {
				required : "损坏原因不能为空"
			},
			manager : {
				required : "物品管理员不能为空"
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
				url : "add.json",
				success : function(data) {
					if (data.result == "success") {
						window.location.href = "list.htm";
					} else {
						alert(data.msg);
					}
				}
			});
		},
	});
});
