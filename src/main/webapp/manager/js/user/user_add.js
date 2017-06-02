function checkUserName()
{
	$.ajax({
		url:"/manager/user/check_username.json",
		type:"post",
		success:function(r){
			if(r.result!="success"){
				showInfo("warning","用户名已经存在");
			}
			
		}
	});
}
$(function() {
	/**
	 * Menu表单校验
	 */
	$('#addForm').validate({
		rules : {
			"account.username" : {
				required : true,
				username_check:true
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
				required : "用户名不能为空",
				username_check:"用户名已经存在"
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
				type : "get",
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
