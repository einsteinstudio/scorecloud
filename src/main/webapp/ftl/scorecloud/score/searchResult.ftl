<#include "../templ/global.ftl"> <@framework m2="searchScore">

<h3 class="page-title">成绩查询</h3>
<p>按学生查询时，无法显示排名信息</p>
<p>如果想要导出pdf文件，请选择打印，在目标打印机中选择“另存为PDF”。(前请先确保安装了Adboe相关软件)</p>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-dark">
					<i class="icon-settings font-dark"></i> <span
						class="caption-subject bold uppercase">成绩查询结果 (<#if scoreResultDto.gradeDto??>${scoreResultDto.gradeDto.gradeName!}</#if> &nbsp;<#if scoreResultDto.classDto??>${scoreResultDto.classDto.className!}</#if>)</span>
				</div>
				<div class="tools"></div>
			</div>
			<input type="hidden" id="exportTitle" value="${scoreResultDto.title}">
			<div class="portlet-body">
				<table 
					class="table table-striped table-bordered table-hover order-column" 
					id="sample_1">
					<thead>
						<tr>
							<th  >学号</th>
							<th >姓名</th> <#list scoreResultDto.subjects as sub>
							<th >${sub.subjectName}</th> </#list>
							<th  >总分</th>
							<th>平均分</th>
							<th  >班级排名</th>
							<th  >年级排名</th>
							<#list scoreResultDto.subjects as sub>
							 <th  >${sub.subjectName}班级名次</th> 
							 <th  >${sub.subjectName}年级名次</th> 
							</#list>
							
						</tr>
						
					</thead>
					<tbody>
						<#list scoreResultDto.studentScoreDtos as studentScoreDto>
						<tr class="odd gradeX">
							<td>${(studentScoreDto.studentNo)!}</td>
							<td>${(studentScoreDto.studentName)!}</td> <#list
							studentScoreDto.subjectScores as score>
							<td>${(score)!}</td> </#list>
							<td>${(studentScoreDto.totalScore)!}</td>
							<th>${(studentScoreDto.avgScore)!}</th>
							<td>${(studentScoreDto.classRank)!'无'}</td>
							<td>${(studentScoreDto.gradeRank)!'无'}</td>
							<#list scoreResultDto.subjects as sub>
							<th>${(studentScoreDto.subjectClassRanks[sub_index])!'无'}</th> 
							<th>${(studentScoreDto.subjectGradeRanks[sub_index])!'无'}</th>
							</#list>
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
<script src="/myapp/plugins/export/pdfmake.min.js"
	type="text/javascript"></script>
<script src="/myapp/plugins/export/jszip.min.js" type="text/javascript"></script>
<script src="/myapp/plugins/export/buttons.html5.js"
	type="text/javascript"></script>
<script src="/myapp/plugins/export/buttons.colVis.min.js"
	type="text/javascript"></script>

<script>
	var initTable1 = function() {

		var table = $('#sample_1');
		// begin first table
		var oSetting = $.extend(true, {}, window.DT_defaultSetting,window.DT_export);
		oSetting = $
				.extend(
						true,
						oSetting,
						{
							"order" : [ [ 0, "asc" ] ],
							"pageLength" : -1,
							
						});
		table.dataTable(oSetting);
	}

	$(document).ready(function() {
		initTable1();

	});
</script>
