<#include "../templ/global.ftl"> <@framework m2="listOption">

<h3 class="page-title">选项添加</h3>

<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>选项信息
		</div>
	</div>
	<div class="portlet-body form" style="display: block;">
		<!-- BEGIN FORM-->
		<form id="form" action="add.json" class="form-horizontal">
			<div class="form-body">
				<div class="alert alert-danger display-hide" style="display: none;">
					<button class="close" data-close="alert"></button>
					参数校验错误，无法提交
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">选项名称<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="optionName" type="text" class="form-control"
									placeholder="">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">排序<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="weight" type="number" class="form-control"
									placeholder=""> 
							</div>
						</div>
					</div>
					<!--/span-->
				</div>

				<!--/row-->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">显示提示</label>
							<div class="col-md-9">
								<div class="md-radio-inline">
									<div class="md-radio">
										<input type="radio" id="radioboy" name="showTip"
											class="md-radiobtn" value="true"> <label for="radioboy"> <span
											class="inc"></span> <span class="check"></span> <span
											class="box"></span> 显示
										</label>
									</div>
									<div class="md-radio">
										<input type="radio" name="showTip" id="radiogirl"
											class="md-radiobtn" checked="" value="false"> <label
											for="radiogirl"> <span class="inc"></span> <span
											class="check"></span> <span class="box"></span> 不显示
										</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">提示内容</label>
							<div class="col-md-9">
								<input name="tip" type="text" class="form-control"
									placeholder="" value="--请选择">
							</div>
						</div>
					</div>
					<!--/span-->
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">内部代码</label>
							<div class="col-md-9">
								<input name="innerCode" type="text" class="form-control"
									placeholder="">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions">
				<div class="row">
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn green">提交</button>
							</div>
						</div>
					</div>
					<div class="col-md-6"></div>
				</div>
			</div>
		</form>
		<!-- END FORM-->
	</div>
</div>

</@framework>
<script>
	
</script>
<script>
	// basic validation
	var FormValidation = function() {
		var handleValidationForm = function() {
			// for more info visit the official plugin documentation: 
			// http://docs.jquery.com/Plugins/Validation

			var form1 = $('#form');
			var error1 = $('.alert-danger', form1);
			var success1 = $('.alert-success', form1);

			oSetting = {
				rules : {
					optionName : {
						minlength : 2,
						required : true
					},
					innerCode : {
						minlength : 2,
						required : true
					},
					weight : {
						digits : true,
						required : true
					}
				},
				messages : {
					optionName : {
						minlength : "选项名称至少包含两个字符",
						required : "请输入选项名称"
					},
					innerCode : {
						required : "你输入内部代码",
						minlength : "内部代码名称至少包含两个字符",
					},
					weight : {
						number : "排序必须是数字",
						required : "排序不能为空"
					}
				},
				invalidHandler : function(event, validator) { // display error alert on
					// form submit
					success1.hide();
					error1.show();
					App.scrollTo(error1, -200);
				},
				submitHandler : function(form) {
					success1.show();
					error1.hide();
					//执行ajax提交
					if (!submitLock) {
						submitLock = true;
						console.log("submit");
						postHandler();
					} else {
						//重复提交报错显示
						popGrowl('您的请求已经提交，请稍后再试', 'danger');
					}

				},
			};
			oSetting = $.extend(true, oSetting, window.VALIDATE_default);
			form1.validate(oSetting);
		};
		return {
			//main function to initiate the module
			init : function() {
				handleValidationForm();
			}

		};
	}();
	var postHandler = function() //函数定义
	{
		console.log('post');
		//使用jquery form可以避免自己组装form参数
		$("#form").ajaxSubmit({
			type : "get",
			url : "add.json",
			success : function(data) {
				if (data.result == "success") {
					window.location.href = "addSuccess.htm";
				} else {
					popGrowl(data.msg);
				}
			}
		});

	}

	
	$(document).ready(function() {
		submitLock = false; //全局变量
		FormValidation.init();
		//TableDatatablesEditable.init();
	});
</script>
