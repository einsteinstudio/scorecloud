<#include "../templ/global.ftl"> <@framework m2="searchScore">

<h3 class="page-title">成绩修改</h3>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-dark">
					<i class="icon-settings font-dark"></i> <span
						class="caption-subject bold uppercase">待修改成绩</span>
				</div>
			</div>
			<p>如果需要删除一个成绩，直接把该成绩项清空即可。</p>
			<div class="portlet-body">
				<input type="hidden" name="col" value="${(scoreResultDto.subjects?size)!}">
				<input type="hidden" name="examId" value="${(scoreResultDto.examId)!}">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_editable_1">
					<thead>
						<tr>
							<th>学号</th>
							<th>姓名</th>
							<#list scoreResultDto.subjects as sub>
								<th>${sub.subjectName}</th>
							</#list>
							<th>编辑</th>
						</tr>
					</thead>
					<tbody>
						<#list scoreResultDto.studentScoreDtos as studentScoreDto>
						<tr class="odd gradeX">
							<td class="rowId" rowId="${studentScoreDto.studentId}" >${(studentScoreDto.studentNo)!}</td>
							<td>${(studentScoreDto.studentName)!}</td>
							<#list studentScoreDto.subjectScores as score>
								<td>${(score)!}</td>
							</#list>
							<td><a class="edit" href="javascript:;"> 编辑 </a></td>
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
</@framework>
<script src="/myapp/js/scorecloud/score/searchResultForModify.js" type="text/javascript"></script>
<script>
$(document).ready(function() {
	TableDatatablesEditable.init();
});
</script>
