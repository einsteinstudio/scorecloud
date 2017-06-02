	function refreshimg(){
		document.all.checkcode.src='/code?'+Math.random();
	}
$(document).ready(function(){

	//判断错误信息
	
	
	$('#login-form').validate({
		rules: {
			j_username: {
				required: true,
			},
			j_password: {
				required: true,
			},
			j_code: {
				required: true,
			}
		},
		messages: {
			j_username: {
				required: "请输入用户名",
			},
			j_password: {
				required: "请输入密码",
			},
			j_code: {
				required: "请输入验证码",
			},	   	
		},
		errorPlacement: function(error, element) {  
			element.popover({
				trigger: 'manual',
				'html': true,
				content: '<p class="error" style="width:100px;">'+error.html()+'</p>',				
			}).popover('show');
		},
		showErrors:function(errorMap,errorList) {
			var element = this.currentElements;
			if(element.next().hasClass('popover')){
				element.popover('destroy');
			}
			this.defaultShowErrors();
		},
		submitHandler: function(form) 
    	{   
//			alert($('input[name="j_password"]').val());
//			alert(CryptoJS.MD5($('input[name="j_password"]').val()));
//			alert(CryptoJS.MD5(CryptoJS.MD5($('input[name="j_password"]').val())+""));
			$('input[name="j_password"]').val(CryptoJS.MD5(CryptoJS.MD5($('input[name="j_password"]').val())+""));
    		$(form).ajaxSubmit(function(data) { 
    			if(data.result=='success'){
    				window.location.href = '/manager/index.htm'
    			}else{
    				$('.error-hold').html(data.msg);
    				refreshimg();
    			}       
    	    });      
    	} 
	});
});