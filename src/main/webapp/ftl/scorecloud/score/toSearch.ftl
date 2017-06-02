<#include "../templ/fields/select_class_id.ftl">
<#include "../templ/fields/select_grade_id.ftl">

<#include "../templ/fields/select_exam_id.ftl">
<#include "../templ/global.ftl"> <@framework m2="searchScore">

<h3 class="page-title">成绩查询</h3>

<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>按班级查询
		</div>
	</div>
	<div class="portlet-body form" style="display: block;">
		<!-- BEGIN FORM-->
		<form id="form2" action="searchByExam.htm" class="form-horizontal">
			<div class="form-body">
				<div class="alert alert-danger display-hide" style="display: none;">
					<button class="close" data-close="alert"></button>
					参数校验错误，无法提交
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">选择考试</label>
							<div class="col-md-9">
								<@select_exam_id />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">选择班级</label>
							<div class="col-md-9">
								<@select_class_id/>
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
								<button type="submit" class="btn green">查询</button>
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

<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>按年级查询
		</div>
	</div>
	<div class="portlet-body form" style="display: block;">
		<!-- BEGIN FORM-->
		<form id="form2" action="searchByGrade.htm" class="form-horizontal">
			<div class="form-body">
				<div class="alert alert-danger display-hide" style="display: none;">
					<button class="close" data-close="alert"></button>
					参数校验错误，无法提交
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">选择考试</label>
							<div class="col-md-9">
								<@select_exam_id />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">选择年级</label>
							<div class="col-md-9">
								<@select_grade_id/>
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
								<button type="submit" class="btn green">查询</button>
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


<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>按学生查询
		</div>
	</div>
	
	<div class="portlet-body form" style="display: block;">
		<!-- BEGIN FORM-->
		<form id="form" action="searchByStudent.htm" class="form-horizontal">
			<div class="form-body">
				<div class="alert alert-danger display-hide" style="display: none;">
					<button class="close" data-close="alert"></button>
					参数校验错误，无法提交
				</div>
				<div class="row"><div class="col-md-12">
						<p>按学生查询时，无法显示排名信息</p>
				</div></div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">考试名称<span
								class="required">*</span></label>
							<div class="col-md-9">
								<@select_exam_id />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">姓名学号<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="nameno" type="text" class="form-control"
									placeholder="">
									<span class="tip-block">可以是模糊查询</span>
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
								<button type="submit" class="btn green">查询</button>
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
					nameno : {
						minlength : 2,
						required : true
					}
				},
				messages : {
					nameno : {
						minlength : "查询条件至少包含两个字符",
						required : "请输入查询条件"
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
					return true; //提交表单
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
	
	$(document).ready(function() {
		submitLock = false; //全局变量
		FormValidation.init();
	});
</script>
