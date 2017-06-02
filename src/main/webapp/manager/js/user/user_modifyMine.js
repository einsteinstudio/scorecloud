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
				url : "modifyMine.json",
				success : function(data) {
					if (data.result == "success") {
						window.location.href = "/manager/index.htm";
					} else {
						alert(data.msg);
					}
				}
			});
		},
	});
	
	
	$("#uploadImage").zyUpload({
		width            :   "90%",                 // 宽度
		height           :   "400px",                 // 宽度
		itemWidth        :   "120px",                 // 文件项的宽度
		itemHeight       :   "100px",                 // 文件项的高度
		url              :   "/manager/upload/addImage.htm",  // 上传文件的路径
		multiple         :   true,                    // 是否可以多个文件上传
		dragDrop         :   true,                    // 是否可以拖动上传文件
		del              :   true,                    // 是否可以删除文件
		finishDel        :   false,  				  // 是否在上传文件完成后删除预览
		/* 外部获得的回调接口 */
		onSelect: function(files, allFiles){                    // 选择文件的回调方法
			console.info("当前选择了以下文件：");
			console.info(files);
			console.info("之前没上传的文件：");
			console.info(allFiles);
		},
		onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法
			console.info("当前删除了此文件：");
			console.info(file);
			console.info("当前剩余的文件：");
			console.info(surplusFiles);
		},
		onSuccess: function(file,response){                    // 文件上传成功的回调方法
			console.info("此文件上传成功：");
			console.info(file);
		},
		onFailure: function(file){                    // 文件上传失败的回调方法
			console.info("此文件上传失败：");
			console.info(file);
		},
		onComplete: function(responseInfo){           // 上传完成的回调方法
			console.info("文件上传完成");
			console.info(responseInfo);
		}
	});
	
	 var image_ue = UE.getEditor('photo', {
 		toolbars: [[
		            'simpleupload'
		        ]]
		        ,initialFrameWidth:600  //初始化编辑器宽度,默认1000
		        ,initialFrameHeight:200  //初始化编辑器高度,默认320
		        ,wordCount:false
		        ,elementPathEnabled : false
		        ,maximumWords:1
 	});
});
