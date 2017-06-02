<#include "../templ/fields/select_class_id.ftl"> <#include
"../templ/fields/select_exam_id.ftl"> <#include "../templ/global.ftl">
<@framework m2="recordScore">

<h3 class="page-title">成绩导入</h3>
<div class="portlet light bordered" id="form_wizard_1">
	<div class="portlet-title">
		<div class="caption">
			<i class=" icon-layers font-red"></i> <span
				class="caption-subject font-red bold uppercase"> 成绩导入 - <span
				class="step-title"> 选择科目 </span>
			</span>
		</div>
	</div>
	<div class="portlet-body form">
		<form action="toRecordStep3.htm" class="form-horizontal" id="submit_form"
			method="POST" novalidate="novalidate">
			<input type="hidden" name="examId" value="${examId}">
			<input type="hidden" name="classId" value="${classId}">
			<div class="form-wizard">
				<div class="portlet-body">
					<div class="mt-element-step">
						<div class="row step-background-thin">

							<div class="col-md-4 bg-grey-steel mt-step-col active">
								<div class="mt-step-number">1</div>
								<div class="mt-step-title uppercase font-grey-cascade">选择考试</div>
								<div class="mt-step-content font-grey-cascade">选择考试和班级</div>
							</div>
							<div class="col-md-4 bg-grey-steel mt-step-col active">
								<div class="mt-step-number">2</div>
								<div class="mt-step-title uppercase font-grey-cascade">选择科目</div>
								<div class="mt-step-content font-grey-cascade">至少一个科目</div>
							</div>
							<div class="col-md-4 bg-grey-steel mt-step-col">
								<div class="mt-step-number">3</div>
								<div class="mt-step-title uppercase font-grey-cascade">成绩登记</div>
								<div class="mt-step-content font-grey-cascade">登记学生成绩</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-body">
					<div class="row">
						<div class="form-group form-md-checkboxes">
							<label class="col-md-3 control-label" for="form_control_1">科目</label>
							<div class="col-md-6">
								<div class="md-checkbox-inline">
									<#list subjects as sub>
										<div class="md-checkbox">
											<input type="checkbox" id="checkbox${sub_index}_3" name="subjectIds[]"
												value="${sub.id}" class="md-check"> <label for="checkbox${sub_index}_3">
												<span></span> <span class="check"></span> <span class="box"></span>
												${sub.subjectName} 
											</label>
										</div>
									</#list>
								</div>
							</div>
						</div>
						<!--/span-->
					</div>
				</div>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-offset-3 col-md-9">
							<a onclick="javascript:history.go(-1)" href="toRecordStep2.htm"
								class="btn btn-outline green button-next"> 返回 <i
								class="fa fa-angle-right"></i>
							</a> 
							<button type="submit" class="btn btn-outline green button-next">
								下一步 <i class="fa fa-angle-right"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

</@framework>
