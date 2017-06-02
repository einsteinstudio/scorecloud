$(function() {
	/**
	 * Menu表单校验
	 */
	$('#addForm').validate({
		rules : {
			roleName : {
				required : true
			}
		},
		messages : {
			roleName : {
				required : "角色名称不能为空"
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
				url : "/manager/role/modify.json",
				success : function(data) {
					if (data.result == "success") {
						window.location.href = "/manager/role/list.htm";
					} else {
						alert(data.msg);
					}
				}
			});
		},
	});
});
