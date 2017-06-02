$(function() {
	
	
	
	
	/**
	 * Menu表单校验
	 */
	$('#addForm').validate({
		rules : {
			department : {
				required : true
			},
			useReason : {
				required :true
			},
			fromLocation : {
				required : true
			},
			toLocation : {
				required : true
			},
			fromDaten : {
				required : true
			},
			toDate : {
				required : true
			}
		},
		messages : {
			department : {
				required : "部门不能为空"
			},
			useReason : {
				required : "用车原因不能为空"
			},
			fromLocation : {
				required : "出发地不能为空"
			},
			toLocation : {
				required : "目的地不能为空"
			},
			fromDaten : {
				required : "用车开始时间不能为空"
			},
			toDate : {
				required : "用车结束时间不能为空"
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
