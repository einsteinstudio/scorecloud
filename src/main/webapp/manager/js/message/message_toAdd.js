$(function() {
	/**
	 * Menu表单校验
	 */
	$('#addForm').validate({
		rules : {
			title : {
				required : true
			},
			content : {
				required : true
			},
			receivers : {
				required : true
			}
		},
		messages : {
			title : {
				required : "消息标题不能为空"
			},
			content : {
				required : "消息内容不能为空"
			},
			receiver : {
				required : "接收者"
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
	
	//弹出账号选择框
	$('.choose_account').bind("click",function(){
		$.jBox("iframe:/manager/user/dialog/list.htm", {
		    title: "选择用户",
			id:"chooseAccountDialog",
		    width: 800,
		    height: 450,
		    buttons: {  }
		});
	});
});
