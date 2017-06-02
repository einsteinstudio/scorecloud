$(function() {
	
	
	
	
	/**
	 * Menu表单校验
	 */
	$('#addForm').validate({
		rules : {
			department : {
				required : true
			},
			type : {
				required : true
			},
			deliverDate : {
				required : true
			},
			payWay : {
				required : true
			}
		},
		messages : {
			department : {
				required : "部门不能为空"
			},
			type : {
				required : "采购类型不能为空"
			},
			deliverDate : {
				required : "配送日期不能为空"
			},
			payWay : {
				required : "支付方式不能为空"
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
