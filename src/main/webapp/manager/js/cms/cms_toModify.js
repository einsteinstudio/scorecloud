$(function() {
	/**
	 * Menu表单校验
	 */
	$('#addForm').validate({
		rules : {
			id : {
				required : true
			},
			description : {
				required : true
			},
			n18 : {
				required : true
			},
			content : {
				required : true
			}
		},
		messages : {
			id : {
				required : "CMS名不能为空"
			},
			description : {
				required : "描述不能为空"
			},
			n18 : {
				required : "语言不能为空"
			},
			content : {
				required : "配置内容不能为空"
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
