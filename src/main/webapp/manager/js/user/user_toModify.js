$(function() {
	/**
	 * Menu表单校验
	 */
	$('#addForm').validate({
		rules : {
			"account.username" : {
				required : true
			},
			"account.password" : {
				required : true
			},
			"teacher.name" : {
				required : true
			}
		},
		messages : {
			"account.username" : {
				required : "用户名不能为空"
			},
			"account.password" : {
				required : "密码不能为空"
			},
			"teacher.name" : {
				required : "姓名不能为空"
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
