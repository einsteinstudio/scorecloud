$(function() {
	/**
	 * Menu表单校验
	 */
	$('#addForm').validate({
		rules : {
			name : {
				required : true
			},
			nameCn : {
				required : true
			},
			description : {
				required : true
			},
			value : {
				required : true
			}
		},
		messages : {
			name : {
				required : "配置名不能为空"
			},
			nameCn : {
				required : "配置中文名不能为空"
			},
			description : {
				required : "配置描述不能为空"
			},
			value : {
				required : "配置值不能为空"
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
				url : "modify.json",
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
