<#include "../templ/fields/select_subject.ftl">
<#include "../templ/global.ftl"> <@framework m2="listOption">
<div class="subject-temp" style="display:none">
	<@select_subject  />
</div>
<h3 class="page-title">考试修改</h3>

<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>考试信息
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
					<input type="hidden" name="id" value="${examDto.id}">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">考试名称<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="examName" type="text" class="form-control"
									placeholder="" value="${(examDto.examName)!}">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">年度<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="year" type="number" class="form-control"
									placeholder="" value="${examDto.year}">
							</div>
						</div>
					</div>
					<!--/span-->
				</div>

			</div>
			<h3 class="form-section">科目设置</h3>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light portlet-fit "></div>
					<div class="portlet-body">
						<div class="table-toolbar">
							<div class="row">
								<div class="col-md-6">
									<div class="btn-group">
										<button id="sample_editable_1_new" class="btn green">
											添加科目 <i class="fa fa-plus"></i>
										</button>
									</div>
								</div>
								<div class="col-md-6"></div>
							</div>
						</div>
						<table class="table table-striped table-hover table-bordered"
							id="sample_editable_1">
							<thead>
								<tr>
									<th>科目名</th>
									<th>排序</th>
									<th>及格分数线</th>
									<th>优良分数线</th>
									<th>优秀分数线</th>
									<th>编辑</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>

								<#list examDto.subjectDtos as v>
								<tr>
									<td>${v.subjectName}</td>
									<td>${v.weight}</td>
									<td class="rowId" rowId="${v.id}">${(v.hegeScore)!}</td> <!-- 为了设置ID -->
									<td>${(v.youliangScore)!}</td>
									<td>${(v.excellantScore)!}</td>
									<td><a class="edit" href="javascript:;" objectId="${v.id}"> 编辑 </a></td>
									<td><a class="delete" href="javascript:;" objectId="${v.id}" > 删除 </a></td>

								</tr>
								</#list>

							</tbody>
						</table>
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
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
<script src="/myapp/js/scorecloud/exam.js" type="text/javascript"></script>

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
					examName : {
						minlength : 2,
						required : true
					},
					year : {
						number : true,
						required : true
					}
				},
				messages : {
					examName: {
						minlength : "考试名称至少包含两个字符",
						required : "请输入考试名称"
					},
					year : {
						required : "你输入考试年度",
						number : "考试年度必须是数字",
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
		TableDatatablesEditable.init();
	});
</script>
