<#include "../templ/fields/select_class_id.ftl">
<#include "../templ/fields/select_national.ftl">
<#include "../templ/global.ftl"> <@framework m2="listStudent">

<h3 class="page-title">学生修改</h3>

<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>学生信息
		</div>
	</div>
	<div class="portlet-body form" style="display: block;">
		<!-- BEGIN FORM-->
		<form id="form" action="add.json" class="form-horizontal">
			<div class="form-body">
				<input type="hidden" name="id" value="${dto.id}">
				<div class="alert alert-danger display-hide" style="display: none;">
					<button class="close" data-close="alert"></button>
					参数校验错误，无法提交
				</div>
				<h3 class="form-section">账号信息</h3>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">用户名<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="accountId" type="hidden" value="${dto.accountId}">
								<input name="username" type="text" class="form-control"
									placeholder="" value="${(dto.username)!}"> <span class="tip-block">登录用户名不能重复</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">密码<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="password" type="password" class="form-control"
									placeholder="" value="${(dto.password)!}">
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<h3 class="form-section">个人信息</h3>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">学号<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="studentNo" type="text" class="form-control" value="${dto.studentNo}"
									placeholder=""> <span class="tip-block">学号全校唯一</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">姓名<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="studentName" type="text" class="form-control" value="${(dto.studentName)!}"
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
							<label class="control-label col-md-3">性别</label>
							<div class="col-md-9">
								<div class="md-radio-inline">
									<div class="md-radio">
										<input type="radio" id="radioboy" name="sex"
											class="md-radiobtn" <#if dto.sex=='男'>checked=""</#if> value="男"> <label for="radioboy"> <span
											class="inc"></span> <span class="check"></span> <span
											class="box"></span> 男生
										</label>
									</div>
									<div class="md-radio">
										<input type="radio" name="sex" id="radiogirl" <#if dto.sex=='女'>checked=""</#if>
											class="md-radiobtn" value="女"> <label
											for="radiogirl"> <span class="inc"></span> <span
											class="check"></span> <span class="box"></span> 女生
										</label>
									</div>
								</div>
							</div>
						</div>

					</div>
					<!--/span-->
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">生日</label>
							<div class="col-md-9">
								<input name="birthDay" type="text" class="form-control" value="${(dto.birthDay)!}"
									placeholder="yyyy-mm-dd">
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">民族</label>
							<div class="col-md-9">
								<@select_national selectedVal="${(dto.national)!}"/>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">籍贯</label>
							<div class="col-md-9">
								<input name="jiguan" type="text" class="form-control" value="${(dto.jiguan)!}"
									placeholder="">
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<h3 class="form-section">学籍信息</h3>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">入学年份<span
								class="required">*</span></label>
							<div class="col-md-9">
								<input name="enterYear" type="number" class="form-control" value="${(dto.enterYear)!}">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">班级</label>
							<div class="col-md-9">
								<@select_class_id selectedVal="${dto.classId}"/> <span class="help-block">没有班级，点此<a
									href="../class/toAdd.htm">创建班级</a></span>
							</div>
						</div>
					</div>
				</div>
				<h3 class="form-section">家庭信息</h3>
				<!--/row-->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">父亲</label>
							<div class="col-md-9">
								<input name="fatherName" type="text" class="form-control" value="${(dto.fatherName)!}">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">母亲</label>
							<div class="col-md-9">
								<input name="montherName" type="text" class="form-control" value="${(dto.montherName)!}">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">联系电话</label>
							<div class="col-md-9">
								<input name="phone" type="text" class="form-control" value="${(dto.phone)!}">
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">家庭地址</label>
							<div class="col-md-9">
								<input name="address" type="text" class="form-control" value="${(dto.address)!}">
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-3">备注</label>
							<div class="col-md-9">
								<textarea name="note" class="form-control" rows="3" >${(dto.note)!}</textarea>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="col-md-6"></div>
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
						username : {
							minlength : 6,
							required : true
						},
						password : {
							minlength : 6,
							required : true
						},
						studentNo : {
							minlength : 2,
							required : true
						},
						studentName : {
							minlength : 2,
							required : true
						},
						enterYear : {
							required : true,
							digits : true,
							range : [ 1900, 2099 ],
						},
						birthDay:{
							dateISO:true
						}
					},
					messages : {
					username : {
							minlength : "用户名不能少于6位",
							required : "用户名不能为空"
						},
						password : {
							minlength : "密码不能少于6位",
							required : "密码不能为空"
						},
						studentNo : {
							minlength : "学号至少包含两个字符",
							required : "请输入学号"
						},
						enterYear : {
							required : "请输入入校年份",
							digits : "请输入正确的年份",
							range : "年份必须在1900-2099之间"
						},
						studentName : {
							minlength : "学生姓名至少包含两个字符",
							required : "请输入学生姓名"
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
		//$('select[name="national"]').find("option[value='${dto.national}']").attr("selected",true);
		submitLock = false; //全局变量
		FormValidation.init();
		//TableDatatablesEditable.init();
	});
</script>