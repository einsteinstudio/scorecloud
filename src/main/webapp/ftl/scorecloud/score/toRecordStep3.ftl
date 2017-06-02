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
		<form action="recordScore.htm" class="form-horizontal" id="submit_form"
			method="POST" novalidate="novalidate">
			<div class="form-wizard">
				<div class="portlet-body">
					<input type="hidden" name="examId" value="${examId}">
					<input type="hidden" name="classId" value="${classId}">
					<#list subjectIds as sub>
						<input type="hidden" name="subjectIds[]" value="${sub}">
					</#list>
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
							<div class="col-md-4 bg-grey-steel mt-step-col active">
								<div class="mt-step-number">3</div>
								<div class="mt-step-title uppercase font-grey-cascade">成绩登记</div>
								<div class="mt-step-content font-grey-cascade">登记学生成绩</div>
							</div>
						</div>
					</div>
					
					<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_1">
					<thead>
						<tr>
							<th>学号</th>
							<th>姓名</th>
							<#list scoreResultDto.subjects as sub>
								<th>${sub.subjectName}</th>
							</#list>
						</tr>
					</thead>
					<tbody>
						<#list scoreResultDto.studentScoreDtos as studentScoreDto>
						<tr class="odd gradeX">
							<input type="hidden" name="studentIds[]" value="${studentScoreDto.studentId}" >
							<td>${(studentScoreDto.studentNo)!}</td>
							<td>${(studentScoreDto.studentName)!}</td>
							<#list studentScoreDto.subjectScores as score>
								<th> <input type="number" class="form-control" name="inputScores_${studentScoreDto_index}[]" value="${(score)!}"/></th>
							</#list>
						</tr>
						</#list>
					</tbody>
				</table>
				</div>
				<!-- 此处添加表格 -->
				
				<!-- END TABLE -->
				<div class="form-actions">
					<div class="row">
						<div class="col-md-offset-3 col-md-9">
							<a onclick="javascript:history.go(-1)" href="toRecordStep2.htm"
								class="btn btn-outline green button-next"> 返回 <i
								class="fa fa-angle-right"></i>
							</a> 
							<button type="submit" class="btn btn-outline green button-next">
								完成 <i class="fa fa-angle-right"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

</@framework>
