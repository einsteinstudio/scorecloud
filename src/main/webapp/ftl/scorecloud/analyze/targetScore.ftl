<#assign title="${myTitle}">
<#include "../templ/global.ftl"> <#include
"../templ/fields/select_exam_id.ftl"> <@framework m2="targetScore">

<h3 class="page-title">目标分管理</h3>
<p>如果一个年级没有参加考试，则不会计入目标分管理</p>
<div class="row" style="padding-bottom:20px">
	<div class="col-md-6">
		<div class="form-group">
			<label class="control-label col-md-3">选择考试</label>
			<div class="col-md-6"><@select_exam_id selectedVal="${examId}"/></div>
		</div>


	</div>
	<div class="col-md-6">
		<div class="form-group">
			<label class="control-label col-md-3">选择类型</label>
			<div class="col-md-6">
				<select class="form-control  " name="subjectId">
					<option value="总分" <#if  subjectId=='总分' >selected="true"</#if> >总分</option> 
					<#list subjects as sub>
					<option value="${sub.id}" <#if  subjectId==sub.id >selected="true"</#if>>${sub.subjectName}</option> </#list>
				</select>
			</div>
		</div>
	</div>
	

	<!--/span-->
</div>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-dark">
					<i class="icon-settings font-dark"></i> <span
						class="caption-subject bold uppercase">目标分管理</span>
				</div>
			</div>

			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_1">
					<thead>
						<tr>
							<th>序号</th>
							<th>班级</th>
							<th>原有人数</th>
							<th>参考人数</th>
							<th>班级总分</th>
							<th>平均分</th>
							<th>合格人数</th>
							<th>合格率</th>
							<th>优良人数</th>
							<th>优良率</th>
							<th>平均值</th>
							<th>${colTeacher}</th>
						</tr>

					</thead>
					<tbody>
						<#list targetScoreVo.items as item>
						<tr class="odd gradeX">
							<td>${(item_index+1)!}</td>
							<td>${(item.title)!}</td>
							<td>${(item.studentCnt)!}</td>
							<td>${(item.joinExamCnt)!}</td>
							<td>${(item.totalScore)!}</td>
							<td>${(item.avgScore)!}</td>
							<td>${(item.hegeCnt)!}</td>
							<td>${(item.hegeRate)!}</td>
							<td>${(item.youliangCnt)!}</td>
							<td>${(item.youliangRate)!}</td>
							<td>${(item.avgValue)!}</td>
							<td><#if (item.classDto)??
								>${item.classDto.majorTracherName}</#if></td>
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
		var oSetting = $.extend(true, {}, window.DT_defaultSetting,
				window.DT_export);
		oSetting = $.extend(true, oSetting, {
			"pageLength" : -1,
		});
		table.dataTable(oSetting);
	}

	$(document).ready(function() {
		initTable1();
		$("select[name='examId']").bind("change",function(){
			refreshPage();
		});
		$("select[name='subjectId']").bind("change",function(){
			refreshPage();
		});
		
		function refreshPage(){
			window.location.href="targetScore.htm?examId="+$("select[name='examId']").val() +"&subjectId="+$("select[name='subjectId']").val();
		}
	});
</script>
