<#include "../templ/fields/select_grade_id.ftl">
<#include "../templ/global.ftl"> <@framework m2="addGrade">

<h3 class="page-title">班级添加</h3>

<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>班级信息
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
					<input type="hidden" name="id" value="${classDto.id}">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">班级名称<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="className" type="text" class="form-control"
									placeholder="" value="${classDto.className}"> <span class="tip-block">比如：1班、2班</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">排序</label>
							<div class="col-md-9">
								<input name="weight" type="text" class="form-control"
									placeholder="" value="${classDto.weight}"> 
							</div>
						</div>
					</div>
					<!--/span-->
				</div>

				<!--/row-->
				<div class="row">
					<!--/span-->
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">班主任</label>
							<div class="col-md-9">
								<input name="majorTracherName" type="text" class="form-control"
									placeholder="" value="${(classDto.majorTracherName)!}">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">所在年级<span
								class="required">*</span></label>
							<div class="col-md-9">
								<@select_grade_id selectedVal="${(classDto.gradeId)!}"/>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">班级备注</label>
							<div class="col-md-9">
								<textarea  name="note" class="form-control" rows="3"></textarea>
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
					className : {
						minlength : 2,
						required : true
					},
					weight : {
						digits : true,
						required : true
					}
				},
				messages : {
					className : {
						minlength : "班级名称至少包含两个字符",
						required : "请输入班级名称"
					},
					weight : {
						number : "权重必须是数字",
						required : "权重不能为空"
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
			url : "modify.json",
			success : function(data) {
				if (data.result == "success") {
					window.location.href = "modifySuccess.htm";
				} else {
					popGrowl(data.msg);
				}
			}
		});

	}
	$(document).ready(function() {
		submitLock = false; //全局变量
		FormValidation.init();
	});
</script>
