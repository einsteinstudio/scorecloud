<#include "../templ/fields/select_grade_name.ftl">
<#include "../templ/global.ftl"> <@framework m2="addGrade">

<h3 class="page-title">年级修改</h3>

<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>年级信息
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
					<input type="hidden" name="id" value="${gradeDto.id}">
					<input type="hidden" name="status" value="${gradeDto.status}">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">年级名称<span
								class="required">*</span></label>
							<div class="col-md-9">
								<@select_grade_name selectedVal="${(gradeDto.gradeName)!}" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">入学年份<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="enterYear" type="text" class="form-control"
									placeholder="" value="${gradeDto.enterYear}">
							</div>
						</div>
					</div>
					<!--/span-->
				</div>

				<!--/row-->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">排序</label>
							<div class="col-md-9">
								<input name="weight" type="text" class="form-control"
									placeholder="" value="${gradeDto.weight}"> <span
									class="tip-block">为数字，值越小越靠前，一年级为1，二年级为2...</span>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">年级主任</label>
							<div class="col-md-9">
								<input name="majorTeacherName" type="text" class="form-control"
									placeholder="" value="${gradeDto.majorTeacherName}">
							</div>
						</div>
					</div>
					<!--/span-->
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
					
					enterYear : {
						required : true,
						digits : true,
						range : [ 1900, 2099 ],
					},
					weight : {
						digits : true,
						required : true
					}
				},
				messages : {
					
					enterYear : {
						required : "你输入本年级的入校年份",
						digits : "请输入正确的年份",
						range : "年份必须在1900-2099之间"
					},
					weight : {
						number : "权重必须是数字",
						required : "权重不能为空"
					}
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
			oSetting=$.extend(true,oSetting,VALIDATE_default);
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
