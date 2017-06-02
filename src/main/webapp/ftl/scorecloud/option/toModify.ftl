<#include "../templ/global.ftl"> <@framework m2="listOption">

<h3 class="page-title">选项修改</h3>

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
					<input type="hidden" name="id" value="${optionDto.id}">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">选项名称<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="optionName" type="text" class="form-control"
									placeholder="" value="${optionDto.optionName}">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">排序<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="weight" type="number" class="form-control"
									placeholder="" value="${optionDto.weight}">
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
											class="md-radiobtn" value="true"<#if
										optionDto.showTip>checked=""</#if> > <label for="radioboy">
											<span class="inc"></span> <span class="check"></span> <span
											class="box"></span> 显示
										</label>
									</div>
									<div class="md-radio">
										<input type="radio" name="showTip" id="radiogirl"
											class="md-radiobtn"<#if
										!optionDto.showTip>checked=""</#if> value="false"> <label
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
									placeholder="" value="${optionDto.tip}">
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
									placeholder="" value="${optionDto.innerCode}">
							</div>
						</div>
					</div>
				</div>
			</div>
			<h3 class="form-section">配置值</h3>
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
											添加选项值 <i class="fa fa-plus"></i>
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
									<th>选项描述</th>
									<th>选项值</th>
									<th>权重</th>
									<th>默认选中</th>
									<th>编辑</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>

								<#list optionDto.optionValueDtos as v>
								<tr>
									<td>${v.descrp}</td>
									<td class="rowId" rowId="${v.id}">${v.value}</td> <!-- 为了设置ID -->
									<td>${v.weight}</td>
									<td><#if v.checked>选中</#if><#if !v.checked>不选中</#if></td>
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
<script src="/myapp/js/scorecloud/option.js" type="text/javascript"></script>

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
