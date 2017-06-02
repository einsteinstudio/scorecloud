<#include "../templ/fields/select_class_id.ftl">
<#include "../templ/fields/select_exam_id.ftl">
<#include "../templ/global.ftl"> <@framework m2="recordScore">

<h3 class="page-title">成绩导入</h3>
<div class="portlet light bordered" id="form_wizard_1">
	<div class="portlet-title">
		<div class="caption">
			<i class=" icon-layers font-red"></i> <span
				class="caption-subject font-red bold uppercase"> 成绩导入 - <span
				class="step-title"> 选择考试 </span>
			</span>
		</div>
	</div>
	<div class="portlet-body form">
		<form action="toRecordStep2.htm" class="form-horizontal" id="submit_form"
			method="POST" novalidate="novalidate">
			<div class="form-wizard">
				<div class="portlet-body">
					<div class="mt-element-step">
						<div class="row step-background-thin">

							<div class="col-md-4 bg-grey-steel mt-step-col active">
								<div class="mt-step-number">1</div>
								<div class="mt-step-title uppercase font-grey-cascade">选择考试</div>
								<div class="mt-step-content font-grey-cascade">选择考试和班级</div>
							</div>
							<div class="col-md-4 bg-grey-steel mt-step-col">
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
						<div class="form-group">
							<label class="control-label col-md-3">选择考试</label>
							<div class="col-md-4">
								<@select_exam_id />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">选择班级</label>
							<div class="col-md-4">
								<@select_class_id/>
							</div>
						</div>
					<!--/span-->
				</div>
				</div>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-offset-3 col-md-9">
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
